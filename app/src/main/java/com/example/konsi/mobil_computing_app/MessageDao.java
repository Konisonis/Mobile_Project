package com.example.konsi.mobil_computing_app;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MessageDao {

    @Query("SELECT * FROM message")
    List<Message> getAll();

    @Query("SELECT * FROM message WHERE id LIKE :messageId")
    Message findByID(String messageId);

    @Insert
    void insertAll(Message... message);

    @Insert
    void insert(Message message);

    @Delete
    void delete(Message message);

    @Query("Delete from message")
    void deleteAll();
}
