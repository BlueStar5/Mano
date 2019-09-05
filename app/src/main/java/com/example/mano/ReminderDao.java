package com.example.mano;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ReminderDao {

    @Insert
    void insert(Reminder reminder);

    @Query("DELETE FROM Reminder WHERE rowid=:id")
    void delete(int id);

    @Query("SELECT * FROM Reminder WHERE entryId=:entryId")
    LiveData<List<Reminder>> getRemindersByEntry(int entryId);
}
