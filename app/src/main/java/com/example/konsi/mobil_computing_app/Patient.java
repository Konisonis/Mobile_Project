package com.example.konsi.mobil_computing_app;

import android.bluetooth.BluetoothClass;

import java.util.ArrayList;

/**
 * Represents a patient for display and later Database implementation
 */
public class Patient {


    private int img;

    private String ID;
    private String forname;
    private String lastname;
    private String fullname;
    private String email;
    private String password;
    private ArrayList<BluetoothClass.Device> devices;

    public Patient(String id, int img, String forname, String lastname, String email, String password) {
        setID(id);
        setImg(img);
        setForname(forname);
        setLastname(lastname);
        setFullname(forname + " " + lastname);
        setEmail(email);
        setPassword(password);
        this.devices = new ArrayList<>();
    }

    /**
     * returns the id of a patient
     * @return the ID
     */
    public String getID() {
        return ID;
    }

    /**
     * sets the id of a patient
     * @param ID the new ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * returns the image of a patient
     * @return the image
     */
    public int getImg() {
        return img;
    }

    /**
     * sets the image of a patient
     * @param img the image
     */
    public void setImg(int img) {
        this.img = img;
    }

    /**
     * returns the forname of a patient
     * @return the forname
     */
    public String getForname() {
        return forname;
    }

    /**
     * sets the forname of a patient
     * @param forname the new forname
     */
    public void setForname(String forname) {
        this.forname = forname;
    }

    /**
     * returns the lastname of a patient
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * sets the lastname
     * @param lastname the new lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * returns the fullname including for and lastname of a patient
     * @return the fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * builds the fullname of a patient
     * @param fullname the new fullname
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * returns the email of a patient
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * sets the new email of a patient
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * returns the password a patient
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets the new password of a patient
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * returns the devicelist of a patient
     * @return the devices
     */
    public ArrayList<BluetoothClass.Device> getDevices() {
        return devices;
    }

}
