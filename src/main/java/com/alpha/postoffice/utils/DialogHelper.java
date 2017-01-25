package com.alpha.postoffice.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * Created by i24sm on 1/24/2017.
 */

public class DialogHelper {
    public static boolean canShowDialog(Fragment fragment)
    {
        FragmentActivity activity = fragment.getActivity();
        boolean finishing = activity == null || activity.isFinishing();
        boolean destroyed = activity == null || activity.getSupportFragmentManager() == null || activity.getSupportFragmentManager().isDestroyed();

        return !finishing && !destroyed;
    }
}
