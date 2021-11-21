package com.example.oril.mapper;

import com.example.oril.dto.CurrencyPriceDTO;
import com.example.oril.domain.CurrencyPrice;
import org.springframework.stereotype.Component;

@Component
public class CurrencyMapper {
    public CurrencyPrice priceDtoToCurrencyPrice(CurrencyPriceDTO priceDTO) {
        return CurrencyPrice.builder().price(priceDTO.getPrice()).name(priceDTO.getFirstCurrencyCode()).build();
    }
}
