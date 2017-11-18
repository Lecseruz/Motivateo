package com.example.magomed.motivateo.managers.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.example.magomed.motivateo.R;
import com.example.magomed.motivateo.models.User;
import com.example.magomed.motivateo.net.utils.Constants;

/**
 * Created by magomed on 20.10.17.
 */

public class UserManager {

    Context context;

    public UserManager(Context context) {
        this.context = context;
    }

    public void saveUserEmail(@NonNull String email) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.auth0_preferences), Context.MODE_PRIVATE);


        sharedPref.edit()
                .putString(Constants.EMAIL, email)
                .apply();
    }

    public String getUserEmail() {
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.auth0_preferences), Context.MODE_PRIVATE);
        return sharedPref.getString(Constants.EMAIL, null);
    }

    public void deleteUserEmail(){
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.auth0_preferences), Context.MODE_PRIVATE);
        sharedPref.edit()
                .putString(Constants.EMAIL, null)
                .apply();
    }

    public void saveUserID(@NonNull String userID) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.auth0_preferences), Context.MODE_PRIVATE);


        sharedPref.edit()
                .putString(Constants.ID_USER, userID)
                .apply();
    }

    public String getUserID() {
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.auth0_preferences), Context.MODE_PRIVATE);
        return sharedPref.getString(Constants.ID_USER, null);
    }

    public void deleteUserID() {
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.auth0_preferences), Context.MODE_PRIVATE);
        sharedPref.edit()
                .putString(Constants.ID_USER, null)
                .apply();
    }
}
