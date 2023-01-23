package com.example.bakingappclone.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.bakingappclone.models.Recipe;

import java.util.List;

@Dao
public interface RecipeDao {

    @Query("SELECT * FROM recipes")
    LiveData<List<Recipe>> getAllRecipes();

    @Query("SELECT * FROM recipes WHERE id = :id")
    Recipe getRecipeById(int id);

    @Insert
    void insert(Recipe recipe);

    @Delete
    void delete(Recipe recipe);
}
