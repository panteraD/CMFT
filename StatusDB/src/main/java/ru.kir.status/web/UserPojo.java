package ru.kir.status.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Andrey on 15.03.2016.
 */

//TODO: unite this class and user
@XmlRootElement(name = "UserPojo")
@Component
@JsonInclude(JsonInclude.Include.NON_NULL) //there will be no null objects included in response
public class UserPojo {

    @JsonView(Views.Public.class)
    String begin;
    @JsonView(Views.Public.class)
    String end;
    @JsonView(Views.Public.class)
    String status;
    @JsonView(Views.Public.class)
    String address;
    @JsonView(Views.Public.class)
    String email;
    @JsonView(Views.Public.class)
    String fullName;
    @JsonView(Views.Public.class)
    String login;

    public UserPojo() {

    }

    public UserPojo(String begin, String end, String status, String address) {
        this.begin = begin;
        this.end = end;
        this.status = status;
        this.address = address;
        this.email = null;
        this.fullName = null;
        this.login = null;
    }

    public UserPojo(String fullName, String login, String begin,
                    String end, String status,
                    String address, String email) {
        this.begin = begin;
        this.end = end;
        this.status = status;
        this.address = address;
        this.email = email;
        this.fullName = fullName;
        this.login = login;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
