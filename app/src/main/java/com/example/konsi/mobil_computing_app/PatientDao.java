package com.example.konsi.mobil_computing_app;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

public interface PatientDao {

    @Query("SELECT * FROM listpatient")
    List<ListPatient> getAll();

    @Query("SELECT * FROM listpatient WHERE ID IN (:userIds)")
    List<ListPatient> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM listpatient WHERE name LIKE :serchName")
    ListPatient findByName(String serchName);

    @Query("SELECT * FROM listpatient WHERE name LIKE :searchId")
    ListPatient findByID(String searchId);

    @Insert
    void insertAll(ListPatient... ListPatient);

    @Delete
    void delete(ListPatient listPatient);
}
