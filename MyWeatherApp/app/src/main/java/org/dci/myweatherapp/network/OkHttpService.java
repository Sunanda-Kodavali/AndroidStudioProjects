package org.dci.myweatherapp.network;

import java.io.IOException;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpService implements NetworkService {

    public NetworkResponse getResponse(String url) {
        return getResponse(url, null);
    }

    @Override
    public NetworkResponse getResponse(String url, Map<String, String> queryParameters) {

        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder httpUrlBuilder = HttpUrl.parse(url).newBuilder();
        if (queryParameters != null) {
            for (Map.Entry<String, String> entry : queryParameters.entrySet()) {
                httpUrlBuilder.addQueryParameter(
                        entry.getKey(),
                        entry.getValue());
            }
        }

        HttpUrl httpUrl = httpUrlBuilder.build();
        Request request = new Request.Builder()
                .url(httpUrl)
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();

            return new NetworkResponse(response.code(), response.body().string());

        } catch (IOException e) {
            throw new NetworkException(e.getMessage());
        }
    }
}
