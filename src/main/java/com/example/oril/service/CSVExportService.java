package com.example.oril.service;


import com.example.oril.DTO.MinMaxPriceDTO;
import com.example.oril.controllers.exceptions.CustomException;
import com.example.oril.repository.CurrencyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;

@Service
@AllArgsConstructor
@Slf4j
public class CSVExportService implements CSVExportServiceInterface {

    private final CurrencyRepository currencyRepository;

    private MinMaxPriceDTO creatFromRepository (String name){

        return MinMaxPriceDTO.builder()
                .name(name)
                .minPrice(currencyRepository.findFirstByNameOrderByPriceDesc(name).getPrice())
                .maxPrice(currencyRepository.findFirstByNameOrderByPriceAsc(name).getPrice())
                .build();
    }

    @Override
    public void writeMaxAndMinPriceToCSV(Writer writer) {
        MinMaxPriceDTO btc = creatFromRepository("BTC");
        MinMaxPriceDTO eth = creatFromRepository("ETH");
        MinMaxPriceDTO xrp = creatFromRepository("XRP");

        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)){
            csvPrinter.printRecord(btc.getName(),btc.getMinPrice(),btc.getMaxPrice());
            csvPrinter.printRecord(eth.getName(),eth.getMinPrice(),eth.getMaxPrice());
            csvPrinter.printRecord(xrp.getName(),xrp.getMinPrice(),xrp.getMaxPrice());

        } catch (IOException e) {
            log.error("CSVExportServiceImpl: Error while writing csv");
            throw new CustomException("SVExportServiceImpl: Error while writing csv");
        }
    }
}
