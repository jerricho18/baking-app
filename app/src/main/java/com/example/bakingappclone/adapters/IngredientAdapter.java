package com.example.bakingappclone.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingappclone.R;
import com.example.bakingappclone.models.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter {

    List<Ingredient> ingredientList = new ArrayList<>();

    public void setIngredientList(List<Ingredient> ingredients) {
        this.ingredientList = ingredients;
    }

    public List<Ingredient>  getIngredientList() { return this.ingredientList; }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_item_layout, parent, false);
        return new IngredientItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((IngredientItemViewHolder) holder).bindData(ingredientList.get(position));
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public class IngredientItemViewHolder extends RecyclerView.ViewHolder {

        private TextView quantityTextView;
        private TextView measureTextView;
        private TextView nameTextView;

        public IngredientItemViewHolder(@NonNull View itemView) {
            super(itemView);

            quantityTextView = itemView.findViewById(R.id.tv_ingredient_quantity);
            measureTextView = itemView.findViewById(R.id.tv_ingredient_measure);
            nameTextView = itemView.findViewById(R.id.tv_ingredient_name);
        }

        public void bindData(Ingredient ingredient) {
            String quantity = String.valueOf(ingredient.getQuantity());
            quantityTextView.setText(quantity);
            measureTextView.setText(ingredient.getMeasure());
            nameTextView.setText(ingredient.getIngredient());

        }
    }
}
