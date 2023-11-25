package com.vedant.product.service.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(){
        super("Resource not found !!");
    }

    public ResourceNotFoundException(String msg){
        super(msg);
    }

}
