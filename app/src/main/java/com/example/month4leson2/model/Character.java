package com.example.month4leson2.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

@Entity
public class Character {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    private int id;

    @SerializedName("image")
    private String image;

    @SerializedName("name")
    private String name;

    @SerializedName("status")
    private String status;

    @SerializedName("species")
    private String species;

    @SerializedName("gender")
    private String gender;

    @SerializedName("created")
    private String created;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return id == character.id && Objects.equals(image, character.image) && Objects.equals(name, character.name) && Objects.equals(status, character.status) && Objects.equals(species, character.species) && Objects.equals(gender, character.gender) && Objects.equals(created, character.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, image, name, status, species, gender, created);
    }
}
