package juniafirdaus.com.dicodingmovie.movieupcoming.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import juniafirdaus.com.dicodingmovie.R;
import juniafirdaus.com.dicodingmovie.base.mvp.MvpFragment;
import juniafirdaus.com.dicodingmovie.movieupcoming.adapter.UpAdapter;
import juniafirdaus.com.dicodingmovie.movieupcoming.model.ResultsItem;
import juniafirdaus.com.dicodingmovie.movieupcoming.model.UpResponse;
import juniafirdaus.com.dicodingmovie.movieupcoming.presenter.UpPresenter;
import juniafirdaus.com.dicodingmovie.movieupcoming.view.UpView;
import juniafirdaus.com.dicodingmovie.utils.RecyclerItemClickListener;

public class UpFragment extends MvpFragment<UpPresenter> implements UpView {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private UpPresenter presenter;
    private List<ResultsItem> list;

    public UpFragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_now, container, false);

        recyclerView = view.findViewById(R.id.rcvMovie);
        progressBar = view.findViewById(R.id.progress);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.addOnItemTouchListener(recyclerItemClickListener());
        presenter = new UpPresenter(this);
        presenter.loadData();

        return view;
    }

    private RecyclerItemClickListener recyclerItemClickListener(){
        return new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                presenter.getItem(list.get(position), activity);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                presenter.getItem(list.get(position), activity);
            }
        });
    }

    @Override
    protected UpPresenter createPresenter() {
        return new UpPresenter(this);
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
    public void getSuccess(UpResponse upResponse) {
        this.list = upResponse.getResults();
        recyclerView.setAdapter(new UpAdapter(list, R.layout.card_movie, getActivity()));
    }

    @Override
    public void getDataFail(String message) {
        Log.i("Upcoming", message);
    }

    @Override
    public void moveToDetail(Intent intent) {
        startActivity(intent);
    }
}
