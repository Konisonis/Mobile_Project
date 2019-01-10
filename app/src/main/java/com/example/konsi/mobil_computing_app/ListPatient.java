package com.example.konsi.mobil_computing_app;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Represents a smaller patient object for patients list display and not for saving into database
 */
@Entity
public class ListPatient {


    private int img;
    @PrimaryKey
    private String ID;
    @ColumnInfo
    private String name;

    public ListPatient(String id, String fullname, int img) {
        setID(id);
        setName(fullname);
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
    public String getID() {
        return ID;
    }

    /**
     * set the id of a patient
     * @param ID the id
     */
    public void setID(String ID) {
        this.ID = ID;
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
}


