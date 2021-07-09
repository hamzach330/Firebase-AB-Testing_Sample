package com.example.sample.utils

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

class FirebaseLog_Agent_New {

    companion object {

        @Throws(Exception::class)
        fun logEvent(context: Context?, eventName: String, screenName: String, screenEvent: String) {
            val firebaseAnalytics = FirebaseAnalytics.getInstance(context!!);
            val bundle: Bundle? = null

            bundle?.putString(screenName, screenEvent);
            // bundle?.putString(FirebaseAnalytics.Param.ITEM_NAME, food.getName());

            //Logs an app event.
            firebaseAnalytics.logEvent(eventName, bundle);

            //Sets whether analytics collection is enabled for this app on this device.
            //firebaseAnalytics.setAnalyticsCollectionEnabled(true);

            //Sets the minimum engagement time required before starting a session. The default value is 10000 (10 seconds). Let's make it 20 seconds just for the fun
            //firebaseAnalytics.setMinimumSessionDuration(20000);

            //Sets the duration of inactivity that terminates the current session. The default value is 1800000 (30 minutes).
            //firebaseAnalytics.setSessionTimeoutDuration(500);

            //Sets the user ID property.
            //firebaseAnalytics.setUserId(String.valueOf(food.getId()));

            //Sets a user property to a given value.
            //firebaseAnalytics.setUserProperty("Food", food.getName());
        }

    }
}