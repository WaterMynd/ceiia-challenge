package com.ceiia.challenge.controllers;

import com.ceiia.challenge.dtos.CityInputDTO;
import com.ceiia.challenge.dtos.ResponseDTO;
import com.ceiia.challenge.exceptions.CityAlreadyRegisteredException;
import com.ceiia.challenge.exceptions.InvalidNameException;
import com.ceiia.challenge.models.City;
import com.ceiia.challenge.repositories.CityRepository;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CityController {

    private final CityRepository repository;

    CityController(CityRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/city/all")
    List<City> all_cities() {
        return repository.getCityList();
    }

    @PostMapping(value="/city/register", consumes = "application/json")
    ResponseDTO newCity(@RequestBody CityInputDTO cityInputDTO) {
        ResponseDTO response;

        try {
            City newCity = repository.registerCity(cityInputDTO);
            response = new ResponseDTO(newCity);
        } catch (CityAlreadyRegisteredException e) {
            response = new ResponseDTO("City already registered");
        } catch (InvalidNameException e) {
            response = new ResponseDTO("Invalid city name");
        }

        return response;
    }

    @GetMapping("/city/{name}/forecast/{days}")
    ResponseDTO cityForecast(@PathVariable String name, @PathVariable int days) {
        ResponseDTO response;

        City city = repository.getCity(name);

        if (city != null){
            try {
                ArrayList<JSONObject> forecastJson = city.getForecast(days);
                response = new ResponseDTO(forecastJson);
            } catch (Exception ex){
                response = new ResponseDTO("Something went wrong");
            }


        } else {
            response = new ResponseDTO("Invalid city name");
        }

        return response;
    }

}
