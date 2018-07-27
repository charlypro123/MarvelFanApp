package com.example.oneshot.marvelfanapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;

import com.example.oneshot.marvelfanapp.MainActivity;
import com.example.oneshot.marvelfanapp.R;
import com.example.oneshot.marvelfanapp.util.ToolbarManager;


public class HomeActivity extends AppCompatActivity {

    private ToolbarManager toolbarManager = new ToolbarManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbarManager.setupToolbarHome(this);

        findViewById(R.id.v_feedback_overlay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent characters = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(characters);
            }
        });
    }


    public boolean onCreateOptionMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

}
