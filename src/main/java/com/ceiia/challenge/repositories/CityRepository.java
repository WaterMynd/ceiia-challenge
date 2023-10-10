package com.ceiia.challenge.repositories;

import com.ceiia.challenge.dtos.CityInputDTO;
import com.ceiia.challenge.exceptions.CityAlreadyRegisteredException;
import com.ceiia.challenge.exceptions.InvalidIdException;
import com.ceiia.challenge.exceptions.InvalidNameException;
import com.ceiia.challenge.models.City;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class CityRepository {

    private final List<City> cityList;

    public CityRepository() {
        this.cityList = new ArrayList<>();
    }

    public City registerCity(CityInputDTO dto) throws InvalidNameException, CityAlreadyRegisteredException {
        City newCity;

        int new_id = this.cityList.size() + 1;
        newCity = new City(new_id, dto.getName());

        if (!this.cityList.contains(newCity)) {
            this.cityList.add(newCity);
        } else {
            throw new CityAlreadyRegisteredException("City already registered");
            }

        return newCity;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public City getCity(String name) {
        City resultCity = null;

        for (City c : cityList){
            if (c.getName().equals(name)){
                resultCity = c;
                break;
            }
        }

        return resultCity;
    }
}
