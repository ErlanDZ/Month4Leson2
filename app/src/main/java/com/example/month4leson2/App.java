package com.example.month4leson2;

import android.app.Application;

import com.example.month4leson2.data.netWork.interfaces.CharecterApiServise;
import com.example.month4leson2.data.netWork.RetrofitClient;
import com.example.month4leson2.data.netWork.interfaces.EpisodeApiServices;
import com.example.month4leson2.data.netWork.interfaces.LocationApiServise;

public class App extends Application {
    public static CharecterApiServise charecterApiServise;
    public static EpisodeApiServices episodeApiServices;
    public static LocationApiServise locationApiServise;

    @Override
    public void onCreate() {
        super.onCreate();
        charecterApiServise = new RetrofitClient().charecterApiServise();
        episodeApiServices = new RetrofitClient().episodeApiServices();
        locationApiServise = new RetrofitClient().locationApiServise();
    }
}
