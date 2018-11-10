package juniafirdaus.com.dicodingmovie.movieupcoming;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import juniafirdaus.com.dicodingmovie.BuildConfig;
import juniafirdaus.com.dicodingmovie.DetailActivity;
import juniafirdaus.com.dicodingmovie.R;
import juniafirdaus.com.dicodingmovie.modelgenre.GenreResponse;
import juniafirdaus.com.dicodingmovie.movieupcoming.modelupcoming.ResultsItem;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.UpHolder> {

    private Context context;
    private ArrayList<ResultsItem> resultsItems;

    UpcomingAdapter(Context context, ArrayList<ResultsItem> arrayList){
        this.context = context;
        this.resultsItems = arrayList;
    }

    @NonNull
    @Override
    public UpHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_movie, null);

        return new UpHolder(view);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull UpHolder upHolder, final int i) {

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.background);

        Log.i("idGenre", String.valueOf(resultsItems.get(i).getGenreIds().get(0)));

        upHolder.mTxtGenre.setText(resultsItems.get(i).getReleaseDate());
        upHolder.mTxtTitle.setText(resultsItems.get(i).getTitle());
        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(BuildConfig.IMAGE + resultsItems.get(i).getPosterPath()).into(upHolder.mPoster);
        upHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("idMovie", resultsItems.get(i).getId());
                intent.putExtra("title", resultsItems.get(i).getTitle());
                intent.putExtra("image", resultsItems.get(i).getBackdropPath());
                intent.putExtra("overview", resultsItems.get(i).getOverview());
                intent.putExtra("date", resultsItems.get(i).getReleaseDate());
                intent.putExtra("poster", resultsItems.get(i).getPosterPath());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultsItems.size();
    }

    class UpHolder extends RecyclerView.ViewHolder{

        ImageView mPoster;
        TextView mTxtGenre, mTxtTitle;

        UpHolder(@NonNull View itemView) {
            super(itemView);

            mPoster = itemView.findViewById(R.id.imgPoster);
            mTxtGenre = itemView.findViewById(R.id.txtGenre);
            mTxtTitle = itemView.findViewById(R.id.txtTitle);

        }
    }
}
