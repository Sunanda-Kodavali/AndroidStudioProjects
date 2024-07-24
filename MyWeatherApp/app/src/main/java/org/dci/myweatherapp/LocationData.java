package org.dci.myweatherapp;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationData {

    private final String city;
    private final String countryCode;
    private final double lat;
    private final double lon;

    @JsonCreator
    public LocationData(@JsonProperty("name") String city,
                        @JsonProperty("country") String countryCode,
                        @JsonProperty("lat") double lat,
                        @JsonProperty("lng") double lon) {
        this.city = city;
        this.countryCode = countryCode;
        this.lat = lat;
        this.lon = lon;
    }

    public String getCity() {
        return city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationData location = (LocationData) o;
        return Double.compare(lat, location.lat) == 0 && Double.compare(lon, location.lon) == 0 && Objects.equals(city, location.city) && Objects.equals(countryCode, location.countryCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, countryCode, lat, lon);
    }

    @Override
    public String toString() {
        return "Location{" +
                "city='" + city + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}

