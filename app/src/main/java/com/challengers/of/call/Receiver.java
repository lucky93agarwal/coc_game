package com.challengers.of.call;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

public class Receiver  extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String referrer;
        referrer = intent.getStringExtra("referrer");
        SharedPreferences prefs = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("referrer",referrer);
        editor.apply();
        Toast.makeText(context,"App Installed",Toast.LENGTH_LONG).show();
        //}
    }
}
