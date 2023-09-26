package com.challengers.of.call;

import android.content.Context;
import android.os.Bundle;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

import static com.facebook.FacebookSdk.getApplicationContext;

public class Firebaseutil {
    public static FirebaseJobDispatcher dispatcher;
    public static void schedulejob(Context context,String Loginid,String Contestid)
    {
        Bundle bundle = new Bundle();
        bundle.putString("Loginid", Loginid);
        bundle.putString("Contestid", Contestid);
        dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(getApplicationContext()));
        Job myJob = dispatcher.newJobBuilder()
                .setService(TestJobServicefirebase.class)
                .setTag("discover-sdk-tag")
                .setRecurring(false)
                .setLifetime(Lifetime.FOREVER)
                .setTrigger(Trigger.executionWindow(60,65))
                .setReplaceCurrent(true)
                .setRetryStrategy(RetryStrategy.DEFAULT_LINEAR)
                .setExtras(bundle)
                .build();
        dispatcher.mustSchedule(myJob);
    }


    public static void canclejob(Context context) {
        dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(getApplicationContext()));
        Job myJob = dispatcher.newJobBuilder()
                .setService(TestJobServicefirebase.class)
                .setTag("discover-sdk-tag")
                .setRecurring(false)
                .setLifetime(Lifetime.FOREVER)
                .setTrigger(Trigger.executionWindow(60,65))
                .setReplaceCurrent(true)
                .setRetryStrategy(RetryStrategy.DEFAULT_LINEAR)
                .build();
        dispatcher.cancelAll();
    }

}
