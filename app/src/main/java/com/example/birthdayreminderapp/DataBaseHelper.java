package com.example.birthdayreminderapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper
{

    public static final String COLUMN_DAY = "DAY";
    public static final String BIRTHDAYS_TABLE = "BIRTHDAYS_TABLE";
    public static final String COLUMN_YEAR = "YEAR";
    public static final String COLUMN_MONTH = "MONTH";
    public static final String COLUMN_NOTIFICATIONS_ON = "NOTIFICATIONS_ON";
    public static final String COLUMN_NAME = "COLUMN_NAME";

    public DataBaseHelper(@Nullable Context context)
    {
        super(context,"birthdays.db", null, 1);
    }

    // Creates new database
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String createTableStatement = "CREATE TABLE " + BIRTHDAYS_TABLE + " (" + COLUMN_NAME + " TEXT, " + COLUMN_YEAR + " INT, " + COLUMN_MONTH + " INT, " + COLUMN_DAY + " INT, " + COLUMN_NOTIFICATIONS_ON + " BOOL)";
        db.execSQL(createTableStatement);
    }

    // Allows changes to database design
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

    }

    // Adds birthday to database
    public boolean addOne(UserBirthdays userBirthdays)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, userBirthdays.getName());
        cv.put(COLUMN_YEAR, userBirthdays.getYear());
        cv.put(COLUMN_MONTH, userBirthdays.getMonth());
        cv.put(COLUMN_DAY, userBirthdays.getDay());
        cv.put(COLUMN_NOTIFICATIONS_ON, userBirthdays.isNotificationsOn());

        long insert = db.insert(BIRTHDAYS_TABLE, null, cv);
        return insert != -1;
    }

    // Grabs all saved birthdays
    public List<UserBirthdays> getAll()
    {
        List<UserBirthdays> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + BIRTHDAYS_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        // If more results, keep adding until no more
        if (cursor.moveToFirst())
        {
            do
            {
                String userName = cursor.getString(0);
                int userYear = cursor.getInt(1);
                int userMonth = cursor.getInt(2);
                int userDay = cursor.getInt(3);
                boolean notisOn = cursor.getInt(4) == 1 ? true:false;

                UserBirthdays newBirthday = new UserBirthdays(userName, userYear, userMonth, userDay, notisOn);
                returnList.add(newBirthday);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return returnList;
    }

    // Deletes birthday from database
    public boolean deleteOne(UserBirthdays userBirthday)
    {
        SQLiteDatabase db = getWritableDatabase();
        String queryString = "DELETE FROM " + BIRTHDAYS_TABLE + " WHERE " + COLUMN_NAME + " = " + userBirthday.getName();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst())
        {
            return true;
        }
        else return false;
    }
}
