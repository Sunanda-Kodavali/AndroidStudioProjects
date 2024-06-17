package org.dci.starwars;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SWAdapter extends RecyclerView.Adapter<SWViewHolder> {
    private final List<StarWars> swList;

    public SWAdapter(List<StarWars> swList) {
        this.swList = swList;
    }

    @NonNull
    @Override
    public SWViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sw_film, parent, false);

        return new SWViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SWViewHolder holder, int position) {
        TextView year = holder.getYear();
        TextView title = holder.getTitle();
        TextView director = holder.getDirector();

        StarWars starWars = swList.get(position);

        year.setText(starWars.getYear());
        title.setText(starWars.getTitle());
        director.setText(starWars.getDirector());
    }

    @Override
    public int getItemCount() {
        return swList.size();
    }
}
