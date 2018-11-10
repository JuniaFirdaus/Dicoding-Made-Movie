package juniafirdaus.com.dicodingmovie.searchmovie;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import juniafirdaus.com.dicodingmovie.R;
import juniafirdaus.com.dicodingmovie.apirepository.ApiService;
import juniafirdaus.com.dicodingmovie.apirepository.InitRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView = findViewById(R.id.rcvSearch);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        loadSearch(getIntent().getStringExtra("query"));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);

        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                loadSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return true;
    }

    private void loadSearch(String s) {
        ApiService service = InitRetrofit.getInstance();
        Call<SearchResponse> responseCall = service.search_movie(s);
        responseCall.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.isSuccessful()) {
                    Log.i("hasil", String.valueOf(response.body().getResults()));
                    ArrayList<ResultsItem> resultsItems = (ArrayList<ResultsItem>) response.body().getResults();
                    searchAdapter = new SearchAdapter(SearchActivity.this, resultsItems);
                    recyclerView.setAdapter(searchAdapter);
                } else {
                    Log.i("hasil", response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<SearchResponse> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });

    }

}
