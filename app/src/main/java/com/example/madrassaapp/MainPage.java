package com.example.madrassaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends AppCompatActivity {

    private RecyclerView recyclerViewStudents;
    private StudentAdapter studentAdapter;
    private List<student> studentList;

    private database databaseHelper;
    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        btn1=findViewById(R.id.addBtn);
        recyclerViewStudents = findViewById(R.id.recyclerViewStudent);
        recyclerViewStudents.setLayoutManager(new LinearLayoutManager(this));

        databaseHelper = new database(this);
        studentList = databaseHelper.getAllStudents(); // Retrieve all students from the database

        studentAdapter = new StudentAdapter(studentList);
        recyclerViewStudents.setAdapter(studentAdapter);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainPage.this, addStudent.class);
                startActivity(intent);
            }
        });
    }
}