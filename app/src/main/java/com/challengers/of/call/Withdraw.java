package com.challengers.of.call;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import com.kaopiz.kprogresshud.KProgressHUD;

import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class    Withdraw extends AppCompatActivity {

    public String Mobile, Loginid, Wallet, p, Emailid, orderID, confMobile;
    public String Amount;
    public String currencyCode;
    String resTxt = null;

    private static int backbackexit = 1;

    public Animation myAnim;


    public EditText etobjamount, etobjmobile, etobjmobile2;


    public Button btnobjwithdraw;
    JSONObject jasonObject;
    JSONArray jsonArray = null;
    boolean available = false;
    public ImageView informationo;
    String CheckToast;
    public String Rupees;
    String DataParseUrl = "http://callofchallengers.com/coc/cocwithdraw.php";
    public Double Result;

    public String Type, RequestGuid, OrderId, Status, StatusCode, StatusMessage, Response, Metadata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
        myAnim = AnimationUtils.loadAnimation(this, R.anim.buttonanimation);


        etobjmobile = (EditText) findViewById(R.id.etmobile);
        etobjmobile2 = (EditText) findViewById(R.id.reentermobile);


/////////////////////////////////////////////////Enter Amount////////////////////////////////////////////////////////////////////////
        etobjamount = (EditText) findViewById(R.id.etamountnew);


        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        Loginid = prefs.getString("Loginid", null);
        Emailid = prefs.getString("Mobile", null);


        Mobile = etobjmobile.getText().toString().trim();

        confMobile = etobjmobile2.getText().toString().trim();

        Amount = etobjamount.getText().toString().trim();



        informationo = (ImageView) findViewById(R.id.ivinformation);
        informationo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(Withdraw.this);
                builder1.setMessage("if you withdraw " + Amount + " to 499 amount then ");
                builder1.setTitle("Information");
                builder1.setIcon(android.R.drawable.ic_dialog_alert);
                builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder1.setCancelable(true);


                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });


        etobjamount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    AsyncCallWS task = new AsyncCallWS();
                    task.execute();
                }
            }
        });


        btnobjwithdraw = (Button) findViewById(R.id.btnwithdraw);

        btnobjwithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (available) {
                    if (etobjamount.length() == 0) {
                        Toast.makeText(Withdraw.this, "Please Enter Amount", Toast.LENGTH_SHORT).show();
                    } else if (etobjmobile.length() == 0) {
                        Toast.makeText(Withdraw.this, "Please Enter your Mobile", Toast.LENGTH_SHORT).show();
                    } else if (etobjmobile.length() != 10) {
                        Toast.makeText(Withdraw.this, "Please Enter valid Mobile Number", Toast.LENGTH_SHORT).show();
                    } else if (!etobjmobile.getText().toString().equals(etobjmobile2.getText().toString())) {
                        Toast.makeText(Withdraw.this, "Mobile Number Mismatched", Toast.LENGTH_SHORT).show();
                    } else {

                            Amount = etobjamount.getText().toString().trim();
                            int amount = Integer.valueOf(Amount);
                            if (amount>= 50 && amount <= 499){
                                Double amounts = Double.valueOf(amount);
                                Result = amounts * 0.10;
                            }else if (amount>= 500){
                                Result = Double.valueOf(amount);

                            }

                            AlertDialog.Builder builder1 = new AlertDialog.Builder(Withdraw.this);
                            builder1.setMessage("if you withdraw " + Amount + " to 499 amount then "+Result);
                            builder1.setTitle("Information");
                            builder1.setIcon(android.R.drawable.ic_dialog_alert);
                            builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    GetDataFromEditText();
                                    SendDataToServer(Emailid, Loginid, Amount, Mobile);
                                    btnobjwithdraw.setEnabled(false);

                                }
                            });
                            builder1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            builder1.setCancelable(true);


                            AlertDialog alert11 = builder1.create();
                            alert11.show();



                        v.startAnimation(myAnim);


                    }
                } else {
                    if (CheckToast.equalsIgnoreCase("NotExist")) {

                        Toast.makeText(Withdraw.this, "Required Minimum balance is ₹" + Rupees + " for withdraw", Toast.LENGTH_SHORT).show();


                    } else if (CheckToast.equalsIgnoreCase("NotAmount")) {

                        Toast.makeText(Withdraw.this, "You have not enough balance to withdraw", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(Withdraw.this, "Please fill correct details", Toast.LENGTH_SHORT).show();

                    }

                }


            }
        });


    }

    private void GetDataFromEditText() {
        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        Loginid = prefs.getString("Loginid", null);
        Emailid = prefs.getString("Mobile", null);


        Mobile = etobjmobile.getText().toString().trim();
        confMobile = etobjmobile2.getText().toString().trim();
        Amount = etobjamount.getText().toString().trim();
    }

    private void SendDataToServer(final String emailid, final String loginid, final String amount, final String mobile) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String QuicEmail = emailid;
                String QuicLoginid = loginid;
                String QuicAmount = amount;
                String QuicMobile = mobile;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("Email", QuicEmail));
                nameValuePairs.add(new BasicNameValuePair("Login", QuicLoginid));
                nameValuePairs.add(new BasicNameValuePair("Amount", QuicAmount));
                nameValuePairs.add(new BasicNameValuePair("Mobile", QuicMobile));
                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost(DataParseUrl);

                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

                    resTxt = EntityUtils.toString(entity);

                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return resTxt;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                if (result != null) {
                    try {

                        JSONObject resulttwo = new JSONObject(result);

                        JSONArray array = resulttwo.getJSONArray("Result");

                        for (int i = 0; i < array.length(); i++) {

                            JSONObject jsonrowdata = array.getJSONObject(i);


                            Type = jsonrowdata.getString("type");
                            RequestGuid = jsonrowdata.getString("requestGuid");
                            OrderId = jsonrowdata.getString("orderId");
                            Status = jsonrowdata.getString("status");
                            StatusCode = jsonrowdata.getString("statusCode");
                            StatusMessage = jsonrowdata.getString("statusMessage");


                        }
                        if (StatusMessage.equalsIgnoreCase("ACCEPTED")) {
//
                            btnobjwithdraw.setEnabled(false);
                            AsyncCallWSsendotp task = new AsyncCallWSsendotp();
                            task.execute();


                        } else {

                            Toast.makeText(Withdraw.this, StatusMessage, Toast.LENGTH_LONG).show();
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }

            @Override
            protected void onPreExecute() {

                Mobile = etobjmobile.getText().toString().trim();
                confMobile = etobjmobile2.getText().toString().trim();
                Amount = etobjamount.getText().toString().trim();
            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(emailid, loginid, amount, mobile);
    }

////// amount check
    public class AsyncCallWS extends AsyncTask<String, Void, Void> {
        String displayText = "";
        String Username, Count;


        private KProgressHUD hud;

        @Override
        protected Void doInBackground(String... params) {
            displayText = WebService.CheckAmount(Amount, Loginid, "CheckAmount");
            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
                try {
                    jasonObject = new JSONObject(displayText);
                    jsonArray = jasonObject.getJSONArray("Response");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
                        Count = jsonrowdata.getString("Count");
                        Rupees = jsonrowdata.getString("Rupees");

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
                Toast.makeText(Withdraw.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
            } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
                Toast.makeText(Withdraw.this, "Please try after some times...", Toast.LENGTH_LONG).show();
            } else if (displayText.equalsIgnoreCase("Unable to connect to server")) {
                Toast.makeText(Withdraw.this, "Unable to connect to server...", Toast.LENGTH_LONG).show();
            } else if (jsonArray.length() > 0) {


                if (Count.equalsIgnoreCase("NotExist")) {
                    CheckToast = "NotExist";
                    available = false;
                    Snackbar snack = Snackbar.make(findViewById(android.R.id.content), "Required Minimum balance is ₹ " + Rupees + " for withdraw", Snackbar.LENGTH_LONG);
                    View view1 = snack.getView();
                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view1.getLayoutParams();
                    params.gravity = Gravity.BOTTOM;
                    view1.setLayoutParams(params);
                    view1.setBackgroundColor(Color.parseColor("#9b0000"));
                    snack.show();
//                    layoutunavailable.setVisibility(View.VISIBLE);
//                    layoutunavailable.startAnimation(AnimationUtils.loadAnimation(Register.this, R.anim.imageanim));
//                    layoutavailable.setVisibility(View.GONE);
                } else if (Count.equalsIgnoreCase("NotAmount")) {
                    CheckToast = "NotAmount";
                    available = false;
                    Snackbar snack = Snackbar.make(findViewById(android.R.id.content), "You have not enough balance to withdraw", Snackbar.LENGTH_LONG);
                    View view1 = snack.getView();
                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view1.getLayoutParams();
                    params.gravity = Gravity.BOTTOM;
                    view1.setLayoutParams(params);
                    view1.setBackgroundColor(Color.parseColor("#9b0000"));
                    snack.show();
                } else {
                    available = true;
                    Snackbar snack = Snackbar.make(findViewById(android.R.id.content), "This amount is valid", Snackbar.LENGTH_LONG);
                    View view1 = snack.getView();
                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view1.getLayoutParams();
                    params.gravity = Gravity.BOTTOM;
                    view1.setLayoutParams(params);
                    view1.setBackgroundColor(Color.parseColor("#00902E"));
                    snack.show();

                }
            } else {
                Toast.makeText(Withdraw.this, "Please try again.", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onPreExecute() {

            Mobile = etobjmobile.getText().toString().trim();
            confMobile = etobjmobile2.getText().toString().trim();
            Amount = etobjamount.getText().toString().trim();


            hud = KProgressHUD.create(Withdraw.this)
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


    public class AsyncCallWSsendotp extends AsyncTask<String, Void, Void> {
        String From = "", displayText = "", Verify;
        String Username, Mobile, Password, OTP, Responsemobile, Sponsorid;
        private KProgressHUD hud;

        @Override
        protected Void doInBackground(String... params) {
            displayText = WebService.Withdraw(Mobile, Amount, Loginid, OrderId, "Withdraw");
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
            if (displayText != null && !displayText.isEmpty()) {
                if (displayText.equalsIgnoreCase("connection fault")) {
                    Toast.makeText(Withdraw.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
                } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
                    Toast.makeText(Withdraw.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
                } else if (displayText.equalsIgnoreCase("Unable to connect to server")) {
                    Toast.makeText(Withdraw.this, "Unable to connect to server...", Toast.LENGTH_LONG).show();
                } else if (jsonArray.length() > 0) {

                    SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("Totalwallet", Wallet);


                    editor.apply();
                    SharedPreferences pref2 = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = pref2.edit();

                    editor1.putString("Totalwallet", Wallet);
                    editor1.apply();
                    Toast.makeText(Withdraw.this, "Money added to your paytm account successfully", Toast.LENGTH_LONG).show();


                    Intent intent = new Intent(Withdraw.this, Wallet.class);
                    intent.putExtra("from", "dashboard");
                    startActivity(intent);
                    finish();


                }
            } else {
                Toast.makeText(Withdraw.this, "Please try again", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onPreExecute() {

            Mobile = etobjmobile.getText().toString().trim();
            confMobile = etobjmobile2.getText().toString().trim();
            Amount = etobjamount.getText().toString().trim();
            hud = KProgressHUD.create(Withdraw.this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setWindowColor(Color.parseColor("#c20e14"))
                    .setLabel("")
                    .setAnimationSpeed(1);
            hud.show();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Withdraw.this, Wallet.class);
        intent.putExtra("from", "dashboard");
        startActivity(intent);
        finish();
    }
}
