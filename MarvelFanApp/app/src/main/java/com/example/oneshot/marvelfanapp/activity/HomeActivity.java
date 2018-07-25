package com.example.oneshot.marvelfanapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;



import com.example.oneshot.marvelfanapp.R;


public class HomeActivity extends AppCompatActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar =(Toolbar) findViewById(R.id.toolbarHome);
        setSupportActionBar(toolbar);

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
