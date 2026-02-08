package com.example.listycitylab3;

public class City {
    private String city;
    private String province;

    // constructor
    public City(String city, String province){
        this.city = city;
        this.province = province;
    }

    // getters
    public String getCityName(){
        return this.city;
    }

    public String getProvinceName(){
        return this.province;
    }

    // setters
    public void setCityName(String city) {
        this.city = city;
    }

    public void setProvinceName(String province) {
        this.province = province;
    }
}