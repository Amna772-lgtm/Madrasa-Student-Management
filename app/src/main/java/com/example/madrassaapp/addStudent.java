package com.example.madrassaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addStudent extends AppCompatActivity {

    EditText Name, Age, studentClass, Surah, fverse, lverse, Sabqi, Manzil;
    Button savebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        Name = findViewById(R.id.name);
        Age = findViewById(R.id.age);
        studentClass = findViewById(R.id.Class);
        Surah = findViewById(R.id.surah);
        fverse = findViewById(R.id.fAyat);
        lverse = findViewById(R.id.lAyat);
        Sabqi = findViewById(R.id.sPara);
        Manzil = findViewById(R.id.mPara);

        savebtn = findViewById(R.id.btn);

        final database databaseHelper = new database(this);

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveStudent(databaseHelper);
            }
        });
    }

    private void saveStudent(database databaseHelper) {
        String name = Name.getText().toString();
        int age = Integer.parseInt(Age.getText().toString());
        String studentClassName = studentClass.getText().toString();
        String surah = Surah.getText().toString();
        int Fvere = Integer.parseInt(fverse.getText().toString());
        int Lverse = Integer.parseInt(lverse.getText().toString());
        String sabqi = Sabqi.getText().toString();
        String manzil = Manzil.getText().toString();

        student student = new student(name, age, studentClassName, surah, Fvere, Lverse, sabqi, manzil);

        // Save the student object to the database
        long studentId = databaseHelper.insertStudent(student);

        if (studentId != -1) {
            Toast.makeText(this, "Student saved successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to save student", Toast.LENGTH_SHORT).show();
        }
    }
}
