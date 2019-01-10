package com.example.konsi.mobil_computing_app;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PatientDao {

    @Query("SELECT * FROM listpatient")
    List<ListPatient> getAll();

    @Query("SELECT * FROM listpatient WHERE id IN (:patientId)")
    List<ListPatient> loadAllByIds(int[] patientId);

    @Query("SELECT * FROM listpatient WHERE name LIKE :patientName")
    ListPatient findByName(String patientName);

    @Query("SELECT * FROM listpatient WHERE name LIKE :patientId")
    ListPatient findByID(String patientId);

    @Insert
    void insertAll(ListPatient... ListPatient);

    @Delete
    void delete(ListPatient listPatient);
}
