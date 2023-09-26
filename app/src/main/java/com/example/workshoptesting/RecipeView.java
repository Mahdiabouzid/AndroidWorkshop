package com.example.workshoptesting;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecipeView extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_view);

        ImageView imageView = findViewById(R.id.imageView);
        TextView nameTextView = findViewById(R.id.nameTextView);
        RecyclerView usedIngredientsRecyclerView = findViewById(R.id.usedIngredientsRecyclerView);
        RecyclerView unusedIngredientsRecyclerView = findViewById(R.id.unusedIngredientsRecyclerView);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String imageUrl = intent.getStringExtra("imageUrl");
        ArrayList<Ingredient> usedIngredients = intent.getParcelableArrayListExtra("usedIngredients");
        ArrayList<Ingredient> missedIngredients = intent.getParcelableArrayListExtra("missedIngredients");

        nameTextView.setText(name);
        // Use a library like Picasso or Glide to load the image.
        Picasso.get().load(imageUrl).into(imageView);

        usedIngredientsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        unusedIngredientsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        usedIngredientsRecyclerView.setAdapter(new IngredientsAdapter(usedIngredients));
        unusedIngredientsRecyclerView.setAdapter(new IngredientsAdapter(missedIngredients));

        Button back_btn = findViewById(R.id.btnBottom2);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}