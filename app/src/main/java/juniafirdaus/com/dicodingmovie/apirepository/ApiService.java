package juniafirdaus.com.dicodingmovie.apirepository;

import juniafirdaus.com.dicodingmovie.BuildConfig;
import juniafirdaus.com.dicodingmovie.movienowplaying.modelnowplaying.NowResponse;
import juniafirdaus.com.dicodingmovie.modelgenre.GenreResponse;
import juniafirdaus.com.dicodingmovie.movietoprated.modeltoprated.TopRatedResponse;
import juniafirdaus.com.dicodingmovie.movieupcoming.modelupcoming.UpResponse;
import juniafirdaus.com.dicodingmovie.searchmovie.SearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET(BuildConfig.MOVIE + "now_playing?api_key=" + BuildConfig.TOKEN)
    Call<NowResponse> now_playing();

    @GET(BuildConfig.MOVIE + "upcoming?api_key=" + BuildConfig.TOKEN)
    Call<UpResponse> up_coming();

    @GET(BuildConfig.GENRE + BuildConfig.MOVIE + "list?api_key=" + BuildConfig.TOKEN)
    Call<GenreResponse> getGenre();

    @GET("search/movie?api_key=" + BuildConfig.TOKEN + BuildConfig.QUERY)
    Call<SearchResponse>search_movie(@Query("query") String movie);

    @GET(BuildConfig.MOVIE + "top_rated?api_key=" + BuildConfig.TOKEN)
    Call<TopRatedResponse>getToprated();
}
