package juniafirdaus.com.dicodingmovie.apirepository;

import juniafirdaus.com.dicodingmovie.BuildConfig;
import juniafirdaus.com.dicodingmovie.movienowplaying.model.NowResponse;
import juniafirdaus.com.dicodingmovie.movietoprated.model.TopRatedResponse;
import juniafirdaus.com.dicodingmovie.movieupcoming.model.UpResponse;
import juniafirdaus.com.dicodingmovie.searchmovie.model.SearchResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {

    @GET("search/movie?api_key=" + BuildConfig.TOKEN + BuildConfig.QUERY)
    Observable<SearchResponse> searchMovie(@Query("query") String movie);

    @GET(BuildConfig.MOVIE + "now_playing?api_key=" + BuildConfig.TOKEN)
    Observable<NowResponse> getNowPlaying();

    @GET(BuildConfig.MOVIE + "upcoming?api_key=" + BuildConfig.TOKEN)
    Observable<UpResponse> getUpcoming();


    @GET(BuildConfig.MOVIE + "top_rated?api_key=" + BuildConfig.TOKEN)
    Observable<TopRatedResponse>getToprated();
}
