package com.challengers.of.call.fifty_fity_popup;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.FiFityAPI.FifityapiRequestJson;
import com.challengers.of.call.FiFityAPI.FifitybuyiRequestJson;
import com.challengers.of.call.FiFityAPI.FiftyfityResponseJson;
import com.challengers.of.call.FiFityAPI.FiftyfitybuyResponseJson;
import com.challengers.of.call.GameTypes.GametypesActivity;
import com.challengers.of.call.R;
import com.challengers.of.call.Retrofit.ServiceGenerator;
import com.challengers.of.call.Retrofit.UserService;
import com.challengers.of.call.WebService;
import com.challengers.of.call.testing.SettingsPreferences;
import com.challengers.of.call.testing.StaticUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.decrypt;
import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.encrypt;

public class fiftyfifty {
    private final Context _context;
    public Dialog epicDialog, unepicDialog, progressbar, withdraw, fiftyfiftyss;
    public ImageView closepopupbtn;
    String firstobject, twoobject, threeobject, fourobject, fiveobject;
    String firstrupess, tworupess, threerupess, fourrupess, total;
    public String object, money;
    public String Totallife, Totalwallet;
    public Animation myAnim;
    private ljsflsj good;
    JSONObject jasonObject;
    JSONArray jsonArray = null;

    public fiftyfifty(Context context) {
        this._context = context;

        fiftyfiftyss = new Dialog(context, R.style.PauseDialog); // for popup Dialog
        progressbar = new Dialog(context);
        myAnim = AnimationUtils.loadAnimation(context, R.anim.buttonanimation);

    }

