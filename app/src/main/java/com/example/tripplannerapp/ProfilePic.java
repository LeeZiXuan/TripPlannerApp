package com.example.tripplannerapp;

public class ProfilePic {
    private String imageUri;

    public ProfilePic(String imageUri) {
        this.imageUri = imageUri;
    }

    public ProfilePic() {
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
