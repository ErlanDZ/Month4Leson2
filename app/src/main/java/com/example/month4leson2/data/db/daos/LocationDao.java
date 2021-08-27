package com.example.month4leson2.data.db.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.month4leson2.model.EpisodeModel;
import com.example.month4leson2.model.LocationModel;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ArrayList<LocationModel> locationModels );

    @Query("SELECT * FROM locationmodel")
    List<LocationModel> getAll();
}
