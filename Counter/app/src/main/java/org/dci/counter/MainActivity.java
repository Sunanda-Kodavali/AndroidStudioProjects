package org.dci.counter;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private int counter1 = 0;
    private int counter2 = 0;
    private TextView textView1;
    private TextView textView2;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Exercise 1:
        // Only use the first TextView and the Buttons
        // Change the counter value displayed in the textView as the buttons are Clicked

        // Exercise 2:
        // Use the radio group to change the respective label
        // The buttons should only affect the counter that is selected the other remains still

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);

        radioGroup = findViewById(R.id.radioGroup);

        button1.setOnClickListener(v -> {
            setCounterValues(-10);
        });
        button2.setOnClickListener(v -> {
            setCounterValues(-1);
        });
        button3.setOnClickListener(v -> {
            setCounterValues(1);
        });
        button4.setOnClickListener(v -> {
            setCounterValues(10);
        });

    }

    private void setCounterValues(int val) {

        if (radioGroup.getCheckedRadioButtonId() == R.id.radio1) {

            counter1 = counter1 + val;
            String counter1_label = getString(R.string.counter1_label) + counter1;
            textView1.setText(counter1_label);
        } else {
            counter2 = counter2 + val;
            String counter2_label = getString(R.string.counter2_label) + counter2;
            textView2.setText(counter2_label);
        }
    }


}