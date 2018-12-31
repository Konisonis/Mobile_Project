package com.example.konsi.mobil_computing_app;

import android.bluetooth.BluetoothClass;

import java.util.ArrayList;

public class Patient {

    private int ID;

    private String forname;
    private String lastname;
    private String fullname;
    private String email;
    private String password;
    private ArrayList<BluetoothClass.Device> devices;

    public Patient(int id, String forname, String lastname, String email, String password) {
        setID(id);
        setForname(forname);
        setLastname(lastname);
        setFullname(forname + " " + lastname);
        setEmail(email);
        setPassword(password);
        this.devices = new ArrayList<>();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getForname() {
        return forname;
    }

    public void setForname(String forname) {
        this.forname = forname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<BluetoothClass.Device> getDevices() {
        return devices;
    }

}
