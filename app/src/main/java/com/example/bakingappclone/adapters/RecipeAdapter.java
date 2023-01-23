package com.example.bakingappclone.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingappclone.R;
import com.example.bakingappclone.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter {

    private List<Recipe> recipeList = new ArrayList<>();
    final private OnItemClickListener listener;

    public RecipeAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int clickedItemIndex);
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
        notifyDataSetChanged();
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item_layout, parent, false);
        return new RecipeItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((RecipeItemViewHolder) holder).bindData(recipeList.get(position));
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class RecipeItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView nameTextView;
        private TextView servingsTextView;

        public RecipeItemViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            nameTextView = itemView.findViewById(R.id.tv_recipe_name);
            servingsTextView = itemView.findViewById(R.id.tv_recipe_servings);
        }

        public void bindData(Recipe recipe) {
            String servings = String.valueOf(recipe.getServings());
            nameTextView.setText(recipe.getName());
            servingsTextView.setText(servings);
        }

        @Override
        public void onClick(View v) {
            int index = getAdapterPosition();
            listener.onItemClick(index);
        }
    }
}
