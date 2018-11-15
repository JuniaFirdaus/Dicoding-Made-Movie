package juniafirdaus.com.dicodingmovie.movienowplaying.presenter;

import android.app.Activity;
import android.content.Intent;

import juniafirdaus.com.dicodingmovie.DetailActivity;
import juniafirdaus.com.dicodingmovie.apirepository.NetworkCallback;
import juniafirdaus.com.dicodingmovie.base.ui.BasePresenter;
import juniafirdaus.com.dicodingmovie.movienowplaying.model.NowResponse;
import juniafirdaus.com.dicodingmovie.movienowplaying.model.ResultsItem;
import juniafirdaus.com.dicodingmovie.movienowplaying.view.NowView;

public class NowPresenter extends BasePresenter<NowView> {

    public NowPresenter(NowView view){
        super.attachView(view);
    }

    public void loadData(){
        view.showLoading();
        addSubscribe(apiServices.getNowPlaying(), new NetworkCallback<NowResponse>() {

            @Override
            public void onSuccess(NowResponse model) {
                view.getDataSuccess(model);

            }

            @Override
            public void onFailure(String message) {
                view.getDataFail(message);
            }

            @Override
            public void onFinish() {
                view.hideLoading();
            }
        });
    }

    public void getItem(ResultsItem item , Activity activity){
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra("id", item.getId());
        intent.putExtra("title", item.getTitle());
        intent.putExtra("date", item.getReleaseDate());
        intent.putExtra("overview", item.getOverview());
        intent.putExtra("poster", item.getPosterPath());
        intent.putExtra("banner", item.getBackdropPath());
        view.movieToDetail(intent);
    }
}
