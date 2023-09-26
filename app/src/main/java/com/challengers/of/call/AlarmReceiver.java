package com.challengers.of.call;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;
import com.kaopiz.kprogresshud.KProgressHUD;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AlarmReceiver extends BroadcastReceiver {
Context mcontext;
    JSONObject jasonObject;
    JSONArray jsonArray = null;
    String Loginid="",Contestid="";
    @Override
    public void onReceive(Context arg0, Intent arg1) {
        mcontext=arg0;
        Loginid=arg1.getStringExtra("Loginid");
        Contestid=arg1.getStringExtra("Contestid");
        Toast.makeText(arg0, "Alarm Manager Running", Toast.LENGTH_SHORT).show();
    }



    public class AsyncCallWS extends AsyncTask<String, Void, Void> {
        String displayText;
        String Msg, Count;
        private KProgressHUD hud;

        @Override
        protected Void doInBackground(String... params) {

            displayText = WebService.Declareresult(Loginid, Contestid,"","", "Declareresult");
            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
                try {
                    jasonObject = new JSONObject(displayText);
                    jsonArray = jasonObject.getJSONArray("Response");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
                        Msg = jsonrowdata.getString("Msg");
                        Count = jsonrowdata.getString("Count");

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //hud.dismiss();
            if(displayText!=null) {
                if (displayText.equalsIgnoreCase("connection fault")) {
                   // Toast.makeText(, "Please Check your internet connection", Toast.LENGTH_LONG).show();

                } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
                    //Toast.makeText(Splash.this, "Please try after some times...", Toast.LENGTH_LONG).show();
                } else if (displayText.equalsIgnoreCase("Unable to connect to server")) {
                    //Toast.makeText(Splash.this, "Unable to connect to server...", Toast.LENGTH_LONG).show();
                } else if (jsonArray.length() > 0) {
                            SharedPreferences pref2 = mcontext.getSharedPreferences("UserData", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor1 = pref2.edit();
                            editor1.putString("Totalwallet", Count);
                            editor1.apply();

                } else {

                }
            }
        }

        @Override
        protected void onPreExecute() {
//            hud = KProgressHUD.create(Splash.this)
//                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//                    .setWindowColor(Color.parseColor("#000000"))
//                    .setLabel("Please Wait...")
//                    .setAnimationSpeed(1);
            //hud.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }
    }

}