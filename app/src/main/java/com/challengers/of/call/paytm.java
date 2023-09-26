package com.challengers.of.call;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;


import java.util.HashMap;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class paytm extends AppCompatActivity {
    public Button btn, firstbtn, secondbtn, threebtn, fourbtn, fivebtn, sixbtn;
    public EditText etamount;
    JSONObject jasonObject;
    JSONArray jsonArray = null;
    public String Response, custID, Wallet, TXNID, BANKTXNID, ORDERID, TXNAMOUNT, STATUS, TXNTYPE, GATEWAYNAME, RESPMSG, RESPCODE, CHECKSUMHASH, CURRENCY, ettamount;
    public String BANKNAME, MID, PAYMENTMODE, REFUNDAMT, TXNDATE;

    public String OneRs, OneCB, TwoRs, TwoCB, ThreeRs, ThreeCB, FourRs, FourCB, FiveRs, FiveCB, SixRs, SixCB;
    String From;
    public Animation myAnim;
    public TextView tvoners, tvonecb, tvtwors, tvtwocb, tvthreers, tvthreecb, tvfourrs, tvfourcb, tvfivers, tvfivecb, tvsixrs, tvsixcb;

    AsyncCallWSdistribute tasks;
    public LinearLayout linone, lintwo, linthree, linfour, linfive, linsix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paytm);

        tasks = new AsyncCallWSdistribute();
        tasks.execute();


        From = getIntent().getStringExtra("from");
        myAnim = AnimationUtils.loadAnimation(this, R.anim.buttonanimation);

        init();////////////////////////////////////////////////////////////////////////////////////////////////////////////

        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        custID = prefs.getString("Loginid", null);


        if (ContextCompat.checkSelfPermission(paytm.this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(paytm.this, new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS}, 101);
        }
    }

    private void init() {
        btn = (Button) findViewById(R.id.button4);/////////////////////////////////////////////////////////////////////////////////////////
        etamount = (EditText) findViewById(R.id.edpay);//////////////////////////////////////////////////////////////////////////////////////////

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Uri uri = Uri.parse("http://www.callofchallengers.com/"); // missing 'http://' will cause crashed
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(intent);
                etamount = (EditText) findViewById(R.id.edpay);
                ettamount = etamount.getText().toString();
                v.startAnimation(myAnim);
                processPaytm();//////////////////////////////////////////////////////////////////////////////////////////////////////////////
            }
        });
        linone = (LinearLayout) findViewById(R.id.onelin);
        linone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvoners = (TextView) findViewById(R.id.oners);
                ettamount = tvoners.getText().toString();


                tvonecb = (TextView) findViewById(R.id.onecb);
//                etamount = (EditText) findViewById(R.id.edpay);
//                tvoners = (TextView)findViewById(R.id.oners);
//                String data = tvoners.getText().toString();
//                etamount.setText(data);
//                ettamount = "5";
                processPaytm();//////////////////////////////////////////////////////////////////////////////////////////////////////////////

            }
        });
        lintwo = (LinearLayout) findViewById(R.id.twolin);
        lintwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvtwors = (TextView) findViewById(R.id.twors);
                ettamount = tvtwors.getText().toString();

                tvtwocb = (TextView) findViewById(R.id.twocb);


//                etamount = (EditText) findViewById(R.id.edpay);
//                tvtwors = (TextView)findViewById(R.id.twors);
//                String data = tvtwors.getText().toString();
//                etamount.setText(data);

                processPaytm();

            }
        });
        linthree = (LinearLayout) findViewById(R.id.threelin);
        linthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvthreers = (TextView) findViewById(R.id.threers);
                ettamount = tvthreers.getText().toString();

                tvthreecb = (TextView) findViewById(R.id.threecb);


//                etamount = (EditText) findViewById(R.id.edpay);
//                tvthreers = (TextView)findViewById(R.id.threers);
//                String data = tvthreers.getText().toString();
//                etamount.setText(data);

                processPaytm();

            }
        });

        linfour = (LinearLayout) findViewById(R.id.fourlin);
        linfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                etamount = (EditText) findViewById(R.id.edpay);
                tvfourrs = (TextView) findViewById(R.id.fourrs);
                ettamount = tvfourrs.getText().toString();
