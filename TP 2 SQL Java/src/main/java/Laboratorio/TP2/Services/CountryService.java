package Laboratorio.TP2.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class CountryService {
    private final String baseUrl = "https://restcountries.com/v2/callingcode/";
    private final RestTemplate restTemplate;

    public CountryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getCountryInfoJson(int callingCode) {
        String url = baseUrl + callingCode;
        try {
            return restTemplate.getForObject(url, String.class);
        } catch (HttpClientErrorException.NotFound e) {
            return null; // O algún valor por defecto, si es necesario
        }
    }
}
