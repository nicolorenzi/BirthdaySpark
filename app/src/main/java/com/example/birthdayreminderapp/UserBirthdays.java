package com.example.birthdayreminderapp;

public class UserBirthdays
{
    private String name;
    private int year;
    private int month;
    private int day;
    private boolean notificationsOn;

    public UserBirthdays (String name, int year, int month, int day, boolean notificationsOn)
    {
        this.name = name;
        this.year = year;
        this.month = month;
        this.day = day;
        this.notificationsOn = notificationsOn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public boolean isNotificationsOn() {
        return notificationsOn;
    }

    public void setNotificationsOn(boolean notificationsOn) {
        this.notificationsOn = notificationsOn;
    }

    @Override
    public String toString() {
        return  "name='" + name + '\'' +
                ", year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", notificationsOn=" + notificationsOn +
                '}';
    }
}
