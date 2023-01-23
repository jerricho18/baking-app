package com.example.bakingappclone.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.bakingappclone.models.Recipe;

@Database(entities = {Recipe.class}, version = 3, exportSchema = false)
@TypeConverters({IngredientsConverter.class, StepsConverter.class})
public abstract class RecipeDatabase extends RoomDatabase {

    public abstract RecipeDao recipeDao();

    private static RecipeDatabase INSTANCE;

    public static RecipeDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RecipeDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                RecipeDatabase.class, "recipe_database")
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return INSTANCE;
    }

}
