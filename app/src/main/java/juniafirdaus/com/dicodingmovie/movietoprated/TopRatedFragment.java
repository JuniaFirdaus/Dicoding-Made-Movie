package juniafirdaus.com.dicodingmovie.movietoprated;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Objects;

import juniafirdaus.com.dicodingmovie.R;
import juniafirdaus.com.dicodingmovie.apirepository.ApiService;
import juniafirdaus.com.dicodingmovie.apirepository.InitRetrofit;
import juniafirdaus.com.dicodingmovie.movietoprated.modeltoprated.ResultsItem;
import juniafirdaus.com.dicodingmovie.movietoprated.modeltoprated.TopRatedResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopRatedFragment extends Fragment {

    RecyclerView recyclerView;
    TopRatedAdapter topRatedAdapter;

    public TopRatedFragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_now, container, false);

        recyclerView = view.findViewById(R.id.rcvMovie);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setHasFixedSize(true);

        loadTopRated();

        return view;
    }

    private void loadTopRated() {

        ApiService apiService = InitRetrofit.getInstance();
        Call<TopRatedResponse> ratedResponseCall = apiService.getToprated();
        ratedResponseCall.enqueue(new Callback<TopRatedResponse>() {
            @Override
            public void onResponse(@NonNull Call<TopRatedResponse> call, @NonNull Response<TopRatedResponse> response) {
                if (response.isSuccessful()) {
                    ArrayList<ResultsItem> resultsItems = (ArrayList<ResultsItem>)
                            Objects.requireNonNull(response.body()).getResults();
                    topRatedAdapter = new TopRatedAdapter(getActivity(), resultsItems);
                    recyclerView.setAdapter(topRatedAdapter);

                } else {
                    Log.i("Top Rated", response.message());

                }
            }

            @Override
            public void onFailure(@NonNull Call<TopRatedResponse> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });

    }

}
