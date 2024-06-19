package org.dci.detailactivityforresult;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Exercise 1:
    // Create an App with 2 activities
    // The MainActivity has a TextView and a Button
    // When you click the Button you should goto the Second Activity

    // The Second Activity has an EditText and a Button
    // The button should finish the SecondActivity and send back whatever the user wrote in the EditText
    // Back in the MainActivity Display that info on the TextView
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



        Button doSomethingButton = findViewById(R.id.doSomethingButton);
        doSomethingButton.setOnClickListener(view -> {
            Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, DetailActivity.class);
            startActivityForResult(intent, 0);
        });

        Log.d("lifecycle", "CREATED MAIN ACT");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "I AM HERE!!!! YAY", Toast.LENGTH_SHORT).show();

        Log.d("lifecycle", "RESUMED MAIN ACT");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String result = data.getStringExtra("name");

        Toast.makeText(this, "I AM BACK WITH SOME INFORMATION!", Toast.LENGTH_SHORT).show();

        TextView textView = findViewById(R.id.textView);
        textView.setText(result);
    }



}