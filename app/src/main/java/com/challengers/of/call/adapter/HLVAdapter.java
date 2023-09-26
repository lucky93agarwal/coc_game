package com.challengers.of.call.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.CountDownTimer;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.agrawalsuneet.dotsloader.loaders.RotatingCircularDotsLoader;
import com.challengers.of.call.Add_Money_Popup.addmoneypopup;
import com.challengers.of.call.ChallengersAPI.ChallengersGetRequestJson;
import com.challengers.of.call.ChallengersAPI.ChallengersGetResponseJson;
import com.challengers.of.call.ChallengersAPI.ChallengersRequestJson;
import com.challengers.of.call.Contest.ContestListResponseJson;
import com.challengers.of.call.Dashboard;
import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.Main2Activity;
import com.challengers.of.call.Retrofit.ServiceGenerator;
import com.challengers.of.call.Retrofit.UserService;
import com.challengers.of.call.Signin;
import com.challengers.of.call.Splash;
import com.challengers.of.call.TextinActivity;
import com.challengers.of.call.my_contest.MyContestActivity;
import com.challengers.of.call.testing.SettingsPreferences;
import com.challengers.of.call.testing.StaticUtils;
import com.challengers.of.call.testing.TextingtwoActivity;
import com.challengers.of.call.testing.Utils;
import com.google.android.material.snackbar.Snackbar;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.challengers.of.call.Winnerdetail;
import com.challengers.of.call.helper.GlideCircleTransformation;
import com.challengers.of.call.paytm;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.challengers.of.call.Activityquiz;
import com.challengers.of.call.Firebaseutil;
import com.challengers.of.call.ItemClickListener;
import com.challengers.of.call.R;
import com.challengers.of.call.Util;
import com.challengers.of.call.WebService;
import com.challengers.of.call.data.Getcontestdata;
import com.challengers.of.call.data.Getquestiondata;
import com.challengers.of.call.fragment.Fragmentchallenges;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.decrypt;
import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.encrypt;
import static com.facebook.FacebookSdk.getApplicationContext;

public class HLVAdapter extends RecyclerView.Adapter<HLVAdapter.ViewHolder> {
    public static Context mcontext;
//    public static AsyncCallWS task;
    public boolean run = false;
    public static List<Getcontestdata> productList;
    public static boolean runtimer = true;
    public Context context;
    ViewHolder view;
    public String Count, TotalWalletnew, Loginid, Counter;
    long timertime = 0;
    public Getcontestdata getselfdata;
    String Contest = "";
    double entryfees = 0;
    JSONObject jasonObject;
    JSONArray jsonArray = null;
    ViewHolder viewHolder;
    public String totalwallet;
    public String GameName, Gameid, Gamelayout;
    public Dialog progressbar;
    public Dialog Popup;
    private ljsflsj good;
    private static addmoneypopup addpopup;

