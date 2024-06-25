package org.dci.recyclerviewforresult;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Exercise 2:
    // Create a note taking App
    // same as before but in the Main Activity display a RecyclerView instead
    // that shows all the notes created in the SecondActivity
    // You can store the notes in a normal ArrayList for now, no need for persistence
    RecyclerView recyclerView;
    public List<String> list;
    MyAdapter myAdapter;

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

        recyclerView = findViewById(R.id.recyclerView);
        list = new ArrayList<>();


        Button doSomethingButton = findViewById(R.id.doSomethingButton);
        doSomethingButton.setOnClickListener(view -> {

            myAdapter = new MyAdapter(list);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(myAdapter);
            Toast.makeText(this, "On DoSomethingClick", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, DetailActivity.class);
            startActivityForResult(intent, 0);
        });

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
        list.add(result);

        Log.d("ListResult", list.toString());

        // To scroll to the last item in a RecyclerView in Android (to append new data)
        recyclerView.scrollToPosition(list.size() - 1);

        Toast.makeText(this, "I AM BACK WITH SOME INFORMATION!", Toast.LENGTH_SHORT).show();

    }


}
