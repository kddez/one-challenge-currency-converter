package tech.kddez.currencyconverter.configs;

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

    public HttpResponse<String> connection(String baseCode) throws IOException, InterruptedException {
        String url = BASE_URL + API_KEY + "/latest/" + baseCode;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public double extractConversionRate(HttpResponse<String> response, String currencyCodeToConvert) {
        double conversionRate = 0.0;
        if (response.statusCode() == 200) {
            String responseBody = response.body();
            JsonObject jsonObject = new Gson().fromJson(responseBody, JsonObject.class);
            JsonObject rates = jsonObject.getAsJsonObject("conversion_rates");
            conversionRate = rates.get(currencyCodeToConvert).getAsDouble();
        }
        return conversionRate;
    }

    public double currencyConvert(double amount, double conversionRate){
       return amount * conversionRate;
    }



}
