package com.test_technique.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;


@JsonInclude(NON_NULL) // si un champ est null on ne le montre pas dans le json
public class ReponseHttp {

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy hh:mm:ss",
            timezone = "Europe/Paris")
    protected Date timeStamp = new Date();
    protected String resourceUrl;
    protected int httpStatusCode;
    protected HttpStatus httpStatus;
    protected String message;
    protected String logs;
    protected Map<?, ?> data;

    public ReponseHttp() {
        timeStamp = new Date();
    }

    public Date getTimeStamp() {
        return timeStamp;
    }


    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLogs() {
        return logs;
    }

    public void setLogs(String logs) {
        this.logs = logs;
    }

    public Map<?, ?> getData() {
        return data;
    }

    public void setData(Map<?, ?> data) {
        this.data = data;
    }
}
