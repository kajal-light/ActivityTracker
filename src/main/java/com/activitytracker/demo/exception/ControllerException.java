package com.activitytracker.demo.exception;

import org.springframework.stereotype.Component;

@Component
public class ControllerException extends RuntimeException {


    private String errorMeaasage;

    private int errorCode;

    public ControllerException(String errorMeaasage, int errorCode) {
        super();
        this.errorMeaasage = errorMeaasage;
        this.errorCode = errorCode;
    }

    public String getErrorMeaasage() {
        return errorMeaasage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public ControllerException() {

    }
}
