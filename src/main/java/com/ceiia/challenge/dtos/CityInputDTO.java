package com.ceiia.challenge.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;

public class CityInputDTO {

    private String name;

    @JsonCreator
    CityInputDTO(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
