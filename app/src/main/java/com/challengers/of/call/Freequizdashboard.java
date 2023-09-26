package com.challengers.of.call;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.challengers.of.call.adapter.FreequizdashboardAdapter;
import com.challengers.of.call.data.Getfreequizdashboarddata;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.challengers.of.call.helper.GlideCircleTransformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Freequizdashboard extends AppCompatActivity {
    public static double Remaininglife = 0;
    public List<Getfreequizdashboarddata> productList;
    public double Amount = 0;
    FreequizdashboardAdapter adapter;
    String displayText1;
    ListView listView;
    JSONObject jasonObject;
    JSONArray jsonArray = null;
    TextView txtlife, txtwallet, textView;
    String Totallife;
    public ProgressDialog progressDialog;
    public String GetFreeLife;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freequizdashboard);
        listView = findViewById(R.id.listView);

//        progressDialog = new ProgressDialog(this);

        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.buttonanimation);
        textView = (TextView) findViewById(R.id.gotonew);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Freequizdashboard.this, Leaderboardfreequiz.class);
                startActivity(intent);
                v.startAnimation(myAnim);
                finish();
            }
        });


        ImageView ivback = findViewById(R.id.ivback);
        ImageView ivwallet = findViewById(R.id.ivwallet);
        ImageView ivlife = findViewById(R.id.ivlife);
        ImageView img = findViewById(R.id.ivprofile);
        ImageView imageView1 = findViewById(R.id.ivprofileview);
        imageView1.setVisibility(View.VISIBLE);
        ivwallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Freequizdashboard.this, Wallet.class);
                intent.putExtra("from", "freequiz");
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
            }
        });
        ivlife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Freequizdashboard.this, Life.class);
                intent.putExtra("from", "freequiz");
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
            }
        });
        txtlife = findViewById(R.id.txtlife);
        txtlife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Freequizdashboard.this, Life.class);
                intent.putExtra("from", "freequiz");
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
            }
        });

        txtwallet = findViewById(R.id.txtwallet);

        txtwallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Freequizdashboard.this, Wallet.class);
                intent.putExtra("from", "freequiz");
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
            }
        });

        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String Loginid1 = prefs.getString("drawable", null);
        String Name = prefs.getString("Name", null);
        String Profilepic = prefs.getString("Profilepic", null);
        String Customimage = prefs.getString("Imageurl", null);
        String Totalwallet = prefs.getString("Totalwallet", null);

        Totallife = prefs.getString("Totallife", null);
        LinearLayout l1 = findViewById(R.id.l1);
        LinearLayout l2 = findViewById(R.id.l2);
        if (Loginid1 != null) {
            l1.setVisibility(View.VISIBLE);
            l2.setVisibility(View.GONE);
            img.setVisibility(View.VISIBLE);
            imageView1.setVisibility(View.GONE);
            int imageId = getResourseId(this, Loginid1, "drawable", getPackageName());
            img.setImageResource(imageId);
        } else if (Customimage != null && Customimage != "") {
            l1.setVisibility(View.GONE);
            img.setVisibility(View.GONE);
            l2.setVisibility(View.VISIBLE);
            imageView1.setVisibility(View.VISIBLE);
            Glide.with(getApplicationContext()).load("http://callofchallengers.com/coc/images/users/" + Customimage)
                    .crossFade()
                    .placeholder(R.drawable.logo)
                    .error(R.drawable.logo)
                    .bitmapTransform(new GlideCircleTransformation(getApplicationContext()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(false)
                    .into(imageView1);
        } else if (Profilepic != null && Profilepic != "") {
            l1.setVisibility(View.GONE);
            img.setVisibility(View.GONE);
            l2.setVisibility(View.VISIBLE);
            imageView1.setVisibility(View.VISIBLE);
            Glide.with(getApplicationContext()).load(Profilepic)
                    .crossFade()
                    .placeholder(R.drawable.logo)
                    .error(R.drawable.logo)
                    .bitmapTransform(new GlideCircleTransformation(getApplicationContext()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(false)
                    .into(imageView1);
        }

//        GetFreeLife = prefs.getString("UserRemFreeLife", null);
        final Button imageView = findViewById(R.id.imgplay);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GetFreeLife.equals("20")) {
                    view.startAnimation(myAnim);
                    AlertDialog.Builder builder = new AlertDialog.Builder(Freequizdashboard.this);
                    builder.setTitle("Alert");
                    builder.setMessage("Your chances in a day is over now wait for result");
                    builder.setIcon(R.drawable.logo);
                    builder.setCancelable(true);
                    builder.setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });

                    builder.show();

                } else {
                    imageView.startAnimation(AnimationUtils.loadAnimation(Freequizdashboard.this, R.anim.imageanim));
                    imageView.setEnabled(false);
                    if (Double.valueOf(Totallife) >= 1) {
                        AsyncCallWScreatefreequiz task = new AsyncCallWScreatefreequiz();
                        task.execute();

                        view.startAnimation(myAnim);
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Freequizdashboard.this);
                        builder.setTitle("Alert");
                        builder.setMessage("You havn't left any Life");
                        builder.setCancelable(true);
                        builder.setIcon(R.drawable.logo);
                        builder.setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        builder.show();
//
                    }
                }
            }
        });


        if (Totalwallet != null && Totalwallet != "") {
            txtwallet.setText(Totalwallet);
        } else {
            txtwallet.setText("0");
        }
        if (Totallife != null && Totallife != "") {
            txtlife.setText(Totallife);
        } else {
            txtlife.setText("0");
        }
        TextView textView = findViewById(R.id.username);
        textView.setText(Name);
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Freequizdashboard.this, Dashboard.class);
                intent.putExtra("goto", "dashboard");
                intent.putExtra("Totalcontest", Main2Activity.Totalcontest);
                startActivity(intent);
                finish();
                Dashboard.dashboard.finish();
                finish();
            }
        });
        AsyncCallWS task = new AsyncCallWS();
        task.execute();

    }

    public class AsyncCallWS extends AsyncTask<String, Void, Void> {

        String Maxamount, Score, From, Point, To, Type, Remain,UserRemFreeLife;

        @Override
        protected Void doInBackground(String... params) {
            SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
            String Loginid = prefs.getString("Loginid", null);
            displayText1 = WebService.Selectfreequizdashboard(Loginid, "Freequiz");
            productList = new ArrayList<>();
            if (displayText1 != "" && displayText1 != null && displayText1 != "connection fault" && !displayText1.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
                try {
                    jasonObject = new JSONObject(displayText1);

                    jsonArray = jasonObject.getJSONArray("Response");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Getfreequizdashboarddata getquestiondata1 = new Getfreequizdashboarddata();
                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
                        Maxamount = jsonrowdata.getString("Maxamount");
                        Score = jsonrowdata.getString("Score");
                        UserRemFreeLife = jsonrowdata.getString("userRFreeLife");


                        From = jsonrowdata.getString("From");
                        Point = jsonrowdata.getString("Prize");
                        To = jsonrowdata.getString("To");
                        Type = jsonrowdata.getString("Type");
                        Remaininglife = jsonrowdata.getDouble("Remaininglife");
                        Remain = jsonrowdata.getString("Remaininglife");
                        getquestiondata1.setMaxamount(Maxamount);
                        getquestiondata1.setFrom(From);
                        getquestiondata1.setTo(To);
                        getquestiondata1.setType(Type);
                        getquestiondata1.setPoint(Point);
                        getquestiondata1.setScore(Score);
                        adapter.add(getquestiondata1);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            LayoutInflater inflater = getLayoutInflater();
            final ImageView imgplus;
            if (displayText1.equalsIgnoreCase("connection fault")) {
                Toast.makeText(Freequizdashboard.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
            } else if (displayText1.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
                Toast.makeText(Freequizdashboard.this, "Please try after some times...", Toast.LENGTH_LONG).show();
            } else if (jsonArray.length() > 0) {
                final Button imageView = findViewById(R.id.imgplay);
                textView = (TextView) findViewById(R.id.gotonew);
                textView.setEnabled(true);
                imageView.setEnabled(true);
                SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("Totallife", Remain);
                editor.putString("UserRemFreeLife",UserRemFreeLife);
                editor.apply();
                txtlife.setText(Remain);
                ViewGroup header = (ViewGroup) inflater.inflate(R.layout.list_freequizdashboard_header, listView, false);
                ViewGroup footer = (ViewGroup) inflater.inflate(R.layout.list_freequizdashboard_footer, listView, false);

                ViewGroup newfooter = (ViewGroup) inflater.inflate(R.layout.list_view_new_footer, listView, false);
                TextView tvnumber = newfooter.findViewById(R.id.txtleftchanc);

                GetFreeLife = UserRemFreeLife;

                tvnumber.setText(GetFreeLife + "/20");


                TextView txtleftchances = footer.findViewById(R.id.txtleftchances);
                txtleftchances.setText("TOTAL CHANCES : " + Remain);
                imgplus = footer.findViewById(R.id.image);
                imgplus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        imgplus.startAnimation(AnimationUtils.loadAnimation(Freequizdashboard.this, R.anim.imageanim));
                        Intent intent = new Intent(Freequizdashboard.this, Life.class);
                        intent.putExtra("from", "freequiz");
                        startActivity(intent);
                        finish();
                    }
                });
                listView.addHeaderView(header);
                listView.addFooterView(footer);
                listView.addFooterView(newfooter);
                listView.setAdapter(adapter);
                TextView txtheader = findViewById(R.id.textView3);
                txtheader.setText(Maxamount);
                TextView txtheadertwo = findViewById(R.id.scoretextView);
                txtheadertwo.setText(Score);

                //SharedPreferences prefs = getSharedPreferences("UserData",Context.MODE_PRIVATE);


            } else {
                Toast.makeText(Freequizdashboard.this, "Please try again", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onPreExecute() {
            final Button imageView = findViewById(R.id.imgplay);
            textView = (TextView) findViewById(R.id.gotonew);
            textView.setEnabled(false);
            imageView.setEnabled(false);
            adapter = new FreequizdashboardAdapter(Freequizdashboard.this, R.layout.list_questionwise_result_row);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

    public class AsyncCallWScreatefreequiz extends AsyncTask<String, Void, Void> {
        String displayText;
        String Contestid, Totallife;
        private KProgressHUD hud;

        @Override
        protected Void doInBackground(String... params) {
            SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
            String Loginid = prefs.getString("Loginid", null);
            String Name = prefs.getString("Name", null);

            displayText = WebService.Createfreequiz(Loginid, "", "Createquiz");
            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
                try {
                    jasonObject = new JSONObject(displayText);
                    jsonArray = jasonObject.getJSONArray("Response");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
                        Contestid = jsonrowdata.getString("Count");
                        Totallife = jsonrowdata.getString("Totallife");

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            hud.dismiss();
            if (displayText.equalsIgnoreCase("connection fault")) {
                Toast.makeText(Freequizdashboard.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
            } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
                Toast.makeText(Freequizdashboard.this, "Please try after some times...", Toast.LENGTH_LONG).show();
            } else if (jsonArray.length() > 0) {
                if (Remaininglife >= 1) {

                    Intent intent = new Intent(Freequizdashboard.this, Startfreequiz.class);
                    intent.putExtra("Contestid", Contestid.trim());
                    intent.putExtra("data", displayText1);
                    startActivity(intent);
                    finish();
                    SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("Totallife", Totallife);


                    editor.apply();

//                    Toast.makeText(Freequizdashboard.this, "Contest Created Successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Freequizdashboard.this, "You havn't left any Life", Toast.LENGTH_LONG).show();
                }
            } else if (jsonArray.length() == 0) {
                Toast.makeText(Freequizdashboard.this, "No detail found", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(Freequizdashboard.this, "Try Again", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onPreExecute() {
            hud = KProgressHUD.create(Freequizdashboard.this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setWindowColor(Color.parseColor("#c20e14"))
                    .setLabel("")
                    .setAnimationSpeed(1);
            hud.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

    public static int getResourseId(Context context, String pVariableName, String pResourcename, String pPackageName) throws RuntimeException {
        try {
            return context.getResources().getIdentifier(pVariableName, pResourcename, pPackageName);
        } catch (Exception e) {
            throw new RuntimeException("Error getting Resource ID.", e);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Freequizdashboard.this, Dashboard.class);
        intent.putExtra("goto", "dashboard");
        intent.putExtra("Totalcontest", Main2Activity.Totalcontest);
        startActivity(intent);
//        Dashboard.dashboard.finish();
//        int pid = android.os.Process.myPid();
//        android.os.Process.killProcess(pid);
//        android.os.Process.killProcess(android.os.Process.myPid());
        finish();
        super.onBackPressed();
    }
}

