package ru.kir.status.domain;

import javax.persistence.*;

/**
 * Created by Kirill Zhitelev on 08.03.2016.
 */
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "role")
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
