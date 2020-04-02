package com.haanhgs.recyclersearch;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.widget.TextView;

public class Repo {

    public static void highlightText(TextView textView, String string) {
        if (!TextUtils.isEmpty(string)){
            String textString = textView.getText().toString().toLowerCase();
            String query = string.toLowerCase();
            SpannableString spannableString = new SpannableString(textView.getText());
            int position = textString.indexOf(query);
            while (position >= 0){
                spannableString.setSpan(
                        new BackgroundColorSpan(Color.YELLOW),
                        position, position + query.length(), 0);
                position = textString.indexOf(query, position + query.length());
            }
            textView.setText(spannableString);
        }
    }

}
