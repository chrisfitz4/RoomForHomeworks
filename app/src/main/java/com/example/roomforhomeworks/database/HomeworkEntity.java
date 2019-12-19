package com.example.roomforhomeworks.database;


import android.util.Log;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Formatter;

@Entity(tableName = "homeworks")
public class HomeworkEntity implements Comparable<HomeworkEntity>{
    //have to use the @Entity call to make sure it is actually recognized as one

    @PrimaryKey(autoGenerate = true)
    private int homeworkID;

    @ColumnInfo(name = "homework_description")
    private String homeworkDescription;

    @ColumnInfo(name = "homework_date")
    private double homeworkDay;

    @ColumnInfo(name = "homework_complete")
    private boolean homework_complete;






///////////////constructors
    public HomeworkEntity(int homeworkID, String homeworkDescription, double homeworkDay, boolean homework_complete) {
        this.homeworkID = homeworkID;
        this.homeworkDescription = homeworkDescription;
        this.homeworkDay = homeworkDay;
        this.homework_complete = homework_complete;
    }
    @Ignore
    public HomeworkEntity(String homeworkDescription, double homeworkDay, boolean homework_complete) {
        this.homeworkDescription = homeworkDescription;
        this.homeworkDay = homeworkDay;
        this.homework_complete = homework_complete;
    }





///////////toString
    public String toString(){
        String toReturn = "";
        toReturn += homeworkDay+": "+homeworkDescription+" is complete:"+homework_complete;
        return toReturn;
    }

    public String dateToString(){
        int week = (int)Math.round(homeworkDay);
        int day = (int)(homeworkDay*10%10);
        String weekDay;
        switch (day){
            case 0:
                weekDay = "Monday";
                break;
            case 1:
                weekDay = "Tuesday";
                break;
            case 2:
                weekDay = "Wednesday";
                break;
            case 3:
                weekDay = "Thursday";
                break;
            case 4:
                weekDay = "Friday";
                break;
            case 5:
                weekDay = "Weekend";
                break;
            default:
                weekDay = "";
                break;
        }
        return "Week "+week+", "+weekDay;
    }

    public String completedToString(){
        if(homework_complete){
            return "Complete";
        }else{
            return "Not Complete";
        }
    }


///////////getters and setters
    public int getHomeworkID() {
        return homeworkID;
    }

    public void setHomeworkID(int homeworkID) {
        this.homeworkID = homeworkID;
    }

    public String getHomeworkTitle() {
        return homeworkDescription;
    }


    public String getHomeworkDescription() {
        return homeworkDescription;
    }

    public void setHomeworkDescription(String homeworkDescription) {
        this.homeworkDescription = homeworkDescription;
    }

    public double getHomeworkDay() {
        return homeworkDay;
    }

    public void setHomeworkDay(double homeworkDay) {
        this.homeworkDay = homeworkDay;
    }

    public boolean isHomework_complete() {
        return homework_complete;
    }

    public void setHomework_complete(boolean homework_complete) {
        this.homework_complete = homework_complete;
    }

    @Override
    public int compareTo(HomeworkEntity o) {
        return ((Double)homeworkDay).compareTo(o.homeworkDay);
    }
}
