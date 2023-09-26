package com.challengers.of.call.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;

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
import com.challengers.of.call.Activitywin;
import com.challengers.of.call.Contest.ContestResponseJson;
import com.challengers.of.call.Dashboard;
import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.ItemClickListener;
import com.challengers.of.call.Main2Activity;
import com.challengers.of.call.R;
import com.challengers.of.call.ResultApi.ContinueRequestJson;
import com.challengers.of.call.ResultApi.ContinueResponseJson;
import com.challengers.of.call.ResultApi.QuitRequestJson;
import com.challengers.of.call.ResultApi.ShowResultRequestJson;
import com.challengers.of.call.Retrofit.ServiceGenerator;
import com.challengers.of.call.Retrofit.UserService;
import com.challengers.of.call.WebService;
import com.challengers.of.call.Winnerdetail;
import com.challengers.of.call.data.Getmyresultdata;
import com.challengers.of.call.helper.GlideCircleTransformation;
import com.challengers.of.call.my_contest.MyContestActivity;
import com.challengers.of.call.testing.SettingsPreferences;
import com.challengers.of.call.testing.StaticUtils;
import com.kaopiz.kprogresshud.KProgressHUD;

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

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    List<Getmyresultdata> productList;

    Context mcontext;
    ViewHolder view;
    String Message;
    JSONObject jasonObject;
    JSONArray jsonArray = null;
    String Contestid, Newcontestid;
    String Winningamount;
    private ljsflsj good;
    public Dialog Popup;

