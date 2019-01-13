package com.example.konsi.mobil_computing_app;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Doctor {


    @PrimaryKey
    @NonNull
    private String id;
    @ColumnInfo
    private String forname;
    @ColumnInfo
    private String lastname;
    @ColumnInfo
    private String birthdate;
    @ColumnInfo
    private String eMail;
    @ColumnInfo
    private String password;


    public Doctor(String id, String forname, String lastname, String eMail,String birthdate, String password){
        setId(id);
        setForname(forname);
        setLastname(lastname);
        setEMail(eMail);
        setBirthdate(birthdate);
        setPassword(password);

    }

    public String getId(){
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getForname(){
        return forname;
    }

    public void setForname(String forname) {
        this.forname = forname;
    }

    public String getLastname(){
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBirthdate(){
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setEMail(String eMail){
        this.eMail = eMail;
    }

    public String getEMail(){
        return eMail;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }
}
