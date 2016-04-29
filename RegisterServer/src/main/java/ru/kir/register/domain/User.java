package ru.kir.register.domain;


import javax.persistence.*;
import java.util.Set;

/**
 * Created by Kirill Zhitelev on 19.02.2016.
 */
@Entity
@Table(name = "people")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "login")
    private String login;

    @Column(name = "enabled")
    private String enabled;

    @Column(name = "role")
    private int role;

    @Column(name = "password")
    private String password;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Param> params;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<Param> getParams() {
        return params;
    }

    public void setParams(Set<Param> params) {
        this.params = params;
    }



    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}