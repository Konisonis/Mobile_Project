package com.example.konsi.mobil_computing_app;

/**
 * Represents a smaller patient object for patients list display and not for saving into database
 */
public class ListPatient {

    private int img;
    private String ID;
    private String fullname;

    public ListPatient(String id, String fullname, int img) {
        setID(id);
        setFullname(fullname);
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
        return fullname;
    }

    /**
     * set the fullname of a listpatient
     * @param fullname the fullname
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
