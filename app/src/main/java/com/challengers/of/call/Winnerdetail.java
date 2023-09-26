package com.challengers.of.call;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.RotatingCircularDotsLoader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.challengers.of.call.Banned_APP.bannedapp;
import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.FiFityAPI.FifityapiRequestJson;
import com.challengers.of.call.FiFityAPI.FiftyfityResponseJson;
import com.challengers.of.call.GameTypes.GametypesActivity;
import com.challengers.of.call.Retrofit.ServiceGenerator;
import com.challengers.of.call.Retrofit.UserService;
import com.challengers.of.call.WinnerdetailAPI.WinnerdetailsRequestJson;
import com.challengers.of.call.WinnerdetailAPI.WinnerdetailsResponseJson;
import com.challengers.of.call.helper.GlideCircleTransformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.decrypt;
import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.encrypt;

public class Winnerdetail extends AppCompatActivity {

    JSONObject jasonObject;
    public static Activity winnerdetail;
    JSONArray jsonArray = null;
    String displayText="",Loginid="",Customimage="",Winnername,LoginAnother = "";
    ImageView imageView,image;
    String Backmessage;
//    AsyncCallWS task;
    String createdchallenge, TodayContestcreated, joinedchallenge, TodayContestwin,winning, Totalpayedchallenge,losechallenge, TodayContestlose, Contestamount, TodayContestamount, wonchallenge, TodayChallengejoin, Challengewin, TodayChallengewin, Challngeloose, TodayChallngeloose;
    String Contestwon, Todaycontestwon, Contestplayed, Todaycontestplay;
    private ljsflsj good;
    public String GameName;
    private static bannedapp bannedapps;
    public Dialog unepicDialog, progressbar;
    public static int getResourseId(Context context, String pVariableName, String pResourcename, String pPackageName) throws RuntimeException {
        try {
            return context.getResources().getIdentifier(pVariableName, pResourcename, pPackageName);
        } catch (Exception e) {
            throw new RuntimeException("Error getting Resource ID.", e);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        bannedapps = new bannedapp(Winnerdetail.this);
        bannedapps.bannedbig();
    }

    @Override
    protected void onPause() {
        super.onPause();
        bannedapps = new bannedapp(Winnerdetail.this);
        bannedapps.bannedbig();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testtest);
        bannedapps = new bannedapp(Winnerdetail.this);
        bannedapps.bannedbig();
        progressbar = new Dialog(this);
        SharedPreferences sharedPreferences = getSharedPreferences("coding",Context.MODE_PRIVATE);
        GameName = sharedPreferences.getString("gametype",null);
        LoginAnother = getIntent().getStringExtra("Winnerid");

        Winnername=getIntent().getStringExtra("Name");
        Customimage=getIntent().getStringExtra("image");

        TextView textView = findViewById(R.id.username);
        Winnername = decrypt(good.key, Winnername);
        textView.setText(Winnername);

        imageView = findViewById(R.id.image);
        Backmessage = getIntent().getStringExtra("goto");
        image = findViewById(R.id.image2);
        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String Loginid = prefs.getString("Loginid", null);
        String Imagename = prefs.getString("drawable", null);

        Imagename = decrypt(good.key, Imagename);
        Customimage = decrypt(good.key, Customimage);
        if (Imagename != null) {
            image.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
            int imageId = getResourseId(this, Imagename, "drawable", getPackageName());
            imageView.setImageResource(imageId);
        }
        else if (Customimage.length() <=20) {
            if (Customimage != null && Customimage != "") {
                image.setVisibility(View.VISIBLE);
                Glide.with(getApplicationContext()).load("http://callofchallengers.com/coc/images/users/" + Customimage)
                        .crossFade()
                        .placeholder(R.drawable.logo)
                        .error(R.drawable.logo)
                        .bitmapTransform(new GlideCircleTransformation(getApplicationContext()))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .skipMemoryCache(false)
                        .into(image);
                imageView.setVisibility(View.GONE);
            }
        }else {
            image.setVisibility(View.VISIBLE);
            Glide.with(getApplicationContext()).load(Customimage)
                    .crossFade()
                    .placeholder(R.drawable.logo)
                    .error(R.drawable.logo)
                    .bitmapTransform(new GlideCircleTransformation(getApplicationContext()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(false)
                    .into(image);
            imageView.setVisibility(View.GONE);

        }



//
//        task=new AsyncCallWS();
//        task.execute();
        progressbar.setContentView(R.layout.progresbarlayout);
        progressbar.setCancelable(false);
        RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader)progressbar.findViewById(R.id.progress);

        RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Winnerdetail.this,
                20, 60, ContextCompat.getColor(Winnerdetail.this, R.color.white));
        loader.setAnimDuration(3000);

        rotatingCircularDotsLoader.addView(loader);

        progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
        progressbar.show();


        GetDataAPI();




        ImageView ivback = findViewById(R.id.ivback);
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Backmessage.equalsIgnoreCase("fragmentresult")){
                    Intent intent=new Intent(Winnerdetail.this,Main2Activity.class);
                    intent.putExtra("goto","Result");

                    startActivity(intent);
                    finish();
                }else if (Backmessage.equalsIgnoreCase("challenger")){
                    Intent intent = new Intent(Winnerdetail.this,Main2Activity.class);
                    intent.putExtra("goto","challenger");
                    startActivity(intent);
                    finish();
                }else if (Backmessage.equalsIgnoreCase("Leader")){
                    Intent intent = new Intent(Winnerdetail.this,Leaderboard.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (Backmessage.equalsIgnoreCase("fragmentresult")){
            Intent intent=new Intent(Winnerdetail.this,Main2Activity.class);
            intent.putExtra("goto","Result");

            startActivity(intent);
           finish();
        }else if (Backmessage.equalsIgnoreCase("challenger")){
            Intent intent = new Intent(Winnerdetail.this,Main2Activity.class);
            intent.putExtra("goto","challenger");
            startActivity(intent);
            finish();
        }else if (Backmessage.equalsIgnoreCase("Leader")){
            Intent intent = new Intent(Winnerdetail.this,Leaderboard.class);
            startActivity(intent);
            finish();
        }

    }


    private void GetDataAPI() {

        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);

        String Password = prefs.getString("Password", null);
        String Usernames = prefs.getString("Name", null);


        final WinnerdetailsRequestJson request = new WinnerdetailsRequestJson();

        request.setLoginid(decrypt(good.key, LoginAnother));




        UserService service = ServiceGenerator.createService(UserService.class, decrypt(good.key, Usernames), decrypt(good.key, Password));
        service.winnerdetails(request).enqueue(new Callback<WinnerdetailsResponseJson>() {
            @Override
            public void onResponse(Call<WinnerdetailsResponseJson> call, Response<WinnerdetailsResponseJson> response) {
                if (response.isSuccessful()) {
                    for (int z = 0; z < response.body().data.size(); z++) {
                    createdchallenge =  response.body().data.get(z).createdchallenge;
                    joinedchallenge = response.body().data.get(z).joinedchallenge;
                    Totalpayedchallenge = response.body().data.get(z).Totalpayedchallenge;
                    wonchallenge = response.body().data.get(z).wonchallenge;
                    losechallenge = response.body().data.get(z).losechallenge;
                    winning = response.body().data.get(z).winning;


                    createdchallenge = encrypt(good.key, good.initVector, createdchallenge);
                    joinedchallenge = encrypt(good.key, good.initVector, joinedchallenge);
                    Totalpayedchallenge = encrypt(good.key, good.initVector, Totalpayedchallenge);
                    wonchallenge = encrypt(good.key, good.initVector, wonchallenge);
                    losechallenge = encrypt(good.key, good.initVector, losechallenge);
                    winning = encrypt(good.key, good.initVector, winning);
                    }




                    TextView txtcontestcreated, txtcontestwin, txtchallengejoin,textplayerdcontestnew, txtchallengewin,txtchallengewonnew,txtplayerdcontestnew, txtchallengewon, txttodaychallengewon, txtplayerdcontest, txttodayplayedcontest;

                    txtcontestcreated = findViewById(R.id.txtcontestcreated);
                    txtchallengejoin = findViewById(R.id.txtchallengejoin);
                    txtchallengewon = findViewById(R.id.txtchallengewon);
                    txtchallengewonnew = findViewById(R.id.txtchallengewonnew);
                    txtplayerdcontest = findViewById(R.id.txtplayerdcontest);
                    txtplayerdcontestnew = findViewById(R.id.txtplayerdcontestnew);


                    createdchallenge = decrypt(good.key, createdchallenge);
                    joinedchallenge = decrypt(good.key, joinedchallenge);
                    Totalpayedchallenge = decrypt(good.key, Totalpayedchallenge);
                    wonchallenge = decrypt(good.key, wonchallenge);
                    losechallenge = decrypt(good.key, losechallenge);
                    String winn = decrypt(good.key, winning);


                    txtcontestcreated.setText(createdchallenge);
                    txtchallengejoin.setText(joinedchallenge);
                    txtchallengewon.setText(Totalpayedchallenge);
                    txtchallengewonnew.setText(wonchallenge);
                    txtplayerdcontest.setText(losechallenge);
                    txtplayerdcontestnew.setText(winn+"%");





                } else {
                    switch (response.code()) {
                        case 401:
                            Toast.makeText(Winnerdetail.this, "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
                            Toast.makeText(Winnerdetail.this, "ForbiddenException", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
                            Toast.makeText(Winnerdetail.this, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(Winnerdetail.this, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(Winnerdetail.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;

                    }

                }
                progressbar.dismiss();

            }

            @Override
            public void onFailure(Call<WinnerdetailsResponseJson> call, Throwable t) {
                t.printStackTrace();


                Log.e("System error:", t.getLocalizedMessage());

            }
        });
    }

//    public class AsyncCallWS extends AsyncTask<String, Void, Void> {
//
//        String createdchallenge, TodayContestcreated, joinedchallenge, TodayContestwin,winning, Totalpayedchallenge,losechallenge, TodayContestlose, Contestamount, TodayContestamount, wonchallenge, TodayChallengejoin, Challengewin, TodayChallengewin, Challngeloose, TodayChallngeloose;
//        String Contestwon, Todaycontestwon, Contestplayed, Todaycontestplay;
//
//        @Override
//        protected Void doInBackground(String... params) {
//            SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
////            String Loginid = prefs.getString("Loginid", null);

//
//            displayText = WebService.Selectchallenge(Loginid,GameName, "ProfileDetail");
//            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                try {
//                    jasonObject = new JSONObject(displayText);
//                    jsonArray = jasonObject.getJSONArray("Response");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
//                        createdchallenge = jsonrowdata.getString("createdchallenge");
////                        TodayContestcreated = jsonrowdata.getString("Contestcreatedtoday");
//                        joinedchallenge = jsonrowdata.getString("joinedchallenge");
////                        TodayContestwin = jsonrowdata.getString("Contestwintoday");
//                        Totalpayedchallenge = jsonrowdata.getString("Totalpayedchallenge");
////                        TodayContestlose = jsonrowdata.getString("Contestlosetoday");
//                        wonchallenge = jsonrowdata.getString("wonchallenge");
//
//                        losechallenge = jsonrowdata.getString("losechallenge");
//
//                        winning = jsonrowdata.getString("winning");
//
//
//
//
//

//
//
//                        createdchallenge = encrypt(good.key, good.initVector, createdchallenge);
//                        joinedchallenge = encrypt(good.key, good.initVector, joinedchallenge);
//                        Totalpayedchallenge = encrypt(good.key, good.initVector, Totalpayedchallenge);
//                        wonchallenge = encrypt(good.key, good.initVector, wonchallenge);
//                        losechallenge = encrypt(good.key, good.initVector, losechallenge);
//                        winning = encrypt(good.key, good.initVector, winning);
//
//
//
//

//
//
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//            return null;
//        }
//
//        @RequiresApi(api = Build.VERSION_CODES.N)
//        @Override
//        protected void onPostExecute(Void result) {
//            if (displayText.equalsIgnoreCase("connection fault")) {
//                Toast.makeText(Winnerdetail.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
//            } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                Toast.makeText(Winnerdetail.this, "Please try after some times...", Toast.LENGTH_LONG).show();
//            } else if (jsonArray.length() > 0) {
//                TextView txtcontestcreated, txtcontestwin, txtchallengejoin,textplayerdcontestnew, txtchallengewin,txtchallengewonnew,txtplayerdcontestnew, txtchallengewon, txttodaychallengewon, txtplayerdcontest, txttodayplayedcontest;
//
//                txtcontestcreated = findViewById(R.id.txtcontestcreated);
//                txtchallengejoin = findViewById(R.id.txtchallengejoin);
//                txtchallengewon = findViewById(R.id.txtchallengewon);
//                txtchallengewonnew = findViewById(R.id.txtchallengewonnew);
//                txtplayerdcontest = findViewById(R.id.txtplayerdcontest);
//                txtplayerdcontestnew = findViewById(R.id.txtplayerdcontestnew);
//
//
//                createdchallenge = decrypt(good.key, createdchallenge);
//                joinedchallenge = decrypt(good.key, joinedchallenge);
//                Totalpayedchallenge = decrypt(good.key, Totalpayedchallenge);
//                wonchallenge = decrypt(good.key, wonchallenge);
//                losechallenge = decrypt(good.key, losechallenge);
//                String winn = decrypt(good.key, winning);
//
//
//                txtcontestcreated.setText(createdchallenge);
//                txtchallengejoin.setText(joinedchallenge);
//                txtchallengewon.setText(Totalpayedchallenge);
//                txtchallengewonnew.setText(wonchallenge);
//                txtplayerdcontest.setText(losechallenge);
//                txtplayerdcontestnew.setText(winn+"%");
//
//
//
//
//
//
//            } else if (jsonArray.length() == 0) {
//                Toast.makeText(Winnerdetail.this, "No detail found", Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(Winnerdetail.this, "Try Again", Toast.LENGTH_LONG).show();
//            }
//        }
//
//        @Override
//        protected void onCancelled() {
//            task.cancel(true);
//            super.onCancelled();
//        }
//
//        @Override
//        protected void onPreExecute() {
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//        }
//    }
}
