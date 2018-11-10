package juniafirdaus.com.dicodingmovie.movienowplaying;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import juniafirdaus.com.dicodingmovie.movienowplaying.modelnowplaying.NowResponse;
import juniafirdaus.com.dicodingmovie.movienowplaying.modelnowplaying.ResultsItem;
import juniafirdaus.com.dicodingmovie.R;
import juniafirdaus.com.dicodingmovie.apirepository.ApiService;
import juniafirdaus.com.dicodingmovie.apirepository.InitRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NowFragment extends Fragment {

    static final String NOW_PLAYING = "Now_Playing";
    static final String DETAIL_MOVIE = "detail_movie";

    RecyclerView recyclerView;
    NowAdapter nowAdapter;

    public NowFragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_now, container, false);

        recyclerView = view.findViewById(R.id.rcvMovie);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        loadMovieNow();

        return view;
    }

    private void loadMovieNow() {

        ApiService apiService = InitRetrofit.getInstance();
        Call<NowResponse> responseCall = apiService.now_playing();
        responseCall.enqueue(new Callback<NowResponse>() {
            @Override
            public void onResponse(@NonNull Call<NowResponse> call, @NonNull Response<NowResponse> response) {

                if (response.isSuccessful()) {
                    Log.i("movie", response.body().getResults().toString());
                    ArrayList<ResultsItem> resultsItems = (ArrayList<ResultsItem>) response.body().getResults();
                    nowAdapter = new NowAdapter(getActivity(), resultsItems);
                    recyclerView.setAdapter(nowAdapter);
                } else {
                    Log.i("MOVIE", "Empty");
                }

            }

            @Override
            public void onFailure(@NonNull Call<NowResponse> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }
}