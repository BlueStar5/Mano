package com.example.mano;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Entry.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class EntryDatabase extends RoomDatabase {
    public abstract EntryDao entryDao();
}
