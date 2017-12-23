package com.example.markgoode.myweatherapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CurrentWeather extends AppCompatActivity {

    static TextView cTemp;
    static TextView cHum;
    static TextView cWind;
    static TextView cPrecip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_weather);
        Intent intent = getIntent();

        cTemp = (TextView) findViewById(R.id.current_temp);
        cHum = (TextView) findViewById(R.id.current_hum);
        cWind = (TextView) findViewById(R.id.current_ws);
        cPrecip = (TextView) findViewById(R.id.current_precip);

        JSONAsyncTask task = new JSONAsyncTask();
        task.execute("https://api.darksky.net/forecast/91064979649945a5465006904adb8770/30.2672,-97.7431");

    }
}
