package com.example.bakingappclone.database;

import androidx.room.TypeConverter;

import com.example.bakingappclone.models.Ingredient;
import com.example.bakingappclone.models.Step;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class StepsConverter {

    @TypeConverter
    public static List<Step> toList(String stepsString) {
        if (stepsString == null) return null;
        Type listType = new TypeToken<List<Step>>() {}.getType();
        return new Gson().fromJson(stepsString, listType);
    }

    @TypeConverter
    public static String fromList(List<Step> list) {
        if (list == null) return null;
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
