package dev.gerardo.threatrest.apirest.services;

import dev.gerardo.threatrest.apirest.models.enums.CurrencyExchangeType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "exchange", url = "http://data.fixer.io/api")
public interface ExchangeService {

    @GetMapping("/latest")
    Map<String, Object> getExchangeData(@RequestParam("access_key") String accessKey, @RequestParam("symbols") CurrencyExchangeType symbols);

    @GetMapping("/convert")
    Map<String, Object> getConversionData(
            @RequestParam("access_key") String accessKey,
            @RequestParam("from") String from,
            @RequestParam("to") String to,
            @RequestParam("amount") Integer amoun);
}
