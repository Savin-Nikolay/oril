package com.example.oril.service;


import com.example.oril.dto.CurrencyPriceDTO;
import com.example.oril.enums.CurrencyPair;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Stream;

@AllArgsConstructor
@Slf4j
@Service
public class CurrencyRestService {
    //TODO: dev configurationsProperty
    private final String URL = "https://cex.io/api/last_price/";

    private final RestTemplate restTemplate;
    private final CurrencyService currencyService;

    public CurrencyPriceDTO getLastPrice(CurrencyPair currency) {
        return restTemplate.getForObject(URL + currency, CurrencyPriceDTO.class);
    }

    @Scheduled(fixedRate = 10000)
    public void getFromApAndSave() {
        Stream<CurrencyPriceDTO> priceDTOStream = Stream.of(
                getLastPrice(CurrencyPair.BTC_USD),
                getLastPrice(CurrencyPair.ETH_USD),
                getLastPrice(CurrencyPair.XRP_USD));
        currencyService.saveAll(priceDTOStream);
    }
}
