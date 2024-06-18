package org.dci.starwars;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SWAdapter extends RecyclerView.Adapter<SWViewHolder> {
    public interface OnItemClickListener {
        void onClick(View view, int position);
    }
    private final List<StarWarsFilm> swList;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public SWAdapter(List<StarWarsFilm> swList) {
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

        StarWarsFilm starWarsFilm = swList.get(position);

        year.setText(starWarsFilm.getYear());
        title.setText(starWarsFilm.getTitle());
        director.setText(starWarsFilm.getDirector());
        holder.itemView.setOnClickListener(view -> {
            if (onItemClickListener == null) {
                return;
            }
            onItemClickListener.onClick(view, position);
        });
    }

    @Override
    public int getItemCount() {
        return swList.size();
    }
}
