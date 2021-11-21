package com.example.oril.controller;

import com.example.oril.domain.CurrencyPrice;
import com.example.oril.service.CSVExportService;
import com.example.oril.service.CurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@RestController
@RequestMapping("/cryptocurrencies")
@AllArgsConstructor
public class CurrenciesController {

    private final CurrencyService currencyService;
    private final CSVExportService csvExportService;

    @GetMapping("/minprice")
    public BigDecimal getMinPriceByName(@RequestParam String name) {
        return currencyService.getMinPriceByName(name);
    }

    @GetMapping("/maxprice")
    public BigDecimal getMaxPriceByName(@RequestParam String name) {
        return currencyService.getMaxPriceByName(name);
    }
//TODO: возвращать DTO
    @GetMapping
    Page<CurrencyPrice> getCurrencyByNamePagination(@RequestParam String name,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "price");
        return currencyService.findAllByNameOnPageable(name, pageable);
    }

    @GetMapping("/csv")
    public void getCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.addHeader("Content-Disposition", "attachment; filename=\"currencies.csv\"");
        csvExportService.writeMaxAndMinPriceToCSV(response.getWriter());
    }
}
