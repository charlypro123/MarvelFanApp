package com.example.oneshot.marvelfanapp.util;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.oneshot.marvelfanapp.R;
import com.example.oneshot.marvelfanapp.activity.HomeActivity;

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

    public void setupToolbarCharacter(AppCompatActivity activity, String name) {
        this.activity = activity;
        toolbar = (Toolbar) this.activity.findViewById(R.id.toolbarCharacter);
        this.activity.setSupportActionBar(toolbar);
        this.activity.getSupportActionBar().setTitle(name);
        this.activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public Toolbar getToolbar() {
        return toolbar;
    }
}
