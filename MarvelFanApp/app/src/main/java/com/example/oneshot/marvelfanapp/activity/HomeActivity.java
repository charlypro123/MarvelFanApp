package com.example.oneshot.marvelfanapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.oneshot.marvelfanapp.R;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


      clickControl();
    }

    public void clickControl(){
        ImageView entry = findViewById(R.id.imageCharacters);
        entry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent characters = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(characters);

            }
        });
    }
}
