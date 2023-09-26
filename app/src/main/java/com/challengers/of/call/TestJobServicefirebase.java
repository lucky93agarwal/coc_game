package com.challengers.of.call;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.kaopiz.kprogresshud.KProgressHUD;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class TestJobServicefirebase extends JobService {
    public static final String TAG = "SyncService";
    JSONObject jasonObject;
    JSONArray jsonArray = null;
    String Loginid = "", Contestid = "";
    @Override
    public boolean onStartJob(JobParameters params) {
//        Toast.makeText(getApplicationContext(),"Job Started",Toast.LENGTH_LONG).show();
        Loginid = params.getExtras().getString("Loginid");
        Contestid = params.getExtras().getString("Contestid");
        Toast.makeText(getApplicationContext(), "Job Started", Toast.LENGTH_LONG).show();
        AsyncCallWS task = new AsyncCallWS();
        task.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
//        Toast.makeText(getApplicationContext(),"Job finished",Toast.LENGTH_LONG).show();
        Firebaseutil.canclejob(getApplicationContext());
        return true;
    }

    public class AsyncCallWS extends AsyncTask<String, Void, Void> {
        String displayText;
        String Msg, Count;
        private KProgressHUD hud;

        @Override
        protected Void doInBackground(String... params) {

            displayText = WebService.Declareresult(Loginid, Contestid, "", "", "Declareresult");
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
            if (displayText != null) {
                if (displayText.equalsIgnoreCase("connection fault")) {
                    // Toast.makeText(, "Please Check your internet connection", Toast.LENGTH_LONG).show();

                } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
                    //Toast.makeText(Splash.this, "Please try after some times...", Toast.LENGTH_LONG).show();
                } else if (displayText.equalsIgnoreCase("Unable to connect to server")) {
                    //Toast.makeText(Splash.this, "Unable to connect to server...", Toast.LENGTH_LONG).show();
                } else if (jsonArray.length() > 0) {
                    SharedPreferences pref2 = getApplicationContext().getSharedPreferences("UserData", Context.MODE_PRIVATE);
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

