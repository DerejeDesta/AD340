package com.example.student;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText school;
    EditText SID;
    EditText grade;
    TextView textView;
    private Context context =this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        button= findViewById(R.id.button);
        school = (EditText) findViewById(R.id.School);
        school.getText().toString();
        SID =(EditText) findViewById(R.id.SID);

        grade=(EditText) findViewById(R.id.grade);
        textView = (TextView)findViewById(R.id.textViewResult);



        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(school.getText().toString().isEmpty() || SID.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Enter some values", Toast.LENGTH_SHORT).show();
                }else{
                    Student newStudent = new Student(Integer.parseInt(SID.getText().toString()), school.getText().toString());

                    textView.setText(newStudent.Status( Double.parseDouble(grade.getText().toString()),"AD340"  ) );

                }

            }
        });
    }
}
