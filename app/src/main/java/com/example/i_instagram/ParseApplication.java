package com.example.i_instagram;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    public static final String TAG = "ParseApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Post.class);
        // set applicationId, and server server based on the values in the Heroku settings.
        // clientKey is not needed unless explicitly configured
        // any network interceptors must be added with the Configuration Builder given this syntax
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("i-instagram") // should correspond to APP_ID env variable
                .clientKey("B0b6yi3Lik3sT0Sl33p")  // set explicitly unless clientKey is explicitly configured on Parse server
                .server("http://i-instagram.herokuapp.com/parse").build());

        Log.i(TAG, "success");
    }
}
