package org.dci.starwars;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SWViewHolder extends RecyclerView.ViewHolder {

    private final TextView year;
    private final TextView title;
    private final TextView director;
    public SWViewHolder(@NonNull View itemView) {
        super(itemView);
        this.year = itemView.findViewById(R.id.year);
        this.title = itemView.findViewById(R.id.title);
        this.director = itemView.findViewById(R.id.director);
    }

    public TextView getYear() {
        return year;
    }

    public TextView getTitle() {
        return title;
    }

    public TextView getDirector() {
        return director;
    }
}
