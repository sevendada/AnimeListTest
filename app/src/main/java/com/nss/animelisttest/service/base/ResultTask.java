package com.nss.animelisttest.service.base;

/**
 * Created by mac on 2/22/2017 AD.
 */

public class ResultTask<T> {

    private boolean resultOK = false;
    private Class<?> exceptionClass = null;
    private String exceptionMessage = null;
    private T result = null;

    public boolean isResultOK() {
        return resultOK;
    }
    public void setResultOK(boolean resultOK) {
        this.resultOK = resultOK;
    }
    public Class<?> getExceptionClass() {
        return exceptionClass;
    }
    public void setExceptionClass(Class<?> exceptionClass) {
        this.exceptionClass = exceptionClass;
    }
    public String getExceptionMessage() {
        return exceptionMessage;
    }
    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
    public T getResult() {
        return result;
    }
    public void setResult(T result) {
        this.result = result;
    }

}
