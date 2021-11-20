package com.example.oril.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceDTO {

    private BigDecimal lprice;

    private String curr1;

    private String curr2;
}
