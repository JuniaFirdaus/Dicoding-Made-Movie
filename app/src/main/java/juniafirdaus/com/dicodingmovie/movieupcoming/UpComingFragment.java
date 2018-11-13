package juniafirdaus.com.dicodingmovie.movieupcoming;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vlonjatg.progressactivity.ProgressLayout;

import java.util.ArrayList;
import java.util.Objects;

import juniafirdaus.com.dicodingmovie.R;
import juniafirdaus.com.dicodingmovie.apirepository.ApiService;
import juniafirdaus.com.dicodingmovie.apirepository.InitRetrofit;
import juniafirdaus.com.dicodingmovie.movieupcoming.modelupcoming.ResultsItem;
import juniafirdaus.com.dicodingmovie.movieupcoming.modelupcoming.UpResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpComingFragment extends Fragment {

    RecyclerView recyclerView;
    UpcomingAdapter upcomingAdapter;
    ProgressLayout progressLayout;

    public UpComingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_now, container, false);

       progressLayout = view.findViewById(R.id.progressActivity);
       recyclerView = view.findViewById(R.id.rcvMovie);
       recyclerView.setLayoutManager( new GridLayoutManager(getActivity(),2));
       progressLayout.showLoading();
       loadUpcoming();

       return view;
    }

    private void loadUpcoming() {

        ApiService apiService = InitRetrofit.getInstance();
        Call<UpResponse> upResponseCall = apiService.up_coming();
        upResponseCall.enqueue(new Callback<UpResponse>() {
            @Override
            public void onResponse(@NonNull Call<UpResponse> call, @NonNull Response<UpResponse> response) {
                if (response.isSuccessful()){
                    progressLayout.showContent();
                    ArrayList<ResultsItem> arrayList = (ArrayList<ResultsItem>)
                            Objects.requireNonNull(response.body()).getResults();
                    upcomingAdapter = new UpcomingAdapter(getActivity(), arrayList);
                    recyclerView.setAdapter(upcomingAdapter);
                }else {
                    progressLayout.showEmpty(R.drawable.background,"No Movie",
                            "We could not establish a connection with our servers. Try again when you are connected to the internet");

                }
            }

            @Override
            public void onFailure(@NonNull Call<UpResponse> call, @NonNull Throwable t) {
                t.printStackTrace();
                progressLayout.showError(R.drawable.ic_signal, "No Connection",
                        "We could not establish a connection with our servers. Try again when you are connected to the internet.",
                        "Try Again", errorClickListener);        }
        });
    }
    private View.OnClickListener errorClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            loadUpcoming();
        }
    };

}
