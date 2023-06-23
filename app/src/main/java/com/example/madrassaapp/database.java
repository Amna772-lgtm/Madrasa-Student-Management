package com.example.madrassaapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class database extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "madrasa.db";

    private static final String TABLE_STUDENTS = "students";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_CLASS = "Class";
    private static final String COLUMN_SURAH_NUMBER = "surah_number";
    private static final String COLUMN_FIRST_VERSE = "first_verse";
    private static final String COLUMN_LAST_VERSE = "last_verse";
    private static final String COLUMN_SABQI_PARA = "sabqi_para";
    private static final String COLUMN_MANZIL_PARA = "manzil_para";

    // Constructor
    public database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the students table
        String CREATE_STUDENTS_TABLE = "CREATE TABLE " + TABLE_STUDENTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_AGE + " INTEGER,"
                + COLUMN_CLASS + " TEXT,"
                + COLUMN_SURAH_NUMBER + " INTEGER,"
                + COLUMN_FIRST_VERSE + " INTEGER,"
                + COLUMN_LAST_VERSE + " INTEGER,"
                + COLUMN_SABQI_PARA + " INTEGER,"
                + COLUMN_MANZIL_PARA + " INTEGER"
                + ")";
        db.execSQL(CREATE_STUDENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);

        // Create the table again
        onCreate(db);
    }

    public long insertStudent(student student) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, student.getName());
        values.put(COLUMN_AGE, student.getAge());
        values.put(COLUMN_CLASS, student.getClassName());
        values.put(COLUMN_SURAH_NUMBER, student.getSurah());
        values.put(COLUMN_FIRST_VERSE, student.getFverse());
        values.put(COLUMN_LAST_VERSE, student.getLverse());
        values.put(COLUMN_SABQI_PARA, student.getSabqi());
        values.put(COLUMN_MANZIL_PARA, student.getManzil());

        long insertedRowId = db.insert(TABLE_STUDENTS, null, values);
        db.close();

        return insertedRowId;
    }

    public List<student> getAllStudents() {
        List<student> studentList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STUDENTS, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                @SuppressLint("Range") int age = cursor.getInt(cursor.getColumnIndex(COLUMN_AGE));
                @SuppressLint("Range") String className = cursor.getString(cursor.getColumnIndex(COLUMN_CLASS));
                @SuppressLint("Range") String surah = cursor.getString(cursor.getColumnIndex(COLUMN_SURAH_NUMBER));
                @SuppressLint("Range") int fverse = cursor.getInt(cursor.getColumnIndex(COLUMN_FIRST_VERSE));
                @SuppressLint("Range") int lverse = cursor.getInt(cursor.getColumnIndex(COLUMN_LAST_VERSE));
                @SuppressLint("Range") String sabqi = cursor.getString(cursor.getColumnIndex(COLUMN_SABQI_PARA));
                @SuppressLint("Range") String manzil = cursor.getString(cursor.getColumnIndex(COLUMN_MANZIL_PARA));

                student student = new student(name, age, className, surah, fverse, lverse, sabqi, manzil);
                student.setId(id);
                studentList.add(student);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return studentList;
    }

    public student getStudentById(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_STUDENTS, null, COLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);
        student student = null;

        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            @SuppressLint("Range") int age = cursor.getInt(cursor.getColumnIndex(COLUMN_AGE));
            @SuppressLint("Range") String className = cursor.getString(cursor.getColumnIndex(COLUMN_CLASS));
            @SuppressLint("Range") String surah = cursor.getString(cursor.getColumnIndex(COLUMN_SURAH_NUMBER));
            @SuppressLint("Range") int fverse = cursor.getInt(cursor.getColumnIndex(COLUMN_FIRST_VERSE));
            @SuppressLint("Range") int lverse = cursor.getInt(cursor.getColumnIndex(COLUMN_LAST_VERSE));
            @SuppressLint("Range") String sabqi = cursor.getString(cursor.getColumnIndex(COLUMN_SABQI_PARA));
            @SuppressLint("Range") String manzil = cursor.getString(cursor.getColumnIndex(COLUMN_MANZIL_PARA));

            student = new student(name, age, className, surah, fverse, lverse, sabqi, manzil);
            student.setId(id);
        }

        if (cursor != null) {
            cursor.close();
        }

        db.close();

        return student;
    }

    public void updateStudent(student student) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, student.getName());
        values.put(COLUMN_AGE, student.getAge());
        values.put(COLUMN_CLASS, student.getClassName());
        values.put(COLUMN_SURAH_NUMBER, student.getSurah());
        values.put(COLUMN_FIRST_VERSE, student.getFverse());
        values.put(COLUMN_LAST_VERSE, student.getLverse());
        values.put(COLUMN_SABQI_PARA, student.getSabqi());
        values.put(COLUMN_MANZIL_PARA, student.getManzil());

        db.update(TABLE_STUDENTS, values, COLUMN_ID + " = ?", new String[]{String.valueOf(student.getId())});
        db.close();
    }

    public void deleteStudent(student student) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_STUDENTS, COLUMN_ID + " = ?", new String[]{String.valueOf(student.getId())});
        db.close();
    }
}
