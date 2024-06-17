package org.dci.starwars;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class StarWarsService {

    public static List<StarWars> getStarWars(){
        ObjectMapper objectMapper = new ObjectMapper();

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://swapi.dev/api/films")
                .build();

        try {


            Response response = client.newCall(request).execute();
            JsonNode root = objectMapper.readTree(response.body().string());
            JsonNode results = root.get("results");

            List<StarWars> swList = new ArrayList<>();

            for (JsonNode result : results) {
                String year = "Year: "+result.get("release_date").asText().substring(0,4);
                int episodeId = result.get("episode_id").asInt();
                String title = "Episode "+IntegerToRoman.intToRoman(episodeId)+": "+result.get("title").asText(); // "Episode IV: A New Hope"
                String director = "Director: "+result.get("director").asText();
                StarWars starWars = new StarWars(
                        year ,
                        title,
                        director
                );
                swList.add(starWars);
            }

            return swList;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
