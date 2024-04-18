package tech.kddez.currencyconverter;

import tech.kddez.currencyconverter.api.ExchangeRateAPI;
import tech.kddez.currencyconverter.model.ConversionHistory;
import tech.kddez.currencyconverter.service.ConversionService;

import java.util.Locale;
import java.util.Scanner;

public class Application {



    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ConversionService conversionService = new ConversionService(new ExchangeRateAPI());
        ConversionHistory history = new ConversionHistory();

        int option = 0;
        String baseCurrency, targetCurrency;
        double amount;

        while (option != 7){

            System.out.println(menu());
            option = sc.nextInt();

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
                    history.getConversionList().forEach(System.out::println);
                    return;
                default:
                    System.out.println("Opção Inválida!");
                    continue;
            }

            System.out.println("Enter with amount to conversion");
            amount = sc.nextDouble();

            var conversion = conversionService.createConversion(baseCurrency, targetCurrency, amount);
            System.out.println(conversion);
            history.addConversion(conversion);

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

