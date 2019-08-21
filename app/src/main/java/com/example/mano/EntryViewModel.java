package com.example.mano;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import java.time.LocalDateTime;
import java.util.List;

public class EntryViewModel extends AndroidViewModel {
    private EntryDatabase database;
    private LiveData<List<Entry>> entries;

    public EntryViewModel(@NonNull Application application) {
        super(application);
        database = Room.databaseBuilder(application, EntryDatabase.class, "entryDB")
                .build();
        entries = database.entryDao().getAll();
    }
    public void insert(final String title, final String body, final LocalDateTime dateTime) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                database.entryDao().insert(new Entry(title, body,
                        dateTime));
                return null;
            }
        }.execute();
    }
    public void update(final int id, final String title, final String body,
                       final LocalDateTime dateTime) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                Entry entry = new Entry(title, body, dateTime);
                entry.setId(id);
                database.entryDao().update(entry);
                return null;
            }
        }.execute();
    }
    public LiveData<Entry> get(int id) {
        return database.entryDao().get(id);
    }
    public LiveData<List<Entry>> getAll() {
        return entries;
    }
}
