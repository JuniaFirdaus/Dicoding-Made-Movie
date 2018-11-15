package juniafirdaus.com.dicodingmovie.movieupcoming.presenter;

import android.app.Activity;
import android.content.Intent;

import juniafirdaus.com.dicodingmovie.DetailActivity;
import juniafirdaus.com.dicodingmovie.apirepository.NetworkCallback;
import juniafirdaus.com.dicodingmovie.base.ui.BasePresenter;
import juniafirdaus.com.dicodingmovie.movieupcoming.model.ResultsItem;
import juniafirdaus.com.dicodingmovie.movieupcoming.model.UpResponse;
import juniafirdaus.com.dicodingmovie.movieupcoming.view.UpView;

public class UpPresenter extends BasePresenter<UpView> {

    public UpPresenter(UpView upView){
        super.attachView(upView);
    }

    public void loadData(){
        view.showLoading();
        addSubscribe(apiServices.getUpcoming(), new NetworkCallback<UpResponse>() {

            @Override
            public void onSuccess(UpResponse model) {
                view.getSuccess(model);
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

    public void getItem(ResultsItem item, Activity activity){
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra("id", item.getId());
        intent.putExtra("title", item.getTitle());
        intent.putExtra("date", item.getReleaseDate());
        intent.putExtra("overview", item.getOverview());
        intent.putExtra("poster", item.getPosterPath());
        intent.putExtra("banner", item.getBackdropPath());
        view.moveToDetail(intent);
    }

}
