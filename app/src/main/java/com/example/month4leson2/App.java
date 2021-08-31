package com.example.month4leson2;

import android.app.Application;

import com.example.month4leson2.data.db.RoomClient;
import com.example.month4leson2.data.db.daos.CharacterDao;
import com.example.month4leson2.data.db.daos.EpisodeDao;
import com.example.month4leson2.data.db.daos.LocationDao;
import com.example.month4leson2.data.netWork.RetrofitClient;
import com.example.month4leson2.data.netWork.apiservice.CharecterApiServise;
import com.example.month4leson2.data.netWork.apiservice.EpisodeApiServices;
import com.example.month4leson2.data.netWork.apiservice.LocationApiServise;

public class App extends Application {
    public static CharecterApiServise charecterApiServise;
    public static EpisodeApiServices episodeApiServices;
    public static LocationApiServise locationApiServise;
    public static CharacterDao characterDao;
    public static EpisodeDao episodeDao;
    public static LocationDao locationDao;

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitClient retrofitClient = new RetrofitClient();
        RoomClient roomClient = new RoomClient();
        charecterApiServise = retrofitClient.charecterApiServise();
        episodeApiServices = retrofitClient.episodeApiServices();
        locationApiServise = retrofitClient.locationApiServise();
        characterDao = roomClient.provideCharacterDao(roomClient.provideDatabase(this));
        episodeDao = roomClient.provideEpisodeDao(roomClient.provideDatabase(this));
        locationDao = roomClient.provideLocationDao(roomClient.provideDatabase(this));

    }
}
