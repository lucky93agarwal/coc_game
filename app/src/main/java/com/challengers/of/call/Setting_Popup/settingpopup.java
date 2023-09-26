package com.challengers.of.call.Setting_Popup;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SwitchCompat;

import com.challengers.of.call.AppController;
import com.challengers.of.call.R;
import com.challengers.of.call.testing.SettingsPreferences;
import com.challengers.of.call.testing.StaticUtils;

import static com.challengers.of.call.AppController.StopSound;
import static com.challengers.of.call.AppController.getAppContext;

public class settingpopup {
    public Dialog settingDialog;
    private SwitchCompat mSoundCheckBox, mVibrationCheckBox;
    private final Context _context;

    private boolean isSoundOn;
    private boolean isVibrationOn;
    private boolean isMusicOn;
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    private Context mContext;
    public static AlertDialog alertDialog;
    public ImageView closepopupbtn;

    public settingpopup(Context context) {
        this._context = context;
        settingDialog = new Dialog( context, R.style.PauseDialog);
        mContext = context;
        AppController.currentActivity = (Activity) context;


    }



    public void setting(){
        settingDialog.setContentView(R.layout.settingpage);
        settingDialog.setCancelable(false);
        initViews();

        closepopupbtn = (ImageView)settingDialog.findViewById(R.id.closePopup);// popup Close button

        closepopupbtn.setOnClickListener(new View.OnClickListener() {// popup Close button working
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(_context)) {
                    StaticUtils.vibrate(_context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(_context)) {
                    StaticUtils.backSoundonclick(_context);
                }

                settingDialog.dismiss();
            }
        });

        settingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
        settingDialog.show();

    }
    private void initViews() {
        mSoundCheckBox = (SwitchCompat) settingDialog.findViewById(R.id.sound_checkbox); ///// Fsx
        mVibrationCheckBox = (SwitchCompat) settingDialog.findViewById(R.id.vibration_checkbox);



        populateSoundContents();
        populateVibrationContents();



        mVibrationCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchVibrationCheckbox();
            }
        });

        mSoundCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchSoundCheckbox();
            }
        });
    }
    private void switchSoundCheckbox() {
        isSoundOn = !isSoundOn;
        SettingsPreferences.setSoundEnableDisable(mContext, isSoundOn);

        populateSoundContents();
    }

    private void switchVibrationCheckbox() {
        isVibrationOn = !isVibrationOn;
        SettingsPreferences.setVibration(mContext, isVibrationOn);
        populateVibrationContents();
    }



    protected void populateSoundContents() {
        if (SettingsPreferences.getSoundEnableDisable(mContext)) {
            mSoundCheckBox.setChecked(true);
        } else {
            mSoundCheckBox.setChecked(false);
        }
        isSoundOn = SettingsPreferences.getSoundEnableDisable(mContext);
    }

    protected void populateVibrationContents() {
        if (SettingsPreferences.getVibration(mContext)) {
            mVibrationCheckBox.setChecked(true);
        } else {
            mVibrationCheckBox.setChecked(false);
        }
        isVibrationOn = SettingsPreferences.getVibration(mContext);
    }





    private void setTelephoneListener() {
        PhoneStateListener phoneStateListener = new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                if (state == TelephonyManager.CALL_STATE_RINGING) {
                    StopSound();
                } else if (state == TelephonyManager.CALL_STATE_IDLE) {
                } else if (state == TelephonyManager.CALL_STATE_OFFHOOK) {
                    StopSound();
                }
                super.onCallStateChanged(state, incomingNumber);
            }
        };

        TelephonyManager telephoneManager = (TelephonyManager) getAppContext().getSystemService(Context.TELEPHONY_SERVICE);
        if (telephoneManager != null) {
            telephoneManager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
        }
    }






}
