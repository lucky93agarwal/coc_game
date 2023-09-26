package com.challengers.of.call;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.challengers.of.call.DeviceUtils.rsa;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

import static com.paytm.pgsdk.easypay.manager.PaytmAssist.getContext;

public class Testing extends AppCompatActivity {


    public EditText etoneet, ettwoet;
    public Button btnen, btnde;

    private String publicKey = "";
    private String privateKey = "";

    private byte[] encodeData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);


        TextView textView = (TextView)findViewById(R.id.etone);

        textView.setText(Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));




    }

}
