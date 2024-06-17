package org.dci.listofitem;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {

    private List<String> list;

    public PokemonAdapter(List<String> list) {
        this.list = list;
    }

    public static class PokemonViewHolder extends RecyclerView.ViewHolder {

        private final TextView pokemonName;
        private final TextView sampleText;

        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);

            pokemonName = itemView.findViewById(R.id.pokemonName);
            sampleText = itemView.findViewById(R.id.sampleText);
        }


        public TextView getPokemonName() {
            return pokemonName;
        }

        public TextView getSampleText() {
            return sampleText;
        }
    }



    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.d("test", "Holder being created");

        // This view has inflated (instantiated) all the elements in the XML file
        // and returned them as the root element of that XML which in this case
        // is a Linear Layout. (with only a Textview inside)

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pokemon, parent, false);


        PokemonViewHolder viewHolder = new PokemonViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {

        Log.d("test", "Holder being binded at position " + position);

        holder.getPokemonName().setText(list.get(position));
        holder.getSampleText().setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}