package com.ceiia.challenge.models;

import com.ceiia.challenge.exceptions.InvalidNameException;
import com.ceiia.challenge.services.OpenWeatherService;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class City {

    private int id;
    private String name;
    private double lat;
    private double lon;

    public City(int id, String name) throws InvalidNameException {
        this.id = id;
        this.updateInfo(name);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void updateInfo(String name) throws InvalidNameException {
        JSONObject json = OpenWeatherService.getCity(name);

        this.name = name;
        JSONObject coord = (JSONObject) json.get("coord");
        lat = (double)coord.get("lat");
        lon = (double)coord.get("lon");
    }

    public ArrayList<JSONObject> getForecast(int days) throws Exception {
        return OpenWeatherService.getForecast(lat, lon, days);
    }


    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((City)obj).getName());
    }
}
