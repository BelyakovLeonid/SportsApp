package com.modo.modo.sportsapp.model.domain.common;

@SuppressWarnings("unused")
public class DataWrapper<T> {
    private T dataObject;
    private boolean error;
    private int code;

    public DataWrapper(T dataObject) {
        this.dataObject = dataObject;
        this.error = false;
    }

    public DataWrapper(int code) {
        this.code = code;
        this.error = true;
    }

    public T getDataObject() {
        return dataObject;
    }

    public void setDataObject(T dataObject) {
        this.dataObject = dataObject;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
