package com.challengers.of.call;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.challengers.of.call.Banned_APP.bannedapp;
import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.History.HistoryResponseJson;
import com.challengers.of.call.Profiledata.ProfileRequestJson;
import com.challengers.of.call.Profiledata.ProfileResponseJson;
import com.challengers.of.call.Retrofit.ServiceGenerator;
import com.challengers.of.call.Retrofit.UserService;
import com.challengers.of.call.data.GetwalletHistory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.decrypt;
import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.encrypt;

public class wallethistory extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    public List<GetwalletHistory> productList;
    com.challengers.of.call.WalletHistoryadapter adapter;
    public SwipeRefreshLayout swipe_refresh_layout;
    public ListView listView;
    JSONObject jasonObject;
    JSONArray jsonArray = null;
    private static bannedapp bannedapps;
    String Loginid = "";
//    AsyncCallWS task;
    String From = "";
    private ljsflsj good;
    public  String Date, WalletType, Rupess, CreditDebit;

    @Override
    protected void onResume() {
        super.onResume();

        bannedapps = new bannedapp(wallethistory.this);
        bannedapps.bannedbig();
    }

    @Override
    protected void onPause() {
        super.onPause();

        bannedapps = new bannedapp(wallethistory.this);
        bannedapps.bannedbig();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallethistory);
        bannedapps = new bannedapp(wallethistory.this);
        bannedapps.bannedbig();
        swipe_refresh_layout =(SwipeRefreshLayout)findViewById(R.id.swipeToRefresh);
        swipe_refresh_layout.setOnRefreshListener(this);

        listView = (ListView)findViewById(R.id.listview);
        ImageView ivback = findViewById(R.id.ivback);
        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);


        Loginid = prefs.getString("Loginid", null);


        From = getIntent().getStringExtra("from");
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intent = new Intent(wallethistory.this, Profile.class);

                startActivity(intent);

                finish();
            }
        });
