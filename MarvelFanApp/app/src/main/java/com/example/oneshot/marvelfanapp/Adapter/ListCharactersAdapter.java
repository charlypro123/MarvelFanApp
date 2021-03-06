package com.example.oneshot.marvelfanapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.oneshot.marvelfanapp.R;
import com.example.oneshot.marvelfanapp.activity.CharactersActivity;
import com.example.oneshot.marvelfanapp.models.Result;

import java.util.ArrayList;

public class ListCharactersAdapter extends RecyclerView.Adapter<ListCharactersAdapter.ViewHolder> {
    private ArrayList<Result> data;
    private Context context;

    public ArrayList<Result> getData() {
        return data;
    }

    public ListCharactersAdapter(Context context) {
        this.context = context;
        data = new ArrayList<Result>();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_characters, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        final Result r = data.get(position);
        viewHolder.tv_characterName.setText(r.getName());

        final String imageURL = String.format("%s/%s.%s", r.getThumbnail().getPath(), "standard_xlarge", r.getThumbnail().getExtension());

        Glide.with(context)
                .load(imageURL)
                //.centerCrop()
                //.crossFade()
                //.diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.iv_imageCharacter);

        viewHolder.layout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, data.get(position), Toast.LENGTH_SHORT).show();
                CharactersActivity.launch(context, r.getName(), r.getDescription(), imageURL);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_imageCharacter;
        TextView tv_characterName;
        RelativeLayout layout_item;


        public ViewHolder(View itemView) {
            super(itemView);

            iv_imageCharacter = (ImageView) itemView.findViewById(R.id.iv_imageCharacter);
            tv_characterName = (TextView) itemView.findViewById(R.id.tv_characterName);
            layout_item = (RelativeLayout) itemView.findViewById(R.id.layout_item);
        }
    }

    public void addListCharacters(ArrayList<Result> listCharacters) {
        data.addAll(listCharacters);
        notifyDataSetChanged();
    }

    public void setFilter(ArrayList<Result> listCharacters) {
        this.data = new ArrayList<>();
        this.data.addAll(listCharacters);
        notifyDataSetChanged();
    }
}
