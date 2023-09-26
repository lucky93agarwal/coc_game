package com.challengers.of.call.Notification;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.challengers.of.call.Dashboard;
import com.challengers.of.call.MainActivity;
import com.challengers.of.call.R;
import com.challengers.of.call.testing.SettingsPreferences;
import com.facebook.internal.ImageRequest;
import com.facebook.places.internal.LocationPackageManager;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import static com.challengers.of.call.Notification.NotificationActivity.CHANNEL_1_ID;

public class MyMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    private MyNotificationManager notificationUtils;

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        //Getting registration token

        //Displaying token on logcat
        Log.d(TAG, "Refreshed token: " + s);

        // Saving reg id to shared preferences
        StoreToken(s);
        Log.d("Instance ID ", FirebaseInstanceId.getInstance().getId());

    }
    private void StoreToken(String token) {

        //save the token in sharedPreferences
        SettingsPreferences.setDeviceToken(token,getApplicationContext());

    }
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if (remoteMessage.getData().size() > 0) {

            try {
                JSONObject json = new JSONObject(remoteMessage.getData().toString());
                sendPushNotification(json);
            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
        }

    }

    //this method will display the notification
    //We are passing the JSONObject that is received from
    //fireBase cloud messaging
    private void sendPushNotification(JSONObject json) {
        try {
            //getting the json data
            JSONObject data = json.getJSONObject("data");

            //parsing json data
            String title = data.getString("title");
            String message = data.getString("message");
            String imageUrl = data.getString("image");

            //creating MyNotificationManager object
            MyNotificationManager mNotificationManager = new MyNotificationManager(getApplicationContext());

            //creating an intent for the notification
            Intent intent = new Intent(getApplicationContext(), Dashboard.class);

            //if there is no image
            if (imageUrl.equals("null")) {
                //displaying small notification
                mNotificationManager.showSmallNotification(title, message, intent);
            } else {
                //if there is an image
                //displaying a big notification
                mNotificationManager.showBigNotification(title, message, imageUrl, intent);
            }
        } catch (JSONException e) {
            Log.e(TAG, "Json Exception: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }
}
