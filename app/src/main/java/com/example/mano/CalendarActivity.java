package com.example.mano;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ListView;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    private LocalDateTime date;
    public static final String TAG = "CalendarActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        date = LocalDateTime.now();
        ((CalendarView)findViewById(R.id.calendarView)).setOnDateChangeListener(
                new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(CalendarView calendarView, int year, int month,
                                                    int dayOfMonth) {
                        date = date.withYear(year);
                        date = date.withMonth(month + 1);
                        date = date.withDayOfMonth(dayOfMonth);
                        updateEntryList();
                        Log.d(TAG, "Month: " + month);
                    }
                }
        );
    }
    public void onAddEntryPress(View button) {
        startActivity(new Intent(this, AddEntryActivity.class));
    }
    public void updateEntryList() {
        ViewModelProviders.of(this)
                .get(EntryViewModel.class).getAll().observe(this, new Observer<List<Entry>>() {
            @Override
            public void onChanged(List<Entry> entries) {
                List<Entry> entriesOnDate = new ArrayList<>();
                //Converters.fromTimestamp(((DatePicker)findViewById(R.id.datePicker)).getDate());

                for (Entry entry : entries) {
                    if (DateTimeTools.sameDate(entry.getDateTime(), date)) {
                        entriesOnDate.add(entry);
                    }
                }
                EntryArrayAdapter adapter = new EntryArrayAdapter(CalendarActivity.this,
                        R.layout.entry_layout, entriesOnDate);
                ((ListView) findViewById(R.id.listView)).setAdapter(adapter);
            }
        });
    }
    public void onEntryPress(int entryId) {
        Intent intent = new Intent(this, EntryActivity.class);
        intent.putExtra("id", entryId);

        startActivity(intent);
    }
}
