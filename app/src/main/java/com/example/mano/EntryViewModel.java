package com.example.mano;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import java.time.LocalDateTime;
import java.util.List;

public class EntryViewModel extends AndroidViewModel {
    private EntryDatabase database;
    private LiveData<List<Entry>> entries;
    private long id;

    public EntryViewModel(@NonNull Application application) {
        super(application);
        database = Room.databaseBuilder(application, EntryDatabase.class, "entryDB")
                .build();
        entries = database.entryDao().getAll();
    }
    public long insert(final String title, final String body, final LocalDateTime dateTime) {
        new AsyncTask<Long, Void, Long>() {

            @Override
            protected Long doInBackground(Long... longs) {
                return database.entryDao().insert(new Entry(title, body,
                        dateTime));
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                Log.d("onPostExecute", "" + aLong);
                id = aLong;
            }
        }.execute();
        Log.d("insertEntry", "" + id);
        return id;
    }
    public void insert(final String title, final String body, final LocalDateTime dateTime,
                       ParamUpdateCallback onPostExecute) {
        new InsertEntryTask(database, title, body, dateTime, onPostExecute).execute();
    }
    public void insertReminder(final String title, final String body, final LocalDateTime dateTime,
                               final int entryId) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                Log.d("insertReminder", title + " " + body + " " + dateTime.toString() +
                        " " + entryId);
                database.reminderDao().insert(new Reminder(title, body, dateTime, entryId));
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
    public void delete(final int id) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                database.entryDao().delete(id);
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
    public LiveData<List<Reminder>> getRemindersByEntry(int entryId) {
        return database.reminderDao().getRemindersByEntry(entryId);
    }

    private static class InsertEntryTask extends AsyncTask<Long, Void, Long> {

        private EntryDatabase database;
        private String title;
        private String body;
        private LocalDateTime dateTime;
        private ParamUpdateCallback onPostExecute;
        public InsertEntryTask(EntryDatabase database, String title, String body,
                                    LocalDateTime dateTime, ParamUpdateCallback onPostExecute) {
            this.database = database;
            this.title = title;
            this.body = body;
            this.dateTime = dateTime;
            this.onPostExecute = onPostExecute;
        }
        @Override
        protected Long doInBackground(Long... longs) {
            return database.entryDao().insert(new Entry(title, body,
                    dateTime));
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            if (onPostExecute != null) {
                onPostExecute.onUpdate(aLong);
            }
        }
    }
}
