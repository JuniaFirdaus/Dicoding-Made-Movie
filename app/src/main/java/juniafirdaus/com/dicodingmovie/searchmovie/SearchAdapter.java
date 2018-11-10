package juniafirdaus.com.dicodingmovie.searchmovie;

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

import juniafirdaus.com.dicodingmovie.BuildConfig;
import juniafirdaus.com.dicodingmovie.R;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchHolder> {

    private Context mContext;
    private ArrayList<ResultsItem> resultsItems;

    public SearchAdapter(Context context, ArrayList<ResultsItem> resultsItemArrayList){
        this.mContext = context;
        this.resultsItems = resultsItemArrayList;
    }

    @NonNull
    @Override
    public SearchAdapter.SearchHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_movie, null);
        return new SearchHolder(view);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.SearchHolder searchHolder, int i) {

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.background);

        searchHolder.mTxtTitle.setText(resultsItems.get(i).getTitle());
        searchHolder.mTxtDate.setText(resultsItems.get(i).getReleaseDate());
        Glide.with(mContext)
                .setDefaultRequestOptions(requestOptions)
                .load(BuildConfig.IMAGE + resultsItems.get(i).getPosterPath())
                .into(searchHolder.mImgPoster);


    }

    @Override
    public int getItemCount() {
        return resultsItems.size();
    }

    class SearchHolder extends RecyclerView.ViewHolder{

        ImageView mImgPoster;
        TextView  mTxtTitle,  mTxtDate;

        SearchHolder(@NonNull View itemView) {
            super(itemView);

            mTxtTitle = itemView.findViewById(R.id.txtTitle);
            mTxtDate = itemView.findViewById(R.id.txtGenre);
            mImgPoster = itemView.findViewById(R.id.imgPoster);

        }
    }

}
