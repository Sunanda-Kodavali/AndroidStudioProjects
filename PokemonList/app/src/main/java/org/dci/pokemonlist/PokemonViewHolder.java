package org.dci.pokemonlist;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PokemonViewHolder extends RecyclerView.ViewHolder {

    private final TextView pokemonName;
    private final TextView pokemonURL;

    public PokemonViewHolder(@NonNull View itemView) {
        super(itemView);
        pokemonName = itemView.findViewById(R.id.pokemonName);
        pokemonURL = itemView.findViewById(R.id.pokemonURL);
    }

    public TextView getPokemonName() {
        return pokemonName;
    }

    public TextView getPokemonURL() {
        return pokemonURL;
    }
}