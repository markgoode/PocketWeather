package com.example.markgoode.myweatherapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




   //     JSONAsyncTask task = new JSONAsyncTask();
   //     task.execute("https://api.darksky.net/forecast/91064979649945a5465006904adb8770/30.2672,-97.7431");
    }

    public void sendCurrentWeather(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, CurrentWeather.class);
        startActivity(intent);
    }

    public void sendHourlyForecast(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, HourlyForecast.class);
        startActivity(intent);
    }

    public void sendFortyEight(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, FortyEight.class);
        startActivity(intent);
    }

    public void sendWeekAhead(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, WeekAhead.class);
        startActivity(intent);
    }

    public void sendPastWeather(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, PastWeather.class);
        startActivity(intent);
    }
}
