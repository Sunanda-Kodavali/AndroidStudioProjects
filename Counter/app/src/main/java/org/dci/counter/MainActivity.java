package org.dci.counter;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private int counter1 = 0;
    private int counter2 = 0;

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


        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        TextView textView1 = findViewById(R.id.textView1);
        TextView textView2 = findViewById(R.id.textView2);
        RadioButton checkedRadioButton = findViewById(R.id.radio1);

        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(v -> {

            int val = Integer.parseInt(button1.getText().toString().trim());
            boolean isChecked = checkedRadioButton.isChecked();
            if (isChecked)
            {
                counter1 = counter1 + val;
                textView1.setText("Counter 1: " + counter1);
            } else {
                counter2 = counter2 + val;
                textView2.setText("Counter 2: " + counter2);
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(v -> {

            int val = Integer.parseInt(button2.getText().toString().trim());
            boolean isChecked = checkedRadioButton.isChecked();
            if (isChecked)
            {
                counter1 = counter1 + val;
                textView1.setText("Counter 1: " + counter1);
            } else {
                counter2 = counter2 + val;
                textView2.setText("Counter 2: " + counter2);
            }

        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(v -> {

            int val = Integer.parseInt(button3.getText().toString().trim());
            boolean isChecked = checkedRadioButton.isChecked();
            if (isChecked)
            {
                counter1 = counter1 + val;
                textView1.setText("Counter 1: " + counter1);
            } else {
                counter2 = counter2 + val;
                textView2.setText("Counter 2: " + counter2);
            }
        });

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(v -> {

            int val = Integer.parseInt(button4.getText().toString().trim());
            boolean isChecked = checkedRadioButton.isChecked();
            if (isChecked)
            {
                counter1 = counter1 + val;
                textView1.setText("Counter 1: " + counter1);
            } else {
                counter2 = counter2 + val;
                textView2.setText("Counter 2: " + counter2);
            }
        });


    }


}