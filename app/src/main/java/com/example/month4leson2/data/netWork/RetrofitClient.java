package com.example.month4leson2.data.netWork;

import androidx.recyclerview.widget.RecyclerView;

import com.example.month4leson2.data.netWork.interfaces.CharecterApiServise;
import com.example.month4leson2.data.netWork.interfaces.EpisodeApiServices;
import com.example.month4leson2.data.netWork.interfaces.LocationApiServise;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private OkHttpClient okHttpClient = new OkHttpClient()
            .newBuilder()
            .addInterceptor(pravideLoginInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    private HttpLoggingInterceptor pravideLoginInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    private Retrofit provideRetrofit = new Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public CharecterApiServise charecterApiServise (){
        return provideRetrofit.create(CharecterApiServise.class);
    }

    public EpisodeApiServices  episodeApiServices(){
        return provideRetrofit.create(EpisodeApiServices.class);
    }

    public LocationApiServise locationApiServise(){
        return provideRetrofit.create(LocationApiServise.class);
    }
}
