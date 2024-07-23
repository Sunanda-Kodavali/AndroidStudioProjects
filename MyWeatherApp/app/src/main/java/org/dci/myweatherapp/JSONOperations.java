package org.dci.myweatherapp;

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JSONOperations {

    private static JSONOperations INSTANCE;

    public static synchronized JSONOperations getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new JSONOperations();
        }
        return INSTANCE;
    }

    private JSONOperations() {

    }

    public List<String> getCityNamesFromJson(String jsonString) {
        List<String> cityList = new ArrayList<>();
        try {

            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = jsonArray.getJSONObject(i);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                String cityName = jsonObject.getString("name");
                cityList.add(cityName);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cityList;
    }

    public String loadJSONFromRaw(Context context, int resourceId) {
        String json = null;
        try {
            InputStream inputStream = context.getResources().openRawResource(resourceId);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            json = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;

    }
}
