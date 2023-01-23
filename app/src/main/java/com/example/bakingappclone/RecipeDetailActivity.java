package com.example.bakingappclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.bakingappclone.adapters.IngredientAdapter;
import com.example.bakingappclone.adapters.StepAdapter;
import com.example.bakingappclone.database.RecipeDatabase;
import com.example.bakingappclone.databinding.ActivityRecipeDetailBinding;
import com.example.bakingappclone.models.Ingredient;
import com.example.bakingappclone.models.Recipe;
import com.example.bakingappclone.models.Step;
import com.google.gson.Gson;

import java.util.List;

public class RecipeDetailActivity extends AppCompatActivity {

    ActivityRecipeDetailBinding binding;

    Recipe recipe;
    List<Ingredient> ingredients;
    List<Step> steps;
    Gson gson;

    IngredientAdapter ingredientAdapter;
    StepAdapter stepAdapter;

    private boolean isFavorite = false;
    private RecipeDatabase recipeDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_detail);

        gson = new Gson();

        recipe = getIntent().getParcelableExtra("recipe");
        ingredients = getIntent().getParcelableArrayListExtra("ingredients");
        steps = getIntent().getParcelableArrayListExtra("steps");

        recipeDatabase = RecipeDatabase.getDatabase(this);

        initUI();
        checkIsFavorite();
    }

    private void initUI() {
        String servingText = "Servings for: " + recipe.getServings();
        binding.tvRecipeNameDetail.setText(recipe.getName());
        binding.tvRecipeServingsDetail.setText(servingText);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        ingredientAdapter = new IngredientAdapter();
        binding.rvIngredientList.setLayoutManager(layoutManager);
        ingredientAdapter.setIngredientList(ingredients);
        binding.rvIngredientList.setHasFixedSize(true);
        binding.rvIngredientList.setNestedScrollingEnabled(false);
        binding.rvIngredientList.setAdapter(ingredientAdapter);

        LinearLayoutManager stepLayoutManager = new LinearLayoutManager(this);
        stepAdapter = new StepAdapter();
        binding.rvStepList.setLayoutManager(stepLayoutManager);
        stepAdapter.setStepList(steps);
        binding.rvStepList.setHasFixedSize(true);
        binding.rvStepList.setNestedScrollingEnabled(false);
        binding.rvStepList.setAdapter(stepAdapter);

        binding.ivFavoriteButton.setImageResource(R.drawable.ic_baseline_favorite_border_24);
        binding.ivFavoriteButton.setOnClickListener(v -> {
            if (isFavorite) {
                isFavorite = false;
                recipeDatabase.recipeDao().delete(recipe);
                binding.ivFavoriteButton.setImageResource(R.drawable.ic_baseline_favorite_border_24);
            } else {
                isFavorite = true;
                recipeDatabase.recipeDao().insert(recipe);
                binding.ivFavoriteButton.setImageResource(R.drawable.ic_baseline_favorite_24);
            }
        });
    }

    private void checkIsFavorite() {
        Recipe favorite = recipeDatabase.recipeDao().getRecipeById(recipe.getId());

        if (favorite != null) {
            isFavorite = true;
            binding.ivFavoriteButton.setImageResource(R.drawable.ic_baseline_favorite_24);
        }
    }
}