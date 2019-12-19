package com.example.roomforhomeworks.presenter;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import androidx.room.Room;

import com.example.roomforhomeworks.database.HomeworkEntity;
import com.example.roomforhomeworks.database.HomeworkDatabase;
import com.example.roomforhomeworks.database.HomeworkEntity;
import com.example.roomforhomeworks.presenter.Contract.HomeworkPresenter;
import com.example.roomforhomeworks.view.MainActivity;

import java.util.List;

public class HomePresenter implements HomeworkPresenter {


    private Contract.HomeworkView homeworkView;
    private HomeworkDatabase homeworkDatabase;
    private List<HomeworkEntity> homeworks;

    public HomePresenter(Contract.HomeworkView homeworkView) {
        this.homeworkView = homeworkView;
        try{
            homeworkDatabase = Room.databaseBuilder(((MainActivity)homeworkView).getApplicationContext(),
                    HomeworkDatabase.class,
                    "room.db").allowMainThreadQueries().build();
        }catch (Exception e){
            homeworkView.displayError("Failed to create database.");
        }

    }

    @Override
    public void getHomeworks() {
        homeworks = homeworkDatabase.homeworkDAO().getAllHomeworks();
        if(homeworks.isEmpty()){
            homeworkView.homeworksEmpty();
        }else {
            homeworkView.displayHomeworks(homeworks);
        }
    }

    @Override
    public void getTotalCost() {
        homeworks = homeworkDatabase.homeworkDAO().getAllHomeworks();
        if(homeworks.isEmpty()){
            homeworkView.homeworksEmpty();
        }else {
            homeworkView.displayCost(homeworkDatabase.homeworkDAO().getTotalCost());
        }
    }

    @Override
    public void insertHomework(HomeworkEntity homework) {
        try{
            homeworkDatabase.homeworkDAO().insertNewHomework(homework);
        }catch(Exception e){
            homeworkView.displayError("Failed to insert "+homework.getHomeworkTitle());
        }
    }

    @Override
    public void deleteHomework(HomeworkEntity homework) {
        try {
            homeworkDatabase.homeworkDAO().deleteHomework(homework);
        }catch (Exception e){
            homeworkView.displayError("Failed to delete "+homework.getHomeworkTitle());
        }
    }

    @Override
    public void modifyHomework(HomeworkEntity homework) {
        try {
            homeworkDatabase.homeworkDAO().updateEntity(homework);
        }catch (Exception e){
            homeworkView.displayError("Failed to update "+homework.getHomeworkTitle());
        }
    }


}