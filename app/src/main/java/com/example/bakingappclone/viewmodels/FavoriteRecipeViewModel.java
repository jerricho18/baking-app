package com.example.bakingappclone.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.bakingappclone.database.RecipeDatabase;
import com.example.bakingappclone.models.Recipe;

import java.util.List;

public class FavoriteRecipeViewModel extends AndroidViewModel {

    private LiveData<List<Recipe>> favoriteRecipeList;

    public FavoriteRecipeViewModel(@NonNull Application application) {
        super(application);

        favoriteRecipeList = RecipeDatabase.getDatabase(application.getApplicationContext())
                .recipeDao().getAllRecipes();
    }

    public LiveData<List<Recipe>> getFavoriteRecipeList() { return favoriteRecipeList; }
}
