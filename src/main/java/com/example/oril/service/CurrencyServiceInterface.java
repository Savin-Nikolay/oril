package com.example.oril.service;

import com.example.oril.DTO.PriceDTO;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface CurrencyServiceInterface {

    void save(PriceDTO priceDTO);

    String getMinPriceByName(String name);

    String getMaxPriceByName(String name);

    Map<String, Object> findAllByNameOnPageable(String name, Pageable pageable);

}
