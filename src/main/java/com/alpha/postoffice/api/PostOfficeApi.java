package com.alpha.postoffice.api;

import com.alpha.postoffice.BuildConfig;
import com.alpha.postoffice.models.api.Movies;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by i24sm on 1/24/2017.
 */

public interface PostOfficeApi {

    @GET("movie/top_rated?api_key="+ BuildConfig.API_KEY)
    Observable<Movies> getMovies();
}
