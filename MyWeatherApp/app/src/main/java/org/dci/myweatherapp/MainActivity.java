package org.dci.myweatherapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.squareup.picasso.Picasso;
import org.dci.myweatherapp.network.NetworkService;
import org.dci.myweatherapp.network.OkHttpService;
import org.dci.myweatherapp.weather.Weather;
import org.dci.myweatherapp.weather.WeatherService;
import org.dci.myweatherapp.weather.WeatherServiceImpl;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Weather weather;
    private ImageView weatherIcon;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String jsonString = JSONOperations.getInstance().loadJSONFromRaw(this, R.raw.cities);
        List<String> cityNames = JSONOperations.getInstance().getCityNamesFromJson(jsonString);

        AutoCompleteTextView searchBar = findViewById(R.id.searchBar);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, cityNames);
        searchBar.setAdapter(adapter);

        weatherIcon = findViewById(R.id.weatherIcon);
        textView = findViewById(R.id.textView);

        NetworkService networkService = new OkHttpService();
        WeatherService weatherService = new WeatherServiceImpl(networkService);
        searchBar.setOnItemClickListener((parent, view, position, id) -> {
            String selectedCity = (String) parent.getItemAtPosition(position);
            System.out.println("Selected city: " + selectedCity);
            new Thread(() -> {
                weather = weatherService.getWeatherByLocation(selectedCity);

                runOnUiThread(() -> {
                    textView.setText("Temperature: " + weather.getTemp());

                    String iconURL = "https://openweathermap.org/img/wn/" + weather.getIconCode() + "@2x.png";
                    Picasso.get().load(iconURL).into(weatherIcon);

                    System.out.println(weather);
                });
            }).start();
        });
    }
}
