package tech.kddez.currencyconverter.entities;

public class CurrencyConvert {

    private String baseCurrency;
    private String currencyCodeToConvert;
    private double amountInBaseCurrency;
    private double amountAfterConvert;

    public CurrencyConvert(){

    }

    public CurrencyConvert(String baseCurrency, String currencyToConvert, double amountInBaseCurrency) {
        this.baseCurrency = baseCurrency;
        this.currencyCodeToConvert = currencyToConvert;
        this.amountInBaseCurrency = amountInBaseCurrency;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getCurrencyCodeToConvert() {
        return currencyCodeToConvert;
    }

    public void setCurrencyCodeToConvert(String currencyCodeToConvert) {
        this.currencyCodeToConvert = currencyCodeToConvert;
    }

    public double getAmountInBaseCurrency() {
        return amountInBaseCurrency;
    }

    public void setAmountInBaseCurrency(double amountInBaseCurrency) {
        this.amountInBaseCurrency = amountInBaseCurrency;
    }

    public double getAmountAfterConvert() {
        return amountAfterConvert;
    }

    public void setAmountAfterConvert(double amountAfterConvert) {
        this.amountAfterConvert = amountAfterConvert;
    }

    //@Override
    //public String toString() {
     //   return "Conversão de " + amountInBaseCurrency + " " + baseCurrency + " para " + currencyCodeToConvert + ":\n" +
       //         "Taxa de conversão: 1 " + baseCurrency + " = " + amountAfterConvert/amountInBaseCurrency + " " + currencyCodeToConvert + "\n" +
         //       "Valor convertido: " + amountInBaseCurrency + " " + baseCurrency + " = " + (amountAfterConvert/amountInBaseCurrency) + " " + currencyCodeToConvert;
    //}


    @Override
    public String toString() {
        double conversionRate = amountAfterConvert/amountInBaseCurrency;
        return """
           Conversão de %.2f %s para %s:
           Taxa de conversão: 1 %s = %.2f %s
           Valor convertido: %.2f %s = %.2f %s
           """.formatted(amountInBaseCurrency, baseCurrency,
                currencyCodeToConvert, baseCurrency, conversionRate, currencyCodeToConvert,
                amountInBaseCurrency, baseCurrency, amountAfterConvert, currencyCodeToConvert);
    }
}
