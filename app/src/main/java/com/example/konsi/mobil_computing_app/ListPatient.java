package com.example.konsi.mobil_computing_app;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


/**
 * Represents a smaller patient object for patients list display and not for saving into database
 */
public class ListPatient {




    private String id;
    private String name;
    private int img;

    public ListPatient(String id, String name, int img) {
        setId(id);
        setName(name);
        setImg(img);
    }

    /**
     * returns the image of a listpatient
     * @return the image as bytes
     */
    public int getImg() {
        return img;
    }

    /**
     * set the image of a listpatient
     * @param img the image
     */
    public void setImg(int img) {
        this.img = img;
    }

    /**
     * returns the id of a listpatient
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * set the id of a patient
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * returns the fullname of a listpatient
     * @return the fullname
     */
    public String getFullname() {
        return name;
    }

    /**
     * set the fullname of a listpatient
     * @param name the fullname
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}


