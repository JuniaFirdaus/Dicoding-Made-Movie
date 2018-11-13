package juniafirdaus.com.dicodingmovie.movienowplaying;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vlonjatg.progressactivity.ProgressLinearLayout;

import java.util.ArrayList;
import java.util.Objects;

import juniafirdaus.com.dicodingmovie.movienowplaying.modelnowplaying.NowResponse;
import juniafirdaus.com.dicodingmovie.movienowplaying.modelnowplaying.ResultsItem;
import juniafirdaus.com.dicodingmovie.R;
import juniafirdaus.com.dicodingmovie.apirepository.ApiService;
import juniafirdaus.com.dicodingmovie.apirepository.InitRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NowFragment extends Fragment {

    RecyclerView recyclerView;
    NowAdapter nowAdapter;
    ProgressLinearLayout progressLinearLayout;


    public NowFragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_now, container, false);

        progressLinearLayout = view.findViewById(R.id.progressActivity);
        recyclerView = view.findViewById(R.id.rcvMovie);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        progressLinearLayout.showLoading();
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
                    progressLinearLayout.showContent();
                    Log.i("movie", Objects.requireNonNull(response.body()).getResults().toString());
                    ArrayList<ResultsItem> resultsItems = (ArrayList<ResultsItem>) response.body().getResults();
                    nowAdapter = new NowAdapter(getActivity(), resultsItems);
                    recyclerView.setAdapter(nowAdapter);
                } else {
                    progressLinearLayout.showEmpty(R.drawable.background, "No Movie",
                            "We could not establish a connection with our servers. Try again when you are connected to the internet.");
                }

            }

            @Override
            public void onFailure(@NonNull Call<NowResponse> call, @NonNull Throwable t) {
                progressLinearLayout.showError(R.drawable.ic_signal, "No Connection",
                        "We could not establish a connection with our servers. Try again when you are connected to the internet.",
                        "Try Again", errorClickListener);
                t.printStackTrace();
            }
        });
    }

    private View.OnClickListener errorClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            loadMovieNow();
        }
    };
}