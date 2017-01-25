package com.alpha.postoffice.database;

import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by i24sm on 1/24/2017.
 */
public class MoviesTable {
    public static final String TABLE_NAME = "movies";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_MOVIE_ID = "movie_id";
    public static final String COLUMN_POSTER_PATH = "poster_path";
    public static final String COLUMN_ADULT = "adult";
    public static final String COLUMN_OVERVIEW = "overview";
    public static final String COLUMN_RELEASE_DATE = "release_date";
    public static final String COLUMN_ORIGINAL_TITLE = "original_title";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_BACKDROP_PATH = "back_drop_path";
    public static final String COLUMN_POPULARITY = "popularity";
    public static final String COLUMN_VOTE_COUNT = "vote_count";
    public static final String COLUMN_VIDEO = "video";
    public static final String COLUMN_VOTE_AVERAGE = "vote_average";
    public static final String COLUMN_ORIGNAL_LANGUAGE = "original_language";
    public static final String COLUMN_CATEGORY = "category";




    //All column names here
    public static final String COLUMN_ID_FULL = TABLE_NAME + "." + COLUMN_ID;
    public static final String COLUMN_MOVIE_ID_FULL = TABLE_NAME + "." + COLUMN_MOVIE_ID;
    public static final String COLUMN_POSTER_PATH_FULL = TABLE_NAME + "." + COLUMN_POSTER_PATH;
    public static final String COLUMN_ADULT_FULL = TABLE_NAME + "." + COLUMN_ADULT;
    public static final String COLUMN_OVERVIEW_FULL = TABLE_NAME + "." + COLUMN_OVERVIEW;
    public static final String COLUMN_RELEASE_DATE_FULL = TABLE_NAME + "." + COLUMN_RELEASE_DATE;
    public static final String COLUMN_ORIGINAL_TITLE_FULL = TABLE_NAME + "." + COLUMN_ORIGINAL_TITLE;
    public static final String COLUMN_TITLE_FULL = TABLE_NAME + "." + COLUMN_TITLE;
    public static final String COLUMN_BACKDROP_PATH_FULL = TABLE_NAME + "." + COLUMN_BACKDROP_PATH;
    public static final String COLUMN_POPULARITY_FULL = TABLE_NAME + "." + COLUMN_POPULARITY;
    public static final String COLUMN_VOTE_COUNT_FULL = TABLE_NAME + "." + COLUMN_VOTE_COUNT;
    public static final String COLUMN_VIDEO_FULL = TABLE_NAME + "." + COLUMN_VIDEO;
    public static final String COLUMN_VOTE_AVERAGE_FULL = TABLE_NAME + "." + COLUMN_VOTE_AVERAGE;
    public static final String COLUMN_ORIGNAL_LANGUAGE_FULL = TABLE_NAME + "." + COLUMN_ORIGNAL_LANGUAGE;
    public static final String COLUMN_CATEGORY_FULL = TABLE_NAME + "." + COLUMN_CATEGORY;


    //all column alias here
    public static final String COLUMN_ID_ALIAS = TABLE_NAME + "_" + COLUMN_ID;
    public static final String COLUMN_MOVIE_ID_ALIAS = TABLE_NAME + "_" + COLUMN_MOVIE_ID;
    public static final String COLUMN_POSTER_PATH_ALIAS = TABLE_NAME + "_" + COLUMN_POSTER_PATH;
    public static final String COLUMN_ADULT_ALIAS = TABLE_NAME + "_" + COLUMN_ADULT;
    public static final String COLUMN_OVERVIEW_ALIAS = TABLE_NAME + "_" + COLUMN_OVERVIEW;
    public static final String COLUMN_RELEASE_DATE_ALIAS = TABLE_NAME + "_" + COLUMN_RELEASE_DATE;
    public static final String COLUMN_ORIGINAL_TITLE_ALIAS = TABLE_NAME + "_" + COLUMN_ORIGINAL_TITLE;
    public static final String COLUMN_TITLE_ALIAS = TABLE_NAME + "_" + COLUMN_TITLE;
    public static final String COLUMN_BACKDROP_PATH_ALIAS = TABLE_NAME + "_" + COLUMN_BACKDROP_PATH;
    public static final String COLUMN_POPULARITY_ALIAS = TABLE_NAME + "_" + COLUMN_POPULARITY;
    public static final String COLUMN_VOTE_COUNT_ALIAS = TABLE_NAME + "_" + COLUMN_VOTE_COUNT;
    public static final String COLUMN_VIDEO_ALIAS = TABLE_NAME + "_" + COLUMN_VIDEO;
    public static final String COLUMN_VOTE_AVERAGE_ALIAS = TABLE_NAME + "_" + COLUMN_VOTE_AVERAGE;
    public static final String COLUMN_ORIGNAL_LANGUAGE_ALIAS = TABLE_NAME + "_" + COLUMN_ORIGNAL_LANGUAGE;
    public static final String COLUMN_CATEGORY_ALIAS = TABLE_NAME + "_" + COLUMN_CATEGORY;

