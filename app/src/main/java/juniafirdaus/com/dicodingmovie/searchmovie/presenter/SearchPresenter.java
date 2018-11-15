package juniafirdaus.com.dicodingmovie.searchmovie.presenter;

import android.app.Activity;
import android.content.Intent;

import juniafirdaus.com.dicodingmovie.DetailActivity;
import juniafirdaus.com.dicodingmovie.apirepository.NetworkCallback;
import juniafirdaus.com.dicodingmovie.base.ui.BasePresenter;
import juniafirdaus.com.dicodingmovie.movieupcoming.model.ResultsItem;
import juniafirdaus.com.dicodingmovie.searchmovie.model.SearchResponse;
import juniafirdaus.com.dicodingmovie.searchmovie.view.SearchView;

public class SearchPresenter extends BasePresenter<SearchView> {

    public SearchPresenter(SearchView upView){
        super.attachView(upView);
    }

    public void loadData(String s){
        view.showLoading();
        addSubscribe(apiServices.searchMovie(s), new NetworkCallback<SearchResponse>() {

            @Override
            public void onSuccess(SearchResponse model) {
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

    public void getItem(juniafirdaus.com.dicodingmovie.searchmovie.model.ResultsItem item, Activity activity){
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
