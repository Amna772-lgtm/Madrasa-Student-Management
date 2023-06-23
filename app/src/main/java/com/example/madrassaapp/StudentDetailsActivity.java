/*package com.example.madrassaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class StudentDetailsActivity extends AppCompatActivity {

    private TextView txtSabqi;
    private TextView txtManzil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        txtSabqi = findViewById(R.id.txtSabqi);
        txtManzil = findViewById(R.id.txtManzil);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("student_id")) {
            int studentId = intent.getIntExtra("student_id", -1);
            if (studentId != -1) {
                // Retrieve the student information from the database based on the student ID
                database databaseHelper = new database(this);
                student student = databaseHelper.getStudentById(studentId);
                if (student != null) {
                    // Display the student information in the activity
                    // Use the TextViews or other views to show the sabqi and manzil
                    txtSabqi.setText(student.getSabqi());
                    txtManzil.setText(student.getManzil());
                    // ...
                }
            }
        }
    }
}*/