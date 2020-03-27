package com.zgw.personaltravel.entity;

public class Person {
    private Integer id;
    private String account;
    private String password;
    private String nickname;
    private String headportrait;
    private Character identity;
    private Character gender;
    private String introduction;
    private String obligate;
    private String obligate1;

    public String getObligate() {
        return obligate;
    }

    public void setObligate(String obligate) {
        this.obligate = obligate;
    }

    public String getObligate1() {
        return obligate1;
    }

    public void setObligate1(String obligate1) {
        this.obligate1 = obligate1;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", headportrait='" + headportrait + '\'' +
                ", identity=" + identity +
                ", gender=" + gender +
                ", introduction='" + introduction + '\'' +
                ", obligate='" + obligate + '\'' +
                ", obligate1='" + obligate1 + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadportrait() {
        return headportrait;
    }

    public void setHeadportrait(String headportrait) {
        this.headportrait = headportrait;
    }

    public Character getIdentity() {
        return identity;
    }

    public void setIdentity(Character identity) {
        this.identity = identity;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
