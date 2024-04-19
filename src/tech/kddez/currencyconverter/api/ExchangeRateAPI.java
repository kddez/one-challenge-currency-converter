package tech.kddez.currencyconverter.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateAPI {

    private final String API_KEY = "6161d24fc6d14f1c95e1bf6a";
    private final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public String sendRequest(String baseCurrency) throws IOException, InterruptedException {
        String url = BASE_URL + API_KEY + "/latest/" + baseCurrency.toUpperCase();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public double extractConversionRate(String jsonResponse, String targetCurrency) {

        double conversionRate;

        JsonObject jsonObject = new Gson().fromJson(jsonResponse, JsonObject.class);
        JsonObject rates = jsonObject.getAsJsonObject("conversion_rates");
        if(rates.has(targetCurrency.toUpperCase())){
            conversionRate = rates.get(targetCurrency.toUpperCase()).getAsDouble();
        } else {
            return -1;
        }
        return conversionRate;
    }

    public Double currencyConvert(String baseCurrency, String targetCurrency, double amount){

        try {

            String jsonResponse = sendRequest(baseCurrency);
            double conversionRate = extractConversionRate(jsonResponse, targetCurrency);
            return amount * conversionRate;

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


}
