package com.example.oril.service;

import com.example.oril.DTO.PriceDTO;
import com.example.oril.controllers.exceptions.CustomException;
import com.example.oril.domain.CurrencyPrice;
import com.example.oril.mapper.CurrencyMapper;
import com.example.oril.repository.CurrencyRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class CurrencyService implements CurrencyServiceInterface {

    private final CurrencyRepository currencyRepository;
    private final CurrencyMapper currencyMapper;

    @Override
    public void save(PriceDTO priceDTO) {
        currencyRepository.save(currencyMapper.PriceDtoToCurrencyPrice(priceDTO));
    }

    @Override
    public String getMinPriceByName(String name) {
        CurrencyPrice firstByNameOrderByPriceAsc = currencyRepository.findFirstByNameOrderByPriceAsc(name);
        if (firstByNameOrderByPriceAsc == null) {
            throw new CustomException("Currency name is invalid or DB is empty");
        }
        return firstByNameOrderByPriceAsc.getPrice().toString();
    }

    @Override
    public String getMaxPriceByName(String name) {
        CurrencyPrice firstByNameOrderByPriceDesc = currencyRepository.findFirstByNameOrderByPriceDesc(name);

        if (firstByNameOrderByPriceDesc == null) {
            throw new CustomException("Currency name is invalid or DB is empty");
        }
        return firstByNameOrderByPriceDesc.getPrice().toString();
    }

    @Override
    public Map<String, Object> findAllByNameOnPageable(String name, Pageable pageable) {
        Map<String, Object> result = new HashMap<>();
        Page<CurrencyPrice> page = currencyRepository.findAllByName(name, pageable);
        if (page == null) {
            throw new CustomException("Currency name is invalid or DB is empty");
        }
        result.put("Currencies", page.getContent());
        result.put("CurrentPage", page.getNumber());
        result.put("totalItems:", page.getTotalElements());
        result.put("Pages:", page.getTotalPages());

        return result;
    }
}
