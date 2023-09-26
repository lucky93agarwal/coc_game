package com.challengers.of.call.Banned_APP;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.challengers.of.call.Add_Money_Popup.addmoneypopup;
import com.challengers.of.call.BannedData.GetBannedResponseJson;
import com.challengers.of.call.Profile;
import com.challengers.of.call.QuestionWisResult.QuestionWisResultRequestJson;
import com.challengers.of.call.R;
import com.challengers.of.call.Retrofit.ServiceGenerator;
import com.challengers.of.call.Retrofit.UserService;
import com.challengers.of.call.Splash;
import com.challengers.of.call.SplashData.VersionRequestJson;
import com.challengers.of.call.adapter.HLVAdapter;
import com.challengers.of.call.data.GetDataBannedApp;

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
import org.json.JSONObject;

import java.io.IOException;
import java.security.Signature;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class bannedapp {
    public Button btn, btnok;
    private final Context _context;
    public Dialog banneddata, progressbar;
    String DataParseUrl = "http://callofchallengers.com/coc/api/trapi.php";
    String resTxt = null;
    private String PackageName, AppName;

    /// it for RecyclerView
    public List<GetDataBannedApp> productList = new ArrayList<>();
    public GetDataBannedApp getbcdata;
    public HLVAdapter adapter;
    public RecyclerView mRecyclerView;
    public RecyclerView.LayoutManager mLayoutManager;
    public String LIST_STATE_KEY = "list_state";
    /// it for RecyclerView
    private Boolean returntype = false;
    public String IMEINumber;


    public bannedapp(Context context) {
        this._context = context;
        progressbar = new Dialog(context);
        banneddata = new Dialog(context, R.style.PauseDialog);

    }


    public boolean bannedbig() {

        SendDataToServer();

//        Intent intent99 = _context.getPackageManager().getLaunchIntentForPackage("com.truedevelopersstudio.automatictap.autoclicker");
//        if (intent99 != null) {
//
//            return true;
//
//        } else {
//
//
        return returntype;
//        }


    }
    private void SendDataToServer() {
        IMEINumber = Settings.Secure.getString(_context.getContentResolver(), Settings.Secure.ANDROID_ID);
        final bannedRequestJson request = new bannedRequestJson();
        request.setLoginid(IMEINumber);


        UserService userService = ServiceGenerator.createService(UserService.class, null, null);
        userService.getBanned(request).enqueue(new Callback<GetBannedResponseJson>() {
            @Override
            public void onResponse(Call<GetBannedResponseJson> call, Response<GetBannedResponseJson> response) {
                if (response.isSuccessful()) {

                    Intent intent88 = _context.getPackageManager().getLaunchIntentForPackage("com.whatsapp");

                    if (intent88 !=null ){
                        for (int z=0; z<response.body().data.size(); z++){
                            PackageName = response.body().data.get(z).packageName;
                            AppName = response.body().data.get(z).gameName;


                            Intent intent99 = _context.getPackageManager().getLaunchIntentForPackage(PackageName);




                            if (intent99 != null) {

                                getbcdata = new GetDataBannedApp();
                                getbcdata.setAppname(AppName);
                                getbcdata.setPackagename(PackageName);
                                productList.add(getbcdata);

                            }
                        }

                        if (productList.size() > 0) {

                            try {
                                banneddata.setContentView(R.layout.bannedlayouttwo);
                                banneddata.setCancelable(false);


                                btn = (Button) banneddata.findViewById(R.id.button4);/////////////////////////////////////////////////////////////////////////////////////////
                                TextView tvappdata = (TextView)banneddata.findViewById(R.id.appdata);
                                String item = "";
                                for (int z = 0; z < productList.size(); z++) {
                                    item = item + productList.get(z).getAppname();
                                    item = item + "\n";
                                    tvappdata.setText(item);

                                }


                                btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        android.os.Process.killProcess(android.os.Process.myPid());
                                        System.exit(1);
                                        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                                        homeIntent.addCategory(Intent.CATEGORY_HOME);
                                        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        _context.startActivity(homeIntent);
                                        banneddata.dismiss();
                                    }
                                });
                                banneddata.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
                                banneddata.show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            returntype = true;
                        } else {
                            returntype = false;
                        }
                    }else {

                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }


                } else {
                    switch (response.code()) {
                        case 401:
//                            Toast.makeText(_context, "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
//                            Toast.makeText(_context, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
//                            Toast.makeText(_context, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
//                            Toast.makeText(_context, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    Toast.makeText(_context, "Something error please try again", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<GetBannedResponseJson> call, Throwable t) {
                Log.e("System error:", t.getLocalizedMessage());
            }
        });
    }
}
