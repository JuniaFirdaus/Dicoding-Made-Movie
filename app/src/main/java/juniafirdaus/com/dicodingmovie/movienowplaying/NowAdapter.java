package juniafirdaus.com.dicodingmovie.movienowplaying;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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
import juniafirdaus.com.dicodingmovie.movienowplaying.modelnowplaying.ResultsItem;
import juniafirdaus.com.dicodingmovie.R;

public class NowAdapter extends RecyclerView.Adapter<NowAdapter.NowHolder> {

    private Context context;
    private ArrayList<ResultsItem> arrayList;

    NowAdapter(Context context, ArrayList<ResultsItem> itemArrayList) {
        this.context = context;
        this.arrayList = itemArrayList;
    }

    @NonNull
    @Override
    public NowHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_movie, null);
        return new NowHolder(view);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull NowHolder nowHolder, @SuppressLint("RecyclerView") final int i) {

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.background);

        nowHolder.mTxtTitle.setText(arrayList.get(i).getTitle());
        nowHolder.mTxtGenre.setText(arrayList.get(i).getReleaseDate());

        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(BuildConfig.IMAGE + arrayList.get(i).getPosterPath())
                .into(nowHolder.mImage);

        nowHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("idMovie", arrayList.get(i).getId());
                intent.putExtra("title", arrayList.get(i).getTitle());
                intent.putExtra("image", arrayList.get(i).getBackdropPath());
                intent.putExtra("overview", arrayList.get(i).getOverview());
                intent.putExtra("date", arrayList.get(i).getReleaseDate());
                intent.putExtra("poster", arrayList.get(i).getPosterPath());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class NowHolder extends RecyclerView.ViewHolder {

        ImageView mImage;
        TextView mTxtGenre, mTxtTitle;

        NowHolder(@NonNull View itemView) {
            super(itemView);

            mImage = itemView.findViewById(R.id.imgPoster);
            mTxtGenre = itemView.findViewById(R.id.txtGenre);
            mTxtTitle = itemView.findViewById(R.id.txtTitle);
        }
    }
}
