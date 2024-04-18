package tech.kddez.currencyconverter;

import tech.kddez.currencyconverter.api.ExchangeRateAPI;
import tech.kddez.currencyconverter.model.Conversion;
import tech.kddez.currencyconverter.model.ConversionHistory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        ExchangeRateAPI rateAPI = new ExchangeRateAPI();
        ConversionHistory conversionHistory = new ConversionHistory();
        long id = 0;
        int option = 0;

        String baseCurrency, targetCurrency;
        double amount;

        while (option != 7) {

            System.out.println(menu());
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    baseCurrency = "USD";
                    targetCurrency = "ARS";
                    break;
                case 2:
                    baseCurrency = "ARS";
                    targetCurrency = "USD";
                    break;
                case 3:
                    baseCurrency = "USD";
                    targetCurrency = "BRL";
                    break;
                case 4:
                    baseCurrency = "BRL";
                    targetCurrency = "USD";
                    break;
                case 5:
                    baseCurrency = "USD";
                    targetCurrency = "COP";
                    break;
                case 6:
                    baseCurrency = "COP";
                    targetCurrency = "USD";
                    break;
                case 7:
                    System.out.println("Conversion History:");
                    conversionHistory.getConversionList().forEach(System.out::println);
                    return;
                default:
                    System.out.println("Opção inválida!");
                    continue;
            }
                id++;

                System.out.println("Enter with amount to conversion");
                amount = sc.nextDouble();

                double convertedAmount = rateAPI.currencyConvert(baseCurrency, targetCurrency, amount);

                Conversion conversion = new Conversion(id, baseCurrency, targetCurrency, amount, convertedAmount, LocalDateTime.now());
                System.out.println(conversion);
                conversionHistory.addConversion(conversion);

        }


    }

    public static String menu(){
        return """
                ***********************************
                WELCOME TO THE CURRENCY CONVERTER
                
                1. DÓLAR ==> PESO ARGENTINO
                2. PESO ARGENTINO ==> DÓLAR
                3. DÓLAR ==> REAL BRASILEIRO
                4. REAL BRASILEIRO ==> DÓLAR
                5. DÓLAR ==> PESO COLOMBIANO
                6. PESO COLOMBIANO ==> DÓLAR
                7. VIEW CONVERSION HISTORY AND EXIT
                
                ==> CHOOSE A VALID OPTION
                ***********************************
                """;
    }
}

