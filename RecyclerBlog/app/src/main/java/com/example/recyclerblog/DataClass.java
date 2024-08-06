package com.example.recyclerblog;

public class DataClass {

    private String dataTitle;
    private int dataDesc;
    private String dataLang;
    private int dataImage;

    // creating a getter

    public String getDataTitle() {
        return dataTitle;
    }

    public int getDataDesc() {
        return dataDesc;
    }

    public String getDataLang() {
        return dataLang;
    }

    public int getDataImage() {
        return dataImage;
    }


    //creating a constructor first

    public DataClass(String dataTitle, int dataDesc, String dataLang, int dataImage) {
        this.dataTitle = dataTitle;
        this.dataDesc = dataDesc;
        this.dataLang = dataLang;
        this.dataImage = dataImage;
    }


    //creating getter




}
