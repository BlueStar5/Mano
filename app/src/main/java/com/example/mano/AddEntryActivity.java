package com.example.mano;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddEntryActivity extends AppCompatActivity {

    private DatePickerFragment datePicker;
    private TimePickerFragment timePicker;
    private List<DateTimePicker> dateTimePickers;
    private Map<View, LocalDateTime> dateTimes;
    public static final String TAG = "AddEntryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);
        datePicker = new DatePickerFragment();
        timePicker = new TimePickerFragment();
        datePicker.bindTextView(findViewById(R.id.dateText), getSupportFragmentManager(),
                "datePicker");
        timePicker.bindTextView(findViewById(R.id.timeText), getSupportFragmentManager(),
                "timePicker");
        dateTimes = new HashMap<>();
        dateTimePickers = new ArrayList<>();
    }
    public void onAddReminderButtonPress(View view) {
        dateTimePickers.add(new DateTimePicker(this::updateReminderList));
        updateReminderList();

    }
    public void updateReminderList() {
        ReminderArrayAdapter adapter = new ReminderArrayAdapter(this,
                R.layout.reminder_layout, dateTimePickers);
        Log.d("AddEntryActivity", dateTimePickers.get(0).getDateTime().toString());

        ((ListView) findViewById(R.id.remindersListView)).setAdapter(adapter);


    }
    public void onCreatePress(View v) {
        String entryTitle = ViewTools.getViewText(this, R.id.entryTitleText);
        String entryBody = ViewTools.getViewText(this, R.id.bodyText);

        EntryViewModel entryViewModel = ViewModelProviders.of(this)
                .get(EntryViewModel.class);

        entryViewModel.insert(entryTitle, entryBody, LocalDateTime.of(datePicker.getDate(),
                timePicker.getTime()), new ParamUpdateCallback() {
            @Override
            public void onUpdate(Object... objects) {
                int id = ((Long) objects[0]).intValue();
                for (DateTimePicker dateTimePicker : dateTimePickers) {
                    //View reminderView = ((View) dateTimePicker.getDateTextView().getParent());
                    Log.d(TAG + " for", entryTitle + " " + entryBody + " " + dateTimePicker
                            .getDateTime().toString() + " " + id);
                    entryViewModel.insertReminder(entryTitle, entryBody, dateTimePicker.getDateTime(),
                            id);
                }
            }
        });

        startActivity(new Intent(this, CalendarActivity.class));
    }
}
