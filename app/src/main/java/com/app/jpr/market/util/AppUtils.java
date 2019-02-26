package com.app.jpr.market.util;

import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/**
 * written by Shubham Dwivedi
 */


public class AppUtils {
   static ProgressDialog pDialog;

    /**
     * method to check internet connection
     * @param context
     * @return
     */
    public static boolean isInternetConnected(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager)
                context.getSystemService(Service.CONNECTIVITY_SERVICE);

           if (connectivity != null){
               NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {

                    return true;
                }
            }
        }

        return false;
     }




    public static void showProgressDialog(Context context, String s) {
        if (pDialog != null) {
            pDialog.dismiss();
        }
        try {
            pDialog = new ProgressDialog(context);
            pDialog.setMessage("Please wait....");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(false);
            pDialog.setCanceledOnTouchOutside(false);
            pDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void dismisDialog() {
        if (pDialog!=null && pDialog.isShowing()){
            pDialog.dismiss();
        }
    }
}

