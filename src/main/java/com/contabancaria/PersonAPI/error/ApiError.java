package com.contabancaria.PersonAPI.error;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
@Getter
public class ApiError {
    private int status;
    private String message;
    private List<String> errors;

    public ApiError(int status,String message, List<String>errors){
        super();
        this.status=status;
        this.message=message;
        this.errors=errors;
    }
    public ApiError(int status,String message, String errors){
        super();
        this.status=status;
        this.message=message;
        this.errors= Arrays.asList(errors);
    }
}
