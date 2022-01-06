package dev.gerardo.threatrest.apirest.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IpInfo {
    private String countryName;
    private String countryISOCode;
    private String localCurrencyCode;
    private String localCurrencyName;
    private Double currentDollarCotization;
}
