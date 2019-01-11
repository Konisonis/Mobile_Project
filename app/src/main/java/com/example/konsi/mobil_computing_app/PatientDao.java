package com.example.konsi.mobil_computing_app;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PatientDao {

    @Query("SELECT * FROM patient")
    List<Patient> getAll();

    @Query("SELECT * FROM patient WHERE id IN (:patientId)")
    List<Patient> loadAllByIds(int[] patientId);

    @Query("SELECT * FROM patient WHERE email LIKE :email")
    Patient findByEmail(String email);

    @Insert
    void insertAll(Patient... Patient);

    @Insert
    void insert(Patient patient);

    @Delete
    void delete(Patient patient);

    @Query("DELETE FROM patient")
    void deleteAll();
}
