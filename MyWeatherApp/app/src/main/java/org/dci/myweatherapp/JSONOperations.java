package org.dci.myweatherapp;

import android.content.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class JSONOperations {

    private static JSONOperations INSTANCE;

    public static synchronized JSONOperations getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new JSONOperations();
        }
        return INSTANCE;
    }

    private JSONOperations() {
    }

    public List<LocationData> getLocationsFromJson(String jsonString) {
        ObjectMapper mapper = new ObjectMapper();
        List<LocationData> locations;
        try {
            locations = mapper.readValue(jsonString, new TypeReference<List<LocationData>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return locations;
    }

    public String loadJSONFromRaw(Context context, int resourceId) {
        StringBuilder stringBuilder = new StringBuilder();

        try (InputStream inputStream = context.getResources().openRawResource(resourceId);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return stringBuilder.toString();
    }
}