    public HLVAdapter(Context context, List<Getcontestdata> productList) {
        super();
        this.context = context;
        this.productList = productList;
        mcontext = context;
        progressbar = new Dialog(context);
        addpopup = new addmoneypopup(context);
        Popup = new Dialog(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_contest_row, viewGroup, false);
        viewHolder = new ViewHolder(v);


        return viewHolder;
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull ViewHolder holder) {
        if (holder.timer != null) {
            holder.timer.cancel();
        }
        super.onViewDetachedFromWindow(holder);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        getselfdata = productList.get(i);
        view = viewHolder;
        String name = decrypt(good.key, productList.get(i).getChallengername());
        viewHolder.tvNickname.setText(name);
        String poiint = decrypt(good.key, productList.get(i).getPoints());
        viewHolder.tvPoints.setText(poiint + "+");

        viewHolder.tvWiningamount.setText("₹" + productList.get(i).getWinningamount() + "");
        String Entryfeesss = decrypt(good.key, productList.get(i).getEntryfees());
        viewHolder.tvEntryfees.setText("₹" + Entryfeesss);
        String time = decrypt(good.key, productList.get(i).getTimer());
        timertime = Integer.valueOf(time);
        if (viewHolder.timer != null) {
            viewHolder.timer.cancel();
        }

        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SettingsPreferences.getVibration(mcontext)) {
                    StaticUtils.vibrate(mcontext, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(mcontext)) {
                    StaticUtils.backSoundonclick(mcontext);
                }
                viewHolder.imageView.startAnimation(AnimationUtils.loadAnimation(mcontext, R.anim.imageanim));
                SharedPreferences prefs = mcontext.getSharedPreferences("UserData", 0);

                String Totalwallet = prefs.getString("Totalwallet", null);


                getselfdata = productList.get(i);
                Contest = getselfdata.getContestid();


                entryfees = Double.valueOf(decrypt(good.key, getselfdata.getEntryfees()));
                Totalwallet = decrypt(good.key, Totalwallet);
                if (Double.valueOf(Totalwallet) > 0 && Double.valueOf(Totalwallet) >= entryfees) {

                    SharedPreferences lucky = mcontext.getSharedPreferences("coding", Context.MODE_PRIVATE);
                    Gameid = lucky.getString("gameid", null);


                    GameName = lucky.getString("gametype", null);
                    Gamelayout = lucky.getString("gamelayout", null);
                    Gamelayout = decrypt(good.key, Gamelayout);
                    Popup.setContentView(R.layout.quiztextknow);
                    Button okbtn = (Button) Popup.findViewById(R.id.okyes);
                    Button nobtn = (Button) Popup.findViewById(R.id.closePopup);
                    nobtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (SettingsPreferences.getVibration(context)) {
                                com.challengers.of.call.testing.StaticUtils.vibrate(context, com.challengers.of.call.testing.StaticUtils.VIBRATION_DURATION);
                            }
                            if (com.challengers.of.call.testing.SettingsPreferences.getSoundEnableDisable(context)) {
                                com.challengers.of.call.testing.StaticUtils.backSoundonclick(context);
                            }

                            Popup.dismiss();
                        }
                    });
                    okbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (com.challengers.of.call.testing.SettingsPreferences.getVibration(context)) {
                                com.challengers.of.call.testing.StaticUtils.vibrate(context, com.challengers.of.call.testing.StaticUtils.VIBRATION_DURATION);
                            }
                            if (com.challengers.of.call.testing.SettingsPreferences.getSoundEnableDisable(context)) {
                                com.challengers.of.call.testing.StaticUtils.backSoundonclick(context);
                            }


                            progressbar.setContentView(R.layout.progresbarlayout);
                            progressbar.setCancelable(false);
                            RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);

                            RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(mcontext,
                                    20, 60, ContextCompat.getColor(mcontext, R.color.white));
                            loader.setAnimDuration(3000);

                            rotatingCircularDotsLoader.addView(loader);

                            progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
                            progressbar.show();





                            ContestGet();


                            Popup.dismiss();
                        }
                    });
                    Popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
                    Popup.show();
