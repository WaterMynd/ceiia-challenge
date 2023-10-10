package com.ceiia.challenge.dtos;

import java.util.Date;

public class ResponseDTO {

    private final Date date;
    private final Object responseObject;

    public ResponseDTO(Object responseObject){
        this.date = new Date();
        this.responseObject = responseObject;
    }

    public Date getDate() {
        return date;
    }

    public Object getResponseObject() {
        return responseObject;
    }
}
