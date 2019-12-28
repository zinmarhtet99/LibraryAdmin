package com.example.libraryadmin.Utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import me.myatminsoe.mdetect.MDetect;

import com.example.libraryadmin.BorrowBookActivity;
import com.example.libraryadmin.MainApplication;
import com.example.libraryadmin.model.Author;
import com.example.libraryadmin.model.Book1;

import java.util.List;


public class FontEmbedder {

    private static Typeface typeface;

    public static void init(Typeface typeface){
        FontEmbedder.typeface = typeface;
    }

    public static String force(TextView textView, String text){
        textView.setText(Moulder.mercyOnZgUser(text));
        textView.setTypeface(typeface);
        return text;
    }
    public static String force(String text){
        return Moulder.mercyOnZgUser(text);
    }

    public static void forceTitle(TextView textView, String text) {
        textView.setText(Moulder.mercyOnZgUser(text));
        if(MDetect.INSTANCE.isUnicode()) {
            textView.setTypeface(MainApplication.typefaceManager.getMyanmarSagar());
        } else {
            textView.setTypeface(typeface);
        }
    }

    public static void force(Button button, String text){
        button.setText(Moulder.mercyOnZgUser(text));
        button.setTypeface(typeface);
    }

    public static void force(Button button){

        force(button, button.getText().toString());
    }
}
