package juniafirdaus.com.dicodingmovie.modelgenre;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;

import juniafirdaus.com.dicodingmovie.apirepository.ApiService;
import juniafirdaus.com.dicodingmovie.apirepository.InitRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenreMovie {

    public void getGenre(){
        ApiService apiService = InitRetrofit.getInstance();
        Call<GenreResponse> genresItemCall = apiService.getGenre();
        genresItemCall.enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(@NonNull Call<GenreResponse> call, @NonNull Response<GenreResponse> response) {
                ArrayList<GenresItem> genresItems = (ArrayList<GenresItem>) response.body().getGenres();
                Log.i("genree", String.valueOf(genresItems));
            }

            @Override
            public void onFailure(@NonNull Call<GenreResponse> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
