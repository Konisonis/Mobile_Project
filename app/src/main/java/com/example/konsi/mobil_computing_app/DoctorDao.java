package com.example.konsi.mobil_computing_app;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface DoctorDao {

    @Query("SELECT * FROM listdoctor")
    List<ListDoctor> getAll();

    @Query("SELECT * FROM listdoctor WHERE ID IN (:doctorId)")
    List<ListDoctor> loadAllByIds(int[] doctorId);

    @Query("SELECT * FROM listdoctor WHERE name LIKE :doctorName")
    ListDoctor findByName(String doctorName);

    @Query("SELECT * FROM listdoctor WHERE name LIKE :doctorId")
    ListDoctor findByID(String doctorId);

    @Insert
    void insertAll(ListDoctor... listDoctors);

    @Insert
    void insert(ListDoctor doctor);

    @Delete
    void delete(ListDoctor listDoctors);
}
