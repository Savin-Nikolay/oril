package com.example.oril.controllers;

import com.example.oril.service.CSVExportServiceInterface;
import com.example.oril.service.CurrencyServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/cryptocurrencies")
@AllArgsConstructor
public class CurrenciesController {

    private final CurrencyServiceInterface currencyService;
    private final CSVExportServiceInterface csvExportService;

    @GetMapping("/minprice")
    ResponseEntity<String> getMinPriceByName(@RequestParam String name) {
        return new ResponseEntity<>(currencyService.getMinPriceByName(name), HttpStatus.OK);

    }

    @GetMapping("/maxprice")
    ResponseEntity<String> getMaxPriceByName(@RequestParam String name) {

        return new ResponseEntity<>(currencyService.getMaxPriceByName(name), HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<Map<String, Object>> getCurrencyByNamePagination(@RequestParam String name,
                                                                    @RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "price");
        Map<String, Object> response = currencyService.findAllByNameOnPageable(name, pageable);


        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/csv")

    public void getCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.addHeader("Content-Disposition", "attachment; filename=\"currencies.csv\"");
        csvExportService.writeMaxAndMinPriceToCSV(response.getWriter());
    }

}
