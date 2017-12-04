package com.wilson404.blog.domain;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T_USER")
public class User {
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;
    @Column(name = "USER_LOGIN", unique = true)
    private String userLogin;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "USER_NICKNAME", insertable = false, updatable = false)
    @Generated(GenerationTime.INSERT)
    private String userNickname;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "STATUS")
    private Integer status = 1;
    @Column(name = "REGISTER_TIME")
    private Date registerTime;
    @Column(name = "SALT")
    private String salt;

    public User() {
    }

    public User(String userLogin, String password, String userNickname, String email, Integer status, Date registerTime, String salt) {
        this.userLogin = userLogin;
        this.password = password;
        this.userNickname = userNickname;
        this.email = email;
        this.status = status;
        this.registerTime = registerTime;
        this.salt = salt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userLogin='" + userLogin + '\'' +
                ", password='" + password + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                ", registerTime=" + registerTime +
                ", salt='" + salt + '\'' +
                '}';
    }
}
