package com.example.oneshot.marvelfanapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.oneshot.marvelfanapp.R;
import com.example.oneshot.marvelfanapp.models.Result;

import java.util.ArrayList;

public class  ListCharactersAdapter extends RecyclerView.Adapter<ListCharactersAdapter.ViewHolder> {
    private ArrayList<Result> data;
    private Context context;

    public ListCharactersAdapter(Context context){
        this.context = context;
        data = new ArrayList<Result>();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_characters,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Result r = data.get(i);
        viewHolder.tv_characterName.setText(r.getName());

        Glide.with(context)
                .load(String.format("%s/%s.%s",r.getThumbnail().getPath(),"standard_xlarge",r.getThumbnail().getExtension()))
                //.centerCrop()
                //.crossFade()
               //.diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.iv_imageCharacter);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addListCharacters(ArrayList<Result> listaCharacters) {
        data.addAll(listaCharacters);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_imageCharacter;
        private TextView tv_characterName;
        public ViewHolder(View itemView){
            super(itemView);

            iv_imageCharacter = (ImageView) itemView.findViewById(R.id.iv_imageCharacter);
            tv_characterName = (TextView)itemView.findViewById(R.id.tv_characterName);
        }
    }
}