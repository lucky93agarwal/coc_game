package com.challengers.of.call;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Viewall extends AppCompatActivity {
    String displayText = "";
    JSONObject jasonObject;
    JSONArray jsonArray = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewall);
        displayText = getIntent().getStringExtra("data");
        AsyncCallWS task = new AsyncCallWS();
        task.execute();
    }

    public class AsyncCallWS extends AsyncTask<String, Void, Void> {
        String Contestcreated, TodayContestcreated, Contestwin, TodayContestwin, Contestlose, TodayContestlose, Contestamount, TodayContestamount, Challengejoin, TodayChallengejoin, Challengewin, TodayChallengewin, Challngeloose, TodayChallngeloose;

        @Override
        protected Void doInBackground(String... params) {
            //displayText = WebService.Selectchallenge(Loginid, "Selectcontestdetail");
            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
                try {
                    jasonObject = new JSONObject(displayText);
                    jsonArray = jasonObject.getJSONArray("Response");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
                        Contestcreated = jsonrowdata.getString("Contestcreated");
                        TodayContestcreated = jsonrowdata.getString("Contestcreatedtoday");
                        Contestwin = jsonrowdata.getString("Contestwin");
                        TodayContestwin = jsonrowdata.getString("Contestwintoday");
                        Contestlose = jsonrowdata.getString("Contestlose");
                        TodayContestlose = jsonrowdata.getString("Contestlosetoday");
                        Challengejoin = jsonrowdata.getString("Challengejoin");
                        TodayChallengejoin = jsonrowdata.getString("Challengejointoday");
                        Challengewin = jsonrowdata.getString("Challengewin");
                        TodayChallengewin = jsonrowdata.getString("Challengewintoday");
                        Challngeloose = jsonrowdata.getString("Challengelose");
                        TodayChallngeloose = jsonrowdata.getString("Challengelosetoday");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected void onPostExecute(Void result) {
            if (displayText.equalsIgnoreCase("connection fault")) {
                Toast.makeText(Viewall.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
            } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
                Toast.makeText(Viewall.this, "Please try after some times...", Toast.LENGTH_LONG).show();
            } else if (jsonArray.length() > 0) {
                TextView txtcontestcreated, txtcontestcreatedtoday, txtcontestwin, txtcontestwintoday, txtcontestlose, txtcontestlosetoday, txtcontestamount, txtcontestamounttoday, txtchallengejoin, txtchallengejointoday, txtchallengewon, txtchallengewontoday;
                txtcontestcreated = findViewById(R.id.textView);
                txtcontestcreated.setText("Total: " + Contestcreated);
                txtcontestcreatedtoday = findViewById(R.id.textView2);
                txtcontestcreatedtoday.setText("Today: " + TodayContestcreated);
                txtcontestwin = findViewById(R.id.textView3);
                txtcontestwin.setText("Total: " + Contestwin);
                txtcontestwintoday = findViewById(R.id.textView4);
                txtcontestwintoday.setText("Today: " + TodayContestwin);
                txtcontestlose = findViewById(R.id.textView5);
                txtcontestlose.setText("Total: " + Contestlose);
                txtcontestlosetoday = findViewById(R.id.textView6);
                txtcontestlosetoday.setText("Today: " + TodayContestlose);
                txtcontestamount = findViewById(R.id.textView7);
                txtcontestamount.setText("Total: " + Contestamount);
                txtcontestamounttoday = findViewById(R.id.textView8);
                txtcontestamounttoday.setText("Today: " + TodayContestamount);
                txtchallengejoin = findViewById(R.id.textView9);
                txtchallengejoin.setText("Total: " + Challengejoin);
                txtchallengejointoday = findViewById(R.id.textView10);
                txtchallengejointoday.setText("Today: " + TodayChallengejoin);
                txtchallengewon = findViewById(R.id.textView11);
                txtchallengewon.setText("Total: " + Challengewin);
                txtchallengewontoday = findViewById(R.id.textView12);
                txtchallengewontoday.setText("Total: " + TodayChallengewin);
            } else if (jsonArray.length() == 0) {
                Toast.makeText(Viewall.this, "No detail found", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(Viewall.this, "Try Again", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
}
