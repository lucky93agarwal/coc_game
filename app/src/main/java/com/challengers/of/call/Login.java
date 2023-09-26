package com.challengers.of.call;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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

import com.agrawalsuneet.dotsloader.loaders.RotatingCircularDotsLoader;
import com.challengers.of.call.Banned_APP.bannedapp;
import com.challengers.of.call.DeviceUtils.AESUtils;
import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.Retrofit.ServiceGenerator;
import com.challengers.of.call.Retrofit.UserService;
import com.challengers.of.call.Signindata.FBLoginRequestJson;
import com.challengers.of.call.Signindata.FBLoginResponseJson;
import com.challengers.of.call.Signindata.LoginRequestJson;
import com.challengers.of.call.Signindata.LoginResponseJson;
import com.challengers.of.call.SplashData.VersionResponseJson;
import com.challengers.of.call.testing.SettingsPreferences;
import com.challengers.of.call.testing.StaticUtils;
import com.challengers.of.call.testing.Utils;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.jar.Attributes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.decrypt;
import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.encrypt;
import static com.challengers.of.call.Setting_Popup.settingpopup.editor;

public class Login extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    String Totalcontest, Status;
    private String Gender, Role, Dateofbirth, Playstatus, Imageurl, Totalwallet, Totallife, UserRemFreeLife, Message, Token;
    private static bannedapp bannedapps;
    String Username, Password, Sponsorid, mobile;
    //google plus login
    private static final int RC_SIGN_IN = 007;
    public static Activity login;
    JSONObject jasonObject;
    JSONArray jsonArray = null;
    public String Name, Email, Action;
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private AccessToken accessToken;
    private GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;
    private SignInButton btnSignIn;
    public static String facebookuri = "";

    public Dialog epicDialog; //Popup Dialog Box
    public String object;
    //Popup Dialog Box
    public TextView tvlifepupup;
    public ImageView closepopupbtn;
    public TextView popupyesbtn;
    public static Context context;
    public String Gameid;
    public String displayText, Userid;

    ///// new page
    EditText editText, editText2;

    private Vibrator vibrator;
    public Dialog unepicDialog, progressbar,closeapp;
    ///// new page
    public String IMEINumber;

    private ljsflsj good;

    @Override
    protected void onResume() {
        super.onResume();

        bannedapps = new bannedapp(Login.this);
        bannedapps.bannedbig();
    }

    @Override
    protected void onPause() {
        super.onPause();

        bannedapps = new bannedapp(Login.this);
        bannedapps.bannedbig();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = getApplicationContext();


        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(Login.this, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                Token = instanceIdResult.getToken();

            }

        });
        closeapp = new Dialog(this, R.style.PauseDialog);

        bannedapps = new bannedapp(Login.this);
        bannedapps.bannedbig();

        progressbar = new Dialog(this);
        SharedPreferences prefs = getSharedPreferences("UserDatasss", Context.MODE_PRIVATE);

        IMEINumber = prefs.getString("IMEI", null);

        Button btnlogin = findViewById(R.id.btnlogin);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.buttonanimation);

        //// new page
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        Button btn = findViewById(R.id.btnsignup);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editText = findViewById(R.id.editText);
                editText2 = findViewById(R.id.editText2);
                if (editText.length() == 0) {
                    Toast.makeText(Login.this, "Enter Mobile No./ Email Id", Toast.LENGTH_LONG).show();
                    if (SettingsPreferences.getSoundEnableDisable(context)) {
                        StaticUtils.setwronAnssound(context);
                    }
                    if (SettingsPreferences.getVibration(context)) {
                        StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                    }
                } else if (editText2.length() == 0) {
                    Toast.makeText(Login.this, "Enter Password", Toast.LENGTH_LONG).show();
                    if (SettingsPreferences.getVibration(context)) {
                        StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                    }
                    if (SettingsPreferences.getSoundEnableDisable(context)) {
                        StaticUtils.setwronAnssound(context);
                    }
                } else {

                    if (Utils.isNetworkAvailable(Login.this)) {
//                        AsyncCallWSSS task = new AsyncCallWSSS();
//                        task.execute();
                        progressbar.setContentView(R.layout.progresbarlayout);
                        progressbar.setCancelable(false);
                        RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);

                        RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Login.this,
                                20, 60, ContextCompat.getColor(Login.this, R.color.white));
                        loader.setAnimDuration(3000);

                        rotatingCircularDotsLoader.addView(loader);

                        progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
                        progressbar.show();

                        MenualLogin();
                        if (SettingsPreferences.getVibration(context)) {
                            StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                        }
                        if (SettingsPreferences.getSoundEnableDisable(context)) {
                            StaticUtils.backSoundonclick(context);
                        }
                    } else {
                        Toast.makeText(Login.this, "No internet connection!", Toast.LENGTH_LONG).show();
                        if (SettingsPreferences.getVibration(context)) {
                            StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                        }
                        if (SettingsPreferences.getSoundEnableDisable(context)) {
                            StaticUtils.setwronAnssound(context);
                        }
                    }


                    view.startAnimation(myAnim);
                    if (SettingsPreferences.getVibration(context)) {
                        StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                    }
                    if (SettingsPreferences.getSoundEnableDisable(context)) {
                        StaticUtils.backSoundonclick(context);
                    }

                }
            }
        });
        TextView txtsignup = findViewById(R.id.txtsignup);

        TextView txtforget = findViewById(R.id.txtforget);
        txtforget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }
                Intent intent = new Intent(Login.this, Frogetpassword.class);
                startActivity(intent);
                finish();

            }
        });
        txtsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }
                view.startAnimation(myAnim);
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
                finish();
            }
        });
        ///// new page


        login = this;

        Button btnsignup = findViewById(R.id.btnsignups);
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
                view.startAnimation(myAnim);
                overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
            }
        });


        final LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);

