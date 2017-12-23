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

public class WeekAsync extends AsyncTask<String, Void, JSONObject> {

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
            Integer names[] = new Integer[7];
            String unix;
            String day[] = new String[7];
            long unixTimestamp = 0;
            for(int i=0; i<7; i++)
            {
                int j = i+1;
                json4 = json3.getJSONObject(j);
                int max = (int)Double.parseDouble(json4.getString("temperatureHigh"));
                int min = (int)Double.parseDouble(json4.getString("temperatureLow"));
                names[i] = (max + min) / 2;
                unix = json4.getString("time");
                unixTimestamp = Long.parseLong(unix);
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(unixTimestamp * 1000L);
                if(calendar.get(Calendar.DAY_OF_WEEK) == 1)
                {
                    day[i] = "Sunday";
                }
                else if(calendar.get(Calendar.DAY_OF_WEEK) == 2)
                {
                    day[i] = "Monday";
                }
                else if(calendar.get(Calendar.DAY_OF_WEEK) == 3)
                {
                    day[i] = "Tuesday";
                }
                else if(calendar.get(Calendar.DAY_OF_WEEK) == 4)
                {
                    day[i] = "Wednesday";
                }
                else if(calendar.get(Calendar.DAY_OF_WEEK) == 5)
                {
                    day[i] = "Thursday";
                }
                else if(calendar.get(Calendar.DAY_OF_WEEK) == 6)
                {
                    day[i] = "Friday";
                }
                else if(calendar.get(Calendar.DAY_OF_WEEK) == 7)
                {
                    day[i] = "Saturday";
                }
            }

            String print0 = "    " + names[0] + "\u00b0 F " + "                " + day[0];
            String print1 = "    " + names[1] + "\u00b0 F " + "                " + day[1];
            String print2 = "    " + names[2] + "\u00b0 F " + "                " + day[2];
            String print3 = "    " + names[3] + "\u00b0 F " + "                " + day[3];
            String print4 = "    " + names[4] + "\u00b0 F " + "                " + day[4];
            String print5 = "    " + names[5] + "\u00b0 F " + "                " + day[5];
            String print6 = "    " + names[6] + "\u00b0 F " + "                " + day[6];

            WeekAhead.day1.setText(print0);
            WeekAhead.day2.setText(print1);
            WeekAhead.day3.setText(print2);
            WeekAhead.day4.setText(print3);
            WeekAhead.day5.setText(print4);
            WeekAhead.day6.setText(print5);
            WeekAhead.day7.setText(print6);



        }catch (JSONException e){
            e.printStackTrace();
        }






    }



}
