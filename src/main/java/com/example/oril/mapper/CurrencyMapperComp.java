package com.example.oril.mapper;

import com.example.oril.DTO.PriceDTO;
import com.example.oril.domain.CurrencyPrice;
import org.springframework.stereotype.Component;

@Component
public class CurrencyMapperComp implements CurrencyMapper {

    @Override
    public CurrencyPrice PriceDtoToCurrencyPrice(PriceDTO priceDTO) {
        return CurrencyPrice.builder().price(priceDTO.getLprice()).name(priceDTO.getCurr1()).build();
    }
}
