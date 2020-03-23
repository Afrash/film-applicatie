package com.example.filmapplicatie;

public class Element {

    private String image;
    private String title;
    private String geographicalLocation;
    private String identificationNumber;
    private String artist;
    private String description;
    private String material;
    private String underground;
    private String placement_date;

    public Element(String image, String title, String geographicalLocation, String identificationNumber, String artist, String description, String material,String underground, String placement_date) {
        this.image = image;
        this.title = title;
        this.geographicalLocation = geographicalLocation;
        this.identificationNumber = identificationNumber;
        this.artist = artist;
        this.description = description;
        this.material = material;
        this.underground = underground;
        this.placement_date = placement_date;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getGeographicalLocation() {
        return geographicalLocation;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public String getArtist() {
        return artist;
    }

    public String getDescription() {
        return description;
    }

    public String getMaterial() {
        return material;
    }

    public String getUnderground() {
        return underground;
    }

    public String getPlacement_date() {
        return placement_date;
    }
}
