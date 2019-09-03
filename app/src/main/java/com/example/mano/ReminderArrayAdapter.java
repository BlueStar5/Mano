package com.example.mano;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.util.List;

public class ReminderArrayAdapter extends ArrayAdapter<DateTimePicker> {

    private LayoutInflater inflater;
    private FragmentManager fragmentManager;

    public ReminderArrayAdapter(@NonNull Context context, int resource,
                                List<DateTimePicker> objects) {
        super(context, resource, objects);
        inflater = (LayoutInflater) context.getSystemService(Service
                .LAYOUT_INFLATER_SERVICE);
        fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view;
        if (convertView != null) {
            view = convertView;
        } else {
            view = inflater.inflate(R.layout.reminder_layout, parent,
                    false);
        }
        getItem(position).bindTextViews(view.findViewById(R.id.dateText),
                view.findViewById(R.id.timeText), fragmentManager);
        return view;
    }
}