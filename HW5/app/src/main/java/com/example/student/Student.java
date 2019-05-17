package com.example.student;

import android.content.Context;
import android.content.res.Resources;
import android.renderscript.Sampler;

public class Student {

    private int SID;
    private String school;

    public Student(int SID, String school) {
        this.SID = SID;
        if (school == "") {
            this.school = "North Seattle College";
        } else {
            this.school = school;
        }
    }
    public int getSID() {
        return SID;
    }

    public String getSchool() {
        return school;
    }

    public void setSID(int SID) {
        this.SID = SID;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String Status(double grade, String course){
        if(grade>4.0 || grade<0.0){
            return "Invalid grade input, you should enter value between 0.0 and 4.0";
        } else if(grade>=3.5){
            return this.school +" ,For SID: "+ this.SID +" ,Passed "+ course +" with good grade!";
        }else if(grade>=2.5){
            return this.school+" ,For SID: "+ this.SID +" ,Passed in "+course;

        }else{
            return this.school+ " ,For SID: "+ this.SID +" ,Not passed in " +course;
        }
    }
    public Student(Context context) {
        this.SID = context.getResources().getInteger(R.integer.default_ID);
        this.school= context.getString(R.string.default_School);


    }

}
