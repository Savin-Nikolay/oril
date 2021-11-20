package com.example.oril.mapper;

import com.example.oril.DTO.PriceDTO;
import com.example.oril.domain.CurrencyPrice;

public interface CurrencyMapper {

    CurrencyPrice PriceDtoToCurrencyPrice(PriceDTO priceDTO);
}
