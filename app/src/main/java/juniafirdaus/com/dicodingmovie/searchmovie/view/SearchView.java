package juniafirdaus.com.dicodingmovie.searchmovie.view;

import android.content.Intent;

import juniafirdaus.com.dicodingmovie.searchmovie.model.SearchResponse;

public interface SearchView {

    void showLoading();

    void  hideLoading();

    void getSuccess(SearchResponse searchResponse);

    void getDataFail(String message);

    void moveToDetail(Intent intent);

}
