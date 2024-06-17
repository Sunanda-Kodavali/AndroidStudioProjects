package org.dci.pokemonlist;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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

//        List<PokemonInfo> pokemon = new ArrayList<>();
//        pokemon.add(new PokemonInfo("Pikachu", "pikachu url"));
//        pokemon.add(new PokemonInfo("Squirtle", "squirtle url..."));

        RecyclerView pokemonList = findViewById(R.id.pokemonList);
        pokemonList.setLayoutManager(new LinearLayoutManager(this));

        new Thread(() -> {
            List<PokemonInfo> pokemonInfoList = PokemonService.getPokemon();
            runOnUiThread(() -> {
                pokemonList.setAdapter(new PokemonAdapter(pokemonInfoList));
            });
        }).start();
    }
}