package com.example.month4leson2.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.month4leson2.data.db.daos.CharacterDao;
import com.example.month4leson2.data.db.daos.EpisodeDao;
import com.example.month4leson2.data.db.daos.LocationDao;
import com.example.month4leson2.model.Character;
import com.example.month4leson2.model.EpisodeModel;
import com.example.month4leson2.model.LocationModel;

@Database(entities = {Character.class, EpisodeModel.class, LocationModel.class}, version = 2)
abstract class AppDataBase extends RoomDatabase {
    public abstract CharacterDao characterDao ();
    public abstract EpisodeDao episodeDao();
    public abstract LocationDao locationDao();
}
