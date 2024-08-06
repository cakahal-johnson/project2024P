package com.example.recyclerclass;

public class DataClass {
    private String dataTitle;
    private int dataDesc;
    private String recLang;
    private int dataImage;

    // creating getter

    public String getDataTitle() {
        return dataTitle;
    }

    public int getDataDesc() {
        return dataDesc;
    }

    public String getRecLang() {
        return recLang;
    }

    public int getDataImage() {
        return dataImage;
    }

    // creating constructor

    public DataClass(String dataTitle, int dataDesc, String recLang, int dataImage) {
        this.dataTitle = dataTitle;
        this.dataDesc = dataDesc;
        this.recLang = recLang;
        this.dataImage = dataImage;
    }
}
