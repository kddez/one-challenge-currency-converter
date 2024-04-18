package tech.kddez.currencyconverter.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Conversion {

    private Long id;
    private String baseCurrency;
    private String targetCurrency;
    private LocalDateTime convertedAt;
    private Double amount;
    private Double convertedAmount;

    public Conversion(){

    }

    public Conversion(Long id, String baseCurrency, String targetCurrency, Double amount, Double convertedAmount, LocalDateTime convertedAt) {
        this.id = id;
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.convertedAt = convertedAt;
        this.amount = amount;
        this.convertedAmount = convertedAmount;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getConvertedAt() {
        return convertedAt;
    }

    public void setConvertedAt(LocalDateTime convertedAt) {
        this.convertedAt = convertedAt;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public double getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(Double convertedAmount) {
        this.convertedAmount = convertedAmount;
    }

    @Override
    public String toString() {
        double conversionRate = convertedAmount/amount;
        var formattedDate = convertedAt.format(DateTimeFormatter.ofPattern("dd/MM/yy | HH:mm:ss"));
        return """
           ID: %d
           Momento da conversão: %s
           Conversão de %.2f %s para %s:
           Taxa de conversão: 1 %s = %.2f %s
           Valor convertido: %.2f %s = %.2f %s
           """.formatted(id, formattedDate, amount, baseCurrency,
                targetCurrency, baseCurrency, conversionRate, targetCurrency,
                amount, baseCurrency, convertedAmount, targetCurrency);
    }

}

