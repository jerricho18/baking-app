package com.example.bakingappclone.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "recipes")
public class Recipe implements Parcelable {
    @PrimaryKey
    private int id;
    private String name;
    private int servings;
    private List<Ingredient> ingredients;
    private List<Step> steps;


    public Recipe(int id, String name, List<Ingredient> ingredients, List<Step> steps, int servings) {
        this.id = id;
        this.name = name;
        this.servings = servings;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    protected Recipe(Parcel in) {
        id = in.readInt();
        name = in.readString();
        servings = in.readInt();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(servings);
    }
}
