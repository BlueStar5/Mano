package com.example.mano;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Fts4;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Entry.class,
        parentColumns = "rowid",
        childColumns = "entryId",
        onDelete = CASCADE))

public class Reminder {
    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private LocalDateTime dateTime;
    private String title;
    private String body;
    private int entryId;

    public Reminder(String title, String body, LocalDateTime dateTime, int entryId) {
        this.title = title;
        this.body = body;
        this.dateTime = dateTime;
        this.entryId = entryId;
    }
    public int getEntryId() {
        return entryId;
    }

    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
