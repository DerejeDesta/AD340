package com.example.myfirstapp;
import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout ll = new LinearLayout(this);
        TextView tx1= new TextView(this);
        TextView tx2= new TextView(this);
        tx1.setText("Name: Dereje Desta");
        ll.addView(tx1);
        tx2.setText("     " +"Technology Interest: Java developer");
        ll.addView(tx2);
        setContentView(ll);


    }




}
