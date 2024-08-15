package com.pharmacy.region.domain.entity;

import java.text.MessageFormat;

public class Region {
    private String codereg;
    private String namereg;
    private String codecountry;

    public Region() {
    }

    public Region(String codereg, String namereg, String codecountry) {
        this.codereg = codereg;
        this.namereg = namereg;
        this.codecountry = codecountry;
    }

    public String getCodereg() {
        return codereg;
    }

    public void setCodereg(String codereg) {
        this.codereg = codereg;
    }

    public String getNamereg() {
        return namereg;
    }

    public void setNamereg(String namereg) {
        this.namereg = namereg;
    }

    public String getCodecountry() {
        return codecountry;
    }

    public void setCodecountry(String codecountry) {
        this.codecountry = codecountry;
    }

    @Override
    public String toString() {
        String data = MessageFormat.format("Code :{0}\nName : {1}\nCode country : {2}",this.codereg, this.namereg, this.codecountry);;
        return data;
    }
}
