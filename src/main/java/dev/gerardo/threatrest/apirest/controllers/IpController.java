package dev.gerardo.threatrest.apirest.controllers;

import dev.gerardo.threatrest.apirest.dao.IpBannedDAO;
import dev.gerardo.threatrest.apirest.exceptions.IpBannedInvalidException;
import dev.gerardo.threatrest.apirest.models.dto.IpInfo;
import dev.gerardo.threatrest.apirest.models.entities.IpBanned;
import dev.gerardo.threatrest.apirest.models.enums.CurrencyExchangeType;
import dev.gerardo.threatrest.apirest.services.CountryService;
import dev.gerardo.threatrest.apirest.services.ExchangeService;
import dev.gerardo.threatrest.apirest.services.IpService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.QueryParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/findMyIp")
public class IpController {

    @Value("${ipapi.accesskey}")
    private String apiAccessKey;

    @Value("${countryapi.accesskey}")
    private String countryAccessKey;

    @Value("${exapi.accesskey}")
    private String exAccessKey;

    @Autowired
    private IpService ipService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private ExchangeService exchangeService;

    @Autowired
    private IpBannedDAO dao;

    /**
     * Endpoint to check the information about in ip address.
     * @param ip IP Address
     * @return ResponseEntity with the relevant information.
     */
    @GetMapping
    @CircuitBreaker(name = "default", fallbackMethod = "fallback")
    @RateLimiter(name = "default")
    public ResponseEntity<IpInfo> requestMyInfo(@RequestParam(name = "ip", required = true) String ip) {
        dao.checkIfIpIsBanned(ip);

        IpInfo responseInfo = new IpInfo();
        Map<String, Object> ipData = ipService.getIpData(ip, apiAccessKey);
        responseInfo.setCountryName(ipData.get("country_name").toString());

        Map<String, Object> countryData = countryService.getCountryDataByName(responseInfo.getCountryName(), countryAccessKey, true).get(0);
        responseInfo.setCountryISOCode(countryData.get("alpha3Code").toString());

        List<Map<String, String>> currencies = (List<Map<String, String>>) countryData.get("currencies");
        responseInfo.setLocalCurrencyCode(currencies.get(0).get("code"));
        responseInfo.setLocalCurrencyName(currencies.get(0).get("name"));

        Map<String, Object> currencyData = exchangeService.getConversionData(exAccessKey, responseInfo.getLocalCurrencyCode(), CurrencyExchangeType.USD.toString(), 1);
        responseInfo.setCurrentDollarCotization(Double.parseDouble(currencyData.get("result").toString()));
        return ResponseEntity.ok(responseInfo);
    }

    /**
     * Adds an ip address to the blacklist.
     * @param ipBanned It is an IpBanned object that contains the address that will be banned.
     * @param validationResult It is the validation of the request body.
     * @return ResponseEntity with the information about the ip banned.
     */
    @PostMapping("/blacklist")
    public ResponseEntity<IpBanned> addIpToBlackList(@RequestBody @Valid IpBanned ipBanned, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            throw new IpBannedInvalidException(validationResult.getFieldErrors());
        }

        IpBanned result = dao.addIpBanned(ipBanned);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    public ResponseEntity<Map<String, String>> fallback(Exception ex) {
        Map<String, String> response = new HashMap<>();

        response.put("message", "fallback: " + ex.getMessage());

        return ResponseEntity.ok(response);
    }
}
