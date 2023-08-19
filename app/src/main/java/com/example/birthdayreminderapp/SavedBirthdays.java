package com.example.birthdayreminderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SavedBirthdays extends AppCompatActivity
{
    private RecyclerView bdayList;
    private Button goHome;
    private TextView nameAndAge;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_birthdays);

        bdayList = findViewById(R.id.rView);

        // Gets data from all birthdays
        DataBaseHelper dataBaseHelper = new DataBaseHelper(SavedBirthdays.this);
        List<UserBirthdays> allBirthdays = dataBaseHelper.getAll();

        // Adapter goes here
        BirthdaysRecyclerViewAdapter birthdays = new BirthdaysRecyclerViewAdapter(SavedBirthdays.this, allBirthdays);
        bdayList.setAdapter(birthdays);
        bdayList.setLayoutManager(new LinearLayoutManager(SavedBirthdays.this));
        //
     //   nameAndAge = findViewById(R.id.nameAndAge);

        goHome = findViewById(R.id.returnButton);
        goHome.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Returns to the home screen
                Intent main = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main);
            }
        });
    }
}
