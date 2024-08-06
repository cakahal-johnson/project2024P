package com.example.bhub;

public class ModelComment {

//    Variables
        String id, name, profileImage, published, comment;

    //Constructor
    public ModelComment(String id, String name, String profileImage, String published, String comment) {
        this.id = id;
        this.name = name;
        this.profileImage = profileImage;
        this.published = published;
        this.comment = comment;
    }


    //Getter n Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
