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
import java.text.DecimalFormat;
import java.util.Calendar;

public class FortyEightAsync extends AsyncTask<String, Void, JSONObject> {

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

            JSONObject json2;
            json2 = json.getJSONObject("daily");
            JSONArray json3;
            json3 = json2.getJSONArray("data");
            JSONObject json4;
            Double names[] = new Double[3];
            for(int i=0; i<3; i++)
            {
                json4 = json3.getJSONObject(i);
                double max = Double.parseDouble(json4.getString("temperatureHigh"));
                double min = Double.parseDouble(json4.getString("temperatureLow"));
                names[i] = (max + min) / 2;
            }

            double tempavg = (names[0] + names[1] + names[2]) / 3 ;
           tempavg = Math.floor(tempavg * 100) / 100;

            FortyEight.fortyeight.setText(String.valueOf(tempavg) + "\u00b0 F");

        }catch (JSONException e){
            e.printStackTrace();
        }






    }



}