package com.activitytracker.demo.exception;

import org.springframework.stereotype.Component;

@Component
public class BusinessEception extends RuntimeException {

private String errorMeaasage;

private int errorCode;

    public BusinessEception(String errorMeaasage, int errorCode) {
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


    public BusinessEception() {

    }

}
