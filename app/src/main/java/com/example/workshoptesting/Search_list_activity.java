package com.example.workshoptesting;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class Search_list_activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);
        Intent intent = getIntent();
        String inputValue = intent.getStringExtra("inputValue");
        Log.i("info", inputValue);
        getRecipesByIngredients(inputValue);
        Button back_btn = findViewById(R.id.btnBottom);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    /**
     * call API and get JSON response back
     */
    private void getRecipesByIngredients(String ingredients) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String apiKey = "717ef540af464e1b91cf1a9e3bfbc5e8";
        String baseUrl = "https://api.spoonacular.com/recipes/findByIngredients";
        StringBuilder url = new StringBuilder(baseUrl + "?apiKey=" + apiKey + "&ingredients=");
        if (ingredients.contains(" ")) {
            String[] ingrediant_values = ingredients.split(" ");
            for (String value : ingrediant_values) {
                url.append(value).append(",+");
            }
        }
        else {
            url.append(ingredients);
        }


        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Parse the response here
                        handleResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle errors here
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    /**
     * Parse JSON response and get information out of it
     *
     */
    private void handleResponse(String jsonResponse) {
        Gson gson = new Gson();
        Type recipeListType = new TypeToken<List<Recipe>>() {
        }.getType();
        List<Recipe> recipes = gson.fromJson(jsonResponse, recipeListType);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ItemAdapter adapter = new ItemAdapter(this, recipes, new ItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Recipe item) {
                Intent intent = new Intent(Search_list_activity.this, RecipeView.class);
                intent.putExtra("name", item.title);
                intent.putExtra("imageUrl", item.image);
                intent.putExtra("missedIngredients", item.missedIngredients);
                intent.putExtra("usedIngredients", item.usedIngredients);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adapter);
    }

}
