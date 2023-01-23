package com.example.bakingappclone;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bakingappclone.adapters.RecipeAdapter;
import com.example.bakingappclone.database.RecipeDatabase;
import com.example.bakingappclone.models.Ingredient;
import com.example.bakingappclone.models.Recipe;
import com.example.bakingappclone.models.Step;
import com.example.bakingappclone.viewmodels.FavoriteRecipeViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavoriteListFragment extends Fragment implements RecipeAdapter.OnItemClickListener {

    RecipeDatabase recipeDatabase;

    RecyclerView favoriteRecyclerView;
    RecipeAdapter recipeAdapter;

    public FavoriteListFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        favoriteRecyclerView = view.findViewById(R.id.rv_favorite_list);
        recipeAdapter = new RecipeAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        favoriteRecyclerView.setLayoutManager(layoutManager);
        favoriteRecyclerView.setHasFixedSize(true);
        favoriteRecyclerView.setAdapter(recipeAdapter);

        setupFavoriteRecipeData();
    }

    public void setupFavoriteRecipeData() {
        FavoriteRecipeViewModel favoriteRecipeViewModel = new ViewModelProvider(this).get(FavoriteRecipeViewModel.class);
        favoriteRecipeViewModel.getFavoriteRecipeList().observe(this, movieList -> {
            recipeAdapter.setRecipeList(movieList);
        });
    }

    @Override
    public void onItemClick(int clickedItemIndex) {
        Recipe r = recipeAdapter.getRecipeList().get(clickedItemIndex);
        List<Ingredient> ingredients = r.getIngredients();
        List<Step> steps = r.getSteps();

        Log.d("JER", ingredients.toString());

//        Intent intent = new Intent(this.getContext(), RecipeDetailActivity.class);
//        intent.putExtra("recipe", r);
//        intent.putParcelableArrayListExtra("ingredients", (ArrayList<? extends Parcelable>) ingredients);
//        intent.putParcelableArrayListExtra("steps", (ArrayList<? extends Parcelable>) steps);
//        startActivity(intent);
    }
}