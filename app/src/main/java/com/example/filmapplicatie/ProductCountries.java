package com.example.filmapplicatie;

public class ProductCountries {

    private String iso_639_1;
    private String spokenLanguageName;

    public ProductCountries(String iso_639_1, String spokenLanguageName) {
        this.iso_639_1 = iso_639_1;
        this.spokenLanguageName = spokenLanguageName;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }

    public String getSpokenLanguageName() {
        return spokenLanguageName;
    }

    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    public void setSpokenLanguageName(String spokenLanguageName) {
        this.spokenLanguageName = spokenLanguageName;
    }
}
