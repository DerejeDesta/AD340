package com.example.myapplication;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    private static final String TAG = "Dereje says...";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Log.i(TAG, this.getLifecycle()
                .getCurrentState()
                .toString());
        Intent intent =getIntent();
        String textName = intent.getStringExtra(MainActivity.EXTRA_TEXT_NAME);
        String textInterest = intent.getStringExtra(MainActivity.EXTRA_TEXT_INTEREST );
        TextView textViewName = (TextView) findViewById(R.id.textViewName);
        TextView textViewInterest = (TextView) findViewById(R.id.textViewInterest);
        textViewName.setText(textName);
        textViewInterest.setText(textInterest);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "started");
        Log.i(TAG, this.getLifecycle().getCurrentState().toString());
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "resumed");
        Log.i(TAG, this.getLifecycle().getCurrentState().toString());
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "stopped");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "destroyed");

    }
}
