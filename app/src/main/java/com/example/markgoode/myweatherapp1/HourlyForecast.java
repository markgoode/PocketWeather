package com.example.markgoode.myweatherapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HourlyForecast extends AppCompatActivity {

    static TextView hour1;
    static TextView hour2;
    static TextView hour3;
    static TextView hour4;
    static TextView hour5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_forecast);

        Intent intent = getIntent();

        hour1 = (TextView) findViewById(R.id.hour1);
        hour2 = (TextView) findViewById(R.id.hour2);
        hour3 = (TextView) findViewById(R.id.hour3);
        hour4 = (TextView) findViewById(R.id.hour4);
        hour5 = (TextView) findViewById(R.id.hour5);

        HourlyAsync task = new HourlyAsync();
        task.execute("https://api.darksky.net/forecast/91064979649945a5465006904adb8770/30.2672,-97.7431");
    }
}
