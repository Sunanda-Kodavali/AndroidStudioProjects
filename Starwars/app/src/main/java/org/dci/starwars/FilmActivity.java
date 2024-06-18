package org.dci.starwars;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FilmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_film);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String openingCrawl = intent.getStringExtra("Opening Crawl");
        TextView titleTextView = findViewById(R.id.title);
        titleTextView.setText(title);
        TextView openingCrawlView = findViewById(R.id.openingCrawl);
        openingCrawlView.setText("Opening Crawl: \n"+openingCrawl);
        openingCrawlView.setMovementMethod(new ScrollingMovementMethod());

    }
}