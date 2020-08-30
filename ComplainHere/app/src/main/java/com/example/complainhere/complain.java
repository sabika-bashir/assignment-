package com.example.complainhere;

import android.graphics.Bitmap;

public class complain {
    public Long id;
    public String personName;
    public String email;
    public String description;
    public String category;
    public String saverity;
    public Bitmap captureimage;


    public complain(String personName, String email, String description, String category, String saverity, Bitmap image) {
        this.id = id;
        this.personName = personName;
        this.email = email;
        this.description = description;
        this.category = category;
        this.saverity = saverity;
        this.captureimage = image;
    }
    public complain(Long id,String personName, String email, String description, String category, String saverity, Bitmap image) {
        this.id = id;
        this.personName = personName;
        this.email = email;
        this.description = description;
        this.category = category;
        this.saverity = saverity;
        this.captureimage = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSaverity() {
        return saverity;
    }

    public void setSaverity(String saverity) {
        this.saverity = saverity;
    }

    public Bitmap getCaptureimage() {
        return captureimage;
    }

    public void setCaptureimage(Bitmap captureimageimage)

    {
        this.captureimage = captureimage;
    }
}
