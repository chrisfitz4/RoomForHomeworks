package com.example.roomforhomeworks.presenter;

import com.example.roomforhomeworks.database.HomeworkEntity;

import java.util.List;

public interface Contract {

    interface HomeworkPresenter{
//      will be used to fetch homeworks
        void getHomeworks();
//      used to get total cost owed
        void getTotalCost();
        void insertHomework(HomeworkEntity homework);
        void deleteHomework(HomeworkEntity homework);
        void modifyHomework(HomeworkEntity homework);
    }


    interface HomeworkView{
        void displayHomeworks(List<HomeworkEntity> homeworks);
        void homeworksEmpty();
        void displayError(String error);
        void displayCost(double totalCost);
        void updateSuccess();
        void insertSuccess();
    }

}