    public static final Map<String, String> PROJECTION_MAP;

    static {
        PROJECTION_MAP = new HashMap<>();
        PROJECTION_MAP.put(COLUMN_ID_FULL, COLUMN_ID_FULL + " AS " + COLUMN_ID_ALIAS);
        PROJECTION_MAP.put(COLUMN_MOVIE_ID_FULL, COLUMN_MOVIE_ID_FULL + " AS " + COLUMN_MOVIE_ID_ALIAS);
        PROJECTION_MAP.put(COLUMN_POSTER_PATH_FULL, COLUMN_POSTER_PATH_FULL + " AS " + COLUMN_POSTER_PATH_ALIAS);
        PROJECTION_MAP.put(COLUMN_ADULT_FULL, COLUMN_ADULT_FULL + " AS " + COLUMN_ADULT_ALIAS);
        PROJECTION_MAP.put(COLUMN_OVERVIEW_FULL, COLUMN_OVERVIEW_FULL + " AS " + COLUMN_OVERVIEW_ALIAS);
        PROJECTION_MAP.put(COLUMN_RELEASE_DATE_FULL, COLUMN_RELEASE_DATE_FULL + " AS " + COLUMN_RELEASE_DATE_ALIAS);
        PROJECTION_MAP.put(COLUMN_ORIGINAL_TITLE_FULL, COLUMN_ORIGINAL_TITLE_FULL + " AS " + COLUMN_ORIGINAL_TITLE_ALIAS);
        PROJECTION_MAP.put(COLUMN_TITLE_FULL, COLUMN_TITLE_FULL + " AS " + COLUMN_TITLE_ALIAS);
        PROJECTION_MAP.put(COLUMN_BACKDROP_PATH_FULL, COLUMN_BACKDROP_PATH_FULL + " AS " + COLUMN_BACKDROP_PATH_ALIAS);
        PROJECTION_MAP.put(COLUMN_POPULARITY_FULL, COLUMN_POPULARITY_FULL + " AS " + COLUMN_POPULARITY_ALIAS);
        PROJECTION_MAP.put(COLUMN_VOTE_COUNT_FULL, COLUMN_VOTE_COUNT_FULL + " AS " + COLUMN_VOTE_COUNT_ALIAS);
        PROJECTION_MAP.put(COLUMN_VIDEO_FULL, COLUMN_VIDEO_FULL + " AS " + COLUMN_VIDEO_ALIAS);
        PROJECTION_MAP.put(COLUMN_VOTE_AVERAGE_FULL, COLUMN_VOTE_AVERAGE_FULL + " AS " + COLUMN_VOTE_AVERAGE_ALIAS);
        PROJECTION_MAP.put(COLUMN_ORIGNAL_LANGUAGE_FULL, COLUMN_ORIGNAL_LANGUAGE_FULL + " AS " + COLUMN_ORIGNAL_LANGUAGE_ALIAS);
        PROJECTION_MAP.put(COLUMN_CATEGORY_FULL, COLUMN_CATEGORY_FULL + " AS " + COLUMN_CATEGORY_ALIAS);

    }

    private static final String CREATE_TABLE = "create table IF NOT EXISTS " + TABLE_NAME + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_MOVIE_ID + " text , "
            + COLUMN_POSTER_PATH + " text , "
            + COLUMN_ADULT + " text , "
            + COLUMN_OVERVIEW + " text , "
            + COLUMN_ORIGINAL_TITLE + " text , "
            + COLUMN_TITLE + " text , "
            + COLUMN_CATEGORY + " text , "
            + COLUMN_VIDEO + " text , "
            + COLUMN_POPULARITY + " text , "
            + COLUMN_VOTE_AVERAGE + " text , "
            + COLUMN_VOTE_COUNT + " text , "
            + COLUMN_BACKDROP_PATH + " text , "
            + COLUMN_ORIGNAL_LANGUAGE + " text , "
            + COLUMN_RELEASE_DATE + " text  "

            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        while (oldVersion < newVersion) {
            switch (oldVersion) {
                default:
                    database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
                    onCreate(database);
                    break;
            }
            oldVersion++;
        }

    }
}