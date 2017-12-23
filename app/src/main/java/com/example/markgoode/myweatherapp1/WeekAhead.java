package com.example.markgoode.myweatherapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WeekAhead extends AppCompatActivity {

    static TextView day1;
    static TextView day2;
    static TextView day3;
    static TextView day4;
    static TextView day5;
    static TextView day6;
    static TextView day7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_ahead);

        Intent intent = getIntent();

        day1 = (TextView) findViewById(R.id.day1);
        day2 = (TextView) findViewById(R.id.day2);
        day3 = (TextView) findViewById(R.id.day3);
        day4 = (TextView) findViewById(R.id.day4);
        day5 = (TextView) findViewById(R.id.day5);
        day6 = (TextView) findViewById(R.id.day6);
        day7 = (TextView) findViewById(R.id.day7);

        WeekAsync task = new WeekAsync();
        task.execute("https://api.darksky.net/forecast/91064979649945a5465006904adb8770/30.2672,-97.7431");
    }
}
