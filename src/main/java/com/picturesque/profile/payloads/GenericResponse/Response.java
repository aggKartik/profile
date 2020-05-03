package com.picturesque.profile.payloads.GenericResponse;

public class Response<T> {

    private T response;
    private int statusCode;

    public Response(T response, int statusCode) {
        this.response = response;
        this.statusCode = statusCode;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
