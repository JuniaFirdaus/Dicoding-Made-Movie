package juniafirdaus.com.dicodingmovie.movietoprated;

import android.annotation.SuppressLint;
import android.content.Context;
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
import java.util.List;

import juniafirdaus.com.dicodingmovie.BuildConfig;
import juniafirdaus.com.dicodingmovie.R;
import juniafirdaus.com.dicodingmovie.movietoprated.modeltoprated.ResultsItem;

public class TopRatedAdapter extends RecyclerView.Adapter<TopRatedAdapter.TopHolder> {

    private Context context;
    private List<ResultsItem> resultsItems;

    public TopRatedAdapter(Context context, List<ResultsItem> items){
        this.context = context;
        this.resultsItems = items;
    }

    @NonNull
    @Override
    public TopRatedAdapter.TopHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_movie, null);

        return new TopHolder(view);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull TopRatedAdapter.TopHolder topHolder, int i) {

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.background);
        topHolder.txtTitle.setText(resultsItems.get(i).getTitle());
        topHolder.txtDate.setText(resultsItems.get(i).getReleaseDate());

        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(BuildConfig.IMAGE + resultsItems.get(i)
                .getPosterPath()).into(topHolder.imageView);

        topHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return resultsItems.size();
    }

    class TopHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView txtTitle, txtDate;

        TopHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imgPoster);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDate = itemView.findViewById(R.id.txtGenre);
        }
    }

}