//    public static AsyncCallWS taskcontinoue;
//    public static AsyncCallWSquit taskquit;
    public String GameName, Gameid, Entryfees, Chasingamount;
    public Dialog progressbar;

    public ResultAdapter(Context context, List<Getmyresultdata> productList) {
        super();
        this.mcontext = context;
        this.productList = productList;
        progressbar = new Dialog(context);
        Popup = new Dialog(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_result_row, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        final Getmyresultdata getselfdata = productList.get(i);
        view = viewHolder;

        SharedPreferences lucky = mcontext.getSharedPreferences("coding", Context.MODE_PRIVATE);
        Gameid = lucky.getString("gameid", null);
        String Nickname = decrypt(good.key, productList.get(i).getNickname());
        viewHolder.tvNickname.setText(Nickname);
        String Timer = decrypt(good.key, productList.get(i).getTimer());
        viewHolder.tvPoints.setText(Timer);
        String Entryfeesss = decrypt(good.key, productList.get(i).getEntryfees());
        viewHolder.tvEntryfees.setText("₹" + Entryfeesss);
        String Winnername = decrypt(good.key, productList.get(i).getWinnername());
        viewHolder.winnername.setText(Winnername);
        String Winnerscore = decrypt(good.key, productList.get(i).getWinnerscore());
        viewHolder.winnerpoint.setText(Winnerscore);

        String Loosername = decrypt(good.key, productList.get(i).getLoosername());
        String Looserpoint = decrypt(good.key, productList.get(i).getLooserpoint());
        viewHolder.loosername.setText(Loosername);
        viewHolder.looserpoint.setText(Looserpoint);

        String Result = decrypt(good.key, productList.get(i).getResult());
        String Winningamountss = decrypt(good.key, productList.get(i).getWinningamount());
        viewHolder.tvTimer.setText("You " + Result);
        if (Result.equalsIgnoreCase("WATING FOR SOMEONE TO JOIN YOUR CONTEST")) {
            viewHolder.layoutwait.setVisibility(View.VISIBLE);
            viewHolder.layoutwin.setVisibility(View.GONE);

        } else if (Result.equalsIgnoreCase("Won")) {

            viewHolder.l1.setVisibility(View.GONE);
            viewHolder.layoutwait.setVisibility(View.GONE);
            viewHolder.layoutwin.setVisibility(View.VISIBLE);
            viewHolder.tvTimer.setCompoundDrawables(null, null, null, null);
            viewHolder.tvTimer.setText("You Won");
            viewHolder.tvWiningamount.setText("₹" + Winningamountss);
        } else if (Result.equalsIgnoreCase("Win")) {

            viewHolder.l1.setVisibility(View.GONE);
            viewHolder.layoutwait.setVisibility(View.GONE);
            viewHolder.layoutwin.setVisibility(View.GONE);
            viewHolder.tvTimer.setCompoundDrawables(null, null, null, null);
            viewHolder.tvWiningamount.setText("₹" + Winningamountss);
            viewHolder.tvTimer.setText("You Won");
        } else if (Result.equalsIgnoreCase("LOSE")) {
            viewHolder.l1.setVisibility(View.GONE);
            viewHolder.txtwinnername.setText(getselfdata.getWinnername());
            viewHolder.txtwinnerscore.setText("Winner Point: " + Winnerscore);
            viewHolder.layoutwait.setVisibility(View.GONE);
            viewHolder.layoutwin.setVisibility(View.GONE);
            viewHolder.tvTimer.setCompoundDrawables(null, null, null, null);
            viewHolder.tvTimer.setText("You Lose");
            viewHolder.tvWiningamount.setText("₹" + Winningamountss);
        }
        String Winnerimage = decrypt(good.key, productList.get(i).getWinnerimage());
        if (getselfdata.getLengthwin() < 30) {
//            Log.i("sumitverma",getselfdata.getWinnerimage());

            if (Winnerimage != null && Winnerimage != "") {
                Glide.with(getApplicationContext()).load("http://callofchallengers.com/coc/images/users/" + Winnerimage)
                        .crossFade()
                        .placeholder(R.drawable.logo)
                        .error(R.drawable.logo)
                        .bitmapTransform(new GlideCircleTransformation(getApplicationContext()))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .skipMemoryCache(false)
                        .into(viewHolder.winimage);
            }
        } else {
            Glide.with(getApplicationContext()).load(Winnerimage)
                    .crossFade()
                    .placeholder(R.drawable.logo)
                    .error(R.drawable.logo)
                    .bitmapTransform(new GlideCircleTransformation(getApplicationContext()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(false)
                    .into(viewHolder.winimage);
        }

        String Looserimage = decrypt(good.key, productList.get(i).getLooserimage());
        if (getselfdata.getLengthlos() < 30) {
            if (Looserimage != null && Looserimage != "") {
                Glide.with(getApplicationContext()).load("http://callofchallengers.com/coc/images/users/" + Looserimage)
                        .crossFade()
                        .placeholder(R.drawable.logo)
                        .error(R.drawable.logo)
                        .bitmapTransform(new GlideCircleTransformation(getApplicationContext()))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .skipMemoryCache(false)
                        .into(viewHolder.userimage);
            }
        } else {
            Glide.with(getApplicationContext()).load(Looserimage)
                    .crossFade()
                    .placeholder(R.drawable.logo)
                    .error(R.drawable.logo)
                    .bitmapTransform(new GlideCircleTransformation(getApplicationContext()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(false)
                    .into(viewHolder.userimage);
        }


        //on Quit Click
        viewHolder.txtquit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                taskquit = new AsyncCallWSquit();
//                taskquit.execute();
                Popup.setContentView(R.layout.resultpopup);
                Button okbtn = (Button) Popup.findViewById(R.id.okyes);
                Button nobtn = (Button) Popup.findViewById(R.id.closePopup);

                nobtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (SettingsPreferences.getVibration(mcontext)) {
                            StaticUtils.vibrate(mcontext, StaticUtils.VIBRATION_DURATION);
                        }
                        if (SettingsPreferences.getSoundEnableDisable(mcontext)) {
                            StaticUtils.backSoundonclick(mcontext);
                        }

                        Popup.dismiss();
                    }
                });
                okbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (SettingsPreferences.getVibration(mcontext)) {
                            StaticUtils.vibrate(mcontext, StaticUtils.VIBRATION_DURATION);
                        }
                        if (SettingsPreferences.getSoundEnableDisable(mcontext)) {
                            StaticUtils.backSoundonclick(mcontext);
                        }
                        viewHolder.txtquit.setEnabled(false);

                        Contestid = getselfdata.getContestid();
                        Entryfees = getselfdata.getEntryfees();
                        Chasingamount = getselfdata.getPoints();
                        Winningamount = getselfdata.getWinningamount();
                        viewHolder.layoutwin.setVisibility(View.GONE);

                        progressbar.setContentView(R.layout.progresbarlayout);
                        progressbar.setCancelable(false);
                        RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);

                        RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(mcontext,
                                20, 60, ContextCompat.getColor(mcontext, R.color.white));
                        loader.setAnimDuration(3000);

                        rotatingCircularDotsLoader.addView(loader);

                        progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
                        progressbar.show();





                        QuitAPI();


                        Popup.dismiss();
                    }
                });
                Popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
                Popup.show();






            }
        });
        //on Continue Click
        viewHolder.txtcontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                taskcontinoue = new AsyncCallWS();
