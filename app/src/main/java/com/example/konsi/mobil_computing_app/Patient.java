package com.example.konsi.mobil_computing_app;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Represents a patient for display and later Database implementation
 */
@Entity
public class Patient {


    private int img;

    @PrimaryKey
    @NonNull
    private String id;
    @ColumnInfo
    private String forname;
    @ColumnInfo
    private String lastname;
    @ColumnInfo
    private String fullname;
    @ColumnInfo
    private String birthdate;
    @ColumnInfo
    private String email;
    @ColumnInfo
    private String password;
    @ColumnInfo
    private String correspondingDoctorID;
    //TODO no Lists allowed
    @Ignore
    private ArrayList<String> devices;

    public Patient(String id, int img, String forname, String lastname, String birthdate, String email, String password, String correspondingDoctorID) {
        setId(id);
        setImg(img);
        setForname(forname);
        setLastname(lastname);
        setFullname(forname + " " + lastname);
        setBirthdate(birthdate);
        setEmail(email);
        setPassword(password);
        setCorrespondingDoctorID(correspondingDoctorID);
        /*
        this.devices = new ArrayList<>();
        this.devices.add("Heartbeat");
        this.devices.add("Bloodpressure");
        this.devices.add("Steps");
        */
    }

    /**
     * returns the id of a patient
     * @return the ID
     */
    public String getId() {
        return id;
    }

    /**
     * sets the id of a patient
     * @param ID the new ID
     */
    public void setId(String id) {
        this.id = id;
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
     * returns the birthdate of a patient
     * @return the birthdate as string
     */
    public String getBirthdate() {
        return birthdate;
    }

    /**
     * sets the birthdate of a patient
     * @param birthdate the birthdate as string
     */
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
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
     * sets the doctor id
     * @param doctorid the doctor id
     */
    public void setCorrespondingDoctorID(String doctorid) {
        this.correspondingDoctorID = doctorid;
    }

    /**
     * returns the doctor id operating the patient
     * @return the doctor id
     */
    public String getCorrespondingDoctorID() {
        return this.correspondingDoctorID;
    }

    /**
     * returns the devicelist of a patient
     * @return the devices
     */
    public ArrayList<String> getDevices() {
        return devices;
    }


    public void setDevices(ArrayList<String> devices){
        this.devices = devices;
    }

}