//                String data = tvfourrs.getText().toString();
//                etamount.setText(data);

                processPaytm();


            }
        });

        linfive = (LinearLayout) findViewById(R.id.fivelin);
        linfive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                etamount = (EditText) findViewById(R.id.edpay);
                tvfivers = (TextView) findViewById(R.id.fivers);
                String data = tvfivers.getText().toString();
//                etamount.setText(data);
                ettamount = data;
                processPaytm();

            }
        });
        linsix = (LinearLayout) findViewById(R.id.sixlin);
        linsix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                etamount = (EditText) findViewById(R.id.edpay);
                tvsixrs = (TextView) findViewById(R.id.sixrs);
                String data = tvsixrs.getText().toString();
//                etamount.setText(data);

                ettamount = data;
                processPaytm();

            }
        });
    }

    private void processPaytm() {
//        //String custID = generateString();
//        String orderID = generateString();
//        String callBackurl = "https://securegw.paytm.in/theia/paytmCallback?ORDER_ID=" + orderID;
//
//
//        final pytmclass pytmclas = new pytmclass(
//                "JLAnMp76856228269273",
//                orderID,
//                custID, "WAP",
//                ettamount,
//                "DEFAULT",
//                callBackurl,
//                "Retail"
//        );
//
//
//        WebServiceCaller.getClient().getChecksum(pytmclas.getmId(), pytmclas.getOrderId(), pytmclas.getCustId()
//                , pytmclas.getChannelId(), pytmclas.getTxnAmount(), pytmclas.getWebsite(), pytmclas.getCallBackUrl(), pytmclas.getIndustryTypeId()
//        ).enqueue(new Callback<Checksumnew>() {
//            @Override
//            public void onResponse(Call<Checksumnew> call, Response<Checksumnew> response) {
//                if (response.isSuccessful()) {
//                    processToPay(response.body().getChecksumHash(), pytmclas);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Checksumnew> call, Throwable t) {
//
//            }
//        });


    }


    private void processToPay(String checksumHash, pytmclass pytmclass) {
        PaytmPGService Service = PaytmPGService.getProductionService();

        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("MID", pytmclass.getmId());
// Key in your staging and production MID available in your dashboard
        paramMap.put("ORDER_ID", pytmclass.getOrderId());
        paramMap.put("CUST_ID", pytmclass.getCustId());
        paramMap.put("CHANNEL_ID", pytmclass.getChannelId());
        paramMap.put("TXN_AMOUNT", pytmclass.getTxnAmount());
        paramMap.put("WEBSITE", pytmclass.getWebsite());
// This is the staging value. Production value is available in your dashboard
        paramMap.put("INDUSTRY_TYPE_ID", pytmclass.getIndustryTypeId());
// This is the staging value. Production value is available in your dashboard
        paramMap.put("CALLBACK_URL", pytmclass.getCallBackUrl());
        paramMap.put("CHECKSUMHASH", checksumHash);
        PaytmOrder Order = new PaytmOrder(paramMap);
        Service.initialize(Order, null);

        Service.startPaymentTransaction(this, true, true, new PaytmPaymentTransactionCallback() {
            /*Call Backs*/
            public void someUIErrorOccurred(String inErrorMessage) {
            }

            public void onTransactionResponse(Bundle inResponse) {
                // Toast.makeText(paytm.this, inResponse.toString(), Toast.LENGTH_SHORT).show();


                STATUS = inResponse.getString("STATUS");
                TXNTYPE = inResponse.getString("CHECKSUMHASH");
                BANKNAME = inResponse.getString("BANKNAME");
                ORDERID = inResponse.getString("ORDERID");
                TXNAMOUNT = inResponse.getString("TXNAMOUNT");
                TXNDATE = inResponse.getString("TXNDATE");
                MID = inResponse.getString("MID");
                TXNID = inResponse.getString("TXNID");
                RESPCODE = inResponse.getString("RESPCODE");
                PAYMENTMODE = inResponse.getString("PAYMENTMODE");
                BANKTXNID = inResponse.getString("BANKTXNID");
                REFUNDAMT = inResponse.getString("CURRENCY");
                GATEWAYNAME = inResponse.getString("GATEWAYNAME");
                RESPMSG = inResponse.getString("RESPMSG");

                if (STATUS.equalsIgnoreCase("TXN_SUCCESS")) {
                    AsyncCallWS task = new AsyncCallWS();
                    task.execute();
                } else {
                    Toast.makeText(paytm.this, RESPMSG, Toast.LENGTH_LONG).show();
                }




//                if(inResponse != null)
//                {
//                    try
//                    {
//                        //JSONObject resulttwo = new JSONObject("Bundle");
//
//                        JSONArray array = new JSONArray(inResponse.toString());
//
//                        for(int i = 0; i < array.length(); i++)
//                        {
//                            JSONObject jsonrowdata = array.getJSONObject(i);
//
//                            STATUS = jsonrowdata.getString("STATUS");
//                            CHECKSUMHASH = jsonrowdata.getString("CHECKSUMHASH");
//                            BANKNAME = jsonrowdata.getString("BANKNAME");
//                            ORDERID = jsonrowdata.getString("ORDERID");
//                            TXNAMOUNT = jsonrowdata.getString("TXNAMOUNT");
//                            TXNDATE = jsonrowdata.getString("TXNDATE");
//                            MID = jsonrowdata.getString("MID");
//                            TXNID = jsonrowdata.getString("TXNID");
//                            RESPCODE = jsonrowdata.getString("RESPCODE");
//                            PAYMENTMODE = jsonrowdata.getString("PAYMENTMODE");
//                            BANKTXNID = jsonrowdata.getString("BANKTXNID");
//                            CURRENCY = jsonrowdata.getString("CURRENCY");
//                            GATEWAYNAME = jsonrowdata.getString("GATEWAYNAME");
//                            RESPMSG = jsonrowdata.getString("RESPMSG");
//
//
//
//
//                        }
//                    }
//                    catch (Exception e)
//                    {
//                        e.printStackTrace();
//                    }
//
//
//                }


            }

            public void networkNotAvailable() {
                /*Display the message as below */
                Toast.makeText(getApplicationContext(), "Network connection error: Check your internet connectivity", Toast.LENGTH_LONG).show();
            }

            public void clientAuthenticationFailed(String inErrorMessage) {
                /*Display the message as below */
                Toast.makeText(getApplicationContext(), "Authentication failed: Server error" + inErrorMessage.toString(), Toast.LENGTH_LONG).show();
            }

            public void onErrorLoadingWebPage(int iniErrorCode, String inErrorMessage, String inFailingUrl) {
            }

            public void onBackPressedCancelTransaction() {
                /*Display the message as below */
                Toast.makeText(getApplicationContext(), "Transaction cancelled", Toast.LENGTH_LONG).show();
            }

            public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {


            }
        });

    }

    @Override
    public void onBackPressed() {
        if (From.equalsIgnoreCase("life")) {
            Intent intent = new Intent(paytm.this, Life.class);
            intent.putExtra("from", "add");
            startActivity(intent);
            finish();
        } else if (From.equalsIgnoreCase("main")) {
            Intent intent = new Intent(paytm.this, Main2Activity.class);
            intent.putExtra("goto", "main");
            intent.putExtra("Totalcontest", Main2Activity.Totalcontest);
            startActivity(intent);
            Main2Activity.main.finish();
            finish();
        } else if (From.equalsIgnoreCase("wallet")) {
            Intent intent = new Intent(paytm.this, Wallet.class);
            intent.putExtra("from", "dashboard");
            startActivity(intent);
            finish();
        }
    }

    private String generateString() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    //// playtiam time
    public class AsyncCallWS extends AsyncTask<String, Void, Void> {
        String displayText, Userid, Password;
        String Username, Name, Sponsorid, Email, Gender, Role, Dateofbirth, Playstatus, Imageurl, Totalwallet, Totallife;
        private KProgressHUD hud;

        @Override
        protected Void doInBackground(String... params) {
            displayText = WebService.Paytm(TXNID, BANKTXNID, ORDERID, TXNAMOUNT, STATUS, TXNTYPE, GATEWAYNAME, RESPCODE, RESPMSG, BANKNAME, MID, PAYMENTMODE, REFUNDAMT, TXNDATE, custID, "Paytm");
            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
                try {
                    jasonObject = new JSONObject(displayText);
                    jsonArray = jasonObject.getJSONArray("Response");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
                        Wallet = jsonrowdata.getString("Wallet");

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
                Toast.makeText(paytm.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
            } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
                Toast.makeText(paytm.this, "Please try after some times...", Toast.LENGTH_LONG).show();
            } else if (jsonArray.length() > 0) {
                SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("Totalwallet", Wallet);
                editor.apply();
                SharedPreferences pref2 = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor1 = pref2.edit();

                editor1.putString("Totalwallet", Wallet);
                editor1.apply();
                Toast.makeText(paytm.this, "Money added successfully to your wallet", Toast.LENGTH_SHORT).show();
                //Toast.makeText(paytm.this, "Thank you", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(paytm.this, Wallet.class);
                intent.putExtra("from", "dashboard");
                startActivity(intent);


            } else {
                Toast.makeText(paytm.this, "Netwark Error", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onPreExecute() {


            hud = KProgressHUD.create(paytm.this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setWindowColor(Color.parseColor("#c20e14"))
                    .setLabel("")
                    .setAnimationSpeed(1);
            hud.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }


    ///// starting time get data
    public class AsyncCallWSdistribute extends AsyncTask<String, Void, Void> {
        String displayText;
        String Count;
//        private KProgressHUD hud;

        @Override
        protected Void doInBackground(String... params) {
            SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
            String Loginid = prefs.getString("Loginid", null);
            if (Loginid != null) {
                displayText = WebService.Paytmstart(Loginid, "paytmstart");
            }
            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
                try {
                    int count = 1;
                    jasonObject = new JSONObject(displayText);
                    jsonArray = jasonObject.getJSONArray("Response");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
                        OneRs = jsonrowdata.getString("oners");
                        OneCB = jsonrowdata.getString("onecb");

                        TwoRs = jsonrowdata.getString("twors");
                        TwoCB = jsonrowdata.getString("twocb");

                        ThreeRs = jsonrowdata.getString("threers");
                        ThreeCB = jsonrowdata.getString("threecb");

                        FourRs = jsonrowdata.getString("fourrs");
                        FourCB = jsonrowdata.getString("fourcb");

                        FiveRs = jsonrowdata.getString("fivers");
                        FiveCB = jsonrowdata.getString("fivecb");

                        SixRs = jsonrowdata.getString("sixrs");
                        SixCB = jsonrowdata.getString("sixcb");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected void onPostExecute(Void result) {
//            hud.dismiss();
            if (displayText != null) {
                if (displayText.equalsIgnoreCase("connection fault")) {
                    Toast.makeText(paytm.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
                } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
                    Toast.makeText(paytm.this, "Please try after some times...", Toast.LENGTH_LONG).show();
                } else if (jsonArray.length() > 0) {

                    tvoners = (TextView) findViewById(R.id.oners);
                    tvoners.setText(OneRs);

                    tvonecb = (TextView) findViewById(R.id.onecb);
                    tvonecb.setText(OneCB);

                    tvtwors = (TextView) findViewById(R.id.twors);
                    tvtwors.setText(TwoRs);

                    tvtwocb = (TextView) findViewById(R.id.twocb);
                    tvtwocb.setText(TwoCB);


                    tvthreers = (TextView) findViewById(R.id.threers);
                    tvthreers.setText(ThreeRs);

                    tvthreecb = (TextView) findViewById(R.id.threecb);
                    tvthreecb.setText(ThreeCB);

                    tvfourrs = (TextView) findViewById(R.id.fourrs);
                    tvfourrs.setText(FourRs);

                    tvfourcb = (TextView) findViewById(R.id.fourcb);
                    tvfourcb.setText(FourCB);


                    tvfivers = (TextView) findViewById(R.id.fivers);
                    tvfivers.setText(FiveRs);

                    tvfivecb = (TextView) findViewById(R.id.fivecb);
                    tvfivecb.setText(FiveCB);

                    tvsixrs = (TextView) findViewById(R.id.sixrs);
                    tvsixrs.setText(SixRs);

                    tvsixcb = (TextView) findViewById(R.id.sixcb);
                    tvsixcb.setText(SixCB);

                } else if (jsonArray.length() == 0) {
                    Toast.makeText(paytm.this, "No detail found", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(paytm.this, "Please try again", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onPreExecute() {
//            hud = KProgressHUD.create(Leaderboard.this)
//                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//                    .setWindowColor(Color.parseColor("#000000"))
//                    .setLabel("Please Wait...")
//                    .setAnimationSpeed(1);
//            hud.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
}
