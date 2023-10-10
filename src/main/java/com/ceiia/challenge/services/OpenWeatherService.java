package com.ceiia.challenge.services;

import com.ceiia.challenge.exceptions.InvalidNameException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class OpenWeatherService {

    private static String appid = "";


    public static JSONObject getCity(String name) throws InvalidNameException {
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + name + "&appid=" + appid);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("accept", "application/json");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(content.toString());
            return json;

        } catch (Exception ex){
            throw new InvalidNameException("Invalid name");
        }
    }

    public static ArrayList<JSONObject> getForecast(double lat, double lon, int days)  throws Exception{
        String urlStr = "https://api.openweathermap.org/data/2.5/forecast?lat=" + lat + "&lon=" + lon + "&appid=" + appid;

        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("accept", "application/json");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(content.toString());
        JSONArray weatherListJSON = (JSONArray) json.get("list");

        Date date = new Date();
        Timestamp myTimestamp = new Timestamp(date.getTime());
        Timestamp someDaysAfter = Timestamp.valueOf(myTimestamp.toLocalDateTime().plusDays(days));
        long after = someDaysAfter.getTime()/1000;

        ArrayList<JSONObject> weatherList = new ArrayList<>();

        for (Object jo : weatherListJSON) {
            long jo_dt = (long)((JSONObject)jo).get("dt");

            if(jo_dt <= after){
                weatherList.add((JSONObject)jo);
            }
        }

        return weatherList;
    }
}
