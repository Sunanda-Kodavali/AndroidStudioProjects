package org.dci.myweatherapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.squareup.picasso.Picasso;

import org.dci.myweatherapp.network.NetworkService;
import org.dci.myweatherapp.network.OkHttpService;
import org.dci.myweatherapp.weather.Weather;
import org.dci.myweatherapp.weather.WeatherService;
import org.dci.myweatherapp.weather.WeatherServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_LOCATION_PERMISSION = 1;

    private Weather weather;
    private ImageView weatherIcon;
    private TextView weatherDisplay;
    private FusedLocationProviderClient fusedLocationClient;
    private WeatherService weatherService;
    private ImageButton useCurrentLocationButton;
    private AutoCompleteTextView searchBar;
    private LocationCallback locationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        setupInsets();
        initializeViews();
        setupSearchBar();
        setupWeatherService();
        setupLocationClient();

        useCurrentLocationButton.setOnClickListener(v -> checkLocationPermissionAndStartUpdates());
    }

    private void setupInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initializeViews() {
        weatherIcon = findViewById(R.id.weatherIcon);
        weatherDisplay = findViewById(R.id.weatherDisplay);
        useCurrentLocationButton = findViewById(R.id.useCurrentLocationButton);
        searchBar = findViewById(R.id.searchBar);
    }

    private void setupSearchBar() {
        String jsonString = JSONOperations.getInstance().loadJSONFromRaw(this, R.raw.cities);
        List<LocationData> locations = JSONOperations.getInstance().getLocationsFromJson(jsonString);

        List<String> cityNames = new ArrayList<>();
        for (LocationData city : locations) {
            cityNames.add(city.getCity());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, cityNames);
        searchBar.setAdapter(adapter);

        searchBar.setOnItemClickListener((parent, view, position, id) -> {
            String selectedCity = (String) parent.getItemAtPosition(position);
            System.out.println("Selected city: " + selectedCity);
            fetchWeatherByLocation(selectedCity);
        });
    }

    private void setupWeatherService() {
        NetworkService networkService = new OkHttpService();
        weatherService = new WeatherServiceImpl(networkService);
    }

    private void setupLocationClient() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
    }

    private void checkLocationPermissionAndStartUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_LOCATION_PERMISSION);
            return;
        }
        startLocationUpdates();
    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return; // Permissions not granted, do nothing
        }

        LocationRequest locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10000)  // 10 seconds
                .setFastestInterval(5000); // 5 seconds

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    fusedLocationClient.removeLocationUpdates(locationCallback);
                    fetchWeatherForLocation(location);
                }
            }
        };

        try {
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
        } catch (SecurityException e) {
            e.printStackTrace(); // Handle the security exception if permissions are denied
        }
    }

    private void fetchWeatherByLocation(String location) {
        new Thread(() -> {
            weather = weatherService.getWeatherByLocation(location);
            runOnUiThread(this::updateUI);
        }).start();
    }

    private void fetchWeatherForLocation(Location location) {
        new Thread(() -> {
            weather = weatherService.getWeatherByCoordinates(location.getLatitude(), location.getLongitude());
            runOnUiThread(this::updateUI);
        }).start();
    }

    private void updateUI() {
        weatherDisplay.setText("Temperature: " + weather.getTemp() + "°C");

        String iconURL = "https://openweathermap.org/img/wn/" + weather.getIconCode() + "@2x.png";
        Picasso.get().load(iconURL).into(weatherIcon);

        if (weather.getLocationName() != null) {
            searchBar.setText(weather.getLocationName());
        }

        System.out.println(weather);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates(); // Start requesting location updates after permission is granted
            } else {
                System.out.println("Location permission denied");
            }
        }
    }
}
