package com.example.kevin.bitdate;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;

/**
 * Created by kevin on 6/1/15.
 */
public class App extends Application {

    private static final String TAG = "App";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "app is run");
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "rgHspdCFiUuKeRqA6vtNjtGK1pLx6EtiuYAgyzgo", "jhzqFwzuqZN7xxk0LW4rFkQN37VRY0OCrE4XYEBa");

    }
}
