package ru.kir.register.domain;

import javax.persistence.*;

/**
 * Created by Kirill Zhitelev on 23.02.2016.
 */
@Entity
@Table(name = "people_params")
public class Param {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "people_id")
    private User user;

    @Column(name = "people_attribute_id")
    private int attrId;

    @Column(name = "value")
    private String value;


    public Param() {
    }

    public Param(User user, int attrId, String value) {
        this.user = user;
        this.attrId = attrId;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAttrId() {
        return attrId;
    }

    public void setAttrId(int attrId) {
        this.attrId = attrId;
    }

    @Override
    public String toString() {
        return "Param{" +
                "value='" + value + '\'' +
                '}';
    }
}
