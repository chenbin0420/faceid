package com.dhcc.magiceyes.exceptions;

public class BusinessException extends RuntimeException {

    public BusinessException(){}

    public BusinessException(String msg){
        super( msg );
    }
}
