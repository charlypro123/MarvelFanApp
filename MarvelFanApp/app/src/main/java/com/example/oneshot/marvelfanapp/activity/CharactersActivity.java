package com.example.oneshot.marvelfanapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.oneshot.marvelfanapp.MainActivity;
import com.example.oneshot.marvelfanapp.R;
import com.example.oneshot.marvelfanapp.util.ToolbarManager;

public class CharactersActivity extends AppCompatActivity {
    private ToolbarManager toolbarManager = new ToolbarManager();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);

        getIncomingIntent();
    }

    private void getIncomingIntent() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String imageUrl = getIntent().getStringExtra("iv_imageCharacter");
            String name = getIntent().getStringExtra("tv_characterName");
            String description = getIntent().getStringExtra("tv_characterDescription");
            setCharacter(imageUrl, name, description);
            toolbarManager.setupToolbarCharacter(this, name);
        }
    }

    private void setCharacter(String imageUrl, String chName, String chDescription) {


        TextView tvName = findViewById(R.id.tv_characterNameGallery);
        tvName.setText(chName);
        TextView tvDescription = findViewById(R.id.tv_characterDescription);
        if (chDescription != null && !"".equals(chDescription)) {
            tvDescription.setText(chDescription);
        } else {
            tvDescription.setText(this.getString(R.string.info_not_available));
        }

        ImageView image = findViewById(R.id.iv_imageCharacterGallery);
        Glide.with(this)
                //.asBitmap()
                .load(imageUrl)
                .into(image);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

