package com.aki.jokeviewer;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hanks.htextview.HTextView;
import com.hanks.htextview.HTextViewType;

public class JokeDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);
        String joke = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        HTextView hTextView = (HTextView) findViewById(R.id.text);
        hTextView.setAnimateType(HTextViewType.TYPER);
        hTextView.setBackgroundColor(Color.BLACK);
        hTextView.setTextColor(Color.WHITE);
        hTextView.animateText(joke);
    }
}
