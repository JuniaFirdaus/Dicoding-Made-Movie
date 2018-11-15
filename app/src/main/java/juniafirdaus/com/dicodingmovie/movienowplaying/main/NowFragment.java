package juniafirdaus.com.dicodingmovie.movienowplaying.main;


import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.widget.ProgressBar;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.Toast;
import android.os.Bundle;
import android.view.View;
import java.util.List;
import juniafirdaus.com.dicodingmovie.R;
import juniafirdaus.com.dicodingmovie.base.mvp.MvpFragment;
import juniafirdaus.com.dicodingmovie.movienowplaying.view.NowView;
import juniafirdaus.com.dicodingmovie.utils.RecyclerItemClickListener;
import juniafirdaus.com.dicodingmovie.movienowplaying.model.NowResponse;
import juniafirdaus.com.dicodingmovie.movienowplaying.model.ResultsItem;
import juniafirdaus.com.dicodingmovie.movienowplaying.adapter.NowAdapter;
import juniafirdaus.com.dicodingmovie.movienowplaying.presenter.NowPresenter;

public class NowFragment extends MvpFragment<NowPresenter> implements NowView {

    private List<ResultsItem> list;
    RecyclerView recyclerView;
    private NowPresenter nowPresenter;
    ProgressBar progressBar;

    @Override
    protected NowPresenter createPresenter() {
        return new NowPresenter(this);
    }

    public NowFragment() {

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_now, container, false);

        nowPresenter = new NowPresenter(this);
        recyclerView = view.findViewById(R.id.rcvMovie);
        progressBar = view.findViewById(R.id.progress);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.addOnItemTouchListener(recyclerItemClickListener());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        nowPresenter.loadData();
        return view;
    }

    private RecyclerItemClickListener recyclerItemClickListener() {
        return new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                nowPresenter.getItem(list.get(position), activity);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                nowPresenter.getItem(list.get(position), activity);

            }
        });
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
    public void getDataSuccess(NowResponse nowResponse) {
        this.list = nowResponse.getResults();
        recyclerView.setAdapter(new NowAdapter(list, R.layout.card_movie, getActivity()));

    }

    @Override
    public void getDataFail(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void movieToDetail(Intent intent) {
        startActivity(intent);
    }

}