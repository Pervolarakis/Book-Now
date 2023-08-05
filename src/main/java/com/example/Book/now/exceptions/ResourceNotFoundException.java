package com.example.Book.now.exceptions;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends Exception{
    private String resourceName;
    public ResourceNotFoundException(String resourceName){
        this.resourceName = resourceName;
    }
}
