package org.dci.myweatherapp.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.dci.myweatherapp.network.NetworkResponse;
import org.dci.myweatherapp.network.NetworkService;

import java.util.HashMap;
import java.util.Map;

public class WeatherServiceImpl implements WeatherService {

    private final static String APP_ID = "e69dc7ab5f38cc15ea3deb2166618ef7";

    private final NetworkService networkService;

    public WeatherServiceImpl(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Weather getWeather(String url, Map<String, String> queryParameters) {
        NetworkResponse response = networkService.getResponse(url, queryParameters);
        String body = response.getBody();

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode rootNode = mapper.readTree(body);

            return new Weather(
                    rootNode.get("coord").get("lat").asDouble(),
                    rootNode.get("coord").get("lon").asDouble(),
                    rootNode.get("weather").get(0).get("description").asText(),
                    rootNode.get("weather").get(0).get("icon").asText(),
                    rootNode.get("main").get("temp").asDouble(),
                    rootNode.get("main").get("feels_like").asDouble(),
                    rootNode.get("main").get("temp_min").asDouble(),
                    rootNode.get("main").get("temp_max").asDouble(),
                    rootNode.get("name").asText()
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public Weather getWeatherByCoordinates(double lat, double lon) {
        String url = "https://api.openweathermap.org/data/2.5/weather";

        Map<String, String> params = new HashMap<>();
        params.put("appid", APP_ID);
        params.put("lat", String.valueOf(lat));
        params.put("lon", String.valueOf(lon));
        params.put("units", "metric");

        return getWeather(url, params);
    }

    @Override
    public Weather getWeatherByLocation(String locationQuery) {
        String url = "https://api.openweathermap.org/data/2.5/weather";

        Map<String, String> params = new HashMap<>();
        params.put("appid", APP_ID);
        params.put("q", locationQuery);
        params.put("units", "metric");

        return getWeather(url, params);
    }
}