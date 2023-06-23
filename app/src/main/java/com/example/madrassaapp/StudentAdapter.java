package com.example.madrassaapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<student> studentList;

    public StudentAdapter(List<student> studentList) {
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        student student = studentList.get(position);
        holder.textId.setText(String.valueOf(student.getId()));
        holder.textViewName.setText(student.getName());
        holder.textViewClass.setText(student.getClassName());
        holder.textViewAge.setText(String.valueOf(student.getAge()));
        holder.textViewSabqi.setText(String.valueOf(student.getSabqi()));
        holder.textViewManzil.setText(String.valueOf(student.getManzil()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open the StudentDetailsActivity and pass the student ID
                Context context = view.getContext();
                Intent intent = new Intent(context, StudentDetailsActivity.class);
                intent.putExtra("student_id", student.getId());
                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return studentList.size();
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {

        TextView textId;
        TextView textViewName;
        TextView textViewClass;
        TextView textViewAge;
        TextView textViewSabqi;
        TextView textViewManzil;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            textId = itemView.findViewById(R.id.textId);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewClass = itemView.findViewById(R.id.textViewClass);
            textViewAge = itemView.findViewById(R.id.textViewAge);
            textViewSabqi = itemView.findViewById(R.id.textViewSabqi);
            textViewManzil = itemView.findViewById(R.id.textViewManzil);
        }
    }
}

