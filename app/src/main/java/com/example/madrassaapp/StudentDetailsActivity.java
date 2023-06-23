package com.example.madrassaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentDetailsActivity extends AppCompatActivity {

    EditText surah, fverse, lverse, sabqi, manzil;
    Button update, delete;
    int studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        surah = findViewById(R.id.surah);
        fverse = findViewById(R.id.fAyat);
        lverse = findViewById(R.id.lAyat);
        sabqi = findViewById(R.id.sPara);
        manzil = findViewById(R.id.mPara);
        update = findViewById(R.id.btn1);
        delete = findViewById(R.id.btn2);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("student_id")) {
            studentId = intent.getIntExtra("student_id", -1);
            if (studentId != -1) {
                // Retrieve the student information from the database based on the student ID
                database databaseHelper = new database(this);
                student student = databaseHelper.getStudentById(studentId);
                if (student != null) {
                    // Display the student information in the activity
                    surah.setText(student.getSurah());
                    fverse.setText(String.valueOf(student.getFverse()));
                    lverse.setText(String.valueOf(student.getLverse()));
                    sabqi.setText(student.getSabqi());
                    manzil.setText(student.getManzil());

                    update.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            updateStudent();
                        }
                    });

                    delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            deleteStudent();
                        }
                    });
                }
            }
        }
    }

    private void updateStudent() {
        String surahText = surah.getText().toString();
        String fVerseText = fverse.getText().toString();
        String lVerseText = lverse.getText().toString();
        String sabqiText = sabqi.getText().toString();
        String manzilText = manzil.getText().toString();

        // Update the student information in the database
        database databaseHelper = new database(this);
        student student = databaseHelper.getStudentById(studentId);
        if (student != null) {
            student.setSurah(surahText);
            student.setFverse(Integer.parseInt(fVerseText));
            student.setLverse(Integer.parseInt(lVerseText));
            student.setSabqi(sabqiText);
            student.setManzil(manzilText);

            databaseHelper.updateStudent(student);
            Toast.makeText(this, "Student information updated", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteStudent() {
        // Delete the student from the database
        database databaseHelper = new database(this);
        student student = databaseHelper.getStudentById(studentId);
        if (student != null) {
            databaseHelper.deleteStudent(student);
            Toast.makeText(this, "Student deleted", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
