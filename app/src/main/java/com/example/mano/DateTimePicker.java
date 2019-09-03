package com.example.mano;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.fragment.app.FragmentManager;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateTimePicker {
    private DatePickerFragment datePicker;
    private TimePickerFragment timePicker;
    private UpdateCallback callback;

    public DateTimePicker() {
        datePicker = new DatePickerFragment(this);
        timePicker = new TimePickerFragment(this);
    }
    public DateTimePicker(UpdateCallback callback) {
        this();
        this.callback = callback;
    }
    public void bindTextViews(TextView boundDateTextView, TextView boundTimeTextView,
                              FragmentManager fragmentManager) {
        datePicker.bindTextView(boundDateTextView, fragmentManager,
                "datePicker");
        timePicker.bindTextView(boundTimeTextView, fragmentManager,
                "timePicker");
    }
    public void onDateTimeSet() {
        if (callback != null) {
            callback.onUpdate();
        }
    }
    public LocalDateTime getDateTime() {
        return LocalDateTime.of(datePicker.getDate(), timePicker.getTime());
    }
}
