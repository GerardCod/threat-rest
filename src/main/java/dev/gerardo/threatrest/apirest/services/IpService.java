package dev.gerardo.threatrest.apirest.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "ipdata", url = "http://api.ipapi.com")
public interface IpService {

    @GetMapping("/{ip}")
    Map<String, Object> getIpData(@PathVariable String ip, @RequestParam("access_key") String accessKey);

}
