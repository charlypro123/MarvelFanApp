package com.example.oneshot.marvelfanapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.oneshot.marvelfanapp.MainActivity;
import com.example.oneshot.marvelfanapp.R;
import com.example.oneshot.marvelfanapp.util.ToolbarManager;


public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final TextView http = (TextView) this.findViewById(R.id.marvel_http);
        http.setMovementMethod(LinkMovementMethod.getInstance());

        initDrawerLayout();
        findViewById(R.id.v_feedback_overlay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent characters = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(characters);

            }
        });
        findViewById(R.id.bt_characters).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent characters = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(characters);

            }
        });
    }

    public void initDrawerLayout() {
        final DrawerLayout drawerLayout = (DrawerLayout) this.findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbarHome);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        );
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    /* private void addAboutLibrariesActivity() {
         ButtonRectangle button = (ButtonRectangle) this.findViewById(R.id.about_libraries);
         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 new LibsBuilder()
                         .withActivityTitle(HomeActivity.this.getString(R.string.app_name))
                         .withAboutIconShown(true)
                         .withAboutVersionShown(true)
                         .withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR)
                         .start(HomeActivity.this);
             }
         });
     }*/
    public boolean onCreateOptionMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}
