package org.dci.fakestoreapi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class FakeStoreAdapter extends RecyclerView.Adapter<FakeStoreViewHolder> {
    public interface OnItemClickListener {
        void onClick(View view, int position);
    }
    private final List<Product> products;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public FakeStoreAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public FakeStoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);

        return new FakeStoreViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FakeStoreViewHolder holder, int position) {


        TextView titleView = holder.getTitleView();
        TextView categoryView = holder.getCategoryView();
        TextView priceView = holder.getPriceView();
        ImageView imageView = holder.getImageView();

        Product product = products.get(position);

        titleView.setText(product.getTitle());
        categoryView.setText(product.getCategory());
        priceView.setText(product.getPrice()+"");

        new Thread(() -> {
            InputStream inputStream = null;
            try {
                inputStream = new URL(product.getImage()).openStream();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Bitmap bitmap  = BitmapFactory.decodeStream(inputStream);
            new Handler(Looper.getMainLooper()).post(() -> {
                imageView.setImageBitmap(bitmap);
            });

        }).start();
        holder.itemView.setOnClickListener(view -> {
            if (onItemClickListener == null) {
                return;
            }
            onItemClickListener.onClick(view, position);
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
