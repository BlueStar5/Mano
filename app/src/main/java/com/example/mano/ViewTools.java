package com.example.mano;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ViewTools {
    public static String getViewText(Activity activity, int id) {
        return ((TextView) activity.findViewById(id)).getText().toString();
    }
    public static void setViewText(Activity activity, int id, String text) {
        ((TextView) activity.findViewById(id)).setText(text);
    }
}
