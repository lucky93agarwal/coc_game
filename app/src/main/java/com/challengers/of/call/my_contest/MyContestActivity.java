package com.challengers.of.call.my_contest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.challengers.of.call.Add_Money_Popup.addmoneypopup;
import com.challengers.of.call.Banned_APP.bannedapp;
import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.GameTypes.GametypesActivity;
import com.challengers.of.call.GridViewAdapter;
import com.challengers.of.call.Profile;
import com.challengers.of.call.R;
import com.challengers.of.call.fragment.Fragmentmycontest;
import com.challengers.of.call.helper.GlideCircleTransformation;
import com.challengers.of.call.testing.SettingsPreferences;
import com.challengers.of.call.testing.StaticUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.challengers.of.call.Dashboard.getResourseId;
import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.decrypt;

public class MyContestActivity extends AppCompatActivity {
    private FrameLayout frameLayout;
    private static bannedapp bannedapps;
    public LinearLayout linearLayout;
    public static Context context;
    private static addmoneypopup addpopup;
    public Animation myAnim;

    ImageView ivprofile;
    public String Totalwallet,Names;
    public TextView tvwallet;
    public String Totalcontestnew;
    private ljsflsj good;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_contest);

        context = getApplicationContext();
        addpopup = new addmoneypopup(MyContestActivity.this);
        myAnim = AnimationUtils.loadAnimation(this, R.anim.buttonanimation);

        bannedapps = new bannedapp(MyContestActivity.this);
        bannedapps.bannedbig();

        SharedPreferences prefs = getSharedPreferences("UserData",Context.MODE_PRIVATE);
        String Loginid = prefs.getString("drawable", null);
        String Profilepic = prefs.getString("Profilepic", null);
        String Customimage = prefs.getString("Imageurl", null);

        ImageView img = findViewById(R.id.ivprofile);
        ImageView imageView = findViewById(R.id.ivprofileview);
        imageView.setVisibility(View.VISIBLE);
        ivprofile = findViewById(R.id.ivprofile);

        LinearLayout l1 = findViewById(R.id.l1);
        LinearLayout l2 = findViewById(R.id.l2);
        Loginid = decrypt(good.key, Loginid);
        Customimage = decrypt(good.key, Customimage);
        Profilepic = decrypt(good.key, Profilepic);
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
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MyContestActivity.this, Profile.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slideout_from_left);
            }
        });
        ivprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPictureialog();
            }
        });
        SharedPreferences pref2 = getSharedPreferences("UserData", Context.MODE_PRIVATE);

        Names = pref2.getString("Name", null);
        Totalwallet = pref2.getString("Totalwallet",null);
        Totalcontestnew = getIntent().getStringExtra("Totalcontest");
        Names = decrypt(good.key, Names);
        Totalwallet = decrypt(good.key, Totalwallet);
        TextView tvname = (TextView)findViewById(R.id.username);
        tvname.setText(Names);
        tvname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }

                Intent intent2 = new Intent(MyContestActivity.this, Profile.class);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slideout_from_left);
                intent2.putExtra("Totalcontest", Totalcontestnew);
                startActivity(intent2);
                finish();


                overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
            }
        });
        tvwallet = (TextView)findViewById(R.id.txtwallet);
        tvwallet.setText(Totalwallet);
        tvwallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }
//                Intent intent = new Intent(Dashboard.this, Wallet.class);
//                intent.putExtra("from", "dashboard");
//                startActivity(intent);
//                finish();
//                overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
                addpopup.addpopup();
            }
        });


        linearLayout = (LinearLayout)findViewById(R.id.linearlayout);
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

        frameLayout = (FrameLayout)findViewById(R.id.register_framelayout);
        setFragment(new Fragmentmycontest());
    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }

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
                int imageId = getResourseId(MyContestActivity.this, name, "drawable", getPackageName());
                ivprofile.setImageResource(imageId);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (SettingsPreferences.getVibration(context)) {
            StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
        }

        if (SettingsPreferences.getSoundEnableDisable(context)) {
            StaticUtils.backSoundonclick(context);
        }
        Intent intent = new Intent(MyContestActivity.this, GametypesActivity.class);

        overridePendingTransition(R.anim.slide_from_left, R.anim.slideout_from_right);
        startActivity(intent);
        finish();
    }
    public void addwallet (String wallet){
        Log.d("walletwalletssss", "wallet0001111111111_: " + wallet);
        wallet = decrypt(good.key, wallet);
        Log.d("walletwalletssss", "wallet0002222222222_: " + wallet);
        tvwallet = (TextView)findViewById(R.id.txtwallet);
        tvwallet.setText(wallet);
    }
}
