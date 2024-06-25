package org.dci.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

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

        EditText editText= findViewById(R.id.editText);
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);


        ImageView imageView1 = findViewById(R.id.imageView1);
        TextView textView1 = findViewById(R.id.textView1);
        textView1.setText("Loading...");

        new Thread(() -> {
            String result = NetworkService.get("https://pokeapi.co/api/v2/pokemon/pikachu");

            ObjectMapper mapper = new ObjectMapper();
            try {
                JsonNode root = mapper.readTree(result);
                String name = root.get("name").asText();
                String image = root.get("sprites").get("front_default").asText();

                System.out.println(name);
                System.out.println(image);

                Bitmap bitmap = NetworkService.getImage(image);

                runOnUiThread(() -> {
                    textView1.setText(name);
                    imageView1.setImageBitmap(bitmap);

                });
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

        }).start();

        Button goButton = findViewById(R.id.goButton);
        goButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("name", editText.getText().toString());
            intent.putExtra("age", 30);


            startActivity(intent);

//            this.finish();
        });
    }
}