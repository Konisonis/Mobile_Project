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

    @Query("SELECT * FROM listdoctor WHERE ID IN (:userIds)")
    List<ListDoctor> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM listdoctor WHERE name LIKE :serchName")
    ListDoctor findByName(String serchName);

    @Query("SELECT * FROM listdoctor WHERE name LIKE :searchId")
    ListDoctor findByID(String searchId);

    @Insert
    void insertAll(ListDoctor... listDoctors);

    @Delete
    void delete(ListDoctor listDoctors);
}
