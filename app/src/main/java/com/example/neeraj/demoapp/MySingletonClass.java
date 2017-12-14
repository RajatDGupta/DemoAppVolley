package com.example.neeraj.demoapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by neeraj on 6/12/17.
 */

public class MySingletonClass {

    private static MySingletonClass mySingletonClass;
    private static Context ctx;
    private RequestQueue requestQueue;

    public MySingletonClass(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized MySingletonClass getInstance(Context context) {
        if (mySingletonClass == null) {
            mySingletonClass = new MySingletonClass(context);
        }
        return mySingletonClass;
    }

    private RequestQueue getRequestQueue() {

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }


    public<T> void addToRequestQue (Request<T> request){
        requestQueue.add(request);
    }
}
