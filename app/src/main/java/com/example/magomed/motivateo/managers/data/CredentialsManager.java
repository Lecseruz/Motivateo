package com.example.magomed.motivateo.managers.data;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import com.auth0.android.result.Credentials;
import com.example.magomed.motivateo.R;
import com.example.magomed.motivateo.net.utils.Constants;


public class CredentialsManager {

    public void saveCredentials(@NonNull Context context, @NonNull Credentials credentials) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.auth0_preferences), Context.MODE_PRIVATE);

        sharedPref.edit()
                .putString(Constants.ID_TOKEN, credentials.getIdToken())
                .putString(Constants.REFRESH_TOKEN, credentials.getRefreshToken())
                .putString(Constants.ACCESS_TOKEN, credentials.getAccessToken())
                .putString(Constants.CREDENTIAL_TYPE, credentials.getType())
                .apply();
    }

    @NonNull
    public Credentials getCredentials(@NonNull Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.auth0_preferences), Context.MODE_PRIVATE);

        return new Credentials(
                sharedPref.getString(Constants.ID_TOKEN, null),
                sharedPref.getString(Constants.ACCESS_TOKEN, null),
                sharedPref.getString(Constants.CREDENTIAL_TYPE, null),
                sharedPref.getString(Constants.REFRESH_TOKEN, null),
                null);
    }

    public void deleteCredentials(@NonNull Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.auth0_preferences), Context.MODE_PRIVATE);

        sharedPref.edit()
                .putString(Constants.ID_TOKEN, null)
                .putString(Constants.REFRESH_TOKEN, null)
                .putString(Constants.ACCESS_TOKEN, null)
                .putString(Constants.CREDENTIAL_TYPE, null)
                .apply();
    }
}