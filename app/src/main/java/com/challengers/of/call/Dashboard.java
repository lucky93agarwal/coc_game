package com.challengers.of.call;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import com.agrawalsuneet.dotsloader.loaders.RotatingCircularDotsLoader;
import com.challengers.of.call.Add_Money_Popup.addmoneypopup;
import com.challengers.of.call.Banned_APP.bannedapp;
import com.challengers.of.call.Dashboardapidata.FirstAddWalletRequestJson;
import com.challengers.of.call.Dashboardapidata.FirstAddWalletResponseJson;
import com.challengers.of.call.Dashboardapidata.TokenRequestJson;
import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.GameTypes.GametypesActivity;
import com.challengers.of.call.Practice.PracticeActivity;
import com.challengers.of.call.Retrofit.ServiceGenerator;
import com.challengers.of.call.Retrofit.UserService;
import com.challengers.of.call.Setting_Popup.settingpopup;
import com.challengers.of.call.SignUpData.SignUpNameCheckResponseJson;
import com.challengers.of.call.otherpopup.otherspopup;
import com.challengers.of.call.testing.SettingsPreferences;
import com.challengers.of.call.testing.StaticUtils;
import com.google.android.gms.tasks.OnSuccessListener;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.challengers.of.call.helper.GlideCircleTransformation;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.decrypt;
import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.encrypt;

public class Dashboard extends AppCompatActivity {
    public String OneRs, EarnMore, OneTotal, TwoRs, TwoTotal, ThreeRs, ThreeTotal, FourRs, FourTotal;
    public static Activity dashboard;
    public static Drawable d;
    String Totalcontest = "0";
    ImageView ivprofile;
    TextView txtwallet;
    JSONObject jasonObject;
    JSONArray jsonArray = null;
    public String Errors;

    public String Status, StatusTwo;
    private static bannedapp bannedapps;

//    private AdView mAdView;

    public String Totalwallet, Totallife;

    public Dialog progressbar, closeapp, banned; //Popup Dialog Box
    public String object, Name,Message;

    //Popup Dialog Box

    public ImageView closepopupbtn;
    public Button btn;



    String resTxt = null;

    public TextView referralcode;/////////////////////////////////////////////////////////////////////////////////
    private static String REFERAL_CODE = "";//////////////////////////////////////////////////////////////////////

    public static Context context;
    static final int PERMISSION_READ_STATE = 123;

    private ljsflsj good;

    public static int getResourseId(Context context, String pVariableName, String pResourcename, String pPackageName) throws RuntimeException {
        try {
            return context.getResources().getIdentifier(pVariableName, pResourcename, pPackageName);
        } catch (Exception e) {
            throw new RuntimeException("Error getting Resource ID.", e);
        }
    }

    public String Loginids;


    public Animation myAnim;

    public Button leaderboardbtn;

    public LinearLayout linearLayout;

    private static addmoneypopup addpopup;
    private static settingpopup setting;
    private static otherspopup otherpopup;
    public String Response, IMEINumber, Wallet, newToken, BANKTXNID, MinAmount, TXNAMOUNT, STATUS, TXNTYPE, GATEWAYNAME, RESPMSG, RESPCODE, ettamount;
    public String BANKNAME, MID, PAYMENTMODE, REFUNDAMT, TXNDATE;

    public String OnesRs, OnesTotal, TwosRs, TwosTotal, ThreesRs, ThreesTotal, FoursRs, FoursTotal;


    @Override
    protected void onResume() {
        super.onResume();

        bannedapps = new bannedapp(Dashboard.this);
        bannedapps.bannedbig();
    }

    @Override
    protected void onPause() {
        super.onPause();

        bannedapps = new bannedapp(Dashboard.this);
        bannedapps.bannedbig();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        context = getApplicationContext();

        bannedapps = new bannedapp(Dashboard.this);
        bannedapps.bannedbig();

        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        Loginids = prefs.getString("Loginid", null);
        SharedPreferences prefss = getSharedPreferences("UserDatasss", Context.MODE_PRIVATE);
        IMEINumber = prefss.getString("IMEI", null);




        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(Dashboard.this, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                newToken = instanceIdResult.getToken();

                newToken = encrypt(good.key, good.initVector, newToken);






//                SendDataToServer(newToken, Loginids,IMEINumber);

                TokenAPI();

            }
        });




        closeapp = new Dialog(this, R.style.PauseDialog);
        banned = new Dialog(this, R.style.PauseDialog);

        progressbar = new Dialog(this);

        otherpopup = new otherspopup(Dashboard.this);


        ImageView ivppppp = (ImageView) findViewById(R.id.ppppp);
        ivppppp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }


                v.startAnimation(myAnim);
                otherpopup.otherpopupsss();
            }
        });

        progressbar.setContentView(R.layout.progresbarlayout);
        progressbar.setCancelable(false);
        RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);

        RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Dashboard.this,
                20, 60, ContextCompat.getColor(Dashboard.this, R.color.white));
        loader.setAnimDuration(3000);

        rotatingCircularDotsLoader.addView(loader);

        progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
        progressbar.show();
