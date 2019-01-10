package com.example.konsi.mobil_computing_app;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class ListDoctor {


    @PrimaryKey
    private String ID;
    @ColumnInfo
    private String name;

    public String getID(){ return ID; }
    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName(){ return name; }
    public void setName(String ID) {
        this.name = name;
    }
}
