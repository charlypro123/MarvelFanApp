package com.example.oneshot.marvelfanapp.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.oneshot.marvelfanapp.Adapter.ListCharactersAdapter;
import com.example.oneshot.marvelfanapp.R;
import com.example.oneshot.marvelfanapp.marvelApi.MarvelApiService;
import com.example.oneshot.marvelfanapp.models.CharactersResponse;
import com.example.oneshot.marvelfanapp.models.Result;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.oneshot.marvelfanapp.util.Constants.BASE_URL;
import static com.example.oneshot.marvelfanapp.util.Constants.HASH;
import static com.example.oneshot.marvelfanapp.util.Constants.PUBLIC_KEY;
import static com.example.oneshot.marvelfanapp.util.Constants.TIMESTAMP;

public class MainActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private static final String TAG = "MARVEL";
    private RecyclerView recyclerView;
    private ListCharactersAdapter listCharactersAdapter;

    private int offset;
    private boolean nextUpdateScroll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.list);
        listCharactersAdapter = new ListCharactersAdapter(this);
        recyclerView.setAdapter(listCharactersAdapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(dy > 0){
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if(nextUpdateScroll){
                        if((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            Log.i(TAG,"llegamos al final");

                            nextUpdateScroll = false;
                            offset += 20;
                            obtenerDatos(offset);

                        }
                    }
                }
            }
        });


        retrofit= new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        nextUpdateScroll = true;
        offset = 0;
        obtenerDatos(offset);
    }

    private void obtenerDatos(int offset){
        MarvelApiService service = retrofit.create(MarvelApiService.class);

        final Call<CharactersResponse> PokemonRespuestaCall = service.obtenerListaCharacters(offset,PUBLIC_KEY, HASH, TIMESTAMP);
        //"Iron man", "4c5844fc8e684e8ef3a438dd1bc9cbbf", "dcce4983c5c7f8fb8af2a9000dfee5c4", 9

        PokemonRespuestaCall.enqueue(new Callback<CharactersResponse>() {
            @Override
            public void onResponse(Call<CharactersResponse> call, Response<CharactersResponse> response) {
                nextUpdateScroll = true;
                if (response.isSuccessful()){
                    CharactersResponse CharactersRespuesta = response.body();

                    ArrayList<Result> listaCharacters = CharactersRespuesta.getData().getResults();

                    listCharactersAdapter.addListCharacters(listaCharacters);
                }else {
                    Log.e(TAG, "onResponse: "+ response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<CharactersResponse> call, Throwable t) {
                nextUpdateScroll = true;
                Log.e(TAG, "onFailure: "+ t.getMessage());
            }
        });
    }
}
