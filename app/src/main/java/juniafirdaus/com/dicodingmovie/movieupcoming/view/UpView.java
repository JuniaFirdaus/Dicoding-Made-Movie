package juniafirdaus.com.dicodingmovie.movieupcoming.view;

import android.content.Intent;

import juniafirdaus.com.dicodingmovie.movieupcoming.model.UpResponse;

public interface UpView {

    void showLoading();

    void hideLoading();

    void getSuccess(UpResponse upResponse);

    void getDataFail(String message);

    void moveToDetail(Intent intent);
}