////////////////////////////////////////////////////////////////facebook login//////////////////////////////////////////////////////////////////////////////////////////
        Button imageView = findViewById(R.id.ivfb);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Utils.isNetworkAvailable(Login.this)) {
                    loginButton.performClick();
                    if (SettingsPreferences.getVibration(context)) {
                        StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                    }
                    if (SettingsPreferences.getSoundEnableDisable(context)) {
                        StaticUtils.backSoundonclick(context);
                    }
                } else {
                    Toast.makeText(Login.this, "No internet connection!", Toast.LENGTH_LONG).show();
                    if (SettingsPreferences.getVibration(context)) {
                        StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                    }
                    if (SettingsPreferences.getSoundEnableDisable(context)) {
                        StaticUtils.setwronAnssound(context);
                    }
                }

                view.startAnimation(myAnim);
            }
        });
////////////////////////////////////////////////////////////////facebook login//////////////////////////////////////////////////////////////////////////////////////////

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);


        callbackManager = CallbackManager.Factory.create();
        //register access token to check whether user logged in before
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {
                accessToken = newToken;
            }
        };
        // Callback registration

////////////////////////////////////////////////////////////////facebook login//////////////////////////////////////////////////////////////////////////////////////////
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //Once authorized from facebook will directly go to MainActivity
                setFacebookData(loginResult);

            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
        ////////////////////////////////////////////////////////////////facebook login//////////////////////////////////////////////////////////////////////////////////////////

        //Set permission to use in this app
        List<String> permissionNeeds = Arrays.asList("public_profile", "email");
        loginButton.setReadPermissions(permissionNeeds);

        accessTokenTracker.startTracking();


        //google plus login

        btnSignIn = (SignInButton) findViewById(R.id.btn_sign_in);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        btnSignIn.setSize(SignInButton.SIZE_STANDARD);
        btnSignIn.setScopes(gso.getScopeArray());

        ////// google login
        Button imggoogleplus = findViewById(R.id.gplus);
        imggoogleplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Utils.isNetworkAvailable(Login.this)) {
                    signIn();
                    if (SettingsPreferences.getVibration(context)) {
                        StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                    }
                    if (SettingsPreferences.getSoundEnableDisable(context)) {
                        StaticUtils.backSoundonclick(context);
                    }
                } else {
                    Toast.makeText(Login.this, "No internet connection!", Toast.LENGTH_LONG).show();
                    if (SettingsPreferences.getVibration(context)) {
                        StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                    }
                    if (SettingsPreferences.getSoundEnableDisable(context)) {
                        StaticUtils.setwronAnssound(context);
                    }
                }

                view.startAnimation(myAnim);
            }
        });

        ////// google login

    }

    private void MenualLogin() {

        Userid = editText.getText().toString().trim();
        LoginRequestJson request = new LoginRequestJson();
        request.setEmail(editText.getText().toString().trim());
        request.setPassword(editText2.getText().toString().trim());
        request.setImei(decrypt(good.key, IMEINumber));
        request.setToken(Token);


        UserService service = ServiceGenerator.createService(UserService.class, request.getEmail(), request.getPassword());
            service.signin(request).enqueue(new Callback<LoginResponseJson>() {
            @Override
            public void onResponse(Call<LoginResponseJson> call, Response<LoginResponseJson> response) {
                if (response.isSuccessful()) {
                    Message = response.body().message;


                    for (int z = 0; z < response.body().data.size(); z++) {


                        Username = response.body().data.get(z).usernames;

                        Name = response.body().data.get(z).name;/////////////////////// name
                        Email = response.body().data.get(z).email;////////////////// email
                        UserRemFreeLife = response.body().data.get(z).userRFreeLife;

                        Role = response.body().data.get(z).role;
                        Gender = response.body().data.get(z).gender;
                        Dateofbirth = response.body().data.get(z).dateofbirth;
                        Sponsorid = response.body().data.get(z).sponsorid;
                        Playstatus = response.body().data.get(z).playstatus;
                        Totalcontest = response.body().data.get(z).Totalcontest;
                        Imageurl = response.body().data.get(z).Imageurl;
                        Totalwallet = response.body().data.get(z).Totalwallet;
                        Totallife = response.body().data.get(z).Totallife;
                        Gameid = response.body().data.get(z).userid;

                        Username = encrypt(good.key, good.initVector, Username);
                        Name = encrypt(good.key, good.initVector, Name);
                        Email = encrypt(good.key, good.initVector, Email);
                        UserRemFreeLife = encrypt(good.key, good.initVector, UserRemFreeLife);
                        Role = encrypt(good.key, good.initVector, Role);
                        Gender = encrypt(good.key, good.initVector, Gender);
                        Dateofbirth = encrypt(good.key, good.initVector, Dateofbirth);
                        Sponsorid = encrypt(good.key, good.initVector, Sponsorid);
                        Playstatus = encrypt(good.key, good.initVector, Playstatus);
                        Totalcontest = encrypt(good.key, good.initVector, Totalcontest);
                        Imageurl = encrypt(good.key, good.initVector, Imageurl);
                        Totalwallet = encrypt(good.key, good.initVector, Totalwallet);
                        Totallife = encrypt(good.key, good.initVector, Totallife);
                        Gameid = encrypt(good.key, good.initVector, Gameid);

                    }


                    if (Message.equalsIgnoreCase("banned")) {
                        Toast.makeText(Login.this, "Due to unusual activities your account has been blocked.", Toast.LENGTH_LONG).show();
                    } else if (Message.equalsIgnoreCase("notexist")) {
                        Toast.makeText(Login.this, "Incorrect Credential", Toast.LENGTH_LONG).show();
                    } else if (Message.equalsIgnoreCase("imei")) {
                        Toast.makeText(Login.this, "Only one account can be logged in a device.", Toast.LENGTH_LONG).show();
                    } else {
                        SharedPreferences lucky = getSharedPreferences("coding", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = lucky.edit();
                        edit.putString("userid", Gameid);
                        edit.apply();
//                Toast.makeText(Signin.this, Gameid, Toast.LENGTH_LONG).show();

                        Username = decrypt(good.key, Username);

                        Name = decrypt(good.key, Name);
                        Email = decrypt(good.key, Email);


                        Username = encrypt(good.key, good.initVector, Username);
                        Name = encrypt(good.key, good.initVector, Name);
                        Email = encrypt(good.key, good.initVector, Email);
                        String COC = encrypt(good.key, good.initVector, "COC");

                        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("Loginid", Username);
                        editor.putString("Mobile", Userid);
                        editor.putString("UserRemFreeLife", UserRemFreeLife);
                        editor.putString("Name", Name);
                        editor.putString("Password", encrypt(good.key, good.initVector, editText2.getText().toString().trim()));
                        editor.putString("Sponsorid", Sponsorid);
                        editor.putString("Playstatus", Playstatus);
                        editor.putString("Gender", Gender);
                        editor.putString("Totalwallet", Totalwallet);
                        editor.putString("Totallife", Totallife);
                        editor.putString("From", COC);
                        editor.putString("DOB", Dateofbirth);
                        editor.apply();

                        Dateofbirth = decrypt(good.key, Dateofbirth);
//                        if (Dateofbirth.equalsIgnoreCase("True")) {
                        SharedPreferences pref2 = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = pref2.edit();
                        editor1.putString("Playstatus", Playstatus);

                        Imageurl = decrypt(good.key, Imageurl);
                        if (Imageurl != "" && Imageurl != null) {

                            Imageurl = encrypt(good.key, good.initVector, Imageurl);
                            editor1.putString("Imageurl", Imageurl);
                        }
                        editor1.apply();

                        vibrator.vibrate(70);
                        Intent intent = new Intent(Login.this, Dashboard.class);

                        intent.putExtra("Totalcontest", Totalcontest);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
                        finish();

                        Toast.makeText(Login.this, "Logged In", Toast.LENGTH_LONG).show();
                        Login.login.finish();


                    }


                } else {
                    switch (response.code()) {
                        case 401:
//                            Toast.makeText(Login.this, "email and pass not check= " + response.errorBody().toString(), Toast.LENGTH_LONG).show();
                            break;
                        case 403:
//                            Toast.makeText(Login.this, "ForbiddenException", Toast.LENGTH_LONG).show();
                            break;
                        case 404:
//                            Toast.makeText(Login.this, "not found", Toast.LENGTH_LONG).show();
                            break;
                        case 500:
//                            Toast.makeText(Login.this, "server broken", Toast.LENGTH_LONG).show();
                            break;
                        default:
//                            Toast.makeText(Login.this, "unknown error", Toast.LENGTH_LONG).show();
                            break;
                    }
//                    Toast.makeText(Login.this, response.code(), Toast.LENGTH_LONG).show();
                    Toast.makeText(Login.this, "Something error please try again", Toast.LENGTH_SHORT).show();

                }
                progressbar.dismiss();
            }

            @Override
            public void onFailure(Call<LoginResponseJson> call, Throwable t) {
                t.printStackTrace();


                Log.e("System error:", t.getLocalizedMessage());

            }
        });
    }


    ////// google login
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    ////// google login

    ////// google login
    private void signIn() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient); ////// यदि आप चाहते है कि google login के समय हर बार आप से account select करने का पुछे तो

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    ////// google login

    ////////////////////////////////////////////////////////////////facebook login//////////////////////////////////////////////////////////////////////////////////////////
    private void setFacebookData(final LoginResult loginResult) {
        GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        // Application code
                        try {

                            Log.i("Response", response.toString());
                            Profile profile = Profile.getCurrentProfile();
                            //Email = profile.getId();
                            Email = response.getJSONObject().getString("email");

                            String email = object.getString("email");
                            Name = response.getJSONObject().getString("first_name");

                            final String lastName = response.getJSONObject().getString("last_name");
//                            String id = profile.getId();
                            // String link = profile.getLinkUri().toString();
                            // Log.i("Link", link);
                            String facebookuria = "";
                            if (Profile.getCurrentProfile() != null) {
                                Log.i("Login", "ProfilePic" + Profile.getCurrentProfile().getProfilePictureUri(200, 200));
                                facebookuri = Profile.getCurrentProfile().getProfilePictureUri(200, 200).toString();
                                facebookuri = facebookuri;

                            }
                            Log.i("Login" + "Email", Email);
                            Log.i("Login" + "FirstName", Name);


                            Action = "facebook";

                            progressbar.setContentView(R.layout.progresbarlayout);
                            progressbar.setCancelable(false);
                            RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);

                            RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Login.this,
                                    20, 60, ContextCompat.getColor(Login.this, R.color.white));
                            loader.setAnimDuration(3000);

                            rotatingCircularDotsLoader.addView(loader);

                            progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
                            progressbar.show();
                            fb_google_login();

