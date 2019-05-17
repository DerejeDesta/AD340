package com.example.student;
import android.content.Context;
import android.content.res.Resources;

import com.google.common.truth.Truth;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

public class StudentTest {
    private Student testStudent;
    @Mock
    Context mockContext;

    @Mock
    Resources mockResources;
    private Student defaultStudent;

    @Before
    public void setUp() {
        this.testStudent =
                new Student(12345, "NSC");
        MockitoAnnotations.initMocks(this);

        when(mockContext.getString(R.string.default_School))
                .thenReturn("Seattle Colleges");
        when(mockContext.getResources()).thenReturn(mockResources);
        when(mockResources.getInteger(R.integer.default_ID))
                .thenReturn((int) 12345);

        this.defaultStudent = new Student(mockContext);

    }
    @Test
    public void Student_Status_ReturnsCorrectValues() {
        String testStudentStatus1 = testStudent.Status( 4.0,"AD340" );
        assertThat(testStudentStatus1).isEqualTo( testStudent.getSchool()+" ,For SID: "+ testStudent.getSID()+" ,Passed AD340 with good grade!");

        String testStudentStatus2 = testStudent.Status( 1.0,"AD340" );
        assertThat(testStudentStatus2).isEqualTo(testStudent.getSchool()+" ,For SID: "+ testStudent.getSID()+" ,Not passed in AD340");

        String testStudentStatus3 = testStudent.Status( 2.7,"AD340" );
        assertThat(testStudentStatus3).isEqualTo(testStudent.getSchool()+" ,For SID: "+ testStudent.getSID()+" ,Passed in AD340");

        String testStudentStatus4 = testStudent.Status( 11.0,"AD340" );
        assertThat(testStudentStatus4).isEqualTo("Invalid grade input, you should enter value between 0.0 and 4.0");


    }

    @Test
    public void Student_DefaultStudent_ReturnsCorrectValues() {

        String defaultStudentSIDStatus = defaultStudent.Status (3.5,
                "AD340");
        //"Seattle Colleges" is default String value for School
        //12345 is default SID integer value
        assertThat(defaultStudentSIDStatus).isEqualTo("Seattle Colleges ,For SID: 12345 ,Passed AD340 with good grade!");
    }

}
