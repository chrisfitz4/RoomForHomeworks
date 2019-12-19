package com.example.roomforhomeworks.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HomeworkDAO {

    @Query("SELECT * FROM homeworks ORDER BY homework_date")
    List<HomeworkEntity> getAllHomeworks();

    @Insert
    void insertNewHomework(HomeworkEntity homework);

    @Delete
    void deleteHomework(HomeworkEntity homework);

    @Update
    void updateEntity(HomeworkEntity homework);

    @Query("SELECT * FROM homeworks")
    double getTotalCost();



}
