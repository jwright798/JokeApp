package com.udacity.jeremywright.jokeandroidlib;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        TextView jokeText = (TextView)findViewById(R.id.jokeTextView);

        //get the joke from the main activity

        //get the intent
        Intent jokeIntent = getIntent();

        //get the joke from the intent
        String joke= jokeIntent.getStringExtra("joke");

        if (joke != null && !joke.isEmpty()){
            jokeText.setText(joke);
        }
    }
}
