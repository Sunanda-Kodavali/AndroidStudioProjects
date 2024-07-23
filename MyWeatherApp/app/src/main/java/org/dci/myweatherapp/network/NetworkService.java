package org.dci.myweatherapp.network;

import java.util.Map;

public interface NetworkService {
    NetworkResponse getResponse(String url);
    NetworkResponse getResponse(String url, Map<String, String> queryParameters);
}