//                taskcontinoue.execute();

                Popup.setContentView(R.layout.continuepopup);
                Button okbtn = (Button) Popup.findViewById(R.id.okyes);
                Button nobtn = (Button) Popup.findViewById(R.id.closePopup);

                nobtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (SettingsPreferences.getVibration(mcontext)) {
                            StaticUtils.vibrate(mcontext, StaticUtils.VIBRATION_DURATION);
                        }
                        if (SettingsPreferences.getSoundEnableDisable(mcontext)) {
                            StaticUtils.backSoundonclick(mcontext);
                        }

                        Popup.dismiss();
                    }
                });
                okbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (SettingsPreferences.getVibration(mcontext)) {
                            StaticUtils.vibrate(mcontext, StaticUtils.VIBRATION_DURATION);
                        }
                        if (SettingsPreferences.getSoundEnableDisable(mcontext)) {
                            StaticUtils.backSoundonclick(mcontext);
                        }

                        viewHolder.txtcontinue.setEnabled(false);

                        Entryfees = getselfdata.getEntryfees();
                        Chasingamount = getselfdata.getWinnerscore();
                        String a = getselfdata.getNewcontest();
                        if (a.equalsIgnoreCase("N/A")) {
                            Contestid = getselfdata.getContestid();
                            Newcontestid = Contestid;
                        } else {
                            Contestid = getselfdata.getNewcontest();
                            Newcontestid = getselfdata.getContestid();
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





                        ContinueAPI();


                        Popup.dismiss();
                    }
                });
                Popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
                Popup.show();






                //Toast.makeText(mcontext, "Click on Continue", Toast.LENGTH_LONG).show();
            }
        });

