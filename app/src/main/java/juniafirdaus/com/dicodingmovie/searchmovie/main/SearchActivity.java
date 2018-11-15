package juniafirdaus.com.dicodingmovie.searchmovie.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import juniafirdaus.com.dicodingmovie.R;
import juniafirdaus.com.dicodingmovie.base.mvp.MvpActivity;
import juniafirdaus.com.dicodingmovie.searchmovie.adapter.SearchAdapter;
import juniafirdaus.com.dicodingmovie.searchmovie.model.ResultsItem;
import juniafirdaus.com.dicodingmovie.searchmovie.model.SearchResponse;
import juniafirdaus.com.dicodingmovie.searchmovie.presenter.SearchPresenter;
import juniafirdaus.com.dicodingmovie.utils.RecyclerItemClickListener;

public class SearchActivity extends MvpActivity<SearchPresenter> implements juniafirdaus.com.dicodingmovie.searchmovie.view.SearchView {

    private RecyclerView recyclerView;
    private List<ResultsItem> list;
    private ProgressBar progressBar;
    private SearchPresenter searchPresenter;

    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initView();
        searchPresenter.loadData(getIntent().getStringExtra("query"));
    }

    private void initView() {

        searchPresenter = new SearchPresenter(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.rcvSearch);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.addOnItemTouchListener(recyclerItemClickListener());

    }

    private RecyclerItemClickListener recyclerItemClickListener() {
        return new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                searchPresenter.getItem(list.get(position), activity);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                searchPresenter.getItem(list.get(position), activity);
            }
        });
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
                searchPresenter.loadData(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return true;
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void getSuccess(SearchResponse searchResponse) {
        this.list = searchResponse.getResults();
        recyclerView.setAdapter(new SearchAdapter(list, R.layout.card_movie, getApplicationContext()));
    }

    @Override
    public void getDataFail(String message) {
        Log.i("search", message);
    }

    @Override
    public void moveToDetail(Intent intent) {
        startActivity(intent);
    }
}
