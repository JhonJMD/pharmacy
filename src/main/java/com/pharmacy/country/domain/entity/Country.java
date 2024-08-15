package com.pharmacy.country.domain.entity;

import java.text.MessageFormat;

public class Country {
    private String codecountry;
    private String namecountry;
    
    public Country() {
    }

    public Country(String codecountry, String namecountry) {
        this.codecountry = codecountry;
        this.namecountry = namecountry;
    }

    public String getCodecountry() {
        return codecountry;
    }

    public void setCodecountry(String codecountry) {
        this.codecountry = codecountry;
    }

    public String getNamecountry() {
        return namecountry;
    }

    public void setNamecountry(String namecountry) {
        this.namecountry = namecountry;
    }

    @Override
    public String toString() {
        String data = MessageFormat.format("Code :{0}\nName : {1}",this.codecountry, this.namecountry);
        return data;
    }
    
}
