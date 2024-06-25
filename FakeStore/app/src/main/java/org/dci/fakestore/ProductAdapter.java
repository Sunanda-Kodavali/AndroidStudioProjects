package org.dci.fakestore;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    public interface OnItemClickedListener {
        void onItemClick(View view, int position);
    }

    private final List<Product> productList;
    private OnItemClickedListener listener;

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    public void setOnItemClickedListener(OnItemClickedListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_product,
                parent,
                false
        );

        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);

//        holder.getProductImageView().setImageResource(android.R.drawable.ic_menu_search);
        holder.getProductTitle().setText(product.getTitle());
        holder.getProductCategory().setText(product.getCategory());
        holder.getProductPrice().setText(product.getFormattedPrice());

        Picasso.get()
                .load(product.getImageURL())
                .placeholder(android.R.drawable.ic_menu_search)
                .into(holder.getProductImageView());

        holder.itemView.setOnClickListener(view -> {
            if (listener == null) return;
            listener.onItemClick(holder.itemView, position);
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