//

                    view.startAnimation(viewHolder.myAnim);
                } else {

                    addpopup.addpopup();
                }

            }
        });


        if (runtimer == true) {
            viewHolder.timer = new CountDownTimer(timertime, 1000) {
                public void onTick(long millisUntilFinished) {
//                        Toast.makeText(mcontext, "Timer is running", Toast.LENGTH_LONG).show();
                    String second, minute, hour;
                    int seconds = (int) (millisUntilFinished / 1000) % 60;
                    int minutes = (int) ((millisUntilFinished / (1000 * 60)) % 60);
                    int hours = (int) ((millisUntilFinished / (1000 * 60 * 60)) % 24);
                    hour = Integer.toString(hours);
                    minute = Integer.toString(minutes);
                    second = Integer.toString(seconds);
                    if (hours < 10) {
                        String a = "0" + hours;
                        hour = a;
                    }
                    if (minutes < 10) {
                        String a = "0" + minutes;
                        minute = a;
                    }
                    if (seconds < 10) {
                        String a = "0" + seconds;
                        second = a;
                    }
                    if (hours == 0 && minutes == 0 && seconds == 0) {
                        viewHolder.tvTimer.setText(hour + ":" + minute + ":" + second);
                        Fragmentchallenges fragmentchallenges = new Fragmentchallenges();
                        productList.remove(i);
                        fragmentchallenges.Refresh(i);
                        notifyDataSetChanged();
                    } else {
                        viewHolder.tvTimer.setText(hour + ":" + minute + ":" + second);
                    }

                }

                public void onFinish() {
                }
            }.start();
        } else {
            viewHolder.timer.cancel();
        }


        if (getselfdata.getImage() < 30) {
            String imgimg = decrypt(good.key, getselfdata.getChallengerimage());
            if (imgimg != null && imgimg != "") {
                Glide.with(getApplicationContext()).load("http://callofchallengers.com/coc/images/users/" + imgimg)
                        .crossFade()
                        .placeholder(R.drawable.logo)
                        .error(R.drawable.logo)
                        .bitmapTransform(new GlideCircleTransformation(getApplicationContext()))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .skipMemoryCache(false)
                        .into(viewHolder.winimage);
            }
        } else {
            String imgimg = decrypt(good.key, getselfdata.getChallengerimage());
            Glide.with(getApplicationContext()).load(imgimg)
                    .crossFade()
                    .placeholder(R.drawable.logo)
                    .error(R.drawable.logo)
                    .bitmapTransform(new GlideCircleTransformation(getApplicationContext()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(false)
                    .into(viewHolder.winimage);
        }


//        if(getselfdata.getImageurl()!=null && getselfdata.getImageurl()!="")
//        {
//            Glide.with(getApplicationContext()).load("http://beedesigns.in/coc/images/"+getselfdata.getImageurl())
//                    .crossFade()
//                    .placeholder(R.drawable.logo)
//                    .error(R.drawable.logo)
//                    .bitmapTransform(new GlideCircleTransformation(getApplicationContext()))
//                    .diskCacheStrategy(DiskCacheStrategy.NONE)
//                    .skipMemoryCache(true)
//                    .into(viewHolder.winimage);
//        }

        viewHolder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                } else {
                }
            }
        });

        setFadeAnimation(viewHolder.itemView);

    }



    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView tvNickname, tvPoints, tvWiningamount, tvTimer, tvEntryfees, imageView;
        ImageView winimage;
        public CountDownTimer timer;
        private ItemClickListener clickListener;
        public Animation myAnim;

        public ViewHolder(View itemView) {
            super(itemView);

            winimage = (ImageView) itemView.findViewById(R.id.ivprofile);

            myAnim = AnimationUtils.loadAnimation(mcontext, R.anim.buttonanimation);
            tvNickname = (TextView) itemView.findViewById(R.id.textView);
            tvPoints = (TextView) itemView.findViewById(R.id.textView2);
            tvWiningamount = (TextView) itemView.findViewById(R.id.textView3);
            tvTimer = (TextView) itemView.findViewById(R.id.textView4);
            tvEntryfees = (TextView) itemView.findViewById(R.id.textView5);
            imageView = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            winimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Getcontestdata getselfdata = productList.get(getAdapterPosition());
                    Intent intent = new Intent(mcontext, Winnerdetail.class);
                    intent.putExtra("Winnerid", getselfdata.getChallengerid());
                    intent.putExtra("Name", getselfdata.getChallengername());
                    intent.putExtra("goto", "challenger");
                    intent.putExtra("image", getselfdata.getChallengerimage());
                    mcontext.startActivity(intent);
                }
            });

        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onClick(view, getPosition(), true);
            return true;
        }
    }

    private void ContestGet() {
        SharedPreferences prefs = mcontext.getSharedPreferences("UserData", Context.MODE_PRIVATE);

        String Password = prefs.getString("Password", null);
        String Usernames = prefs.getString("Name", null);
        String Loginids = prefs.getString("Loginid", null);

        final ChallengersGetRequestJson request = new ChallengersGetRequestJson();
        request.setContestid(decrypt(good.key, Contest));
        request.setGameid(decrypt(good.key, Gameid));
        request.setLoginid(decrypt(good.key, Loginids));

        UserService service = ServiceGenerator.createService(UserService.class, decrypt(good.key, Usernames), decrypt(good.key, Password));
        service.challengerget(request).enqueue(new Callback<ChallengersGetResponseJson>() {
            @Override
            public void onResponse(Call<ChallengersGetResponseJson> call, Response<ChallengersGetResponseJson> response) {
                if (response.isSuccessful()) {

                    Count = response.body().Count;
                    Counter = response.body().ChallengerCounter;
                    TotalWalletnew = response.body().TotalWallet;





                    TotalWalletnew = encrypt(good.key, good.initVector, TotalWalletnew);




                    SharedPreferences sharedPreferences = context.getSharedPreferences("Count", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edits = sharedPreferences.edit();
                    edits.putString("Count", "0");
                    edits.putString("From", "Challenge");
                    edits.apply();
                    TotalWalletnew = decrypt(good.key, TotalWalletnew);
                    if (TotalWalletnew != null && TotalWalletnew != "") {
                        TotalWalletnew = encrypt(good.key, good.initVector, TotalWalletnew);
                        SharedPreferences pref2 = mcontext.getSharedPreferences("UserData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = pref2.edit();
                        editor1.putString("Totalwallet", TotalWalletnew);

                        editor1.apply();
                    }

                    if (Count.equalsIgnoreCase("Inserted")) {
                        SharedPreferences shear = mcontext.getSharedPreferences("fiftyss", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = shear.edit();
                        edit.putString("fifty", "true");
                        edit.apply();

                        if (GameName.equalsIgnoreCase("no_url")) {
                            Intent intent = new Intent(mcontext, Activityquiz.class);
                            intent.putExtra("Contestid", Contest);
                            intent.putExtra("goto", "Result");
                            intent.putExtra("From", "Challenge");
                            intent.putExtra("From1", "Challenge");
                            mcontext.startActivity(intent);
                        } else {

                            //// second game
//                            Toast.makeText(getActivity(), "one", Toast.LENGTH_SHORT).show();
                            if (Gamelayout.equalsIgnoreCase("0")) {
                                Intent intent = new Intent(mcontext, TextinActivity.class);
                                intent.putExtra("Contestid", Contest);
                                intent.putExtra("goto", "Result");
                                intent.putExtra("From", "Challenge");
                                intent.putExtra("From1", "Challenge");
                                mcontext.startActivity(intent);
                            } else if (Gamelayout.equalsIgnoreCase("1")) {
                                Intent intent = new Intent(mcontext, TextingtwoActivity.class);
                                intent.putExtra("Contestid", Contest);
                                intent.putExtra("goto", "Result");
                                intent.putExtra("From", "Challenge");
                                intent.putExtra("From1", "Challenge");
                                mcontext.startActivity(intent);
                            }

                        }
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Util.scheduleJob(getApplicationContext(), Loginid, Contest);
                        } else {
                            Firebaseutil.schedulejob(getApplicationContext(), Loginid, Contest);
                        }
                        ((Activity) mcontext).finish();
                    } else if (Count.equalsIgnoreCase("Not Play")) {
                        Toast.makeText(mcontext, "Now create 1 contest to join more contest.", Toast.LENGTH_LONG).show();
                    } else if (Count.equalsIgnoreCase("First Match")) {
                        Toast.makeText(mcontext, "Create atleast 1 contest to join more.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(mcontext, "You Can not join this Contest", Toast.LENGTH_LONG).show();
                    }

                } else {
                    switch (response.code()) {
                        case 401:
//                            Toast.makeText(mcontext, "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
//                            Toast.makeText(mcontext, "ForbiddenException", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
//                            Toast.makeText(mcontext, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
//                            Toast.makeText(mcontext, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
//                            Toast.makeText(mcontext, "unknown error", Toast.LENGTH_SHORT).show();
                            break;

                    }
                    Toast.makeText(mcontext, "Something error please try again", Toast.LENGTH_SHORT).show();
                }
                progressbar.dismiss();

            }

            @Override
            public void onFailure(Call<ChallengersGetResponseJson> call, Throwable t) {
                t.printStackTrace();
                progressbar.dismiss();

                Log.e("System error:", t.getLocalizedMessage());

            }
        });


    }


//    public class AsyncCallWS extends AsyncTask<String, Void, Void> {
//        String displayText;
//        String Count, TotalWalletnew, Loginid, Counter;
//        private KProgressHUD hud;
//
//        @Override
//        protected Void doInBackground(String... params) {
//            SharedPreferences prefs = mcontext.getSharedPreferences("UserData", Context.MODE_PRIVATE);
//            Loginid = prefs.getString("Loginid", null);

//
//            displayText = WebService.Joincontestchallenger(Loginid, Contest, Gameid, "", "JoinChallenge");
//            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                try {
//                    jasonObject = new JSONObject(displayText);
//                    jsonArray = jasonObject.getJSONArray("Response");

//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        Getquestiondata getquestiondata1 = new Getquestiondata();
//                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
//
//                        Count = jsonrowdata.getString("Count");
//                        Counter = jsonrowdata.getString("ChallengerCounter");
//
//                        TotalWalletnew = jsonrowdata.getString("Totalwallet");
//

//
//
//                        TotalWalletnew = encrypt(good.key, good.initVector, TotalWalletnew);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            progressbar.dismiss();
//            if (displayText.equalsIgnoreCase("connection fault")) {
//                Toast.makeText(mcontext, "Please Check your internet connection", Toast.LENGTH_LONG).show();
//            } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                Toast.makeText(mcontext, "Please try after some times...", Toast.LENGTH_LONG).show();
//            } else if (jsonArray.length() > 0) {
//                SharedPreferences sharedPreferences = context.getSharedPreferences("Count", Context.MODE_PRIVATE);
//                SharedPreferences.Editor edits = sharedPreferences.edit();
//                edits.putString("Count", "0");
//                edits.putString("From", "Challenge");
//                edits.apply();
//                TotalWalletnew = decrypt(good.key, TotalWalletnew);
//                if (TotalWalletnew != null && TotalWalletnew != "") {
//                    TotalWalletnew = encrypt(good.key, good.initVector, TotalWalletnew);
//                    SharedPreferences pref2 = mcontext.getSharedPreferences("UserData", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor1 = pref2.edit();
//                    editor1.putString("Totalwallet", TotalWalletnew);
////
//                    //   Toast.makeText(mcontext, "Totallwallet", Toast.LENGTH_SHORT).show();
//
//
//                    editor1.apply();
//                }
//                //    Toast.makeText(mcontext, "Totallwalletnew2", Toast.LENGTH_SHORT).show();
//
//                if (Count.equalsIgnoreCase("Inserted")) {
//                    SharedPreferences shear = mcontext.getSharedPreferences("fiftyss", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor edit = shear.edit();
//                    edit.putString("fifty", "true");
//                    edit.apply();

//                    if (GameName.equalsIgnoreCase("no_url")) {
//                        Intent intent = new Intent(mcontext, Activityquiz.class);
//                        intent.putExtra("Contestid", Contest);
//                        intent.putExtra("goto", "Result");
//                        intent.putExtra("From", "Challenge");
//                        intent.putExtra("From1", "Challenge");
//                        mcontext.startActivity(intent);
//                    } else {
//
//                        //// second game
////                            Toast.makeText(getActivity(), "one", Toast.LENGTH_SHORT).show();
//                        if (Gamelayout.equalsIgnoreCase("0")) {
//                            Intent intent = new Intent(mcontext, TextinActivity.class);
//                            intent.putExtra("Contestid", Contest);
//                            intent.putExtra("goto", "Result");
//                            intent.putExtra("From", "Challenge");
//                            intent.putExtra("From1", "Challenge");
//                            mcontext.startActivity(intent);
//                        } else if (Gamelayout.equalsIgnoreCase("1")) {
//                            Intent intent = new Intent(mcontext, TextingtwoActivity.class);
//                            intent.putExtra("Contestid", Contest);
//                            intent.putExtra("goto", "Result");
//                            intent.putExtra("From", "Challenge");
//                            intent.putExtra("From1", "Challenge");
//                            mcontext.startActivity(intent);
//                        }
//
//                    }
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                        Util.scheduleJob(getApplicationContext(), Loginid, Contest);
//                    } else {
//                        Firebaseutil.schedulejob(getApplicationContext(), Loginid, Contest);
//                    }
//                    ((Activity) mcontext).finish();
//                } else if (Count.equalsIgnoreCase("Not Play")) {
//                    Toast.makeText(mcontext, "You have joined "+Counter+" contest now create one contest to join more", Toast.LENGTH_LONG).show();
//                } else if (Count.equalsIgnoreCase("First Match")) {
//                    Toast.makeText(mcontext, "Create atleast 1 Contest to join", Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(mcontext, "You Can not join this Contest", Toast.LENGTH_LONG).show();
//                }
//            } else {
//                Toast.makeText(mcontext, "Try Again", Toast.LENGTH_LONG).show();
//            }
//        }
//
//        @Override
//        protected void onPreExecute() {
//            progressbar.setContentView(R.layout.progresbarlayout);
//            progressbar.setCancelable(false);
//            RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);
//
//            RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(mcontext,
//                    20, 60, ContextCompat.getColor(mcontext, R.color.white));
//            loader.setAnimDuration(3000);
//
//            rotatingCircularDotsLoader.addView(loader);
//
//            progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
//            progressbar.show();
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//        }
//
//        @Override
//        protected void onCancelled() {
//            task.cancel(true);
//            super.onCancelled();
//        }
//    }


    private void setFadeAnimation(View view) {
        Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.fade_in);

//        view
//        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
//        anim.setDuration(200);
        view.startAnimation(animation);
    }

    public void cancletimer() {
        if (viewHolder.timer != null) {
            viewHolder.timer.cancel();
        }
    }
}

