package org.dci.pokemonlist;

import android.util.Log;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PokemonService {

    public static List<PokemonInfo> getPokemon() {

        ObjectMapper objectMapper = new ObjectMapper();

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://pokeapi.co/api/v2/pokemon")
                .build();

        try {
            Log.d("test", "Connecting to the Internet!!!!");

            Response response = client.newCall(request).execute();
            JsonNode root = objectMapper.readTree(response.body().string());
            JsonNode results = root.get("results");

            List<PokemonInfo> pokemonInfoList = new ArrayList<>();

            for (JsonNode result : results) {
                PokemonInfo pokemonInfo = new PokemonInfo(
                        result.get("name").asText(),
                        result.get("url").asText()
                );
                pokemonInfoList.add(pokemonInfo);
            }

            return pokemonInfoList;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}