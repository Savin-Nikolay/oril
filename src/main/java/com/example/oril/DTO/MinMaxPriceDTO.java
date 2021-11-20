package com.example.oril.DTO;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;


@Data
@Builder
public class MinMaxPriceDTO {

    private String name;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
}
