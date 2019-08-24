package com.example.mano;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ViewSwitcher;

import java.time.LocalDateTime;

public class EntryActivity extends AppCompatActivity {
    private static final String TAG = "EntryActivity";
    private DatePickerFragment datePicker;
    private TimePickerFragment timePicker;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        datePicker = new DatePickerFragment();
        timePicker = new TimePickerFragment();

        datePicker.bindTextView(findViewById(R.id.dateTextView), getSupportFragmentManager(),
                "datePicker");
        timePicker.bindTextView(findViewById(R.id.timeTextView), getSupportFragmentManager(),
                "timePicker");

        id = getIntent().getIntExtra("id", 0);
        ViewModelProviders.of(this).get(EntryViewModel.class)
                .get(id).observe(
                        this,
                        new Observer<Entry>() {

                            @Override
                            public void onChanged(Entry entry) {
                                if (entry != null) {
                                    EntryActivity activity = EntryActivity.this;
                                    String title = entry.getTitle();
                                    String body = entry.getBody();
                                    String date = DateTimeTools.formatDate(activity,
                                            entry.getDateTime());
                                    String time = DateTimeTools.formatTime(activity,
                                            entry.getDateTime());

                                    setViewText(R.id.titleTextView, title);
                                    setViewText(R.id.bodyTextView, body);
                                    setViewText(R.id.dateTextView, date);
                                    setViewText(R.id.timeTextView, time);

                                    setViewText(R.id.titleText, title);
                                    setViewText(R.id.bodyText, body);
                                    setViewText(R.id.dateText, date);
                                    setViewText(R.id.timeText, time);
                                }
                            }
                        }
        );

    }
    private String getViewText(int id) {
        return ViewTools.getViewText(this, id);
    }
    private void setViewText(int id, String text) {
        ViewTools.setViewText(this, id, text);
    }
    public void onViewSwitcherClick(View view) {
        ViewSwitcher vw = (ViewSwitcher) view;
        vw.showNext();
        vw.getCurrentView().requestFocus();
    }
    public void onSaveButtonClick(View view) {
       ViewModelProviders.of(this).get(EntryViewModel.class).update(id,
                getViewText(R.id.titleText), getViewText(R.id.bodyText),
                LocalDateTime.of(datePicker.getDate(), timePicker.getTime()));
    }
    public void onDeleteButtonClick(View view) {
        ViewModelProviders.of(this).get(EntryViewModel.class).delete(id);
        startActivity(new Intent(this, CalendarActivity.class));
    }
}
