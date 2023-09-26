package com.challengers.of.call.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;

import com.agrawalsuneet.dotsloader.loaders.RotatingCircularDotsLoader;
import com.challengers.of.call.Add_Money_Popup.addmoneypopup;
import com.challengers.of.call.Banned_APP.bannedapp;
import com.challengers.of.call.Contest.ContestRequestJson;
import com.challengers.of.call.Contest.ContestResponseJson;
import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.FiFityAPI.FifitybuyiRequestJson;
import com.challengers.of.call.FiFityAPI.FiftyfitybuyResponseJson;
import com.challengers.of.call.GameTypes.GametypesActivity;
import com.challengers.of.call.Retrofit.ServiceGenerator;
import com.challengers.of.call.Retrofit.UserService;
import com.challengers.of.call.Splash;
import com.challengers.of.call.TextinActivity;
import com.challengers.of.call.testing.SettingsPreferences;
import com.challengers.of.call.testing.StaticUtils;
import com.challengers.of.call.testing.TextingtwoActivity;
import com.challengers.of.call.testing.Utils;
import com.google.android.material.snackbar.Snackbar;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.challengers.of.call.Activityquiz;
import com.challengers.of.call.Dashboard;
import com.challengers.of.call.Main2Activity;
import com.challengers.of.call.R;
import com.challengers.of.call.WebService;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.decrypt;
import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.encrypt;
import static com.challengers.of.call.Main2Activity.lucky;

public class Fragmentcontest extends Fragment {
    double amount = 0, Entryfees = 0;
    double entryfees = 0;
    JSONObject jasonObject;
    private String a = null;
    JSONArray jsonArray = null;
    private static bannedapp bannedapps;
    String Contestid, TotalWalletnew;
    EditText editText, editText2;
    private static addmoneypopup addpopup;
//    AsyncCallWS task;
    String Loginid, Name, Totalwallet;
    public ImageView imageView;
    Button btncontest;
    public String GameName, Gameid, GameLayout;
    public Dialog unepicDialog, progressbar, information;
    private ljsflsj good;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View rootView;
        rootView = inflater.inflate(R.layout.contest, container, false);
        addpopup = new addmoneypopup(getActivity());
        bannedapps = new bannedapp(getActivity());
        bannedapps.bannedbig();
//        SharedPreferences lucky = getActivity().getSharedPreferences("coding",0);

//        cg = lucky.getString("gametype",null);
//        // Fees Edittext
        progressbar = new Dialog(getActivity());
        editText = rootView.findViewById(R.id.editText);
        information = new Dialog(getActivity(),R.style.PauseDialog);
        imageView = (ImageView)rootView.findViewById(R.id.info);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                information.setContentView(R.layout.notelayout);

