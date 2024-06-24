package org.dci.fakestoreapi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Intent intent = getIntent();

        String title = intent.getStringExtra("title");
        TextView titleView = findViewById(R.id.title);
        titleView.setText(title);

        String category = intent.getStringExtra("category");
        TextView categoryView = findViewById(R.id.category);
        categoryView.setText(category);

        String description = intent.getStringExtra("description");
        TextView descriptionView = findViewById(R.id.description);
        descriptionView.setText(description);

        Double price = intent.getDoubleExtra("price", 0.0);
        TextView priceView = findViewById(R.id.price);
        priceView.setText(price+"");

        Double rating = intent.getDoubleExtra("rating", 0.0);
        TextView ratingView = findViewById(R.id.rating);
        ratingView.setText(rating+"");

        String imageURL = intent.getStringExtra("image");
        Log.d("resssssssssssssssss",imageURL);
        ImageView imageView = findViewById(R.id.imageView);

        new Thread(() -> {
            InputStream inputStream = null;
            try {
                inputStream = new URL(imageURL).openStream();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Bitmap bitmap  = BitmapFactory.decodeStream(inputStream);
            runOnUiThread(() -> {
                imageView.setImageBitmap(bitmap);
            });

        }).start();


        Button backButton = findViewById(R.id.back);
        backButton.setOnClickListener(view -> {
            Intent intent1 = new Intent();

            setResult(0, intent1);
            finish();
        });
    }

}