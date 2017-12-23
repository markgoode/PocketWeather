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

public class HourlyAsync extends AsyncTask<String, Void, JSONObject> {

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
            json2 = json.getJSONObject("hourly");
            JSONArray json3;
            json3 = json2.getJSONArray("data");
            JSONObject json4;
            String names[] = new String[5];
            String unix;
            Integer hours[] = new Integer[5];
            long unixTimestamp = 0;
            for(int i=0; i<5; i++)
            {
                int j = i+1;
                json4 = json3.getJSONObject(j);
                names[i] = json4.getString("temperature");
                unix = json4.getString("time");
                unixTimestamp = Long.parseLong(unix);
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(unixTimestamp * 1000L);
                hours[i] = calendar.get(Calendar.HOUR);
                if(hours[i] == 0)
                {
                    hours[i] = 12;
                }

            }

            String print0 = "Time: " + hours[0] + ":00 " + " Temperature: " + names[0] + "\u00b0 F";
            String print1 = "    " + hours[1] + ":00 " + "                   " + names[1] + "\u00b0 F";
            String print2 = "    " + hours[2] + ":00 " + "                   " + names[2] + "\u00b0 F";
            String print3 = "    " + hours[3] + ":00 " + "                   " + names[3] + "\u00b0 F";
            String print4 = "    " + hours[4] + ":00 " + "                   " + names[4] + "\u00b0 F";

            HourlyForecast.hour1.setText(print0);
            HourlyForecast.hour2.setText(print1);
            HourlyForecast.hour3.setText(print2);
            HourlyForecast.hour4.setText(print3);
            HourlyForecast.hour5.setText(print4);

        }catch (JSONException e){
            e.printStackTrace();
        }






    }



}