//        viewHolder.txtwinnername.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(mcontext, Winnerdetail.class);
//                intent.putExtra("Winnerid",getselfdata.getWinnerid());
//                intent.putExtra("Name",getselfdata.getWinnername());
//                mcontext.startActivity(intent);
//            }
//        });


        viewHolder.txtmypoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SettingsPreferences.getVibration(mcontext)) {
                    StaticUtils.vibrate(mcontext, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(mcontext)) {
                    StaticUtils.backSoundonclick(mcontext);
                }
                Contestid = getselfdata.getContestid();
                SharedPreferences prefs = mcontext.getSharedPreferences("UserData", Context.MODE_PRIVATE);
                String Loginid = prefs.getString("Loginid", null);
                String Name = prefs.getString("Name", null);
//                Intent intent=new Intent(mcontext,Questionwiseresult.class);
//                intent.putExtra("Loginid",Loginid);
//                intent.putExtra("Contestid",Contestid);
//                intent.putExtra("From","Result");
//                mcontext.startActivity(intent);
//                ((Activity)mcontext).finish();
            }
        });
        viewHolder.userimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(mcontext)) {
                    StaticUtils.vibrate(mcontext, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(mcontext)) {
                    StaticUtils.backSoundonclick(mcontext);
                }
                SharedPreferences prefs = mcontext.getSharedPreferences("UserData", Context.MODE_PRIVATE);
                String Loginid = prefs.getString("Loginid", null);
                String Name = prefs.getString("Name", null);

                Intent intent = new Intent(mcontext, Winnerdetail.class);
                intent.putExtra("Winnerid", getselfdata.getLooserid());
                intent.putExtra("Name", getselfdata.getLoosername());
                intent.putExtra("goto", "fragmentresult");
                intent.putExtra("image", getselfdata.getLooserimage());
                mcontext.startActivity(intent);
                ((Activity) mcontext).finish();
            }
        });
        viewHolder.winimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(mcontext)) {
                    StaticUtils.vibrate(mcontext, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(mcontext)) {
                    StaticUtils.backSoundonclick(mcontext);
                }
                Intent intent = new Intent(mcontext, Winnerdetail.class);
                intent.putExtra("Winnerid", getselfdata.getWinnerid());
                intent.putExtra("Name", getselfdata.getWinnername());
                intent.putExtra("goto", "fragmentresult");
                intent.putExtra("image", getselfdata.getWinnerimage());

                mcontext.startActivity(intent);
                ((Activity) mcontext).finish();
            }
        });


        //viewHolder.tvEntryfees.setText( getselfdata.getEntryfees());
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

        public ImageView imgThumbnail;
        public ImageView winimage, userimage;
        public TextView winnername, loosername, looserpoint, winnerpoint;
        public TextView tvNickname, tvPoints, tvWiningamount, tvTimer, tvEntryfees, txtwinnername,
                txtwinnerscore, txtquit, txtcontinue, txtmypoint;
        LinearLayout l1;
        LinearLayout layoutwait, layoutwin;
        private ItemClickListener clickListener;

        public ViewHolder(View itemView) {


            super(itemView);
            winimage = (ImageView) itemView.findViewById(R.id.ivwinprofile);
            userimage = (ImageView) itemView.findViewById(R.id.ivloginuserprofile);


            winnername = (TextView) itemView.findViewById(R.id.winnername);
            winnerpoint = (TextView) itemView.findViewById(R.id.winnerpoint);
            loosername = (TextView) itemView.findViewById(R.id.loosername);
            looserpoint = (TextView) itemView.findViewById(R.id.looserpoint);


            tvNickname = (TextView) itemView.findViewById(R.id.textView);
            tvPoints = (TextView) itemView.findViewById(R.id.textView2);
            tvWiningamount = (TextView) itemView.findViewById(R.id.textView3);
            tvTimer = (TextView) itemView.findViewById(R.id.textView4);
            tvEntryfees = itemView.findViewById(R.id.textView5);
            txtwinnername = (TextView) itemView.findViewById(R.id.textView6);
            txtwinnerscore = (TextView) itemView.findViewById(R.id.textView7);
            txtquit = itemView.findViewById(R.id.txtquit);
            txtcontinue = itemView.findViewById(R.id.txtcontinue);
            layoutwait = itemView.findViewById(R.id.layoutwait);
            layoutwin = itemView.findViewById(R.id.layoutwin);
            l1 = itemView.findViewById(R.id.l1);
            txtmypoint = itemView.findViewById(R.id.mypoint);
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

    private void ContinueAPI() {
        SharedPreferences prefs = mcontext.getSharedPreferences("UserData", Context.MODE_PRIVATE);

        String Password = prefs.getString("Password", null);
        String Usernames = prefs.getString("Name", null);
        String Loginid = prefs.getString("Loginid", null);


        final ContinueRequestJson request = new ContinueRequestJson();

        request.setLoginid(decrypt(good.key, Loginid));
        request.setNickname(decrypt(good.key, Usernames));
        request.setContestid(decrypt(good.key, Contestid));
        request.setNewcontestid(decrypt(good.key, Newcontestid));
        request.setEntryfees(decrypt(good.key, Entryfees));
        request.setAmount(decrypt(good.key, Chasingamount));

        UserService service = ServiceGenerator.createService(UserService.class, decrypt(good.key, Usernames), decrypt(good.key, Password));
        service.resultcontinue(request).enqueue(new Callback<ContinueResponseJson>() {
            @Override
            public void onResponse(Call<ContinueResponseJson> call, Response<ContinueResponseJson> response) {
                if (response.isSuccessful()) {
                    Message = response.body().message;
                    if (Message.equalsIgnoreCase("fail")){
                        Toast.makeText(mcontext, "Unwanted activity detected ! Please try again", Toast.LENGTH_SHORT).show();
                    }else {


                        Intent intent = new Intent(mcontext, MyContestActivity.class);
                        intent.putExtra("goto", "Mycontest");
                        mcontext.startActivity(intent);
                        ((Activity) mcontext).finish();
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

    private void QuitAPI() {
        SharedPreferences prefs = mcontext.getSharedPreferences("UserData", Context.MODE_PRIVATE);

        String Password = prefs.getString("Password", null);
        String Usernames = prefs.getString("Name", null);
        String Loginid = prefs.getString("Loginid", null);


        final QuitRequestJson request = new QuitRequestJson();

        request.setLoginid(decrypt(good.key, Loginid));
        request.setContestid(decrypt(good.key, Contestid));
        request.setWinningamount(decrypt(good.key, Winningamount));

        UserService service = ServiceGenerator.createService(UserService.class, decrypt(good.key, Usernames), decrypt(good.key, Password));
        service.resultquit(request).enqueue(new Callback<ContinueResponseJson>() {
            @Override
            public void onResponse(Call<ContinueResponseJson> call, Response<ContinueResponseJson> response) {
                if (response.isSuccessful()) {
                    String Totalwallet;
                    Totalwallet = response.body().message;

                    if (Totalwallet != null && Totalwallet != "") {///////// message equel to TotalWallet


                        Totalwallet = encrypt(good.key, good.initVector, Totalwallet);
                        SharedPreferences pref2 = mcontext.getSharedPreferences("UserData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = pref2.edit();
                        editor1.putString("Totalwallet", Totalwallet);
                        editor1.apply();
                    }

                    Intent intent = new Intent(mcontext, Activitywin.class);
                    intent.putExtra("goto", "Result");
                    intent.putExtra("win", "Quit");
                    intent.putExtra("winning", Winningamount);
                    intent.putExtra("Totalcontest", Main2Activity.Totalcontest);
                    mcontext.startActivity(intent);
                    ((Activity) mcontext).finish();
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

//    public class AsyncCallWS extends AsyncTask<String, Void, Void> {
//        String displayText;
//        private KProgressHUD hud;
//
//        @Override
//        protected Void doInBackground(String... params) {
//            SharedPreferences prefs = mcontext.getSharedPreferences("UserData", Context.MODE_PRIVATE);
//            String Loginid = prefs.getString("Loginid", null);
//            String Name = prefs.getString("Name", null);
//            displayText = WebService.Createchallenge(Loginid, Name, Contestid, Newcontestid, Entryfees, Chasingamount, Gameid, "ContinueContest");
//            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                try {
//                    jasonObject = new JSONObject(displayText);
//                    jsonArray = jasonObject.getJSONArray("Response");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
//                        Contestid = jsonrowdata.getString("Count");
//
//                        Contestid = decrypt(good.key, Contestid);
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
//                Intent intent = new Intent(mcontext, MyContestActivity.class);
//                intent.putExtra("goto", "Mycontest");
////                int count=Integer.valueOf(Main2Activity.Totalcontest);
////                count+=1;
////                intent.putExtra("Totalcontest",count+"");
//                mcontext.startActivity(intent);
//                ((Activity) mcontext).finish();
//                // Toast.makeText(mcontext, "Contest Created Successfully", Toast.LENGTH_LONG).show();
//            } else if (jsonArray.length() == 0) {
//                Toast.makeText(mcontext, "No detail found", Toast.LENGTH_LONG).show();
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
//            taskcontinoue.cancel(true);
//            super.onCancelled();
//        }
//    }

    // Task for Quiting result
//
//    public class AsyncCallWSquit extends AsyncTask<String, Void, Void> {
//        String displayText, Newcontestid;
//        private KProgressHUD hud;
//
//        @Override
//        protected Void doInBackground(String... params) {
//            SharedPreferences prefs = mcontext.getSharedPreferences("UserData", Context.MODE_PRIVATE);
//            String Loginid = prefs.getString("Loginid", null);
//            String Name = prefs.getString("Name", null);

//            displayText = WebService.Changeresultstatus(Loginid, Contestid, Winningamount, "Quit");
//            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                try {
//                    jasonObject = new JSONObject(displayText);
//                    jsonArray = jasonObject.getJSONArray("Response");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//
//                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
//                        Newcontestid = jsonrowdata.getString("Count");
//
//                        Newcontestid = encrypt(good.key, good.initVector, Newcontestid);
//
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
//                if (Newcontestid != null && Newcontestid != "") {
//                    SharedPreferences pref2 = mcontext.getSharedPreferences("UserData", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor1 = pref2.edit();
//                    editor1.putString("Totalwallet", Newcontestid);
//                    editor1.apply();
//                }
//
//                Intent intent = new Intent(mcontext, Activitywin.class);
//                intent.putExtra("goto", "Result");
//                intent.putExtra("win", "Quit");
//                intent.putExtra("winning", Winningamount);
//                intent.putExtra("Totalcontest", Main2Activity.Totalcontest);
//                mcontext.startActivity(intent);
//                ((Activity) mcontext).finish();
//
//            } else if (jsonArray.length() == 0) {
//                Toast.makeText(mcontext, "No detail found", Toast.LENGTH_LONG).show();
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
//            taskquit.cancel(true);
//            super.onCancelled();
//        }
//    }
}

