package com.solutionstouch.omsaifinance.util.localstorage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class LocalStorage {

    private static final String KEY_FIREBASE_TOKEN = "firebaseToken";
    public static final String KEY_USER = "User";
    public static final String KEY_USER_ADDRESS = "user_address";

    private static final String IS_USER_LOGIN = "IsUserLoggedIn";


    private static LocalStorage instance = null;
    SharedPreferences sharedPreferences;
    Editor editor;
    int PRIVATE_MODE = 0;
    Context _context;

    public LocalStorage(Context context) {
        sharedPreferences = context.getSharedPreferences("Preferences", 0);
    }

    public static LocalStorage getInstance(Context context) {
        if (instance == null) {
            synchronized (LocalStorage.class) {
                if (instance == null) {
                    instance = new LocalStorage(context);
                }
            }
        }
        return instance;
    }

    public void createUserLoginSession(String user) {
        editor = sharedPreferences.edit();
        editor.putBoolean(IS_USER_LOGIN, true);
        editor.putString(KEY_USER, user);
        editor.commit();
    }

    public String getUserLogin() {
        return sharedPreferences.getString(KEY_USER, "");
    }


    public void logoutUser() {
        editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    public boolean checkLogin() {
        // Check login status
        return !this.isUserLoggedIn();
    }


    public boolean isUserLoggedIn() {
        return sharedPreferences.getBoolean(IS_USER_LOGIN, false);
    }




    public String getToken() {
        return sharedPreferences.getString(KEY_FIREBASE_TOKEN, null);
    }

    public void setFirebaseToken(String retrofitToken) {
        editor = sharedPreferences.edit();
        editor.putString(KEY_FIREBASE_TOKEN, retrofitToken);
        editor.commit();
    }


}
