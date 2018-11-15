package juniafirdaus.com.dicodingmovie.movieupcoming.adapter;

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

import java.util.List;

import juniafirdaus.com.dicodingmovie.BuildConfig;
import juniafirdaus.com.dicodingmovie.R;
import juniafirdaus.com.dicodingmovie.movieupcoming.model.ResultsItem;

public class UpAdapter extends RecyclerView.Adapter<UpAdapter.UpHolder> {

    private List<ResultsItem> list;
    private int rowLayout;
    private Context context;

    public UpAdapter(List<ResultsItem> resultsItems, int rowLayout, Context context){
        this.context = context;
        this.rowLayout = rowLayout;
        this.list = resultsItems;
    }

    @NonNull
    @Override
    public UpAdapter.UpHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);

        return new UpHolder(view);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull UpAdapter.UpHolder upHolder, int i) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.background);
        upHolder.mTxtTitle.setText(list.get(i).getTitle());
        upHolder.mTxtGenre.setText(list.get(i).getReleaseDate());
        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(BuildConfig.IMAGE + list.get(i).getPosterPath())
                .into(upHolder.mImage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class UpHolder extends RecyclerView.ViewHolder{

        ImageView mImage;
        TextView mTxtGenre, mTxtTitle;

        UpHolder(@NonNull View itemView) {
            super(itemView);

            mImage = itemView.findViewById(R.id.imgPoster);
            mTxtGenre = itemView.findViewById(R.id.txtGenre);
            mTxtTitle = itemView.findViewById(R.id.txtTitle);

        }
    }
}
