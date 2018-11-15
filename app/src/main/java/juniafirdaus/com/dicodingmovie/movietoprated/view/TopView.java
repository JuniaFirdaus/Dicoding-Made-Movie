package juniafirdaus.com.dicodingmovie.movietoprated.view;

import android.content.Intent;

import juniafirdaus.com.dicodingmovie.movietoprated.model.TopRatedResponse;

public interface TopView {

    void showLoading();

    void hideLoading();

    void getDataSuccess(TopRatedResponse ratedResponse);

    void getDataFail(String message);

    void moveToDetail(Intent intent);

}
