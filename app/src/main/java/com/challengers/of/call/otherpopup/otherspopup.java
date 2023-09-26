package com.challengers.of.call.otherpopup;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.challengers.of.call.Dashboard;
import com.challengers.of.call.FAQ;
import com.challengers.of.call.FeedbackQuestions;
import com.challengers.of.call.HowtoPlay;
import com.challengers.of.call.PrivacyPolicyActivity;
import com.challengers.of.call.R;
import com.challengers.of.call.TermsandConditionsActivity;
import com.challengers.of.call.testing.SettingsPreferences;
import com.challengers.of.call.testing.StaticUtils;

public class otherspopup {
    private final Context _context;
    public Dialog otherpopup;
    public Animation myAnim;
    public LinearLayout btnhtp, btnfaq, btnpp, btntandc, btnfeed;
    public ImageView closepopupbtn;


    public otherspopup(Context context) {
        this._context = context;

        otherpopup = new Dialog(context, R.style.PauseDialog); // for popup Dialog

        myAnim = AnimationUtils.loadAnimation(context, R.anim.buttonanimation);

    }

    public void otherpopupsss(){
        otherpopup.setContentView(R.layout.otherpopuplayout);
        otherpopup.setCancelable(false);

        btnhtp = (LinearLayout) otherpopup.findViewById(R.id.other_how_to_play);
        btnfaq = (LinearLayout) otherpopup.findViewById(R.id.other_faq);
        btnpp = (LinearLayout) otherpopup.findViewById(R.id.other_privacy_policy);
        btntandc = (LinearLayout) otherpopup.findViewById(R.id.other_teams_and_condication);
        btnfeed = (LinearLayout) otherpopup.findViewById(R.id.other_feedback);

        btnhtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(_context)) {
                    StaticUtils.vibrate(_context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(_context)) {
                    StaticUtils.backSoundonclick(_context);
                }


                v.startAnimation(myAnim);
                Intent intent = new Intent(_context, HowtoPlay.class);
                _context.startActivity(intent);

            }
        });

        btnfeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(_context)) {
                    StaticUtils.vibrate(_context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(_context)) {
                    StaticUtils.backSoundonclick(_context);
                }


                v.startAnimation(myAnim);
                Intent intent = new Intent(_context, FeedbackQuestions.class);
                _context.startActivity(intent);
            }
        });

        btnfaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(_context)) {
                    StaticUtils.vibrate(_context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(_context)) {
                    StaticUtils.backSoundonclick(_context);
                }


                v.startAnimation(myAnim);
                Intent intent = new Intent(_context, FAQ.class);
                _context.startActivity(intent);
            }
        });

        btnpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(_context)) {
                    StaticUtils.vibrate(_context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(_context)) {
                    StaticUtils.backSoundonclick(_context);
                }


                v.startAnimation(myAnim);
                Intent intent = new Intent(_context, PrivacyPolicyActivity.class);
                _context.startActivity(intent);
            }
        });

        btntandc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(_context)) {
                    StaticUtils.vibrate(_context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(_context)) {
                    StaticUtils.backSoundonclick(_context);
                }


                v.startAnimation(myAnim);
                Intent intent = new Intent(_context, TermsandConditionsActivity.class);
                _context.startActivity(intent);
            }
        });




        closepopupbtn = (ImageView) otherpopup.findViewById(R.id.closePopup);// popup Close butt
        closepopupbtn.setOnClickListener(new View.OnClickListener() {// popup Close button working
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(_context)) {
                    StaticUtils.vibrate(_context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(_context)) {
                    StaticUtils.backSoundonclick(_context);
                }

                otherpopup.dismiss();
            }
        });

        otherpopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
        otherpopup.show();
    }

}
