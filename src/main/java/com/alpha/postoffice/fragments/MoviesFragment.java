package com.alpha.postoffice.fragments;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alpha.postoffice.R;
import com.alpha.postoffice.adapters.MoviesSectionsPagerAdapter;
import com.alpha.postoffice.api.ApiHelper;
import com.alpha.postoffice.contentProviders.MoviesContentProvider;
import com.alpha.postoffice.database.MoviesTable;
import com.alpha.postoffice.models.api.Movies;
import com.alpha.postoffice.utils.Constants;

import rx.Observer;

public class MoviesFragment extends Fragment implements  LoaderManager.LoaderCallbacks<Cursor>{
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private MoviesSectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    public MoviesFragment() {
        // Required empty public constructor
    }
    public static MoviesFragment newInstance(String param1, String param2) {
        MoviesFragment fragment = new MoviesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);
        mSectionsPagerAdapter = new MoviesSectionsPagerAdapter(getActivity().getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) rootView.findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        this.getData();


        return rootView;
    }
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.getLoaderManager().initLoader(Constants.LOADER_MOVIES, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case Constants.LOADER_MOVIES:
                //String whereClauseMaterials = MoviesTable.TABLE_NAME + "." + MoviesTable.COLUMN_ID + "='" + task_id + "'";
                return new CursorLoader(MoviesFragment.this.getContext(), MoviesContentProvider.CONTENT_URI, null, null, null, null);
            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        DatabaseUtils.dumpCursor(data);
        switch (loader.getId()) {
            case Constants.LOADER_MOVIES:
             /*   timesheets = Timesheet.constructTimesheetListFromCursor(data);
                coworkersAdapter.setTimesheets(timesheets);
                recyclerView.setAdapter(coworkersAdapter);*/

                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    private void getData()
    {
            ApiHelper.getMovies(MoviesFragment.this.getContext()).subscribe(this.dataObserver);

    }

    private Observer<Movies> dataObserver = new Observer<Movies>() {


        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            Log.d("MainActivity", e.getMessage());
        }

        @Override
        public void onNext(Movies movies) {

        }


    };
}
