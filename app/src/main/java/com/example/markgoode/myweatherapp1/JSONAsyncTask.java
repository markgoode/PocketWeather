package com.example.markgoode.myweatherapp1;


import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.json.JSONException;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

public class JSONAsyncTask extends AsyncTask<String, Void, JSONObject> {

    protected JSONObject doInBackground(String... urls) {
        StringBuffer response = new StringBuffer();
        try {
            String USER_AGENT = "Mozilla/5.0";
            ObjectMapper mapper = new ObjectMapper();
            URL obj = new URL(urls[0]);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");                                                                    //establishing connection

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;


            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            String after = response.toString();
            JSONObject json = new JSONObject(after);
            return json;

        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e){
        e.printStackTrace();
    }
        return null;

    }
    protected void onProgressUpdate(Integer... progress) {
    }

    protected void onPostExecute(JSONObject json) {
        // this is executed on the main thread after the process is over
        // update your UI here
        try {

            JSONObject json1;
            json1 = json.getJSONObject("currently");
            String temp = json1.getString("temperature") + "\u00b0 F";
            String humidity = json1.getString("humidity");
            String windSpeed = json1.getString("windSpeed");
      //      String precipType = json1.getString("precipType");
            String precipProb = json1.getString("precipProbability");
            double percentage = Double.parseDouble(precipProb);
            percentage = percentage * 100;
            String precipPer = String.valueOf(percentage) + " %";

            CurrentWeather.cTemp.setText(temp);
            CurrentWeather.cHum.setText(humidity);
            CurrentWeather.cWind.setText(windSpeed);
            CurrentWeather.cPrecip.setText(precipPer);

        }catch (JSONException e){
            e.printStackTrace();
        }






    }



}