//
        addpopup = new addmoneypopup(Dashboard.this);
        setting = new settingpopup(Dashboard.this);

        ///// starting time get data
//        tasks = new AsyncCallWSdistribute();
//        tasks.execute();




        FirstApiAddWallet();

        ImageView tvsetting = (ImageView) findViewById(R.id.setting);
        tvsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }

//                addpopup.addpopup();
                setting.setting();
            }
        });

//        AsyncCallWS task=new AsyncCallWS();
        Button btnpractice = (Button) findViewById(R.id.practice);
        btnpractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }

                Intent intent = new Intent(Dashboard.this, PracticeActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_left, R.anim.slide_right);

            }
        });
//        task.execute();
        leaderboardbtn = (Button) findViewById(R.id.leaderboard);
        leaderboardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }

                v.startAnimation(myAnim);
//                vibrator.vibrate(70);
                Intent intent1 = new Intent(Dashboard.this, Leaderboard.class);

                startActivity(intent1);
                finish();
                overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
            }
        });

        myAnim = AnimationUtils.loadAnimation(this, R.anim.buttonanimation);


        Button button = findViewById(R.id.refer);/////////////////////////////////////////refer///////////////////////////////////////////////////////////////

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//////////////////////////////////////////refer/////////////////////////////////////////////////////////////////
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }


                Intent intent = new Intent(Dashboard.this, Life.class);
                intent.putExtra("from", "dashboard");
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_left, R.anim.slide_right);//////////////////////////////////////////////////////////////////


                view.startAnimation(myAnim);//////////////////////////////////////////////////////////
            }
        });


        final Button btn = findViewById(R.id.button);//// Play Button


        ImageView img = findViewById(R.id.ivprofile);


        ivprofile = findViewById(R.id.ivprofile);
        Button btnwallet = (Button) findViewById(R.id.wallet);
        btnwallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }

                v.startAnimation(myAnim);


                Intent intent2 = new Intent(Dashboard.this, Profile.class);
                intent2.putExtra("Totalcontest", Totalcontest);
                startActivity(intent2);
                finish();
                overridePendingTransition(R.anim.slide_left, R.anim.slide_right);


            }
        });


        linearLayout = (LinearLayout) findViewById(R.id.linearlayout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v.startAnimation(myAnim);
//                vibrator.vibrate(70);
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }

                addpopup.addpopup();

            }
        });


        Totalcontest = getIntent().getStringExtra("Totalcontest");
        if (Totalcontest != null) {
        } else {
            Totalcontest = "0";
        }
        txtwallet = findViewById(R.id.txtwallet);  //////////////////////// Wallet text

        txtwallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }

                addpopup.addpopup();
            }
        });
        ImageView imageView = findViewById(R.id.ivprofileview);
        imageView.setVisibility(View.VISIBLE);

        String Loginid = prefs.getString("drawable", null);
        String Profilepic = prefs.getString("Profilepic", null);
        String Customimage = prefs.getString("Imageurl", null);
        Totalwallet = prefs.getString("Totalwallet", null);
        Totallife = prefs.getString("Totallife", null);



        String decrypted = decrypt(good.key, Totalwallet);
        Loginid = decrypt(good.key, Loginid);
        Customimage = decrypt(good.key, Customimage);
        Profilepic = decrypt(good.key, Profilepic);




        if (decrypted != null && decrypted != "") {
            txtwallet.setText(decrypted);
        } else {
            txtwallet.setText("0");
        }


        LinearLayout l1 = findViewById(R.id.l1);
        LinearLayout l2 = findViewById(R.id.l2);
        if (Loginid != null) {
            Log.i("xsumitxxx", Loginid);
            l1.setVisibility(View.VISIBLE);
            l2.setVisibility(View.GONE);
            img.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.GONE);
            int imageId = getResourseId(this, Loginid, "drawable", getPackageName());
            img.setImageResource(imageId);

        } else if (Customimage != null && Customimage != "") {
            Log.i("xsumitxx", Customimage);
//            Log.i("xsumit", Profilepic);
            l1.setVisibility(View.GONE);
            ivprofile.setVisibility(View.GONE);
            l2.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
            Glide.with(getApplicationContext()).load("http://callofchallengers.com/coc/images/users/" + Customimage)
                    .crossFade()
                    .placeholder(R.drawable.logo)
                    .error(R.drawable.logo)
                    .bitmapTransform(new GlideCircleTransformation(getApplicationContext()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(false)
                    //.diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);

        } else if (Profilepic != null && Profilepic != "") {
            Log.i("xsumit", Profilepic);
            l1.setVisibility(View.GONE);
            img.setVisibility(View.GONE);
            l2.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
            Glide.with(getApplicationContext()).load(Profilepic)
                    .crossFade()
                    .placeholder(R.drawable.logo)
                    .error(R.drawable.logo)
                    .bitmapTransform(new GlideCircleTransformation(getApplicationContext()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(false)
                    .into(imageView);
        }


        Name = prefs.getString("Name", null);


        Name = decrypt(good.key, Name);




        TextView textView = findViewById(R.id.username);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Dashboard.this, Profile.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slideout_from_left);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }

                Intent intent2 = new Intent(Dashboard.this, Profile.class);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slideout_from_left);
                intent2.putExtra("Totalcontest", Totalcontest);
                startActivity(intent2);
                finish();


                overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
            }
        });

        dashboard = this;
        textView.setText(Name);
        //// Play Button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }

                SharedPreferences perper = getSharedPreferences("walletdata", Context.MODE_PRIVATE);
                String StatusTwos = perper.getString("Status", null);
                StatusTwos = decrypt(good.key, StatusTwos);
                Log.d("walletwalletssss","Dash= "+ StatusTwos);

                if (StatusTwos.equalsIgnoreCase("0")) {
                    banned.setContentView(R.layout.bannduserlayout);
                    banned.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
                    banned.show();
                } else {

                    Name = encrypt(good.key, good.initVector, Name);

                    SharedPreferences lucky = getSharedPreferences("coding", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = lucky.edit();
                    edit.putString("name", Name);
                    edit.putString("Totalwallet", Totalwallet);
                    edit.apply();

                    btn.startAnimation(AnimationUtils.loadAnimation(Dashboard.this, R.anim.imageanim));
                    Intent intent = new Intent(Dashboard.this, GametypesActivity.class);
                    intent.putExtra("goto", "main");
                    intent.putExtra("Totalcontest", Totalcontest);
                    intent.putExtra("name", Name);

                    intent.putExtra("Totalwallet", Totalwallet);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.slide_from_right, R.anim.slideout_from_left);
                }


            }
        });


