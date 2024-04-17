package tech.kddez.currencyconverter.enums;

public enum ConversionOptions {

    USD_TO_BRL("Dólar para Real"),
    BRL_TO_USD("Real para Dólar"),
    ARS_TO_USD("Peso Argentino para Dólar"),
    USD_TO_ARS("Dólar para Peso Argentino"),
    EXIT("Sair");

    private final String description;

    ConversionOptions(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
