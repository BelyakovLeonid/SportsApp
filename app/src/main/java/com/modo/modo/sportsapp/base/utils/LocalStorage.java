package com.modo.modo.sportsapp.base.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.modo.modo.sportsapp.model.network.GsonSerializer;

@SuppressWarnings("unused")
public class LocalStorage {

    private static final String APP_PREFERENCES = "APP_PREFERENCES";
    private static SharedPreferences mSharedPreferences;
    private Gson gson = GsonSerializer.getInstance().getGson();

    public LocalStorage(Activity activity) {
        mSharedPreferences =
                activity.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    public <T> T getData(String key, Class<T> classname) {
        if (mSharedPreferences.contains(key)) {
            return gson.fromJson(mSharedPreferences.getString(key, ""), classname);
        } else {
            return null;
        }
    }

    public <T> LocalStorage putData(String key, T object) {
        mSharedPreferences.edit().putString(key, gson.toJson(object)).apply();
        return this;
    }

}
