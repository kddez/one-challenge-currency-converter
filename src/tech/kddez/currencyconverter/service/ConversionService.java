package tech.kddez.currencyconverter.service;

import tech.kddez.currencyconverter.api.ExchangeRateAPI;
import tech.kddez.currencyconverter.model.Conversion;

import java.io.IOException;
import java.time.LocalDateTime;

public class ConversionService {

    private final ExchangeRateAPI exchangeRateAPI;

    public ConversionService(ExchangeRateAPI exchangeRateAPI) {
        this.exchangeRateAPI = exchangeRateAPI;
    }

    public Conversion createConversion(String baseCurrency, String targetCurrency, double amount){
        var convertedAmount = exchangeRateAPI
                .currencyConvert(baseCurrency, targetCurrency, amount);
        return new Conversion(baseCurrency, targetCurrency, amount, convertedAmount, LocalDateTime.now());
    }
}
