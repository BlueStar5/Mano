package com.example.mano;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.app.Application;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.time.LocalTime;
import java.util.Calendar;

public class TimePickerFragment extends PickerFragment
        implements TimePickerDialog.OnTimeSetListener {
    private TimePickerDialog dialog;
    private LocalTime time = LocalTime.now();

    public TimePickerFragment() {

    }

    public TimePickerFragment(DateTimePicker dateTimePicker) {
        super(dateTimePicker);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        toggleHidden();
        if (dialog == null) {
            dialog = new TimePickerDialog(getActivity(), this, time.getHour(),
                    time.getMinute(), DateFormat.is24HourFormat(getActivity()));
        }
        return dialog;
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        time = LocalTime.of(hourOfDay, minute);
        updateTextView(view.getContext());
        toggleHidden();
        dateTimePicker.onDateTimeSet();
    }
    public void updateTextView(Context context) {
        boundTextView.setText(DateTimeTools.formatTime(context, time.getHour(),
                time.getMinute()));
    }
    @Override
    public void bindTextView(TextView view, FragmentManager fragmentManager, String tag) {
        super.bindTextView(view, fragmentManager, tag);
        updateTextView(view.getContext());
    }
    public LocalTime getTime() {
        return time;
    }
}
