package com.example.konsi.mobil_computing_app;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface DoctorDao {

    @Query("SELECT * FROM doctor")
    List<Doctor> getAll();


    @Query("SELECT * FROM doctor WHERE eMail LIKE :eMail")
    Doctor findByEMail(String eMail);

    @Query("SELECT * FROM doctor WHERE id LIKE :doctorId")
    Doctor findByID(String doctorId);

    @Insert
    void insertAll(Doctor... doctor);

    @Insert
    void insert(Doctor doctor);

    @Delete
    void delete(Doctor doctor);

    @Query("Delete from doctor")
    void deleteAll();
}
