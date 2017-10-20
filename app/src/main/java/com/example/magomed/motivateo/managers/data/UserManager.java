package com.example.magomed.motivateo.managers.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.example.magomed.motivateo.R;
import com.example.magomed.motivateo.net.utils.Constants;

/**
 * Created by magomed on 20.10.17.
 */

public class UserManager {
    public void saveUserID(@NonNull Context context, @NonNull String userID) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.auth0_preferences), Context.MODE_PRIVATE);


        sharedPref.edit()
                .putString(Constants.ID_USER, userID)
                .apply();
    }

    public String getUserID(@NonNull Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.auth0_preferences), Context.MODE_PRIVATE);
        return sharedPref.getString(Constants.ID_USER, null);
    }

    public void deleteUserID(@NonNull Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.auth0_preferences), Context.MODE_PRIVATE);
        sharedPref.edit()
                .putString(Constants.ID_USER, null)
                .apply();
    }
}