//        task = new AsyncCallWS();
//        task.execute();


        History();
    }



    @Override
    public void onBackPressed() {

            Intent intent = new Intent(wallethistory.this,  Profile.class);
//            intent.putExtra("from",From);
//            intent.putExtra("goto", "Mycontest");
//            intent.putExtra("Totalcontest", Main2Activity.Totalcontest);
            startActivity(intent);

        finish();
    }

    @Override
    public void onRefresh() {
        swipe_refresh_layout.setRefreshing(false);
    }
    private void History() {
        adapter = new WalletHistoryadapter(wallethistory.this, R.layout.list_wallet_result_row);
        adapter.notifyDataSetChanged();
        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String Loginid = prefs.getString("Loginid", null);
        String Password = prefs.getString("Password", null);
        String Usernames = prefs.getString("Name", null);

        final ProfileRequestJson request = new ProfileRequestJson();
        request.setLoginid(decrypt(good.key, Loginid));


        UserService service = ServiceGenerator.createService(UserService.class, decrypt(good.key, Usernames), decrypt(good.key, Password));
        service.history(request).enqueue(new Callback<HistoryResponseJson>() {
            @Override
            public void onResponse(Call<HistoryResponseJson> call, Response<HistoryResponseJson> response) {
                if (response.isSuccessful()) {
                    productList = new ArrayList<>();
                    if (productList.size() > 0){
                        productList.clear();
                    }
                    for (int z=0; z< response.body().data.size(); z++){

                        Date = response.body().data.get(z).Date;
                        WalletType = response.body().data.get(z).WalletType;
                        Rupess = response.body().data.get(z).Rupees;
                        CreditDebit = response.body().data.get(z).CreditDebit;

                        Date = encrypt(good.key, good.initVector, Date);
                        WalletType = encrypt(good.key, good.initVector, WalletType);
                        Rupess = encrypt(good.key, good.initVector, Rupess);
                        CreditDebit = encrypt(good.key, good.initVector, CreditDebit);

                        GetwalletHistory getwalletHistory = new GetwalletHistory();
                        getwalletHistory.setDate(Date);
                        getwalletHistory.setWalletType(WalletType);
                        getwalletHistory.setRupess(Rupess);
                        getwalletHistory.setCreditDebit(CreditDebit);

                        adapter.add(getwalletHistory);
                    }

                    if (response.body().data.size() > 0){
                        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        LayoutInflater inflater = getLayoutInflater();

//
                        listView.setAdapter(adapter);
                    }else {
                        Toast.makeText(wallethistory.this, "Please try again", Toast.LENGTH_LONG).show();
                    }



                } else {
                    switch (response.code()) {
                        case 401:
//                            Toast.makeText(wallethistory.this, "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
//                            Toast.makeText(wallethistory.this, "ForbiddenException", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
//                            Toast.makeText(wallethistory.this, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
//                            Toast.makeText(wallethistory.this, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
//                            Toast.makeText(wallethistory.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;

                    }

                    Toast.makeText(wallethistory.this, "Something error please try again", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<HistoryResponseJson> call, Throwable t) {
                t.printStackTrace();


                Log.e("System error:", t.getLocalizedMessage());

            }
        });

    }

//    public class AsyncCallWS extends AsyncTask<String, Void, Void> {
//        String displayText;
//        String Date, WalletType, Rupess, CreditDebit;
//        @Override
//        protected Void doInBackground(String... params) {
//
//
//            displayText = WebService.WalletHistory(Loginid, "Transactions");
//            productList = new ArrayList<>();
//            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                try {
//                    jasonObject = new JSONObject(displayText);
//                    jsonArray = jasonObject.getJSONArray("Response");
//
//
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        GetwalletHistory getwalletHistory = new GetwalletHistory();
//                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
//                        Date = jsonrowdata.getString("Date");
//                        WalletType = jsonrowdata.getString("WalletType");
//                        Rupess = jsonrowdata.getString("Rupees");
//                        CreditDebit = jsonrowdata.getString("CreditDebit");
////                        Timenew = jsonrowdata.getString("key");
////                        Totalpoint = jsonrowdata.getString("Totalpoint");
////                        Amount = Amount + Double.parseDouble(Totalpoint);
//                        Date = encrypt(good.key, good.initVector, Date);
//                        WalletType = encrypt(good.key, good.initVector, WalletType);
//                        Rupess = encrypt(good.key, good.initVector, Rupess);
//                        CreditDebit = encrypt(good.key, good.initVector, CreditDebit);
//
//
//                        getwalletHistory.setDate(Date);
//                        getwalletHistory.setWalletType(WalletType);
//                        getwalletHistory.setRupess(Rupess);
//                        getwalletHistory.setCreditDebit(CreditDebit);
//
//                        adapter.add(getwalletHistory);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//
//            if (displayText.equalsIgnoreCase("connection fault")) {
//                Toast.makeText(wallethistory.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
//            } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                Toast.makeText(wallethistory.this, "Please try after some times...", Toast.LENGTH_LONG).show();
//            } else if (jsonArray.length() > 0) {
//                SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = prefs.edit();
//                LayoutInflater inflater = getLayoutInflater();
//
//
////                ViewGroup header = (ViewGroup) inflater.inflate(R.layout.wallet_history_header, listView, false);
//
//
//
////                listView.addHeaderView(header);
////
//                listView.setAdapter(adapter);
//            } else {
//                Toast.makeText(wallethistory.this, "Please try again", Toast.LENGTH_LONG).show();
//            }
//        }
//
//        @Override
//        protected void onPreExecute() {
//            adapter = new WalletHistoryadapter(wallethistory.this, R.layout.list_wallet_result_row);
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//        }
//
//        @Override
//        protected void onCancelled() {
////            task.cancel(true);
////            super.onCancelled();
//        }
//    }


}
