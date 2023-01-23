package com.example.bakingappclone.database;


import androidx.room.TypeConverter;

import com.example.bakingappclone.models.Ingredient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class IngredientsConverter {
    @TypeConverter
    public static List<Ingredient> toList(String ingredientString) {
        if (ingredientString == null) return null;
        Type listType = new TypeToken<List<Ingredient>>() {}.getType();
        return new Gson().fromJson(ingredientString, listType);
    }

    @TypeConverter
    public static String fromList(List<Ingredient> list) {
        if (list == null) return null;
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
