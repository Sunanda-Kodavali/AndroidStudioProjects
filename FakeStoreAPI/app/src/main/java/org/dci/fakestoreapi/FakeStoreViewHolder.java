package org.dci.fakestoreapi;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FakeStoreViewHolder extends RecyclerView.ViewHolder {

    private final TextView titleView;
    private final TextView categoryView;
    private final TextView priceView;
    private final ImageView imageView;
    public FakeStoreViewHolder(@NonNull View itemView) {
        super(itemView);
        titleView = itemView.findViewById(R.id.title);
        categoryView = itemView.findViewById(R.id.category);
        priceView = itemView.findViewById(R.id.price);
        imageView = itemView.findViewById(R.id.myImageView);
    }

    public TextView getTitleView() {
        return titleView;
    }

    public TextView getCategoryView() {
        return categoryView;
    }

    public TextView getPriceView() {
        return priceView;
    }

    public ImageView getImageView() {
        return imageView;
    }
}
