package com.example.oneshot.marvelfanapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.oneshot.marvelfanapp.R;
import com.example.oneshot.marvelfanapp.adapter.ListCharactersAdapter;
import com.example.oneshot.marvelfanapp.MainActivity;
import com.example.oneshot.marvelfanapp.marvelApi.MarvelApiService;
import com.example.oneshot.marvelfanapp.models.CharactersResponse;
import com.example.oneshot.marvelfanapp.models.Result;
import com.example.oneshot.marvelfanapp.util.ToolbarManager;

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

public class ListCharactersActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private Retrofit retrofit;
    private static final String TAG = "MARVEL";
    private RecyclerView recyclerView;
    private ListCharactersAdapter listCharactersAdapter;
    private ArrayList<Result> listAllCharacters = new ArrayList<>();
    private int offset;
    private boolean readyUpdate;

    private ToolbarManager toolbarManager = new ToolbarManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_characters);
        toolbarManager.setupToolbarCharacters(this);

        final GridLayoutManager layoutManager = new GridLayoutManager(this, getResources().getInteger(R.integer.columns));

        listCharactersAdapter = new ListCharactersAdapter(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setAdapter(listCharactersAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                   /* int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();*/

                    if (readyUpdate) {
                        //if ( totalItemCount>=0) {
                        Log.i(TAG, "last row");
                        readyUpdate = false;
                        offset += 20;
                        obtainData(offset);
                        //}
                    }
                }
            }
        });
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        readyUpdate = true;
        offset = 0;
        obtainData(offset);
    }

    private void obtainData(int offset) {
        MarvelApiService service = retrofit.create(MarvelApiService.class);

        final Call<CharactersResponse> CharactersResponseCall = service.obtainListCharacters(offset, PUBLIC_KEY, HASH, TIMESTAMP);

        CharactersResponseCall.enqueue(new Callback<CharactersResponse>() {
            @Override
            public void onResponse(Call<CharactersResponse> call, Response<CharactersResponse> response) {
                readyUpdate = true;
                if (response.isSuccessful()) {
                    CharactersResponse charactersResponse = response.body();

                    listAllCharacters.addAll(charactersResponse.getData().getResults());

                    listCharactersAdapter.addListCharacters(charactersResponse.getData().getResults());
                } else {
                    Log.e(TAG, "onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<CharactersResponse> call, Throwable t) {
                readyUpdate = true;
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
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

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);
        MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {

                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                //listCharactersAdapter.getData();
                //listCharactersAdapter.setFilter(listCharacters);
                //TODO mirar que vols fer quan tanquis la busqueda
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        try {
            //ArrayList<Result> filterList = filter(listCharactersAdapter.getData(), newText);
            ArrayList<Result> filterList = filter(newText);
            listCharactersAdapter.setFilter(filterList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private ArrayList<Result> filter(String text) {
        ArrayList<Result> filterList = new ArrayList<>();
        try {
            text = text.toLowerCase();
            if (listAllCharacters != null && !listAllCharacters.isEmpty()) {
                for (Result result : listAllCharacters) {
                    String resultString = result.getName().toLowerCase();

                    if (resultString.contains(text)) {
                        filterList.add(result);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filterList;
    }

}
