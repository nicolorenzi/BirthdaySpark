package com.example.birthdayreminderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.animation.ObjectAnimator;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.DatePicker;
import java.util.Calendar;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class AddBirthday extends AppCompatActivity
{
    private Button goHome;
    private EditText userName;
    private DatePickerDialog datePicker;
    private Button dateButton, confirm;
    private SwitchCompat notificationsOn;
    private int month, day, year;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_birthday);

        userName = findViewById(R.id.userInput);
        notificationsOn = findViewById(R.id.notifications);

        // Stores data once confirm button is clicked
        confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                UserBirthdays newUser = new UserBirthdays(userName.getText().toString(), year, month, day, notificationsOn.isChecked());


                // Adds birthday to database
                DataBaseHelper database = new DataBaseHelper(AddBirthday.this);
                database.addOne(newUser);


                // Returns home after adding birthday
                Intent main = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main);
            }
        });

        // Creates date picker
        initDatePicker();
        dateButton = findViewById(R.id.userBirthday);
        dateButton.setText(getTodayDate());

        // Allows arrow at the top to return to home screen
        goHome = findViewById(R.id.returnButton);
        goHome.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent main = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main);
            }
        });
    }

    // Defaults picker to current date
    private String getTodayDate()
    {
        Calendar today = Calendar.getInstance();
        int curYear = today.get(Calendar.YEAR);
        int curMonth = today.get(Calendar.MONTH);
        int curDay = today.get(Calendar.DAY_OF_MONTH);

        curMonth = curMonth + 1;
        return makeDateString(curDay, curMonth, curYear);
    }

    // Initializes date picker
    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int uYear, int uMonth, int uDay)
            {
                uMonth = uMonth + 1;
                String date = makeDateString(uDay, uMonth, uYear);
                dateButton.setText(date);
                year = uYear;
                month = uMonth;
                day = uDay;
            }
        };

        Calendar today = Calendar.getInstance();
        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH);
        int day = today.get(Calendar.DAY_OF_MONTH);

        datePicker = new DatePickerDialog(this, dateSetListener, year, month, day);

    }

    // Puts picker in middle endian format
    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    // Gets abbreviation of month
    private String getMonthFormat(int month)
    {
        if (month == 1) return "JAN";
        if (month == 2) return "FEB";
        if (month == 3) return "MAR";
        if (month == 4) return "APR";
        if (month == 5) return "MAY";
        if (month == 6) return "JUN";
        if (month == 7) return "JUL";
        if (month == 8) return "AUG";
        if (month == 9) return "SEP";
        if (month == 10) return "OCT";
        if (month == 11) return "NOV";
        if (month == 12) return "DEC";

        // Should never reach here
        return "";
    }

    // Shows date picker
    public void openDatePicker(View view)
    {
        datePicker.show();
    }
}
