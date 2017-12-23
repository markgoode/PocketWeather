package com.example.markgoode.myweatherapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FortyEight extends AppCompatActivity {

    static TextView fortyeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forty_eight);

        Intent intent = getIntent();

        fortyeight = (TextView) findViewById(R.id.fortyeightavg);

        FortyEightAsync task = new FortyEightAsync();
        task.execute("https://api.darksky.net/forecast/91064979649945a5465006904adb8770/30.2672,-97.7431");
    }
}
