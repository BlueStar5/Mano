package com.example.mano;

import androidx.fragment.app.DialogFragment;

import android.app.Application;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.TimePicker;

import java.time.LocalTime;
import java.util.Calendar;

public class TimePickerFragment extends PickerFragment
        implements TimePickerDialog.OnTimeSetListener {
    private TimePickerDialog dialog;
    private LocalTime time;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        toggleHidden();
        if (dialog == null) {
            time = LocalTime.now();
            dialog = new TimePickerDialog(getActivity(), this, time.getHour(),
                    time.getMinute(), DateFormat.is24HourFormat(getActivity()));
        }
        return dialog;
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        boundTextView.setText(DateTimeTools.formatTime(getContext(), hourOfDay, minute));
        time = LocalTime.of(hourOfDay, minute);
        toggleHidden();
    }
    public LocalTime getTime() {
        return time;
    }
}
