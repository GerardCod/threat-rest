package dev.gerardo.threatrest.apirest.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "countryData", url = "http://api.countrylayer.com/v2/name")
public interface CountryService {

    @GetMapping("/{name}")
    List<Map<String, Object>> getCountryDataByName(@PathVariable String name, @RequestParam("access_key") String accessKey, @RequestParam Boolean fullText);

}
