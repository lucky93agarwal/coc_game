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

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.RotatingCircularDotsLoader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.challengers.of.call.Add_Money_Popup.addmoneypopup;
import com.challengers.of.call.Dashboard;
import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.ItemClickListener;
import com.challengers.of.call.Main2Activity;
import com.challengers.of.call.MyContestAPI.MyAdapterRequestJson;
import com.challengers.of.call.R;
import com.challengers.of.call.ResultApi.ContinueRequestJson;
import com.challengers.of.call.ResultApi.ContinueResponseJson;
import com.challengers.of.call.Retrofit.ServiceGenerator;
import com.challengers.of.call.Retrofit.UserService;
import com.challengers.of.call.WebService;
import com.challengers.of.call.data.Getmycontestdata;
import com.challengers.of.call.errorpopuplayout;
import com.challengers.of.call.fragment.Fragmentchallenges;
import com.challengers.of.call.fragment.Fragmentmycontest;
import com.challengers.of.call.helper.GlideCircleTransformation;
import com.challengers.of.call.my_contest.MyContestActivity;
import com.challengers.of.call.testing.SettingsPreferences;
import com.challengers.of.call.testing.StaticUtils;

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

public class Mycontestadapter extends RecyclerView.Adapter<Mycontestadapter.ViewHolder> {
    public static Context mcontext;
    public static boolean run = false;
    public static boolean play = true;
    //    public static AsyncCallWS task;
//    public static AsyncCallWSrefundamount taskrefund;
    private static boolean cancel = false;
    List<Getmycontestdata> productList;
    Context context;
    ViewHolder view;
    long timertime = 0;
    Getmycontestdata getselfdata;
    Fragmentmycontest fragment;
    JSONObject jasonObject;
    JSONArray jsonArray = null;
    int posrefun = 0;
    int counter = 0;
    String Contestid = "";
    String Entryfees = "0", Winningamount = "0";
    public Dialog unepicDialog;
    private static errorpopuplayout errorpupop;
    private ljsflsj good;
    private String Message;
    public Dialog progressbar;

