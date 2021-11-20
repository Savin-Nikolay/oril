package com.example.oril.repository.scheduler;


import com.example.oril.DTO.PriceDTO;
import com.example.oril.service.CurrencyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Slf4j
@Service
public class CurrencyRestClient {

    private final String BTC = "BTC/USD";
    private final String ETH = "ETH/USD";
    private final String XRP = "XRP/USD";
    private final String URL = "https://cex.io/api/last_price/";

    private final RestTemplate restTemplate;
    private final CurrencyService currencyService;

    public PriceDTO getLastPrice(String currency) {
        return restTemplate.getForObject(URL + currency, PriceDTO.class);
    }

    @Scheduled(fixedRate = 10000)
    public void getFromApAndSave() {
        currencyService.save(getLastPrice(BTC));
        currencyService.save(getLastPrice(ETH));
        currencyService.save(getLastPrice(XRP));
    }

}
