package com.example.jessi.servicesapp;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.jessi.servicesapp.login.LogInDialog;

public class AppController extends Application {
    private static final String TAG = "AppController";
    private RequestQueue requestQueue;
    private static AppController mInstance;
    private User currentUser;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {

        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void alertUserError(String message, Context context) {

        LogInDialog logInDialog = new LogInDialog(context, message);
        logInDialog.show();
    }

    public void getCurrentUserPreferences() {

    }
}
