package com.example.mano;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

@Fts4
@Entity
public class Entry {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowid")
    private int id = 0;

    private LocalDateTime dateTime;

    private String body;
    private String title;

    public Entry(String title, String body, LocalDateTime dateTime) {
        this.title = title;
        this.body = body;
        this.dateTime = dateTime;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setBody(String body) {
        this.body = body;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
    public LocalDateTime getDateTime() {
        return this.dateTime;
    }
}
