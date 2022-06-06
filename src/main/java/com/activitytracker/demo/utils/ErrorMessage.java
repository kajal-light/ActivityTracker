package com.activitytracker.demo.utils;

public enum ErrorMessage {
    NOT_FOUND(" please check file",400),
FILE_READER_ISSUE("Error while reading ",22);


    ErrorMessage(String errorMessage,int errorCode) {

this.errorMessage=errorMessage;
this.errorCode=errorCode;

    }
    private final String errorMessage;
 private final int errorCode;

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
