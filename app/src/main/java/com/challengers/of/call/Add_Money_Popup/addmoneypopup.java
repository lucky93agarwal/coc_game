package com.challengers.of.call.Add_Money_Popup;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.agrawalsuneet.dotsloader.loaders.RotatingCircularDotsLoader;
import com.challengers.of.call.Checksumnew;
import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.FiFityAPI.FifityapiRequestJson;
import com.challengers.of.call.FiFityAPI.FiftyfityResponseJson;
import com.challengers.of.call.GameTypes.GametypesActivity;
import com.challengers.of.call.Profile;
import com.challengers.of.call.R;
import com.challengers.of.call.Retrofit.ServiceGenerator;
import com.challengers.of.call.Retrofit.UserService;
import com.challengers.of.call.Wallet;
import com.challengers.of.call.WebService;
import com.challengers.of.call.WebServiceCaller;
import com.challengers.of.call.Withdraw;
import com.challengers.of.call.pytmclass;
import com.challengers.of.call.testing.SettingsPreferences;
import com.challengers.of.call.testing.StaticUtils;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.VIBRATOR_SERVICE;
import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.decrypt;
import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.encrypt;
import static com.facebook.FacebookSdk.getApplicationContext;

public class addmoneypopup {
    public Dialog epicDialog, unepicDialog, progressbar, withdraw;
    public String OnesRs, OnesPer, OnesTotal, TwosRs, TwosPer, TwosTotal, ThreesRs, MinAmount, ThreesTotal, FoursRs, FoursPer, FoursTotal;
    public String Response, custID, Wallet, TXNID, BANKTXNID, ORDERID, TXNAMOUNT, STATUS, TXNTYPE, GATEWAYNAME, RESPMSG, RESPCODE, CHECKSUMHASH, CURRENCY, ettamount;
    private final Context _context;
    public Button btn, btnok;
    public EditText etamount;
    private ljsflsj good;

    private Vibrator vibrator;
    public ImageView closepopupbtn;
    public Animation myAnim;
    public String BANKNAME, MID, PAYMENTMODE, REFUNDAMT, TXNDATE;
    JSONObject jasonObject;
    JSONArray jsonArray = null;
    String DataParseUrl = "http://callofchallengers.com/coc/api/trapi.php";
    String resTxt = null;
    public String Statuss;

    public addmoneypopup(Context context) {
        this._context = context;
        unepicDialog = new Dialog(context, R.style.PauseDialog);
        epicDialog = new Dialog(context, R.style.PauseDialog); // for popup Dialog
        progressbar = new Dialog(context);


        vibrator = (Vibrator) context.getSystemService(VIBRATOR_SERVICE);
        myAnim = AnimationUtils.loadAnimation(context, R.anim.buttonanimation);
    }


