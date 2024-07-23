package org.dci.myweatherapp.network;

import java.util.Objects;

public class NetworkResponse {

    private final int status;
    private final String body;

    public NetworkResponse(int status, String body) {
        this.status = status;
        this.body = body;
    }

    public int getStatus() {
        return status;
    }

    public String getBody() {
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NetworkResponse that = (NetworkResponse) o;
        return status == that.status && Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, body);
    }

    @Override
    public String toString() {
        return "NetworkResponse{" +
                "status=" + status +
                ", body='" + body + '\'' +
                '}';
    }
}