    public Mycontestadapter(Context context, List<Getmycontestdata> productList, Fragmentmycontest fragmentmycontest) {
        super();
        this.context = context;
        this.productList = productList;
        mcontext = context;
        this.fragment = fragmentmycontest;
        progressbar = new Dialog(context);
        unepicDialog = new Dialog(context);
        errorpupop = new errorpopuplayout(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_mycontest_row, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        getselfdata = productList.get(i);
        view = viewHolder;
        String Nickname = decrypt(good.key, productList.get(i).getNickname());
        viewHolder.tvNickname.setText(Nickname);
        String points = decrypt(good.key, productList.get(i).getPoints());
        viewHolder.tvPoints.setText(points);
        String gamename = decrypt(good.key, productList.get(i).getGamename());
        viewHolder.gamename.setText(gamename);

        String img = decrypt(good.key, productList.get(i).getImg());
        if (img != null && img != "") {
            Glide.with(getApplicationContext()).load("http://callofchallengers.com/coc/coc_arena/challengerGame/img/" + img)
                    .crossFade()
                    .placeholder(R.drawable.logo)
                    .error(R.drawable.logo)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(false)
                    .into(viewHolder.gameimg);
        }
        cancel = false;
        String Winningamounts = decrypt(good.key, productList.get(i).getWinningamount());
        viewHolder.tvWiningamount.setText("₹" + Winningamounts);
        String Entryfeess = decrypt(good.key, productList.get(i).getEntryfees());
        viewHolder.tvEntryfees.setText(Entryfeess);
        String Timerss = decrypt(good.key, productList.get(i).getTimer());
        timertime = Integer.valueOf(Timerss);
        if (viewHolder.timer != null) {
            viewHolder.timer.cancel();
        }
        String Resultss = decrypt(good.key, productList.get(i).getResult());
        if (Resultss.equalsIgnoreCase("WATING FOR SOMEONE TO JOIN YOUR CONTEST")) {///// timer ke time
//            viewHolder.layoutwait.setVisibility(View.VISIBLE);
//            Fragmentmycontest test = (Fragmentmycontest) ((Main2Activity) mcontext).getSupportFragmentManager().findFragmentByTag("umesh");
//            if (test != null && test.isVisible()) {
            viewHolder.txtquizresult.setText("WAIT FOR ANYONE TO JOIN YOUR CONTEST");
            viewHolder.tvrefund.setVisibility(View.VISIBLE);////// refend button
            viewHolder.timer = new CountDownTimer(timertime, 1000) {
                public void onTick(long millisUntilFinished) {
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
                        viewHolder.tvTimer.setTextColor(Color.parseColor("#FFFFFF"));
                        Fragmentchallenges fragmentchallenges = new Fragmentchallenges();
                        productList.remove(i);
                        fragmentchallenges.Refresh(i);
                        notifyDataSetChanged();
                    } else {
                        viewHolder.tvTimer.setText(hour + ":" + minute + ":" + second);
                        viewHolder.tvTimer.setTextColor(Color.parseColor("#FFFFFF"));
                    }
                    counter = i;
//                        if (productList.size() > 0) {
////                            Fragmentmycontest test = (Fragmentmycontest) ((Main2Activity) mcontext).getSupportFragmentManager().findFragmentByTag("umesh");
////                            if (test != null && test.isVisible()) {
//                                if (run == false && play == true) {
//                                    task = new AsyncCallWS();
//                                    task.execute();
//                                } else {
//                                    cancel = true;
//                                }
////                            } else {
////                                cancel = true;
////                            }
//                        }
                }

                public void onFinish() {
                }
            };
            viewHolder.timer.start();
//            }
        } else if (Resultss.equalsIgnoreCase("Win")) {
            viewHolder.tvrefund.setVisibility(View.VISIBLE); ////// refend button
            viewHolder.layoutwait.setVisibility(View.GONE);
            viewHolder.tvTimer.setCompoundDrawables(null, null, null, null);
            viewHolder.tvTimer.setText("You Win");
            viewHolder.tvTimer.setTextColor(Color.parseColor("#FFFFFF"));
        } else if (Resultss.equalsIgnoreCase("LOSE")) {
            viewHolder.tvrefund.setVisibility(View.VISIBLE); ////// refend button
            viewHolder.layoutwait.setVisibility(View.GONE);
            viewHolder.tvTimer.setCompoundDrawables(null, null, null, null);
            viewHolder.tvTimer.setText("You Lose");
            viewHolder.tvTimer.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            viewHolder.tvrefund.setVisibility(View.VISIBLE); ////// refend button
//            viewHolder.layoutwait.setVisibility(View.VISIBLE);
            viewHolder.txtquizresult.setText("");

            viewHolder.tvTimer.setText("Expired");
            viewHolder.tvTimer.setTextColor(Color.parseColor("#D70C0C"));

            viewHolder.tvrefund.setVisibility(View.VISIBLE); ////// refend button

        }
        viewHolder.tvrefund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { ////// refend button
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }

                String timess = viewHolder.tvTimer.getText().toString();
                if (timess.equalsIgnoreCase("Expired")) {
                    unepicDialog.setContentView(R.layout.refend);

                    Button closepopupbtn = (Button) unepicDialog.findViewById(R.id.closePopup);
                    closepopupbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (SettingsPreferences.getVibration(context)) {
                                StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                            }
                            if (SettingsPreferences.getSoundEnableDisable(context)) {
                                StaticUtils.backSoundonclick(context);
                            }

                            Getmycontestdata getselfdata = productList.get(i);
                            posrefun = i;
                            Contestid = getselfdata.getContestid();
                            Entryfees = getselfdata.getEntryfees();
                            Winningamount = getselfdata.getWinningamount();

                            unepicDialog.dismiss();
                            viewHolder.tvrefund.setVisibility(View.GONE);
//                            taskrefund = new AsyncCallWSrefundamount();
//                            taskrefund.execute();
                            progressbar.setContentView(R.layout.progresbarlayout);
                            progressbar.setCancelable(false);
                            RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);

                            RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(mcontext,
                                    20, 60, ContextCompat.getColor(mcontext, R.color.white));
                            loader.setAnimDuration(3000);

                            rotatingCircularDotsLoader.addView(loader);

                            progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
                            progressbar.show();

                            GetAPIData();
                        }
                    });
                    TextView tvmoney = (TextView) unepicDialog.findViewById(R.id.moeny);
                    tvmoney.setText("₹" + decrypt(good.key, productList.get(i).getRefended()));
                    unepicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
                    unepicDialog.show();

                } else {
                    //// show krana h pupop ko
                    errorpupop.error();
                }


            }
        });
        viewHolder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                } else {
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView tvNickname, tvPoints, tvWiningamount, tvTimer, tvEntryfees, txtquizresult;
        public Button tvrefund;
        public ImageView gameimg;
        public TextView gamename;
        CountDownTimer timer;
        LinearLayout layoutwait;
        private ItemClickListener clickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            //imgThumbnail = (ImageView) itemView.findViewById(R.id.ivImg);
            tvNickname = (TextView) itemView.findViewById(R.id.textView);
            tvPoints = (TextView) itemView.findViewById(R.id.textView2);
            tvWiningamount = (TextView) itemView.findViewById(R.id.textView3);
            tvTimer = (TextView) itemView.findViewById(R.id.textView4);
            tvEntryfees = (TextView) itemView.findViewById(R.id.textView5);
            gameimg = (ImageView) itemView.findViewById(R.id.gameimage);
            gamename = (TextView) itemView.findViewById(R.id.gamename);
            tvrefund = (Button) itemView.findViewById(R.id.tvrefund);
            layoutwait = itemView.findViewById(R.id.layoutwait);
            txtquizresult = itemView.findViewById(R.id.txtquizresult);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
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

    //    public class AsyncCallWS extends AsyncTask<String, Void, Void> {
//        String displayText = "";
//        String Count;
//        @Override
//        protected Void doInBackground(String... params) {
//            run = true;
//            SharedPreferences prefs = mcontext.getSharedPreferences("UserData", Context.MODE_PRIVATE);
//            String Loginid = prefs.getString("Loginid", null);
//
//                displayText = WebService.Countcontest(Loginid, "", "Countcontest");
//
//            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                try {
//                    jasonObject = new JSONObject(displayText);
//                    jsonArray = jasonObject.getJSONArray("Response");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
//                        Count = jsonrowdata.getString("Count");
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//            return null;
//        }
//        @RequiresApi(api = Build.VERSION_CODES.N)
//        @Override
//        protected void onPostExecute(Void result) {
//            if(displayText!=null) {
//                if (displayText.equalsIgnoreCase("connection fault")) {
//                    Toast.makeText(mcontext, "Please Check your internet connection", Toast.LENGTH_LONG).show();
//                } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                    Toast.makeText(mcontext, "Please try after some times...", Toast.LENGTH_LONG).show();
//                } else if (jsonArray != null) {
//
//                    if (jsonArray.length() > 0) {
//                        run = false;
////                        if (Count != null && Count != "") {
////                            if (productList.size() != Integer.valueOf(Count)) {
////                                productList.remove(counter);
////                                notifyDataSetChanged();
////                            }
////                        }
//                    } else if (jsonArray.length() == 0) {
//                        Toast.makeText(mcontext, "No detail found", Toast.LENGTH_LONG).show();
//                    }
//                }
//            }
//            else {
//                Toast.makeText(mcontext, "Try Again", Toast.LENGTH_LONG).show();
//            }
//        }
//        @Override
//        protected void onPreExecute() {
//        }
//        @Override
//        protected void onProgressUpdate(Void... values) {
//        }
//        @Override
//        protected void onCancelled() {
//            run = true;
//            super.onCancelled();
//        }
//    }
    private void GetAPIData() {

        SharedPreferences prefs = mcontext.getSharedPreferences("UserData", Context.MODE_PRIVATE);

        String Password = prefs.getString("Password", null);
        String Usernames = prefs.getString("Name", null);
        String Loginid = prefs.getString("Loginid", null);


        final MyAdapterRequestJson request = new MyAdapterRequestJson();

        request.setLoginid(decrypt(good.key, Loginid));
        request.setContestid(decrypt(good.key, Contestid));
        request.setEntryfees(decrypt(good.key, Entryfees));
        request.setWinningamount(decrypt(good.key, Winningamount));

        UserService service = ServiceGenerator.createService(UserService.class, decrypt(good.key, Usernames), decrypt(good.key, Password));
        service.mycontestrefund(request).enqueue(new Callback<ContinueResponseJson>() {
            @Override
            public void onResponse(Call<ContinueResponseJson> call, Response<ContinueResponseJson> response) {
                if (response.isSuccessful()) {
                    String Totalwallet;
                    Totalwallet = response.body().message;

                    if (Totalwallet != null && Totalwallet != "") {
                        Totalwallet = encrypt(good.key, good.initVector, Totalwallet);
                        SharedPreferences pref2 = mcontext.getSharedPreferences("UserData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = pref2.edit();
                        editor1.putString("Totalwallet", Totalwallet);
                        editor1.apply();

                        ((MyContestActivity) mcontext).addwallet(Totalwallet);
                        Toast.makeText(mcontext, "Amount Refunded", Toast.LENGTH_LONG).show();
                        productList.remove(posrefun);
                        notifyDataSetChanged();
                    } else {
                        Toast.makeText(mcontext, "Try Again", Toast.LENGTH_LONG).show();
                    }
                } else {
                    switch (response.code()) {
                        case 401:
                            Toast.makeText(mcontext, "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
                            Toast.makeText(mcontext, "ForbiddenException", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
                            Toast.makeText(mcontext, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(mcontext, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(mcontext, "unknown error", Toast.LENGTH_SHORT).show();
                            break;

                    }

                }
                progressbar.dismiss();

            }

            @Override
            public void onFailure(Call<ContinueResponseJson> call, Throwable t) {
                t.printStackTrace();


                Log.e("System error:", t.getLocalizedMessage());

            }
        });
    }

//    public class AsyncCallWSrefundamount extends AsyncTask<String, Void, Void> {
//        String displayText = "";
//        String Count;
//
//        @Override
//        protected Void doInBackground(String... params) {
//            run = true;
//            SharedPreferences prefs = mcontext.getSharedPreferences("UserData", Context.MODE_PRIVATE);
//            String Loginid = prefs.getString("Loginid", null);

//
//            displayText = WebService.Refundamount(Loginid, Contestid, Entryfees, Winningamount, "", "RefundContest");
//            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                try {
//                    jasonObject = new JSONObject(displayText);
//                    jsonArray = jasonObject.getJSONArray("Response");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
//                        Count = jsonrowdata.getString("Count");

//                        Count = encrypt(good.key, good.initVector, Count);
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
//            if (displayText != null) {
//                if (displayText.equalsIgnoreCase("connection fault")) {
//                    Toast.makeText(mcontext, "Please Check your internet connection", Toast.LENGTH_LONG).show();
//                } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                    Toast.makeText(mcontext, "Please try after some times...", Toast.LENGTH_LONG).show();
//                } else if (jsonArray != null) {
//                    if (jsonArray.length() > 0) {
//                        if (Count != null && Count != "") {
//                            SharedPreferences pref2 = mcontext.getSharedPreferences("UserData", Context.MODE_PRIVATE);
//                            SharedPreferences.Editor editor1 = pref2.edit();
//                            editor1.putString("Totalwallet", Count);
//                            editor1.apply();
////                        ((Main2Activity) mcontext).setwallet(Count);
//                            ((MyContestActivity) mcontext).addwallet(Count);
//                            Toast.makeText(mcontext, "Amount Refunded", Toast.LENGTH_LONG).show();
//                            productList.remove(posrefun);
//                            notifyDataSetChanged();
//                        } else {
//                            Toast.makeText(mcontext, "Try Again", Toast.LENGTH_LONG).show();
//                        }
//                    } else {
//                        Toast.makeText(mcontext, "No detail found", Toast.LENGTH_LONG).show();
//                    }
//                }
//            } else {
//                Toast.makeText(mcontext, "Try Again", Toast.LENGTH_LONG).show();
//            }
//        }
//
//        @Override
//        protected void onPreExecute() {
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//        }
//
//        @Override
//        protected void onCancelled() {
//            run = true;
//            super.onCancelled();
//        }
//    }

}

