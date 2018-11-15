package juniafirdaus.com.dicodingmovie.movienowplaying.view;

import android.content.Intent;

import juniafirdaus.com.dicodingmovie.movienowplaying.model.NowResponse;

public interface NowView {

    void showLoading();

    void hideLoading();

    void getDataSuccess(NowResponse nowResponse);

    void getDataFail(String message);

    void movieToDetail(Intent intent);


}
