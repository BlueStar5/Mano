package com.example.mano;

import android.app.DatePickerDialog;
import android.app.Dialog;
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
        boundTextView.setText(DateTimeTools.formatDate(getContext(), year, month,
                dayOfMonth));
        date = LocalDate.of(year, month, dayOfMonth);
        toggleHidden();
    }
    public LocalDate getDate() {
        return date;
    }

}
