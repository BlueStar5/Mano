package com.example.mano;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class EntryArrayAdapter extends ArrayAdapter<Entry> {
    private LayoutInflater inflater;
    public EntryArrayAdapter(Context context, int resource, List<Entry> objects) {
        super(context, resource, objects);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view;
        if (convertView != null) {
            view = convertView;
        }
        else {
            view = inflater.inflate(R.layout.entry_layout, parent, false);
        }
        Entry entry = getItem(position);

        ((TextView) view.findViewById(R.id.entryTitleText)).setText(entry.getTitle());
        ((TextView) view.findViewById(R.id.entryTimeText))
                .setText(DateTimeTools.formatTime(getContext(), entry.getDateTime()));

        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ((CalendarActivity) getContext()).onEntryPress(entry.getId());
            }
        });
        return view;
    }
}
