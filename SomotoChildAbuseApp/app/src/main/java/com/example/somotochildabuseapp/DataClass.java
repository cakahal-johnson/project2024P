package com.example.somotochildabuseapp;

public class DataClass {
    private String dataTitle;
    private String dataDesc;
    private String dataLang;
    private String dataImage;

//    getter generating without main

    public String getDataTitle() {
        return dataTitle;
    }

    public String getDataDesc() {
        return dataDesc;
    }

    public String getDataLang() {
        return dataLang;
    }

    public String getDataImage() {
        return dataImage;
    }


//    constructor generating all

    public DataClass(String dataTitle, String dataDesc, String dataLang, String dataImage) {
        this.dataTitle = dataTitle;
        this.dataDesc = dataDesc;
        this.dataLang = dataLang;
        this.dataImage = dataImage;
    }
}
