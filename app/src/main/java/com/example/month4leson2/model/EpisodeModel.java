package com.example.month4leson2.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

@Entity
public class EpisodeModel {
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("air_date")
    private String air_date;

    @SerializedName("episode")
    private String episode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAir_date() {
        return air_date;
    }

    public void setAir_date(String air_date) {
        this.air_date = air_date;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EpisodeModel that = (EpisodeModel) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(air_date, that.air_date) && Objects.equals(episode, that.episode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, air_date, episode);
    }
}
