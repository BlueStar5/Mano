package com.example.mano;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EntryDao {

    @Insert
    long insert(Entry entry);

    @Query("SELECT *, `rowid` FROM Entry")
    LiveData<List<Entry>> getAll();

    @Query("SELECT *, `rowid` FROM Entry WHERE rowid=:id")
    LiveData<Entry> get(int id);

    @Update
    void update(Entry entry);

    @Query("DELETE FROM Entry WHERE rowid=:id")
    void delete(int id);
}
