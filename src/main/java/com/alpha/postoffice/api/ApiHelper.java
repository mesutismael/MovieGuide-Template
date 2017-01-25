package com.alpha.postoffice.api;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;

import com.alpha.postoffice.BuildConfig;
import com.alpha.postoffice.contentProviders.MoviesContentProvider;
import com.alpha.postoffice.models.api.Movies;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by i24sm on 1/24/2017.
 */

public class ApiHelper {


    //public static String BASE_URL="http://test.saca.appreciate.be/api/v1/";
    private static PostOfficeApi service;

    public static PostOfficeApi getService() {
        if (service == null) {
            Interceptor logInterceptor = new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .addInterceptor(logInterceptor)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.HOST)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build();

            service = retrofit.create(PostOfficeApi.class);
        }
        return service;
    }

    public static Observable<Movies> getMovies(Context context) {
        return ApiHelper.getService().getMovies()
                .flatMap(movies ->
                {
                    if (movies != null) {
                        return Observable.just(movies);
                    } else {
                        String error = "Getting user tasks went wrong";
                        return Observable.error(new IOException(error));
                    }
                })
                .doOnNext(movies ->
                {
                    ContentResolver contentResolver = context.getContentResolver();
                    contentResolver.delete(MoviesContentProvider.CONTENT_URI, null, null);


                })
                .doOnNext(movies ->
                {
                    ContentResolver contentResolver = context.getContentResolver();
                    ContentValues[] cvMovies = movies.getMoviesContentValues();
                    contentResolver.bulkInsert(MoviesContentProvider.CONTENT_URI, cvMovies);

                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
