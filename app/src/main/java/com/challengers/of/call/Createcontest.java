package com.challengers.of.call;
import android.content.Intent;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import com.challengers.of.call.Banned_APP.bannedapp;
import com.challengers.of.call.data.Getprductdata;

public class Createcontest extends AppCompatActivity {
    public static double totalheight = 0;
    public static boolean click = false;
    private static bannedapp bannedapps;
    ListView listView;
    ListAdapter adapter;
    Button btnres, btncon;
    LinearLayout l1, l2,layoutmain,l3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createcontest);
        bannedapps = new bannedapp(Createcontest.this);
        bannedapps.bannedbig();
        listView = findViewById(R.id.listView);
        final Button btnchallenger = findViewById(R.id.btnchallenger);
        final Button btnchallenges = findViewById(R.id.btnchallenges);
        l1 = findViewById(R.id.l1);
        l2 = findViewById(R.id.l2);
        l3 = findViewById(R.id.l3);
        layoutmain=findViewById(R.id.layoutmain);
        btnres = findViewById(R.id.btnres);
        btncon = findViewById(R.id.btncon);
        final ScrollView scrollView = findViewById(R.id.sc);
        btnchallenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                BottomNavigationMenuView bottomNavigationMenuView =
//                        (BottomNavigationMenuView) navigationView.getChildAt(0);
//                View v = bottomNavigationMenuView.getChildAt(3);
//                BottomNavigationItemView itemView = (BottomNavigationItemView) v;
//
//                View badge = LayoutInflater.from(this)
//                        .inflate(R.layout.notification_badge, bottomNavigationMenuView, false);
//
//                itemView.addView(badge);



                totalheight = 0;
                int l1height = 0,l3height=0;
                String[] screenData = getScreenDimension();
                String Mobileheight=screenData[1];
                l1height = layoutmain.getHeight();
                l3height=l3.getHeight();
                totalheight = l1height+l3height;
                final int sdk = android.os.Build.VERSION.SDK_INT;
                if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    btnchallenger.setBackgroundDrawable(ContextCompat.getDrawable(Createcontest.this, R.drawable.customizebuttonactive));
                    btnchallenges.setBackgroundDrawable(ContextCompat.getDrawable(Createcontest.this, R.drawable.customizebutton));
                } else {
                    btnchallenger.setBackground(ContextCompat.getDrawable(Createcontest.this, R.drawable.customizebuttonactive));
                    btnchallenges.setBackground(ContextCompat.getDrawable(Createcontest.this, R.drawable.customizebutton));
                }
                scrollView.setVisibility(View.VISIBLE);
                l1.setVisibility(View.VISIBLE);
                l2.setVisibility(View.GONE);
            }
        });
        btnchallenges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (totalheight == 0) {
                    if(click==false) {
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) l2.getLayoutParams();
                        int l1height = 0, l3height = 0;
                        l3height = l3.getHeight();
                        totalheight = l1height + l3height;
                        totalheight = totalheight;
                        params.height = (int) totalheight;
                        l2.setLayoutParams(params);
                        click = true;
                    }
                }
                if (click == false) {
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) l2.getLayoutParams();
                    totalheight=totalheight;
                    params.height = (int) totalheight ;
                    l2.setLayoutParams(params);
                    click = true;
                }
                final int sdk = android.os.Build.VERSION.SDK_INT;
                if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    btnchallenges.setBackgroundDrawable(ContextCompat.getDrawable(Createcontest.this, R.drawable.customizebuttonactive));
                    btnchallenger.setBackgroundDrawable(ContextCompat.getDrawable(Createcontest.this, R.drawable.customizebutton));
                } else {
                    btnchallenges.setBackground(ContextCompat.getDrawable(Createcontest.this, R.drawable.customizebuttonactive));
                    btnchallenger.setBackground(ContextCompat.getDrawable(Createcontest.this, R.drawable.customizebutton));
                }
                scrollView.setVisibility(View.GONE);
                l1.setVisibility(View.GONE);
                l2.setVisibility(View.VISIBLE);
            }
        });
        Button btn = findViewById(R.id.btnsignup);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Createcontest.this, Activityquiz.class);
                startActivity(intent);
                finish();
            }
        });
        adapter = new ListAdapter(Createcontest.this, R.layout.list_challenger_row);

        for (int i = 0; i < 10; i++) {
            Getprductdata getprductdata = new Getprductdata("100000000", "100000", "100", "100","5","5");
            adapter.add(getprductdata);
        }
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.challengerheader, listView, false);
        listView.setAdapter(adapter);
        listView.addHeaderView(header, null, false);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    private String[] getScreenDimension(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        int dens = dm.densityDpi;
        double wi = (double)width / (double)dens;
        double hi = (double)height / (double)dens;
        double x = Math.pow(wi, 2);
        double y = Math.pow(hi, 2);
        double screenInches = Math.sqrt(x+y);

        String[] screenInformation = new String[3];
        screenInformation[0] = String.valueOf(width) + " px";
        screenInformation[1] = String.valueOf(height) + " px" ;
        screenInformation[2] = String.format("%.2f", screenInches) + " inches" ;
        return screenInformation;
    }
}
