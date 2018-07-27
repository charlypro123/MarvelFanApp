package com.example.oneshot.marvelfanapp.util;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.oneshot.marvelfanapp.R;

public class ToolbarManager {
    private Toolbar toolbar;
    private AppCompatActivity activity;


    public void setupToolbarHome(AppCompatActivity activity) {
        this.activity = activity;
        toolbar = (Toolbar) this.activity.findViewById(R.id.toolbarHome);
        this.activity.setSupportActionBar(toolbar);
        this.activity.getSupportActionBar().setTitle("Home");

    }

    public void setupToolbarCharacters(AppCompatActivity activity) {
        this.activity = activity;
        toolbar = (Toolbar) this.activity.findViewById(R.id.toolbarCharacters);
        this.activity.setSupportActionBar(toolbar);
        this.activity.getSupportActionBar().setTitle("Characters");
        this.activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
