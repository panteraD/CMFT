package com.chernenkov.status.client.sportsmensnetwork.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Andrey on 16.03.2016.
 */
public class Sportsman {
    private final String begin;
    private final String end;
    private final String status;
    private final String address;
    private final String email;
    private final String fullName;
    private final String login;

    @JsonCreator
    public Sportsman(@JsonProperty("begin") String begin,
                     @JsonProperty("end") String end,
                     @JsonProperty("status") String status,
                     @JsonProperty("address") String address,
                     @JsonProperty("fullName") String fullName,
                     @JsonProperty("login") String login,
                     @JsonProperty("email") String email)   {
        this.begin = begin;
        this.end = end;
        this.status = status;
        this.address = address;
        this.fullName = fullName;
        this.login = login;
        this.email = email;
    }


    public String getBegin() {
        return begin;
    }

    public String getEnd() {
        return end;
    }

    public String getStatus() {
        return status;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getLogin() {
        return login;
    }
}