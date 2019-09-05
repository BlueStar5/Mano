package com.example.mano;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

public class PickerFragment extends DialogFragment {

    private boolean hidden = true;
    protected TextView boundTextView;
    protected DateTimePicker dateTimePicker;

    public PickerFragment() {

    }
    public PickerFragment(DateTimePicker dateTimePicker) {
        this.dateTimePicker = dateTimePicker;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        hidden = true;
        super.onCancel(dialog);
    }
    public boolean hidden() {
        return hidden;
    }
    public void toggleHidden() {
        hidden = !hidden;
    }
    public void requestOpen(FragmentManager fragmentManager, String tag) {
        if (hidden) {
            show(fragmentManager, tag);
        }
    }
    public void bindTextView(TextView view, FragmentManager fragmentManager, String tag) {
        boundTextView = view;
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    requestOpen(fragmentManager, tag);
                    return true;
                }
                return false;
            }
        });
    }
    public TextView getBoundTextView() {
        return boundTextView;
    }
}
