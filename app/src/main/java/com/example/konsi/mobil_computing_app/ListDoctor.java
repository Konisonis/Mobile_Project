package com.example.konsi.mobil_computing_app;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity
public class ListDoctor {


    @PrimaryKey
    @NonNull
    private String id;
    @ColumnInfo
    private String name;

    public ListDoctor(String id, String name){
        this.id = id;
        this.name = name;
    }

    public String getId(){ return id; }
    public void setId(String id) {
        this.id = id;
    }

    public String getName(){ return name; }
    public void setName(String id) {
        this.name = name;
    }
}