//                            runOnUiThread(new Runnable() {
//                                public void run() {
//                            Toast.makeText(Login.this,email+firstName+lastName,Toast.LENGTH_LONG).show();
//                                }
//                            });


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,email,first_name,last_name,gender");
        request.setParameters(parameters);
        request.executeAsync();
    }
    ////////////////////////////////////////////////////////////////facebook login//////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override
    public void onStart() {
        super.onStart();
//        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
//        if (opr.isDone()) {
//            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
//            // and the GoogleSignInResult will be available instantly.
//
//            GoogleSignInResult result = opr.get();
//            handleSignInResult(result);
//        } else {
//            showProgressDialog();
//            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
//                @Override
//                public void onResult(GoogleSignInResult googleSignInResult) {
//                    hideProgressDialog();
//                    handleSignInResult(googleSignInResult);
//                }
//            });
//        }
    }

    private void handleSignInResult(GoogleSignInResult result) {

        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();


            Name = acct.getDisplayName();


            if (acct.getPhotoUrl() != null) {
                String personPhotoUrl = acct.getPhotoUrl().toString();

                facebookuri = personPhotoUrl;

            }
            Email = acct.getEmail();


            Action = "google";


//            AsyncCallWS task = new AsyncCallWS();
//            task.execute();
            progressbar.setContentView(R.layout.progresbarlayout);
            progressbar.setCancelable(false);
            RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);

            RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Login.this,
                    20, 60, ContextCompat.getColor(Login.this, R.color.white));
            loader.setAnimDuration(3000);

            rotatingCircularDotsLoader.addView(loader);

            progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
            progressbar.show();

            fb_google_login();


        } else {

            Toast.makeText(login, "data error= "+result.getStatus().getStatusMessage(), Toast.LENGTH_LONG).show();
            // Signed out, show unauthenticated UI.
            //updateUI(false);
        }
    }

    private void fb_google_login() {
        final FBLoginRequestJson request = new FBLoginRequestJson();
        request.setEmail(Email);
        request.setName(Name);
        request.setAction(Action);
        request.setFacebookuri(facebookuri);
        request.setGender(Gender);
        request.setImei(decrypt(good.key, IMEINumber));
        request.setToken(Token);
        request.setPassword(Name);

        Log.d("walletwalletssss","Email = "+ Email);
        Log.d("walletwalletssss","Name = "+ Name);
        Log.d("walletwalletssss","Action = "+ Action);
        Log.d("walletwalletssss","facebookuri = "+ facebookuri);

        Log.d("walletwalletssss","Gender = "+ Gender);
        Log.d("walletwalletssss","IMEINumber = "+ decrypt(good.key, IMEINumber));
        Log.d("walletwalletssss","Token = "+ Token);
        Log.d("walletwalletssss","Name = "+ Name);

        Action = encrypt(good.key, good.initVector, Action);
        facebookuri = encrypt(good.key, good.initVector, facebookuri);


        UserService service = ServiceGenerator.createService(UserService.class, request.getEmail(), request.getName());

        service.fblogin(request).enqueue(new Callback<FBLoginResponseJson>() {
            @Override
            public void onResponse(Call<FBLoginResponseJson> call, Response<FBLoginResponseJson> response) {
                if (response.isSuccessful()) {
                    Status = response.body().message;
                    Log.d("walletwalletssss","Status= "+ Status);


                    if (Status.equalsIgnoreCase("banned")) {
                        SharedPreferences lucky = getSharedPreferences("coding", Context.MODE_PRIVATE);
                        lucky.edit().clear().commit();
                        SharedPreferences settings = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                        settings.edit().clear().commit();
                        SharedPreferences pref2 = getSharedPreferences("UserChallengeData", Context.MODE_PRIVATE);
                        pref2.edit().clear().commit();
                        Toast.makeText(Login.this, "Due to unusual activities your account has been blocked.", Toast.LENGTH_LONG).show();

                    }
                    else if (Status.equalsIgnoreCase("imei")) {
                        SharedPreferences lucky = getSharedPreferences("coding", Context.MODE_PRIVATE);
                        lucky.edit().clear().commit();
                        SharedPreferences settings = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                        settings.edit().clear().commit();
                        SharedPreferences pref2 = getSharedPreferences("UserChallengeData", Context.MODE_PRIVATE);
                        pref2.edit().clear().commit();
                        Toast.makeText(Login.this, "Only one account can be logged in a device.", Toast.LENGTH_LONG).show();

                    }
                    else {
                        SharedPreferences pref2 = getSharedPreferences("UserChallengeData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = pref2.edit();
                        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        if (Status.equalsIgnoreCase("alreadyregistered")) {


                            for (int z = 0; z < response.body().data.size(); z++) {


                                Username = response.body().data.get(z).usernames;


                                Name = response.body().data.get(z).name;
                                Email = response.body().data.get(z).email;
                                Role = response.body().data.get(z).role;
                                Gender = response.body().data.get(z).gender;
                                Dateofbirth = response.body().data.get(z).dateofbirth;
                                Sponsorid = response.body().data.get(z).sponsorid;
                                Playstatus = response.body().data.get(z).playstatus;
                                Totalcontest = response.body().data.get(z).Totalcontest;
                                Imageurl = response.body().data.get(z).Imageurl;
                                Totalwallet = response.body().data.get(z).Totalwallet;
                                Totallife = response.body().data.get(z).Totallife;
                                Gameid = response.body().data.get(z).userid;


//                        System.out.print("Email-"+Email);

                                Username = encrypt(good.key, good.initVector, Username);
                                Name = encrypt(good.key, good.initVector, Name);
                                Email = encrypt(good.key, good.initVector, Email);
                                Role = encrypt(good.key, good.initVector, Role);
                                Gender = encrypt(good.key, good.initVector, Gender);
                                Dateofbirth = encrypt(good.key, good.initVector, Dateofbirth);
                                Sponsorid = encrypt(good.key, good.initVector, Sponsorid);
                                Playstatus = encrypt(good.key, good.initVector, Playstatus);
                                Totalcontest = encrypt(good.key, good.initVector, Totalcontest);
                                Imageurl = encrypt(good.key, good.initVector, Imageurl);
                                Totalwallet = encrypt(good.key, good.initVector, Totalwallet);
                                Totallife = encrypt(good.key, good.initVector, Totallife);
                                Gameid = encrypt(good.key, good.initVector, Gameid);


                            }


                            editor1.putString("Loginid", Username);
                            editor.putString("Loginid", Username);
                            Intent intent3 = new Intent(Login.this, Dashboard.class);
                            intent3.putExtra("Mobile", Email);
                            intent3.putExtra("Totalcontest", Totalcontest);
                            overridePendingTransition(R.anim.slide_from_right, R.anim.slideout_from_left);
                            startActivity(intent3);
                            finish();


                            SharedPreferences lucky = getSharedPreferences("coding", Context.MODE_PRIVATE);
                            SharedPreferences.Editor edit = lucky.edit();
                            edit.putString("userid", Gameid);
                            edit.apply();


                            editor.putString("Name", Name);

                            editor.putString("Mobile", Email);
                            editor.putString("From", Action);
                            editor.putString("Password", Name);
                            editor.putString("Totallife", Totallife);
                            editor.putString("Totalwallet", Totalwallet);
                            editor.putString("Playstatus", Playstatus);
                            editor.putString("Sponsorid", Sponsorid);
                            editor.putString("Gender", Gender);
                            editor.putString("DOB", Dateofbirth);

                            editor.putString("SocialName", Name);
                            editor.putString("SocialPassword", Name);
                            editor.putString("SocialAction", Action);
                            editor.putString("SocialEmail", Email);
                            editor.putString("SocialRole", "User");
                            editor.putString("SocialGender", Gender);
                            editor.putString("SocialImageURL", facebookuri);
                            Action = decrypt(good.key, Action);
                            if (Action.equalsIgnoreCase("facebook") || Action.equalsIgnoreCase("google")) {
                                Imageurl = decrypt(good.key, Imageurl);

                                if (Imageurl.length() > 30) {
                                    Imageurl = encrypt(good.key, good.initVector, Imageurl);
                                    editor.putString("Profilepic", Imageurl);
                                    editor.putString("Imageurl", null);


                                } else {
                                    Imageurl = encrypt(good.key, good.initVector, Imageurl);
                                    editor.putString("Imageurl", Imageurl);
                                    editor.putString("Profilepic", null);

                                }


                            }


                            editor1.putString("Mobile", Email);


                            editor1.apply();
                            editor.apply();


                        } else if (Status.equalsIgnoreCase("noimei")) {
                            Toast.makeText(Login.this, "Only one account can be logged in a device.", Toast.LENGTH_LONG).show();
                        } else {


                            Username = encrypt(good.key, good.initVector, Username);
                            Name = encrypt(good.key, good.initVector, Name);
                            Email = encrypt(good.key, good.initVector, Email);
                            Gender = encrypt(good.key, good.initVector, Gender);
                            Imageurl = encrypt(good.key, good.initVector, Imageurl);


                            SharedPreferences lucky = getSharedPreferences("coding", Context.MODE_PRIVATE);
                            SharedPreferences.Editor edit = lucky.edit();
                            edit.putString("userid", Gameid);
                            edit.apply();


                            editor.putString("Name", Name);

                            editor.putString("Mobile", Email);
                            editor.putString("From", Action);
                            editor.putString("Password", Name);


                            editor.putString("Gender", Gender);


                            editor.putString("SocialName", Name);
                            editor.putString("SocialPassword", Name);

                            editor.putString("SocialEmail", Email);

                            editor.putString("SocialGender", Gender);
                            editor.putString("SocialImageURL", facebookuri);
                            Action = decrypt(good.key, Action);


                            editor1.putString("Mobile", Email);


                            editor1.apply();
                            editor.apply();


                            Intent intent2 = new Intent(Login.this, socialcode.class);
                            intent2.putExtra("Mobile", Email);
                            intent2.putExtra("Loginid", Username);
                            intent2.putExtra("Totalcontest", "0");
                            overridePendingTransition(R.anim.slide_from_right, R.anim.slideout_from_left);
                            startActivity(intent2);
                            finish();


                        }


                    }

                }
                else {
                    switch (response.code()) {
                        case 401:
//                            Toast.makeText(Login.this, "email and pass not check= " + response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
//                            Toast.makeText(Login.this, "ForbiddenException", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
//                            Toast.makeText(Login.this, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
//                            Toast.makeText(Login.this, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
//                            Toast.makeText(Login.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    Toast.makeText(Login.this, "Something error please try again", Toast.LENGTH_SHORT).show();
                }
                progressbar.dismiss();
            }

            @Override
            public void onFailure(Call<FBLoginResponseJson> call, Throwable t) {
                t.printStackTrace();


                Log.e("System error:", t.getLocalizedMessage());

            }
        });

    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Loading");
            mProgressDialog.setIndeterminate(true);
        } else {
            mProgressDialog.show();
        }
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }


    @Override
    public void onBackPressed() {
        closeapp.setContentView(R.layout.appclose);
        Button okbtn = (Button) closeapp.findViewById(R.id.okyes);
        Button nobtn = (Button) closeapp.findViewById(R.id.closePopup);
        nobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }

                closeapp.dismiss();
            }
        });
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }

                Intent a = new Intent(Intent.ACTION_MAIN);
                a.addCategory(Intent.CATEGORY_HOME);
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(a);
                System.exit(0);
                finish();

                closeapp.dismiss();
            }
        });

        closeapp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
        closeapp.show();
    }
}
