package com.example.oril.service;

import com.example.oril.dto.CurrencyPriceDTO;
import com.example.oril.exception.BadRequestException;
import com.example.oril.domain.CurrencyPrice;
import com.example.oril.mapper.CurrencyMapper;
import com.example.oril.repository.CurrencyRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final CurrencyMapper currencyMapper;


    public void saveAll(Stream<CurrencyPriceDTO> priceDTOStream) {
        List<CurrencyPrice> currencyPrices = new ArrayList<>();
        priceDTOStream.forEach(priceDTO -> {
            currencyPrices.add(currencyMapper.priceDtoToCurrencyPrice(priceDTO));
        });
        currencyRepository.saveAll(currencyPrices);
    }

    public BigDecimal getMinPriceByName(String name) {
        CurrencyPrice firstByNameOrderByPriceAsc = currencyRepository.findFirstByNameOrderByPriceAsc(name);
        if (firstByNameOrderByPriceAsc == null) {
            throw new BadRequestException("Currency name is invalid or DB is empty");
        }
        return firstByNameOrderByPriceAsc.getPrice();
    }

    public BigDecimal getMaxPriceByName(String name) {
        CurrencyPrice firstByNameOrderByPriceDesc = currencyRepository.findFirstByNameOrderByPriceDesc(name);
        if (firstByNameOrderByPriceDesc == null) {
            throw new BadRequestException("Currency name is invalid or DB is empty");
        }
        return firstByNameOrderByPriceDesc.getPrice();
    }

    public Page<CurrencyPrice> findAllByNameOnPageable(String name, Pageable pageable) {
        Page<CurrencyPrice> page = currencyRepository.findAllByName(name, pageable);
        if (page == null) {
            throw new BadRequestException("Currency name is invalid or DB is empty");
        }
        return page;
    }
}