                Button nobtn = (Button)information.findViewById(R.id.closePopup);
                nobtn.setText("Close");
                nobtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (SettingsPreferences.getVibration(getActivity())) {
                            StaticUtils.vibrate(getActivity(), StaticUtils.VIBRATION_DURATION);
                        }
                        if (SettingsPreferences.getSoundEnableDisable(getActivity())) {
                            StaticUtils.backSoundonclick(getActivity());
                        }
                        information.dismiss();
                    }
                });
                information.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
                information.show();
            }
        });
        // Fees Edittext

        // Winning Amount Edittext
        editText2 = rootView.findViewById(R.id.editText2);
        // Winning Amount Edittext

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("coding", Context.MODE_PRIVATE);
        GameName = sharedPreferences.getString("gametype",null);

        SharedPreferences lucky = getActivity().getSharedPreferences("coding",Context.MODE_PRIVATE);
        Gameid = lucky.getString("gameid", null);

        GameLayout = lucky.getString("gamelayout", null);



        SharedPreferences prefs = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        Loginid = prefs.getString("Loginid", null);
        Totalwallet = prefs.getString("Totalwallet", null);
        Name = prefs.getString("Name", null);


        // Fees Edittext Validation start
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // get a Free Edittext value to String Variable "a"
                a = editText.getText().toString();
                // get a Free Edittext value to String Variable "a"

                // 80% multipication coding will starts
                if (a != null && !a.isEmpty()) {

                    // total amount save in a "amount" variable
                    amount = Double.valueOf(editText.getText().toString()) * 0.8 * 2;
                    // total amount save in a "amount" variable
                }
                // 80% multipication coding will starts
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                if (charSequence != null && !charSequence.toString().isEmpty()) {
                    // get a Free Edittext value to String Variable "a"
                    a = editText.getText().toString();
                    // get a Free Edittext value to String Variable "a"

                    // get a Free Edittext value to String Variable "Entryfees"
                    Entryfees = Double.valueOf(editText.getText().toString());
                    // get a Free Edittext value to String Variable "Entryfees"

                    if (a.length() > 0) {

                        // total amount save in a "amount" variable
                        amount = Double.valueOf(editText.getText().toString()) * 0.8 * 2;
                        // total amount save in a "amount" variable
                    }
                } else {

                    amount = 0;
                    Entryfees = 0;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (amount >= 1) {
                    DecimalFormat df = new DecimalFormat("#.00");
                    String Amount = df.format(amount);
                    editText2.setText(Amount);
                } else {
                    editText2.setText("");
                }
            }
        });
        // Fees Edittext Validation start


        // Create Contest button
        btncontest = rootView.findViewById(R.id.btncontest);
        // Create Contest button
        final Animation myAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.buttonanimation);

        // Create Contest button setOnClickListener
        btncontest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SettingsPreferences.getVibration(getActivity())) {
                    StaticUtils.vibrate(getActivity(), StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(getActivity())) {
                    StaticUtils.backSoundonclick(getActivity());
                }
                if (editText.length() == 0) {


                    Toast.makeText(getActivity(), "Please Enter Entry Fee", Toast.LENGTH_LONG).show();
                } else if (amount < 8) {


                    Toast.makeText(getActivity(), "Entre fee should be minimum of 5 Rs", Toast.LENGTH_LONG).show();
                } else {
                    if (amount >= 8 && amount <= 1800) {

                        Totalwallet = decrypt(good.key, Totalwallet);
                        if (Double.valueOf(Totalwallet) > 0 && Double.valueOf(Totalwallet) >= Entryfees) {
                            entryfees = Double.valueOf(editText.getText().toString());

                            btncontest.setEnabled(false);

                            lucky();

                            if (Utils.isNetworkAvailable(getActivity())) {
//                                task = new AsyncCallWS();
//                                task.execute();
                                progressbar.setContentView(R.layout.progresbarlayout);
                                progressbar.setCancelable(false);
                                RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader)progressbar.findViewById(R.id.progress);

                                RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(getActivity(),
                                        20, 60, ContextCompat.getColor(getActivity(), R.color.white));
                                loader.setAnimDuration(3000);

                                rotatingCircularDotsLoader.addView(loader);

                                progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
                                progressbar.show();
                                
                                Contest();




                            } else {
                                Toast.makeText(getActivity(), "No internet connection!", Toast.LENGTH_LONG).show();


                            }

                            view.startAnimation(myAnim);
                        } else {
                            addpopup.addpopup();

//                            Snackbar snack = Snackbar.make(getActivity().findViewById(android.R.id.content), "Please add amount", Snackbar.LENGTH_INDEFINITE);
//                            View view1 = snack.getView();
//                            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view1.getLayoutParams();
//                            params.gravity = Gravity.BOTTOM;
//                            view1.setLayoutParams(params);
//                            view1.setBackgroundColor(Color.parseColor("#F82121"));
//                            snack.setActionTextColor(Color.parseColor("#FFFFFF"));
//
//                            snack.show();
                        }
                    } else {
                        Toast.makeText(getActivity(), "Entry fee should not be greater than 1000", Toast.LENGTH_LONG).show();

                    }
                }
            }
        });
        // Create Contest button setOnClickListener
        return rootView;
    }

    private void Contest() {
        SharedPreferences prefs = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);

        String Password = prefs.getString("Password", null);
        String Usernames = prefs.getString("Name", null);
        String Loginid = prefs.getString("Loginid", null);


        final ContestRequestJson request = new ContestRequestJson();

        request.setLoginid(decrypt(good.key, Loginid));
        request.setNickname(decrypt(good.key, Name));
        request.setEntryfees(String.valueOf(entryfees));
        request.setWinningamount(String.valueOf(amount));
        request.setGameid(decrypt(good.key, Gameid));






        UserService service = ServiceGenerator.createService(UserService.class, decrypt(good.key, Usernames), decrypt(good.key, Password));
        service.contest(request).enqueue(new Callback<ContestResponseJson>() {
            @Override
            public void onResponse(Call<ContestResponseJson> call, Response<ContestResponseJson> response) {
                if (response.isSuccessful()) {


                    Contestid = response.body().Count;//////// Contest id///////
                    TotalWalletnew = response.body().Totalwallet;/////////////// wallet total amount




                    Contestid = encrypt(good.key, good.initVector, Contestid);
                    TotalWalletnew = encrypt(good.key, good.initVector, TotalWalletnew);

                    SharedPreferences sharedPreferenc = getActivity().getSharedPreferences("Count", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = sharedPreferenc.edit();
                    edit.putString("Count", "0");

                    edit.apply();

                    editText.setText("");
                    editText2.setText("");
                    if (TotalWalletnew != null && TotalWalletnew != "") {
                        SharedPreferences pref2 = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = pref2.edit();
                        editor1.putString("Totalwallet", TotalWalletnew);
                        editor1.apply();
                    }
                    SharedPreferences prefs = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);



                    GameLayout = decrypt(good.key, GameLayout);




                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("Totalwallet", TotalWalletnew);
                    editor.apply();
                    SharedPreferences prefs2 = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = prefs2.edit();
                    editor1.putString("Totalwallet", TotalWalletnew);
                    editor1.putString("Playstatus", "true");
                    editor1.apply();

////
                    if (GameName.equalsIgnoreCase("no_url")) {
                        SharedPreferences shear = getActivity().getSharedPreferences("fiftyss", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edits = shear.edit();
                        edits.putString("fifty", "true");
                        edits.apply();
                        Intent intent = new Intent(getActivity(), Activityquiz.class);
                        intent.putExtra("Entryfees", Entryfees);
                        intent.putExtra("Amount", amount);
                        intent.putExtra("Contestid", Contestid);
                        intent.putExtra("From", "Contest");///// this from desid to back
                        intent.putExtra("From1", "Contest");///// this from1 decide to which game play contest or challengers

                        startActivity(intent);
                        getActivity().finish();
                    } else {
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Count", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edits = sharedPreferences.edit();
                        edits.putString("Count", "0");
                        edits.apply();

                        if (GameLayout.equalsIgnoreCase("0")) {
                            Intent intent = new Intent(getActivity(), TextinActivity.class);
                            intent.putExtra("Contestid", Contestid);
                            intent.putExtra("From", "Contest");
                            startActivity(intent);
                        } else if (GameLayout.equalsIgnoreCase("1")) {
                            Intent intent = new Intent(getActivity(), TextingtwoActivity.class);
                            intent.putExtra("Contestid", Contestid);
                            intent.putExtra("From", "Contest");
                            startActivity(intent);
                        }


                    }
                } else {
                    switch (response.code()) {
                        case 401:
                            Toast.makeText(getActivity(), "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
                            Toast.makeText(getActivity(), "ForbiddenException", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
                            Toast.makeText(getActivity(), "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(getActivity(), "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(getActivity(), "unknown error", Toast.LENGTH_SHORT).show();
                            break;

                    }
//                    Toast.makeText(getActivity(), "Something error please try again", Toast.LENGTH_SHORT).show();
                }
                progressbar.dismiss();

            }

            @Override
            public void onFailure(Call<ContestResponseJson> call, Throwable t) {
                t.printStackTrace();


                Log.e("System error:", t.getLocalizedMessage());

            }
        });
    }



    @Override
    public void onResume() {
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {


                    Intent intent = new Intent(getActivity(), GametypesActivity.class);
                    intent.putExtra("goto", "challenger");
                    intent.putExtra("Totalcontest", Main2Activity.Totalcontest);
                    startActivity(intent);
                    getActivity().finish();
                    Dashboard.dashboard.finish();

                }
                return false;
            }
        });
        super.onResume();
    }


}
