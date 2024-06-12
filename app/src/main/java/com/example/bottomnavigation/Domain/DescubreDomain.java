package com.example.bottomnavigation.Domain;

public class DescubreDomain {
    public DescubreDomain(String dataTitle, int dataImage, int dataDesc, String dataLang) {
        this.dataTitle = dataTitle;
        this.dataImage = dataImage;
        this.dataDesc = dataDesc;
        this.dataLang = dataLang;
    }

    private String dataTitle;
    private int dataImage;
    private int dataDesc;

    public String getDataTitle() {
        return dataTitle;
    }

    public int getDataImage() {
        return dataImage;
    }

    public int getDataDesc() {
        return dataDesc;
    }

    public String getDataLang() {
        return dataLang;
    }

    private String dataLang;
}
