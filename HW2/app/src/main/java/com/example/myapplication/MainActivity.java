package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_TEXT_NAME="com.example.myapplication.EXTRA_TEXT_NAME";
    public static final String EXTRA_TEXT_INTEREST="com.example.myapplication.EXTRA_TEXT_INTEREST";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button =(Button) findViewById(R.id.buttonSend);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              openActivity2();
            }
        });
    }
    public void openActivity2(){
        EditText editTextName =(EditText) findViewById(R.id.editTextName);
        String textName =editTextName.getText().toString();

        EditText editTextInterest =(EditText) findViewById(R.id.editTextInterest);
        String textInterest =editTextInterest.getText().toString();

        Intent intent = new Intent(this,Activity2.class);
        intent.putExtra(EXTRA_TEXT_NAME,textName);
        intent.putExtra(EXTRA_TEXT_INTEREST,textInterest);

        startActivity(intent);
    }
}
