package com.alpha.postoffice.contentProviders;

/**
 * Created by i24sm on 1/24/2017.
 */

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import com.alpha.postoffice.database.DatabaseHelper;
import com.alpha.postoffice.database.MoviesTable;

public class MoviesContentProvider extends ContentProvider
{
    private DatabaseHelper databaseHelper;

    private static final String PROVIDER_NAME = "com.alpha.postoffice.contentProviders.MoviesContentProvider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/");
    private static final int MOVIE = 1;
    private static final UriMatcher URI_MATCHER;

    static
    {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(PROVIDER_NAME, null, MOVIE);
    }

    @Override
    public boolean onCreate()
    {
        this.databaseHelper = new DatabaseHelper(this.getContext());
        return false;
    }

    @Nullable
    @Override
    public String getType(Uri uri)
    {
        switch (URI_MATCHER.match(uri))
        {
            case MOVIE:
                return "vnd.android.cursor.dir/" + PROVIDER_NAME;
        }
        return null;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
    {
        SQLiteDatabase db;
        SQLiteQueryBuilder queryBuilder;
        Cursor cursor;

        switch (URI_MATCHER.match(uri))
        {
            case MOVIE:
                db = this.databaseHelper.getReadableDatabase();
                queryBuilder = new SQLiteQueryBuilder();

                queryBuilder.setTables(MoviesTable.TABLE_NAME);
                queryBuilder.setProjectionMap(MoviesTable.PROJECTION_MAP);

                cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
                cursor.setNotificationUri(this.getContext().getContentResolver(), CONTENT_URI);
                return cursor;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values)
    {
        switch (URI_MATCHER.match(uri))
        {
            case MOVIE:
                SQLiteDatabase db = this.databaseHelper.getWritableDatabase();
                long rowID = db.replace(MoviesTable.TABLE_NAME, null, values);

                if (rowID > 0)
                {
                    this.getContext().getContentResolver().notifyChange(CONTENT_URI, null);
                    return CONTENT_URI;
                } else
                {
                    throw new SQLException("Failed to add a record into " + uri);
                }

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs)
    {
        switch (URI_MATCHER.match(uri))
        {
            case MOVIE:
                SQLiteDatabase db = this.databaseHelper.getWritableDatabase();
                int rowsDeleted = db.delete(MoviesTable.TABLE_NAME, selection, selectionArgs);

                if (rowsDeleted > 0)
                {
                    this.getContext().getContentResolver().notifyChange(CONTENT_URI, null);
                }

                return rowsDeleted;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs)
    {
        switch (URI_MATCHER.match(uri))
        {
            case MOVIE:
                SQLiteDatabase db = this.databaseHelper.getWritableDatabase();
                int rowsUpdated = db.update(MoviesTable.TABLE_NAME, values, selection, selectionArgs);

                if (rowsUpdated > 0)
                {
                    this.getContext().getContentResolver().notifyChange(CONTENT_URI, null);
                }

                return rowsUpdated;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }


    @Override
    public int bulkInsert(Uri uri, ContentValues[] values)
    {
        switch (URI_MATCHER.match(uri))
        {
            case MOVIE:
                int rowsInserted = 0;
                SQLiteDatabase db = this.databaseHelper.getWritableDatabase();
                DatabaseUtils.InsertHelper inserter = new DatabaseUtils.InsertHelper(db, MoviesTable.TABLE_NAME);

                db.beginTransaction();

                try
                {
                    if (values != null)
                    {
                        for (ContentValues cv : values)
                        {
                            if (cv != null)
                            {
                                inserter.prepareForInsert();
                                inserter.bind(inserter.getColumnIndex(MoviesTable.COLUMN_MOVIE_ID), cv.getAsString(MoviesTable.COLUMN_MOVIE_ID));
                                inserter.bind(inserter.getColumnIndex(MoviesTable.COLUMN_RELEASE_DATE), cv.getAsString(MoviesTable.COLUMN_RELEASE_DATE));
                                inserter.bind(inserter.getColumnIndex(MoviesTable.COLUMN_CATEGORY), cv.getAsString(MoviesTable.COLUMN_CATEGORY));
                                inserter.bind(inserter.getColumnIndex(MoviesTable.COLUMN_VIDEO), cv.getAsString(MoviesTable.COLUMN_VIDEO));
                                inserter.bind(inserter.getColumnIndex(MoviesTable.COLUMN_VOTE_COUNT), cv.getAsString(MoviesTable.COLUMN_VOTE_COUNT));
                                inserter.bind(inserter.getColumnIndex(MoviesTable.COLUMN_VOTE_AVERAGE), cv.getAsString(MoviesTable.COLUMN_VOTE_AVERAGE));
                                inserter.bind(inserter.getColumnIndex(MoviesTable.COLUMN_OVERVIEW), cv.getAsString(MoviesTable.COLUMN_OVERVIEW));
                                inserter.bind(inserter.getColumnIndex(MoviesTable.COLUMN_ORIGINAL_TITLE), cv.getAsString(MoviesTable.COLUMN_ORIGINAL_TITLE));
                                inserter.bind(inserter.getColumnIndex(MoviesTable.COLUMN_ORIGNAL_LANGUAGE), cv.getAsString(MoviesTable.COLUMN_ORIGNAL_LANGUAGE));
                                inserter.bind(inserter.getColumnIndex(MoviesTable.COLUMN_BACKDROP_PATH), cv.getAsString(MoviesTable.COLUMN_BACKDROP_PATH));
                                inserter.bind(inserter.getColumnIndex(MoviesTable.COLUMN_ADULT), cv.getAsString(MoviesTable.COLUMN_ADULT));
                                inserter.bind(inserter.getColumnIndex(MoviesTable.COLUMN_POPULARITY), cv.getAsString(MoviesTable.COLUMN_POPULARITY));
                                inserter.bind(inserter.getColumnIndex(MoviesTable.COLUMN_TITLE), cv.getAsString(MoviesTable.COLUMN_TITLE));
                                inserter.bind(inserter.getColumnIndex(MoviesTable.COLUMN_POSTER_PATH), cv.getAsString(MoviesTable.COLUMN_POSTER_PATH));

                                long rowId = inserter.execute();

                                if (rowId != -1)
                                {
                                    rowsInserted++;
                                }
                            }
                        }
                    }

                    db.setTransactionSuccessful();
                } catch (Exception e)
                {
                    e.printStackTrace();
                    rowsInserted = 0;
                } finally
                {
                    db.endTransaction();
                    inserter.close();
                }

                if (rowsInserted > 0)
                {
                    this.getContext().getContentResolver().notifyChange(CONTENT_URI, null);
                }

                return rowsInserted;

            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

}

