package org.dci.fakestoreapi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.listOfItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String[] categories = {"---select---","electronics", "jewelery", "men's clothing", "women's clothing"};
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> productArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories);
        spinner.setAdapter(productArrayAdapter);

        new Thread(() -> {
//            List<Product> products = NetworkService.getProducts();
            List<Product> products = NetworkService.getProducts();

            runOnUiThread(() -> {
                updateView(products);
            });



        }).start();


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("category",categories[position]);

                new Thread(() -> {
                    List<Product> products;
                    if(position == 0){
                        products = NetworkService.getProducts();
                    }else{
                       products = NetworkService.getProductByCategory(categories[position]);
                    }

                runOnUiThread(() -> {
                    updateView(products);
                });
                }).start();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void updateView(List<Product> products) {
        FakeStoreAdapter fakeStoreAdapter = new FakeStoreAdapter(products);

        fakeStoreAdapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("title", products.get(position).getTitle());
            intent.putExtra("category", products.get(position).getCategory());
            intent.putExtra("description", products.get(position).getDescription());
            intent.putExtra("price", products.get(position).getPrice());
            intent.putExtra("rating", products.get(position).getRate());
            intent.putExtra("image", products.get(position).getImage());
            startActivity(intent);
        });

        recyclerView.setAdapter(fakeStoreAdapter);
    }
}