//        ivprofile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showPictureialog();
//            }
//        });


        progressbar.dismiss();


    }



    // 1. सबसे पहले हम एक function create करते है जो हमारे DrawerLayout और Toolbar को जोड़ेगा


    private void showPictureialog() {
        final Integer[] items = {R.drawable.sticky1,
                R.drawable.sticky2, R.drawable.sticky3, R.drawable.sticky4,
                R.drawable.sticky5, R.drawable.sticky6, R.drawable.sticky7,
                R.drawable.sticky8, R.drawable.sticky9};


        List<Integer> list = new ArrayList<Integer>(Arrays.asList(items));
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.selectprofile, null);
        dialog.setView(dialogView);
        dialog.show();
        GridView grid = dialogView.findViewById(R.id.grid);
        GridViewAdapter gridAdapter = new GridViewAdapter(this, R.layout.grid_row, list);
        grid.setAdapter(gridAdapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //ImageView img = view.findViewById(R.id.ivprofile);
                String name = getResources().getResourceEntryName(items[i]);
                SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("drawable", name);
                editor.apply();
                dialog.dismiss();
                int imageId = getResourseId(Dashboard.this, name, "drawable", getPackageName());
                ivprofile.setImageResource(imageId);
            }
        });
    }

    @Override
    public void onBackPressed() {
        closeapp.setContentView(R.layout.appclose);
        Button okbtn = (Button) closeapp.findViewById(R.id.okyes);
        Button nobtn = (Button) closeapp.findViewById(R.id.closePopup);
        nobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }

                closeapp.dismiss();
            }
        });
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }

                Intent a = new Intent(Intent.ACTION_MAIN);
                a.addCategory(Intent.CATEGORY_HOME);
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(a);
                System.exit(0);
                finish();

                closeapp.dismiss();
            }
        });

        closeapp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
        closeapp.show();
    }

    private void FirstApiAddWallet() {
        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String Loginid = prefs.getString("Loginid", null);


        String Password = prefs.getString("Password", null);
        String Usernames = prefs.getString("Name", null);
        Loginid = decrypt(good.key, Loginid);
        final FirstAddWalletRequestJson request = new FirstAddWalletRequestJson();
        request.setLoginid(Loginid);


        UserService service = ServiceGenerator.createService(UserService.class, decrypt(good.key, Usernames), decrypt(good.key, Password));
        service.addwallet(request).enqueue(new Callback<FirstAddWalletResponseJson>() {
            @Override
            public void onResponse(Call<FirstAddWalletResponseJson> call, Response<FirstAddWalletResponseJson> response) {
                if (response.isSuccessful()) {

                    Message = response.body().message;

                    for (int z=0; z<response.body().data.size(); z++){
                        OneRs = response.body().data.get(z).oners;
                        EarnMore = response.body().data.get(z).sharewin;
                        OneTotal = response.body().data.get(z).oneptotal;
                        TwoRs = response.body().data.get(z).twors;
                        TwoTotal = response.body().data.get(z).twoptotal;
                        ThreeRs = response.body().data.get(z).threers;
                        ThreeTotal = response.body().data.get(z).threeptotal;
                        FourRs = response.body().data.get(z).fourrs;
                        FourTotal = response.body().data.get(z).fourptotal;
                        Status = response.body().data.get(z).status;


                        EarnMore = encrypt(good.key, good.initVector, EarnMore);
                        OneRs = encrypt(good.key, good.initVector, OneRs);
                        OneTotal = encrypt(good.key, good.initVector, OneTotal);
                        TwoRs = encrypt(good.key, good.initVector, TwoRs);
                        TwoTotal = encrypt(good.key, good.initVector, TwoTotal);
                        ThreeRs = encrypt(good.key, good.initVector, ThreeRs);
                        ThreeTotal = encrypt(good.key, good.initVector, ThreeTotal);
                        FourRs = encrypt(good.key, good.initVector, FourRs);
                        FourTotal = encrypt(good.key, good.initVector, FourTotal);
                        Status = encrypt(good.key, good.initVector, Status);
                    }

                    Message = encrypt(good.key, good.initVector, Message);
                    StatusTwo = Status;
                    OnesRs = OneRs;
                    OnesTotal = OneTotal;
                    TwosRs = TwoRs;
                    TwosTotal = TwoTotal;
                    ThreesRs = ThreeRs;
                    ThreesTotal = ThreeTotal;
                    FoursRs = FourRs;
                    FoursTotal = FourTotal;
                    MinAmount = Message;


                    SharedPreferences perper = getSharedPreferences("walletdata", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = perper.edit();
                    edit.putString("OnesRs", OnesRs);
                    edit.putString("OnesTotal", OnesTotal);
                    edit.putString("TwosRs", TwosRs);
                    edit.putString("TwosTotal", TwosTotal);
                    edit.putString("ThreesRs", ThreesRs);
                    edit.putString("ThreesTotal", ThreesTotal);
                    edit.putString("FoursRs", FoursRs);
                    edit.putString("FoursTotal", FoursTotal);
                    edit.putString("Status", Status);
                    edit.putString("EarnMore",EarnMore);
                    edit.putString("MinAmount",MinAmount);
                    edit.apply();


                    progressbar.dismiss();

                } else {
                    switch (response.code()) {
                        case 401:
//                            Toast.makeText(Dashboard.this, "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
//                            Toast.makeText(Dashboard.this, "ForbiddenException", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
//                            Toast.makeText(Dashboard.this, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
//                            Toast.makeText(Dashboard.this, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
//                            Toast.makeText(Dashboard.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;

                    }
                    Toast.makeText(Dashboard.this, "Something error please try again", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<FirstAddWalletResponseJson> call, Throwable t) {
                t.printStackTrace();


                Log.e("System error:", t.getLocalizedMessage());

            }
        });



    }

    private void TokenAPI() {
        String Token = decrypt(good.key, newToken);
        String Login = decrypt(good.key, Loginids);
        String Imei = decrypt(good.key, IMEINumber);
        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);

        String Password = prefs.getString("Password", null);
        String Usernames = prefs.getString("Name", null);

        final TokenRequestJson request = new TokenRequestJson();
        request.setToken(Token);
        request.setUserid(Login);
        request.setImei(Imei);
        UserService service = ServiceGenerator.createService(UserService.class, decrypt(good.key, Usernames), decrypt(good.key, Password));
        service.token(request).enqueue(new Callback<SignUpNameCheckResponseJson>() {
            @Override
            public void onResponse(Call<SignUpNameCheckResponseJson> call, Response<SignUpNameCheckResponseJson> response) {
                if (response.isSuccessful()) {

                    Message = response.body().message;




                } else {
                    switch (response.code()) {
                        case 401:
//                            Toast.makeText(Dashboard.this, "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
//                            Toast.makeText(Dashboard.this, "ForbiddenException", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
//                            Toast.makeText(Dashboard.this, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
//                            Toast.makeText(Dashboard.this, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
//                            Toast.makeText(Dashboard.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;

                    }
                    Toast.makeText(Dashboard.this, "Something error please try again", Toast.LENGTH_SHORT).show();

                }
                progressbar.dismiss();

            }

            @Override
            public void onFailure(Call<SignUpNameCheckResponseJson> call, Throwable t) {
                t.printStackTrace();


                Log.e("System error:", t.getLocalizedMessage());

            }
        });


    }



}