    public void fiftyfiftys() {
        fiftyfiftyss.setContentView(R.layout.fifityfifitylayout);
        fiftyfiftyss.setCancelable(false);

        SharedPreferences perper = _context.getSharedPreferences("fifty", Context.MODE_PRIVATE);
        SharedPreferences pref2 = _context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        total = decrypt(good.key, pref2.getString("Totallife", null));

        firstobject = decrypt(good.key, perper.getString("firstobject",null));
        twoobject = decrypt(good.key, perper.getString("twoobject",null));
        threeobject = decrypt(good.key, perper.getString("threeobject",null));
        fourobject = decrypt(good.key, perper.getString("fourobject",null));

        firstrupess = decrypt(good.key, perper.getString("firstrupess",null));
        tworupess = decrypt(good.key, perper.getString("tworupess",null));
        threerupess = decrypt(good.key, perper.getString("threerupess",null));
        fourrupess = decrypt(good.key, perper.getString("fourrupess",null));
//        totalfifity = perper.getString("totallife", null);


        closepopupbtn = (ImageView) fiftyfiftyss.findViewById(R.id.closePopup);// popup Close butt

        final TextView onefifty = (TextView) fiftyfiftyss.findViewById(R.id.lifeone);
        final TextView twofifty = (TextView) fiftyfiftyss.findViewById(R.id.lifetwo);
        final TextView threefifty = (TextView) fiftyfiftyss.findViewById(R.id.lifethree);
        final TextView fourfifty = (TextView) fiftyfiftyss.findViewById(R.id.lifefour);

        final TextView totalfifity = (TextView) fiftyfiftyss.findViewById(R.id.total);
        totalfifity.setText(total);

        onefifty.setText(firstobject);
        twofifty.setText(twoobject);
        threefifty.setText(threeobject);
        fourfifty.setText(fourobject);


        final Button btnbuy = (Button) fiftyfiftyss.findViewById(R.id.button4);


        final TextView oneRs = (TextView) fiftyfiftyss.findViewById(R.id.rupeesone);
        final TextView twoRs = (TextView) fiftyfiftyss.findViewById(R.id.rupeestwo);
        final TextView threeRs = (TextView) fiftyfiftyss.findViewById(R.id.rupeesthree);
        final TextView fourRs = (TextView) fiftyfiftyss.findViewById(R.id.rupeesfour);


        oneRs.setText(firstrupess);
        twoRs.setText(tworupess);
        threeRs.setText(threerupess);
        fourRs.setText(fourrupess);

        Totalwallet = decrypt(good.key, pref2.getString("Totalwallet",null));



        final TextView toasts = (TextView) fiftyfiftyss.findViewById(R.id.toast);


        final TextView shownumber = (TextView) fiftyfiftyss.findViewById(R.id.edpay);

        onefifty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shownumber.setText("₹ " + firstrupess);
                object = "one";
                money = oneRs.getText().toString();
                if (Double.parseDouble(Totalwallet) >= Double.parseDouble(money)) {
                    btnbuy.setVisibility(View.VISIBLE);
                    toasts.setText("Fifty fifty Life line will be added to your quiz contest only.");
                    toasts.setTextColor(Color.parseColor("#FFFFFF"));
                } else {
                    btnbuy.setVisibility(View.GONE);
                    toasts.setText("Please Add Amount");
                    toasts.setTextColor(Color.parseColor("#FFEB3B"));
                }
            }
        });
        twofifty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shownumber.setText("₹ " + tworupess);
                object = "two";
                money = twoRs.getText().toString();
                if (Double.parseDouble(Totalwallet) >= Double.parseDouble(money)) {
                    btnbuy.setVisibility(View.VISIBLE);
                    toasts.setText("Fifty fifty Life line will be added to your quiz contest only.");
                    toasts.setTextColor(Color.parseColor("#FFFFFF"));
                } else {
                    btnbuy.setVisibility(View.GONE);
                    toasts.setText("Please Add Amount");
                    toasts.setTextColor(Color.parseColor("#FFEB3B"));
                }
            }
        });
        threefifty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shownumber.setText("₹ " + threerupess);
                object = "three";
                money = threeRs.getText().toString();
                if (Double.parseDouble(Totalwallet) >= Double.parseDouble(money)) {
                    btnbuy.setVisibility(View.VISIBLE);
                    toasts.setText("Fifty fifty Life line will be added to your quiz contest only.");
                    toasts.setTextColor(Color.parseColor("#FFFFFF"));
                } else {
                    btnbuy.setVisibility(View.GONE);
                    toasts.setText("Please Add Amount");
                    toasts.setTextColor(Color.parseColor("#FFEB3B"));
                }
            }
        });
        fourfifty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shownumber.setText("₹ " + fourrupess);
                object = "four";
                money = fourRs.getText().toString();
                if (Double.parseDouble(Totalwallet) >= Double.parseDouble(money)) {
                    btnbuy.setVisibility(View.VISIBLE);
                    toasts.setText("Fifty fifty Life line will be added to your quiz contest only.");
                    toasts.setTextColor(Color.parseColor("#FFFFFF"));
                } else {
                    btnbuy.setVisibility(View.GONE);
                    toasts.setText("Please Add Amount");
                    toasts.setTextColor(Color.parseColor("#FFEB3B"));

                }
            }
        });


        btnbuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (SettingsPreferences.getVibration(_context)) {
                    StaticUtils.vibrate(_context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(_context)) {
                    StaticUtils.backSoundonclick(_context);
                }


                String checkamount = shownumber.getText().toString();
                if (checkamount.length() > 0){
//                    AsyncCallWSgeneratedailylife task = new AsyncCallWSgeneratedailylife();
//                    task.execute();
                    
                    FifityFifitybuy();
                    fiftyfiftyss.dismiss();
                    v.startAnimation(myAnim);
                }else {
                    Toast.makeText(_context, "Please Enter Amount", Toast.LENGTH_SHORT).show();
                }


            }
        });


        closepopupbtn.setOnClickListener(new View.OnClickListener() {// popup Close button working
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(_context)) {
                    StaticUtils.vibrate(_context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(_context)) {
                    StaticUtils.backSoundonclick(_context);
                }

                fiftyfiftyss.dismiss();
            }
        });

        fiftyfiftyss.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
        fiftyfiftyss.show();

    }

    private void FifityFifitybuy() {
        SharedPreferences prefs = _context.getSharedPreferences("UserData", Context.MODE_PRIVATE);

        String Password = prefs.getString("Password", null);
        String Usernames = prefs.getString("Name", null);
        String Loginid = prefs.getString("Loginid", null);

        final FifitybuyiRequestJson request = new FifitybuyiRequestJson();

        request.setUserid(decrypt(good.key, Loginid));
        request.setObject(object);


        UserService service = ServiceGenerator.createService(UserService.class, decrypt(good.key, Usernames), decrypt(good.key, Password));
        service.fifitybuy(request).enqueue(new Callback<FiftyfitybuyResponseJson>() {
            @Override
            public void onResponse(Call<FiftyfitybuyResponseJson> call, Response<FiftyfitybuyResponseJson> response) {
                if (response.isSuccessful()) {




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
            public void onFailure(Call<FiftyfitybuyResponseJson> call, Throwable t) {
                t.printStackTrace();


                Log.e("System error:", t.getLocalizedMessage());

            }
        });
    }


//    public class AsyncCallWSgeneratedailylife extends AsyncTask<String, Void, Void> {
//        String displayText;
//        String Timeremain;
//
//        @Override
//        protected Void doInBackground(String... params) {
//            SharedPreferences prefs = _context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
//            String Loginid = prefs.getString("Loginid", null);
//            object = encrypt(good.key, good.initVector, object);

//
//            displayText = WebService.Selectbtn(Loginid, object, "purchaseFifty");
//            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                try {
//                    jasonObject = new JSONObject(displayText);
//                    jsonArray = jasonObject.getJSONArray("Response");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
////                        Timeremain = jsonrowdata.getString("Timeremaining");////////////////////////////////////////////////////////////////////////////////////
//                        Totallife = jsonrowdata.getString("Totallife");
//                        Totalwallet = jsonrowdata.getString("Totalwallet");
//

//
//                        Totallife = encrypt(good.key, good.initVector, Totallife);
//                        Totalwallet = encrypt(good.key, good.initVector, Totalwallet);
//
//
//

//
//
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//            return null;
//        }
//
//        @RequiresApi(api = Build.VERSION_CODES.N)
//        @Override
//        protected void onPostExecute(Void result) {
//            if (displayText != null) {
//                if (displayText.equalsIgnoreCase("connection fault")) {
//                    Toast.makeText(_context, "Please Check your internet connection", Toast.LENGTH_LONG).show();
//                } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                    Toast.makeText(_context, "Please try after some times...", Toast.LENGTH_LONG).show();
//                } else if (jsonArray != null) {
//                    if (jsonArray.length() > 0) {
////                        TextView txtlife;
////                        txtlife = findViewById(R.id.txtlife);
////                        txtlife.setText(Totallife);
////                        if (Totalwallet != null && Totalwallet != "") {
////                            txtwallet.setText(Totalwallet);
////                        } else {
////
////                            txtwallet.setText("0");
////                        }
//                        SharedPreferences pref2 = _context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor1 = pref2.edit();
//                        editor1.putString("Totallife", Totallife);
//                        editor1.putString("Totalwallet", Totalwallet);
//                        editor1.apply();
//                        SharedPreferences prefs = _context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = prefs.edit();
//                        editor.putString("Totalwallet", Totalwallet);
//                        editor.putString("Totallife", Totallife);
//                        editor.apply();
////                        SharedPreferences sharedPreferences = getSharedPreferences("lucky", MODE_PRIVATE);
////                        SharedPreferences.Editor edit = sharedPreferences.edit();
////                        edit.putString("Totalwallet", Totalwalletnew);
////                        edit.putString("Totallife", Totallife);
//
////                        edit.apply();
//                        ((GametypesActivity)_context).totallife(Totalwallet,Totallife);
//
////                        application.setClICKED("CLICKED");
////                        if (application.getClICKED().equalsIgnoreCase("CLICKED")) {
//////                            playtimer(Integer.valueOf(Timeremain));
////                        }
//                    } else if (jsonArray.length() == 0) {
//                        Toast.makeText(_context, "No detail found", Toast.LENGTH_LONG).show();
//                    }
//                } else {
//                    Toast.makeText(_context, "Try Again", Toast.LENGTH_LONG).show();
//                }
//            } else {
//                Toast.makeText(_context, "Try Again", Toast.LENGTH_LONG).show();
//            }
//        }
//
//        @Override
//        protected void onPreExecute() {
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//        }
//    }
}
