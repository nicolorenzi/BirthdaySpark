package com.example.birthdayreminderapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.List;

public class BirthdaysRecyclerViewAdapter extends RecyclerView.Adapter<BirthdaysRecyclerViewAdapter.MyViewHolder>
{
    private Context context;
    private List<UserBirthdays> birthdayList;
    public BirthdaysRecyclerViewAdapter (Context context, List<UserBirthdays> birthdayList)
    {
        this.context = context;
        this.birthdayList= birthdayList;
    }

    // Assigns values to each row (each saved birthday)
    @NonNull
    @Override
    public BirthdaysRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        return new BirthdaysRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BirthdaysRecyclerViewAdapter.MyViewHolder holder, int position)
    {
        holder.nameAndAge.setText(birthdayList.get(position).getName() + " turns " + (calculateAge(birthdayList.get(position).getYear(), birthdayList.get(position).getMonth(), birthdayList.get(position).getDay()) + 1) + " in: ");
    }

    // Total # of birthdays
    @Override
    public int getItemCount()
    {
        return birthdayList.size();
    }

    // Grabs all views from Recycler View
    public static class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView nameAndAge;
     //   ProgressBar timer;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            nameAndAge = itemView.findViewById(R.id.nameAndAge);
      //      timer = itemView.findViewById(R.id.visualTimer);
        }
    }

    public int calculateAge(int year, int month, int day)
    {
        // Gets current date
        Calendar today = Calendar.getInstance();
        int curYear = today.get(Calendar.YEAR);
        int curMonth = today.get(Calendar.MONTH) + 1;
        int curDay = today.get(Calendar.DAY_OF_MONTH);

        int age = curYear - year;

        // Subtracts 1 if birthday has not happened yet this year
        if (curMonth < month || ((curMonth == month) && (curDay < day))) return age - 1;
        return age;
    }
}
