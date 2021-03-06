package com.example.oril.repository;

import com.example.oril.domain.CurrencyPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends MongoRepository<CurrencyPrice, String> {
    CurrencyPrice findFirstByNameOrderByPriceAsc(String name);

    CurrencyPrice findFirstByNameOrderByPriceDesc(String name);

    Page<CurrencyPrice> findAllByName(String name, Pageable pageable);
}
