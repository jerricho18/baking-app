package com.example.bakingappclone;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.bakingappclone.adapters.RecipeAdapter;
import com.example.bakingappclone.models.Ingredient;
import com.example.bakingappclone.models.Recipe;
import com.example.bakingappclone.models.Step;
import com.example.bakingappclone.utils.Constants;
import com.google.gson.Gson;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class RecipeListFragment extends Fragment implements RecipeAdapter.OnItemClickListener {

    private RecyclerView recipeRecyclerView;
    private RecipeAdapter recipeAdapter;

    private RequestQueue queue;
    private Gson gson;

    public RecipeListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initHttpComponents();
        getRecipeData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_recipe_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recipeRecyclerView = view.findViewById(R.id.rv_recipe_list);
        recipeAdapter = new RecipeAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recipeRecyclerView.setLayoutManager(layoutManager);
        recipeRecyclerView.setHasFixedSize(true);
        recipeRecyclerView.setAdapter(recipeAdapter);
    }

    private void initHttpComponents() {
        queue = Volley.newRequestQueue(this.getContext());
        gson = new Gson();
    }

    private void getRecipeData() {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, Constants.API_URL, null,
                response -> {
                    List<Recipe> recipeList = new ArrayList<>();
                    for (int i=0; i<response.length(); i++) {
                        try {
                            Recipe r = gson.fromJson(response.getString(i), Recipe.class);
                            recipeList.add(r);
                            Log.d("JER", r.getName());
                            Log.d("JER", r.getIngredients().toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    recipeAdapter.setRecipeList(recipeList);
                },
                error -> {
                    Log.d("JER", error.toString());
                }
        );

        queue.add(request);
    }

    @Override
    public void onItemClick(int clickedItemIndex) {
        Recipe r = recipeAdapter.getRecipeList().get(clickedItemIndex);
        List<Ingredient> ingredients = r.getIngredients();
        List<Step> steps = r.getSteps();

        Intent intent = new Intent(this.getContext(), RecipeDetailActivity.class);
        intent.putExtra("recipe", r);
        intent.putParcelableArrayListExtra("ingredients", (ArrayList<? extends Parcelable>) ingredients);
        intent.putParcelableArrayListExtra("steps", (ArrayList<? extends Parcelable>) steps);
        startActivity(intent);
    }
}