    public void addpopup() {
        epicDialog.setContentView(R.layout.depositlayout);
        epicDialog.setCancelable(false);

        SharedPreferences prefs = _context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        custID = prefs.getString("Loginid", null);
        custID = decrypt(good.key, custID);

        SharedPreferences perper = _context.getSharedPreferences("walletdata", Context.MODE_PRIVATE);
        OnesRs = perper.getString("OnesRs", null);
        OnesRs = decrypt(good.key, OnesRs);

        OnesTotal = perper.getString("OnesTotal", null);
        OnesTotal = decrypt(good.key, OnesTotal);

        TwosRs = perper.getString("TwosRs", null);
        TwosRs = decrypt(good.key, TwosRs);

        TwosTotal = perper.getString("TwosTotal", null);
        TwosTotal = decrypt(good.key, TwosTotal);

        ThreesRs = perper.getString("ThreesRs", null);
        ThreesRs = decrypt(good.key, ThreesRs);

        ThreesTotal = perper.getString("ThreesTotal", null);
        ThreesTotal = decrypt(good.key, ThreesTotal);

        FoursRs = perper.getString("FoursRs", null);
        FoursRs = decrypt(good.key, FoursRs);

        FoursTotal = perper.getString("FoursTotal", null);
        FoursTotal = decrypt(good.key, FoursTotal);

        MinAmount = perper.getString("MinAmount", null);
        MinAmount = decrypt(good.key, MinAmount);

        btn = (Button) epicDialog.findViewById(R.id.button4);/////////////////////////////////////////////////////////////////////////////////////////
        etamount = (EditText) epicDialog.findViewById(R.id.edpay);


        TextView rupeesone = (TextView) epicDialog.findViewById(R.id.ruone);
        rupeesone.setText(OnesRs);
        rupeesone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(_context)) {
                    StaticUtils.vibrate(_context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(_context)) {
                    StaticUtils.backSoundonclick(_context);
                }
                TextView rupeesone = (TextView) epicDialog.findViewById(R.id.ruone);
                ettamount = rupeesone.getText().toString();
                etamount.setText(ettamount);
            }
        });

        TextView totalsone = (TextView) epicDialog.findViewById(R.id.totalone);
        totalsone.setText(OnesTotal);


        TextView rupeestwo = (TextView) epicDialog.findViewById(R.id.rutwo);
        rupeestwo.setText(TwosRs);
        rupeestwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(_context)) {
                    StaticUtils.vibrate(_context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(_context)) {
                    StaticUtils.backSoundonclick(_context);
                }
                TextView rupeestwo = (TextView) epicDialog.findViewById(R.id.rutwo);
                ettamount = rupeestwo.getText().toString();
                etamount.setText(ettamount);
            }
        });

        TextView totalstwo = (TextView) epicDialog.findViewById(R.id.totaltwo);
        totalstwo.setText(TwosTotal);


        TextView rupeesthree = (TextView) epicDialog.findViewById(R.id.ruthree);
        rupeesthree.setText(ThreesRs);
        rupeesthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(_context)) {
                    StaticUtils.vibrate(_context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(_context)) {
                    StaticUtils.backSoundonclick(_context);
                }
                TextView rupeesthree = (TextView) epicDialog.findViewById(R.id.ruthree);
                ettamount = rupeesthree.getText().toString();
                etamount.setText(ettamount);
            }
        });

        TextView totalsthree = (TextView) epicDialog.findViewById(R.id.totalthree);
        totalsthree.setText(ThreesTotal);


        TextView rupeesfour = (TextView) epicDialog.findViewById(R.id.rufour);
        rupeesfour.setText(FoursRs);
        rupeesfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(_context)) {
                    StaticUtils.vibrate(_context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(_context)) {
                    StaticUtils.backSoundonclick(_context);
                }
                TextView rupeesfour = (TextView) epicDialog.findViewById(R.id.rufour);
                ettamount = rupeesfour.getText().toString();
                etamount.setText(ettamount);
            }
        });

        TextView totalsfour = (TextView) epicDialog.findViewById(R.id.totalfour);
        totalsfour.setText(FoursTotal);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(_context)) {
                    StaticUtils.vibrate(_context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(_context)) {
                    StaticUtils.backSoundonclick(_context);
                }
                etamount = (EditText) epicDialog.findViewById(R.id.edpay);
                ettamount = etamount.getText().toString();
                Integer amount = Integer.valueOf(ettamount);
                if (amount < Integer.valueOf(MinAmount)) {
                    TextView text = (TextView) epicDialog.findViewById(R.id.texttext);
                    text.setText("Mininum deposit amount is ₹ "+MinAmount);
                    text.setVisibility(View.VISIBLE);
                } else {
                    v.startAnimation(myAnim);
                    epicDialog.dismiss();
                    vibrator.vibrate(70);
                    processPaytm();
                }

            }
        });
        // popup show
        closepopupbtn = (ImageView) epicDialog.findViewById(R.id.closePopup);// popup Close button

        closepopupbtn.setOnClickListener(new View.OnClickListener() {// popup Close button working
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(_context)) {
                    StaticUtils.vibrate(_context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(_context)) {
                    StaticUtils.backSoundonclick(_context);
                }

                epicDialog.dismiss();
            }
        });

        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
        epicDialog.show();
    }

    private void processPaytm() {
        String orderID = generateString();
        String callBackurl = "https://securegw.paytm.in/theia/paytmCallback?ORDER_ID=" + orderID;
        SharedPreferences prefs = _context.getSharedPreferences("UserData", Context.MODE_PRIVATE);

        String Password = prefs.getString("Password", null);
        String Usernames = prefs.getString("Name", null);
        String Loginid = prefs.getString("Loginid", null);

        final pytmclass request = new pytmclass();
        request.setmId("iBPBbb67079036102561");
        request.setOrderId(orderID);
        request.setCustId(custID);
        request.setChannelId("WAP");
        request.setTxnAmount(ettamount);
        request.setWebsite("DEFAULT");
        request.setCallBackUrl(callBackurl);
        request.setIndustryTypeId("Retail");






        UserService service = ServiceGenerator.createService(UserService.class, decrypt(good.key, Usernames), decrypt(good.key, Password));
        service.checksum(request).enqueue(new Callback<Checksumnew>() {
            @Override
            public void onResponse(Call<Checksumnew> call, Response<Checksumnew> response) {
                if (response.isSuccessful()) {

                    CHECKSUMHASH = response.body().checksumHash;

                    processToPay(CHECKSUMHASH, request);

                } else {
                    switch (response.code()) {
                        case 401:
                            Toast.makeText(_context, "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
                            Toast.makeText(_context, "ForbiddenException", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
                            Toast.makeText(_context, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(_context, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(_context, "unknown error", Toast.LENGTH_SHORT).show();
                            break;

                    }

                }
                progressbar.dismiss();

            }

            @Override
            public void onFailure(Call<Checksumnew> call, Throwable t) {
                t.printStackTrace();


                Log.e("System error:", t.getLocalizedMessage());

            }
        });
        //String custID = generateString();


        ///// Production Environment:

//        String callBackurl = "https://securegw.paytm.in/theia/paytmCallback?ORDER_ID=" + orderID;


        ///// Staging Environment:
//
//
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
//        WebServiceCaller.getClient().getChecksum(pytmclas.getmId(), pytmclas.getOrderId(), pytmclas.getCustId()
//                , pytmclas.getChannelId(), pytmclas.getTxnAmount(), pytmclas.getWebsite(), pytmclas.getCallBackUrl(), pytmclas.getIndustryTypeId()
//        ).enqueue(new Callback<Checksumnew>() {
//            @Override
//            public void onResponse(Call<Checksumnew> call, retrofit2.Response<Checksumnew> response) {
//                if (response.isSuccessful()) {
//                    processToPay(response.body().getChecksumHash(), pytmclas);
//
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

        Service.startPaymentTransaction(_context, true, true, new PaytmPaymentTransactionCallback() {
            /*Call Backs*/
            public void someUIErrorOccurred(String inErrorMessage) {
            }


            public void onTransactionResponse(Bundle inResponse) {



                // Toast.makeText(paytm.this, inResponse.toString(), Toast.LENGTH_SHORT).show();

                STATUS = inResponse.getString("STATUS");///
                TXNTYPE = inResponse.getString("CHECKSUMHASH");///
                BANKNAME = inResponse.getString("BANKNAME");////
                ORDERID = inResponse.getString("ORDERID");////
                TXNAMOUNT = inResponse.getString("TXNAMOUNT");////
                TXNDATE = inResponse.getString("TXNDATE");////
                MID = inResponse.getString("MID");/////
                TXNID = inResponse.getString("TXNID");/////
                RESPCODE = inResponse.getString("RESPCODE");//////
                PAYMENTMODE = inResponse.getString("PAYMENTMODE");//////
                BANKTXNID = inResponse.getString("BANKTXNID");/////
                REFUNDAMT = inResponse.getString("CURRENCY");/////
                GATEWAYNAME = inResponse.getString("GATEWAYNAME");//////
                RESPMSG = inResponse.getString("RESPMSG");///////

//                if (STATUS.equalsIgnoreCase("TXN_SUCCESS")) {
                try {
//                    SendDataToServer(ORDERID, MID, TXNTYPE);

                    SecondAPI();
//                    GetAllData();

                } catch (Exception e) {
                    e.printStackTrace();
                }

//                } else {
//                    Toast.makeText(_context, RESPMSG, Toast.LENGTH_LONG).show();
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
//                Toast.makeText(getApplicationContext(), "Transaction cancelled", Toast.LENGTH_LONG).show();
                unepicDialog.setContentView(R.layout.unsuccessfullayout);
                unepicDialog.setCancelable(false);
                btnok = (Button) unepicDialog.findViewById(R.id.okok);
                btnok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (SettingsPreferences.getVibration(_context)) {
                            StaticUtils.vibrate(_context, StaticUtils.VIBRATION_DURATION);
                        }
                        if (SettingsPreferences.getSoundEnableDisable(_context)) {
                            StaticUtils.backSoundonclick(_context);
                        }
                        unepicDialog.dismiss();
                    }
                });
                unepicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
                unepicDialog.show();
            }

            public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {


            }
        });

    }

    private void GetAllData() {
        SharedPreferences prefs = _context.getSharedPreferences("UserData", Context.MODE_PRIVATE);

        String Password = prefs.getString("Password", null);
        String Usernames = prefs.getString("Name", null);
        String Loginid = prefs.getString("Loginid", null);

        final LoginRequest request = new LoginRequest();

        request.setOrderid(ORDERID);
        request.setMid(MID);
        request.setTxntype(TXNTYPE);

        UserService service = ServiceGenerator.createService(UserService.class, decrypt(good.key, Usernames), decrypt(good.key, Password));
        service.firstadd(request).enqueue(new Callback<FirstResponseJson>() {
            @Override
            public void onResponse(Call<FirstResponseJson> call, Response<FirstResponseJson> response) {
                if (response.isSuccessful()) {



                    for (int z = 0; z < response.body().data.size(); z++) {


                        TXNID = response.body().data.get(z).TXNID;
                        BANKTXNID = response.body().data.get(z).BANKTXNID;
                        ORDERID = response.body().data.get(z).ORDERID;
                        TXNAMOUNT = response.body().data.get(z).TXNAMOUNT;

                        STATUS = response.body().data.get(z).STATUS;
                        GATEWAYNAME = response.body().data.get(z).GATEWAYNAME;
                        RESPCODE = response.body().data.get(z).RESPCODE;
                        RESPMSG = response.body().data.get(z).RESPMSG;

                        BANKNAME = response.body().data.get(z).BANKNAME;
                        MID = response.body().data.get(z).MID;
                        PAYMENTMODE = response.body().data.get(z).PAYMENTMODE;
                        TXNDATE = response.body().data.get(z).TXNDATE;

                    }

                    try {
//                        AsyncCallWSXXX task = new AsyncCallWSXXX();
//                        task.execute();


                        SecondAPI();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                } else {
                    switch (response.code()) {
                        case 401:
                            Toast.makeText(_context, "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
                            Toast.makeText(_context, "ForbiddenException", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
                            Toast.makeText(_context, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(_context, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(_context, "unknown error", Toast.LENGTH_SHORT).show();
                            break;

                    }

                }
                progressbar.dismiss();

            }

            @Override
            public void onFailure(Call<FirstResponseJson> call, Throwable t) {
                t.printStackTrace();


                Log.e("System error:", t.getLocalizedMessage());

            }
        });

    }


//    private void SendDataToServer(final String ORDERIDs, final String MIDs, final String TXNTYPEs) {
//        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
//            @Override
//            protected String doInBackground(String... params) {

//                String QuicORDERID = ORDERIDs;
//                String QuicMID = MIDs;
//                String QuicTXNTYPE = TXNTYPEs;
//
//                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//
//                nameValuePairs.add(new BasicNameValuePair("orderid", QuicORDERID));
//                nameValuePairs.add(new BasicNameValuePair("mid", QuicMID));
//                nameValuePairs.add(new BasicNameValuePair("txntype", QuicTXNTYPE));
//                try {
//                    HttpClient httpClient = new DefaultHttpClient();
//
//                    HttpPost httpPost = new HttpPost(DataParseUrl);
//
//                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//
//                    HttpResponse response = httpClient.execute(httpPost);
//
//                    HttpEntity entity = response.getEntity();
//
//                    resTxt = EntityUtils.toString(entity);
//
//                } catch (ClientProtocolException e) {
//
//                } catch (IOException e) {
//
//                }
//                return resTxt;
//            }
//
//            @Override
//            protected void onPostExecute(String result) {
//                super.onPostExecute(result);
//                if (result != null) {
//                    try {
//
//                        JSONObject resulttwo = new JSONObject(result);
//

//
//                        TXNID = resulttwo.getString("TXNID");/////
//                        BANKTXNID = resulttwo.getString("BANKTXNID");/////
//                        ORDERID = resulttwo.getString("ORDERID");////
//                        TXNAMOUNT = resulttwo.getString("TXNAMOUNT");////
//                        STATUS = resulttwo.getString("STATUS");///
//                        GATEWAYNAME = resulttwo.getString("GATEWAYNAME");//////
//                        RESPCODE = resulttwo.getString("RESPCODE");//////
//                        RESPMSG = resulttwo.getString("RESPMSG");///////
//                        BANKNAME = resulttwo.getString("BANKNAME");////
//                        MID = resulttwo.getString("MID");/////
//                        PAYMENTMODE = resulttwo.getString("PAYMENTMODE");//////
//                        TXNDATE = resulttwo.getString("TXNDATE");////
//
//
//
//
//

//
//
////                        JSONArray array = resulttwo.getJSONArray("Result");
//
////                        for (int i = 0; i < array.length(); i++) {
////
////                            JSONObject jsonrowdata = array.getJSONObject(i);
////
////
////                            Statuss = jsonrowdata.getString("status");
////
////
////                        }
////                        if (Statuss.equalsIgnoreCase("ACCEPTED")) {
//////
////
//                            try {
//                                AsyncCallWSXXX task = new AsyncCallWSXXX();
//                                task.execute();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
////
////
////                        } else {
////
////                            Toast.makeText(_context, StatusMessage, Toast.LENGTH_LONG).show();
////                        }
//
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                }
//
//            }
//
//
//        }
//
//        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
//        sendPostReqAsyncTask.execute(ORDERIDs, MIDs, TXNTYPEs);
//    }


    private String generateString() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    private void SecondAPI() {
        SharedPreferences prefs = _context.getSharedPreferences("UserData", Context.MODE_PRIVATE);

        String Password = prefs.getString("Password", null);
        String Usernames = prefs.getString("Name", null);
        String Loginid = prefs.getString("Loginid", null);

        final SecondRequestJson request = new SecondRequestJson();
        request.setTXNID(TXNID);
        request.setBANKTXNID(BANKTXNID);
        request.setORDERID(ORDERID);
        request.setTXNAMOUNT(TXNAMOUNT);
        request.setSTATUS(STATUS);
        request.setTXNTYPE(TXNTYPE);
        request.setGATEWAYNAME(GATEWAYNAME);
        request.setRESPMSG(RESPMSG);
        request.setBANKNAME(BANKNAME);
        request.setMID(MID);
        request.setPAYMENTMODE(PAYMENTMODE);
        request.setREFUNDAMT(REFUNDAMT);
        request.setTXNDATE(TXNDATE);
        request.setCustID(custID);
        request.setRESPCODE(RESPCODE);




        UserService service = ServiceGenerator.createService(UserService.class, decrypt(good.key, Usernames), decrypt(good.key, Password));
        service.secondadd(request).enqueue(new Callback<SecondResponseJson>() {
            @Override
            public void onResponse(Call<SecondResponseJson> call, Response<SecondResponseJson> response) {
                if (response.isSuccessful()) {
                    Wallet = response.body().Wallet;
                    Statuss = response.body().status;




                    Wallet = encrypt(good.key, good.initVector, Wallet);

                    if (Statuss.equalsIgnoreCase("success")) {
                        SharedPreferences prefs = _context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("Totalwallet", Wallet);
                        editor.apply();
                        SharedPreferences pref2 = _context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = pref2.edit();

                        editor1.putString("Totalwallet", Wallet);
                        editor1.apply();
                        Toast.makeText(_context, "Money added successfully to your wallet", Toast.LENGTH_SHORT).show();
//                Toast.makeText(pay_tm.this, "Thank you", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(_context, Profile.class);
                        intent.putExtra("from", "dashboard");
                        _context.startActivity(intent);
                    } else {
                        Intent intent = new Intent(_context, Profile.class);
                        intent.putExtra("from", "dashboard");
                        _context.startActivity(intent);
                        Toast.makeText(_context, "Your Transaction is failed", Toast.LENGTH_LONG).show();
                    }


                } else {
                    switch (response.code()) {
                        case 401:
                            Toast.makeText(_context, "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
                            Toast.makeText(_context, "ForbiddenException", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
                            Toast.makeText(_context, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(_context, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(_context, "unknown error", Toast.LENGTH_SHORT).show();
                            break;

                    }

                }
                progressbar.dismiss();

            }

            @Override
            public void onFailure(Call<SecondResponseJson> call, Throwable t) {
                t.printStackTrace();


                Log.e("System error:", t.getLocalizedMessage());

            }
        });


    }


//    //// playtiam time
//    public class AsyncCallWSXXX extends AsyncTask<String, Void, Void> {
//        String displayText;
//        String Statuss;
//
//
//        @Override
//        protected Void doInBackground(String... params) {
//
//
//            TXNID = encrypt(good.key, good.initVector, TXNID);
//            BANKTXNID = encrypt(good.key, good.initVector, BANKTXNID);
//            ORDERID = encrypt(good.key, good.initVector, ORDERID);
//            TXNAMOUNT = encrypt(good.key, good.initVector, TXNAMOUNT);
//            STATUS = encrypt(good.key, good.initVector, STATUS);
//            TXNTYPE = encrypt(good.key, good.initVector, TXNTYPE);
//            GATEWAYNAME = encrypt(good.key, good.initVector, GATEWAYNAME);
//            RESPMSG = encrypt(good.key, good.initVector, RESPMSG);
//            BANKNAME = encrypt(good.key, good.initVector, BANKNAME);
//            MID = encrypt(good.key, good.initVector, MID);
//            PAYMENTMODE = encrypt(good.key, good.initVector, PAYMENTMODE);
//            REFUNDAMT = encrypt(good.key, good.initVector, REFUNDAMT);
//            TXNDATE = encrypt(good.key, good.initVector, TXNDATE);
//            custID = encrypt(good.key, good.initVector, custID);
//
//
//            displayText = WebService.Paytm(TXNID, BANKTXNID, ORDERID, TXNAMOUNT, STATUS, TXNTYPE, GATEWAYNAME, RESPCODE, RESPMSG, BANKNAME, MID, PAYMENTMODE, REFUNDAMT, TXNDATE, custID, "AddMoney");
//            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                try {
//                    jasonObject = new JSONObject(displayText);
//                    jsonArray = jasonObject.getJSONArray("Response");
//
//
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
//                        Wallet = jsonrowdata.getString("Wallet");
//                        Statuss = jsonrowdata.getString("status");
//
//
//                        Wallet = encrypt(good.key, good.initVector, Wallet);
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
////            hud.dismiss();
//            progressbar.dismiss();
//            if (displayText.equalsIgnoreCase("connection fault")) {
//                Toast.makeText(_context, "Please Check your internet connection", Toast.LENGTH_LONG).show();
//            } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                Toast.makeText(_context, "Please try after some times...", Toast.LENGTH_LONG).show();
//            } else if (jsonArray.length() > 0) {
//                if (Statuss.equalsIgnoreCase("success")) {
//                    SharedPreferences prefs = _context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = prefs.edit();
//                    editor.putString("Totalwallet", Wallet);
//                    editor.apply();
//                    SharedPreferences pref2 = _context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor1 = pref2.edit();
//
//                    editor1.putString("Totalwallet", Wallet);
//                    editor1.apply();
//                    Toast.makeText(_context, "Money added successfully to your wallet", Toast.LENGTH_SHORT).show();
////                Toast.makeText(pay_tm.this, "Thank you", Toast.LENGTH_LONG).show();
//
//                    Intent intent = new Intent(_context, Profile.class);
//                    intent.putExtra("from", "dashboard");
//                    _context.startActivity(intent);
//                } else {
//                    Intent intent = new Intent(_context, Profile.class);
//                    intent.putExtra("from", "dashboard");
//                    _context.startActivity(intent);
//                    Toast.makeText(_context, "Your Transaction is failed", Toast.LENGTH_LONG).show();
//                }
//
//
//            } else {
//                Toast.makeText(_context, "Netwark Error", Toast.LENGTH_LONG).show();
//            }
//        }
//
//        @Override
//        protected void onPreExecute() {
//            progressbar.setContentView(R.layout.progresbarlayout);
//            progressbar.setCancelable(false);
//            RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);
//
//            RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(_context,
//                    20, 60, ContextCompat.getColor(_context, R.color.white));
//            loader.setAnimDuration(3000);
//
//            rotatingCircularDotsLoader.addView(loader);
//
//            progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
//            progressbar.show();
//
////            hud = KProgressHUD.create(Dashboard.this)
////                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
////                    .setWindowColor(Color.parseColor("#c20e14"))
////                    .setLabel("")
////                    .setAnimationSpeed(1);
////            hud.show();
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//        }
//    }
}
