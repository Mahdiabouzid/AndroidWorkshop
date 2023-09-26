package com.example.workshoptesting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSubmitClicked(View view) {
        EditText editText = findViewById(R.id.input_text);
        String inputValue = editText.getText().toString();

        if (!inputValue.isEmpty()) {
            Intent intent = new Intent(this, Search_list_activity.class);
            intent.putExtra("inputValue", inputValue);
            startActivity(intent);
        } else {
            // Handle empty input scenario
            Toast.makeText(this, "Please enter some text", Toast.LENGTH_SHORT).show();
        }
    }

}