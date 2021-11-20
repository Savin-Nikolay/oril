package com.example.oril.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;


@Data
@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyPrice {

    private String name;
    private BigDecimal price;
}
