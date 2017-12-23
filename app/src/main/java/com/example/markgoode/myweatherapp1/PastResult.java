package com.example.markgoode.myweatherapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PastResult extends AppCompatActivity {
    static TextView temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_result);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(PastWeather.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.pastresult);
        String prompt = "Temperature on: " + message;
        temp = (TextView) findViewById(R.id.finaltemp);             // this is the final temp

        textView.setText(prompt);

        PastAsync task = new PastAsync();
        task.execute(message); // put date in here




    }
}
