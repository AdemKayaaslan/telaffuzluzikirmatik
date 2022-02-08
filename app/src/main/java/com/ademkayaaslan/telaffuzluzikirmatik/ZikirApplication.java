package com.ademkayaaslan.telaffuzluzikirmatik;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class ZikirApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Firebase
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setApplicationId(getString(R.string.google_app_id)) // Required for Analytics.
                .setApiKey(getString(R.string.google_api_key)) // Required for Auth.
                .build();
        FirebaseApp.initializeApp(this, options, "Zikirmatik");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
