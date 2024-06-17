package org.dci.pokemonlist;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder> {

    private final List<PokemonInfo> pokemon;

    public PokemonAdapter(List<PokemonInfo> pokemon) {
        this.pokemon = pokemon;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("test", "Creating a vew VieHolder!");
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pokemon, parent, false);

        return new PokemonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        TextView pokemonName = holder.getPokemonName();
        TextView pokemonURL = holder.getPokemonURL();

        PokemonInfo pokemonInfo = pokemon.get(position);

        pokemonName.setText(pokemonInfo.getName());
        pokemonURL.setText(pokemonInfo.getUrl());

        Log.d("test", "Drawing the view for " + pokemonInfo.getName());
    }

    @Override
    public int getItemCount() {
        return pokemon.size();
    }
}