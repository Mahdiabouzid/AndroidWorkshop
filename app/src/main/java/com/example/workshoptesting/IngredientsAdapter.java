package com.example.workshoptesting;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    private ArrayList<Ingredient> ingredients;

    public IngredientsAdapter(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingredient, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ingredient ingredient = ingredients.get(position);
        holder.ingredientNameTextView.setText(ingredient.name);
        holder.amountTextView.setText(String.valueOf(ingredient.amount));
        holder.unitTextView.setText(ingredient.unit);
        Picasso.get().load(ingredient.image).into(holder.ingredientImageView);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView ingredientNameTextView;
        TextView amountTextView;
        TextView unitTextView;
        ImageView ingredientImageView; // If you've added an ImageView

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientNameTextView = itemView.findViewById(R.id.ingredientNameTextView);
            amountTextView = itemView.findViewById(R.id.amountTextView);
            unitTextView = itemView.findViewById(R.id.unitTextView);
            ingredientImageView = itemView.findViewById(R.id.ingredientImageView); // If you've added an ImageView
        }
    }
}

