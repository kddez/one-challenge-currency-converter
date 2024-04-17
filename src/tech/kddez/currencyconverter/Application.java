package tech.kddez.currencyconverter;

import tech.kddez.currencyconverter.configs.ExchangeRateAPI;
import tech.kddez.currencyconverter.enums.ConversionOptions;
import tech.kddez.currencyconverter.entities.CurrencyConvert;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Locale;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        ExchangeRateAPI exchangeRateService = new ExchangeRateAPI();

        try {

            ConversionOptions choice = null;

            while (choice != ConversionOptions.EXIT) {
                System.out.println(options());

                int option = sc.nextInt();
                sc.nextLine();

                try {
                    choice = ConversionOptions.values()[option];
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Opção inválida!");
                    continue;
                }

                if (choice == ConversionOptions.EXIT) {
                    break;
                }

                String baseCode, currencyCodeToConvert;
                double amountInBaseCurrency;

                switch (choice) {
                    case USD_TO_BRL:
                        baseCode = "USD";
                        currencyCodeToConvert = "BRL";
                        break;
                    case BRL_TO_USD:
                        baseCode = "BRL";
                        currencyCodeToConvert = "USD";
                        break;
                    case ARS_TO_USD:
                        baseCode = "ARS";
                        currencyCodeToConvert = "USD";
                        break;
                    case USD_TO_ARS:
                        baseCode = "USD";
                        currencyCodeToConvert = "ARS";
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        continue;
                }

                System.out.println("Digite a quantidade na moeda base:");
                amountInBaseCurrency = sc.nextDouble();

                HttpResponse<String> response = exchangeRateService.connection(baseCode);
                double conversionRate = exchangeRateService.extractConversionRate(response, currencyCodeToConvert);
                double amountAfterConvert = exchangeRateService.currencyConvert(amountInBaseCurrency, conversionRate);

                CurrencyConvert currencyConvert = new CurrencyConvert(baseCode, currencyCodeToConvert, amountInBaseCurrency);
                currencyConvert.setAmountAfterConvert(amountAfterConvert);
                System.out.println(currencyConvert);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }

    public static String options() {
        StringBuilder sb = new StringBuilder();
        sb.append("Escolha uma opção de conversão:\n");
        for (int i = 0; i < ConversionOptions.values().length; i++) {
            sb.append(i).append(". ").append(ConversionOptions.values()[i].getDescription()).append("\n");
        }
        return sb.toString();
    }
}

