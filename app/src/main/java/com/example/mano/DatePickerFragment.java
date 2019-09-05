package com.example.mano;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import java.time.LocalDate;
import java.util.Calendar;

public class DatePickerFragment extends PickerFragment
        implements DatePickerDialog.OnDateSetListener {
    private DatePickerDialog dialog;
    private LocalDate date = LocalDate.now();

    public DatePickerFragment() {
        super();

    }
    public DatePickerFragment(DateTimePicker dateTimePicker) {
        super(dateTimePicker);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        toggleHidden();
        if (dialog == null) {
            dialog = new DatePickerDialog(getActivity(), this, date.getYear(),
                    date.getMonthValue(), date.getDayOfMonth());
        }
        return dialog;
    }
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        date = LocalDate.of(year, month, dayOfMonth);
        updateTextView(boundTextView.getContext());
        Log.d("onDateSet", boundTextView.getText().toString());
        toggleHidden();
        dateTimePicker.onDateTimeSet();
    }
    @Override
    public void bindTextView(TextView view, FragmentManager fragmentManager, String tag) {
        super.bindTextView(view, fragmentManager, tag);
        updateTextView(view.getContext());
    }
    public void updateTextView(Context context) {
        boundTextView.setText(DateTimeTools.formatDate(context, date.getYear(), date.getMonthValue(),
                date.getDayOfMonth()));
    }
    public LocalDate getDate() {
        return date;
    }
}
