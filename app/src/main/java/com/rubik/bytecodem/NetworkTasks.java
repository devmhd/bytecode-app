package com.rubik.bytecodem;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Mehedee Zaman on 5/7/2015.
 */
public class NetworkTasks {

    public static boolean isNetworkConnected(Context appContext) {
        ConnectivityManager cm = (ConnectivityManager) appContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        return (cm.getActiveNetworkInfo() != null);
    }
}
