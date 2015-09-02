package com.bmfn.my.model;

import javax.persistence.*;

@MappedSuperclass
public abstract class Traders {

    @Id
    @Column(name = "LOGIN")
    private String login;

    @Column(name = "COMMISSION")
    private String commission;

    @Column(name = "CLOSE_TIME")
    private String closeTime;

    public String getCommission() {
        return commission;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }
}
