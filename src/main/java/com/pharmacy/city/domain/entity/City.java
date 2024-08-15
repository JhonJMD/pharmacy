package com.pharmacy.city.domain.entity;

import java.text.MessageFormat;

public class City {
    private String codecity;
    private String namecity;
    private String codereg;
    
    public City() {
    }

    public City(String codecity, String namecity, String codereg) {
        this.codecity = codecity;
        this.namecity = namecity;
        this.codereg = codereg;
    }

    public String getCodecity() {
        return codecity;
    }

    public void setCodecity(String codecity) {
        this.codecity = codecity;
    }

    public String getNamecity() {
        return namecity;
    }

    public void setNamecity(String namecity) {
        this.namecity = namecity;
    }

    public String getCodereg() {
        return codereg;
    }

    public void setCodereg(String codereg) {
        this.codereg = codereg;
    }

    @Override
    public String toString() {
        String data = MessageFormat.format("Code : {0}\nName : {1}\nName region : {2}", this.codecity, this.namecity, this.codereg);
        return data;
    }
}
