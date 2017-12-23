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
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PastAsync extends AsyncTask<String, Void, JSONObject> {

    protected JSONObject doInBackground(String... urls) {
        StringBuffer response = new StringBuffer();
        int timeflag = 0;
        try {
            String USER_AGENT = "Mozilla/5.0";
            ObjectMapper mapper = new ObjectMapper();
            String it = "https://api.darksky.net/forecast/91064979649945a5465006904adb8770/30.2672,-97.7431";

            String dateString = urls[0];
            if(dateString.contains("PM"))
            {
                timeflag = 1;
            }
            DateFormat dateFormat = new SimpleDateFormat("MM'/'dd'/'yyyy h':'m a");
            Date date = dateFormat.parse(dateString);
            long unixTime = (long) date.getTime()/1000;

            String fr = it + "," + unixTime;

            URL obj = new URL(fr);
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

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(unixTime * 1000L);
            int hour = calendar.get(Calendar.HOUR);

            if(timeflag == 1){
                hour += 12;
            }

            JSONObject json2;
            json2 = json.getJSONObject("hourly");
            JSONArray json3;
            json3 = json2.getJSONArray("data");
            JSONObject json4;
            json4 = json3.getJSONObject(hour - 1);
            return json4;


        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        } catch (ParseException e){
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
          //     double max = Double.parseDouble(json.getString("temperatureHigh"));
          //      double min = Double.parseDouble(json.getString("temperatureLow"));
          //      double tempavg = (max + min) / 2;

            double tempavg = Double.parseDouble(json.getString("temperature"));


            PastResult.temp.setText(String.valueOf(tempavg) + "\u00b0 F");

        }catch (JSONException e){
            e.printStackTrace();
        }






    }



}
