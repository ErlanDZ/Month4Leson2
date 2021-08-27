package com.example.month4leson2.data.db;


import android.content.Context;

import androidx.room.Room;

import com.example.month4leson2.data.db.daos.CharacterDao;
import com.example.month4leson2.data.db.daos.EpisodeDao;
import com.example.month4leson2.data.db.daos.LocationDao;
import com.example.month4leson2.model.LocationModel;

public class RoomClient {
    public  AppDataBase provideDatabase (Context context){
        return  Room.databaseBuilder(context, AppDataBase.class, "rick-morty-Database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    public  CharacterDao provideCharacterDao(AppDataBase appDataBase ) {
        return appDataBase.characterDao();
    }

    public EpisodeDao provideEpisodeDao(AppDataBase appDataBaseEpisode){
        return appDataBaseEpisode.episodeDao();
    }
    public LocationDao provideLocationDao(AppDataBase appDataBaseLocation){
        return appDataBaseLocation.locationDao();
    }
}
