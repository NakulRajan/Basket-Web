package com.shopping.basket.Exceptions;

/**
 * Exception thrown when a valid data is not found in the database.
 */
public class DataNotFound extends RuntimeException{

    public DataNotFound(String msg){
        super(msg);
    }


}
