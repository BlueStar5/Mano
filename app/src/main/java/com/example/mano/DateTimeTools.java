package com.example.mano;

import android.content.Context;

import androidx.fragment.app.DialogFragment;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateTimeTools {
    public static String formatTime(Context context, int hourOfDay, int minute) {
        return context.getString(R.string.add_entry_time_text,
                hourOfDay == 0 ? 12 : hourOfDay > 12 ? hourOfDay - 12 : hourOfDay,
                (minute < 10 ? "0" : "") + minute, hourOfDay >= 12 ? "PM" : "AM");
    }
    public static String formatDate(Context context, int year, int month, int dayOfMonth) {
        return context.getString(R.string.add_entry_date_text, month, dayOfMonth, year);
    }
    public static String formatTime(Context context, LocalDateTime dateTime) {
        return formatTime(context, dateTime.getHour(), dateTime.getMinute());
    }
    public static String formatDate(Context context, LocalDateTime dateTime) {
        return formatDate(context, dateTime.getYear(), dateTime.getMonthValue(),
                dateTime.getDayOfMonth());
    }
    public static boolean sameDate(LocalDateTime dt1, LocalDateTime dt2) {
        return dt1.toLocalDate().equals(dt2.toLocalDate());
    }
}