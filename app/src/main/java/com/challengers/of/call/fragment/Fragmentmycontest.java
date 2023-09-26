package com.challengers.of.call.fragment;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.RotatingCircularDotsLoader;
import com.challengers.of.call.Activityquiz;
import com.challengers.of.call.Banned_APP.bannedapp;
import com.challengers.of.call.Contest.ContestRequestJson;
import com.challengers.of.call.Contest.ContestResponseJson;
import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.MyContestAPI.MycontestRequestJson;
import com.challengers.of.call.MyContestAPI.MycontestResponseJson;
import com.challengers.of.call.R;
import com.challengers.of.call.Retrofit.ServiceGenerator;
import com.challengers.of.call.Retrofit.UserService;
import com.challengers.of.call.TextinActivity;
import com.challengers.of.call.WebService;
import com.challengers.of.call.adapter.HLVAdapter;
import com.challengers.of.call.adapter.Mycontestadapter;
import com.challengers.of.call.data.Getmycontestdata;
import com.challengers.of.call.testing.TextingtwoActivity;
import com.kaopiz.kprogresshud.KProgressHUD;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.decrypt;
import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.encrypt;

public class Fragmentmycontest extends Fragment {
    public static String[] Arrcontestid;
    public ArrayList<Getmycontestdata> productList;
    JSONObject jasonObject;
    JSONArray jsonArray = null;
    private static bannedapp bannedapps;
    Mycontestadapter adapter;
    RecyclerView.LayoutManager mLayoutManager;
    Getmycontestdata getcontestdata;
    LinearLayout l1;
    private ljsflsj good;
//    AsyncCallWS task;
    private RecyclerView mRecyclerView;
    public String GameName, Gameid;
    String Contestid, Contestname, Entryfees, Winningamount, Expiry, Status, Chassing, Count, Result, refend, Img, gamename;
    public Dialog unepicDialog, progressbar, information;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView;
        rootView = inflater.inflate(R.layout.fragment_mycontest, container, false);
        mRecyclerView = rootView.findViewById(R.id.recyclerview);
        mRecyclerView.setNestedScrollingEnabled(false);
        bannedapps = new bannedapp(getActivity());
        bannedapps.bannedbig();
        progressbar = new Dialog(getActivity());
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("coding", Context.MODE_PRIVATE);
        GameName = sharedPreferences.getString("gametype",null);




        Bindcontest();
        return rootView;
    }

    public void Refresh(int position) {

    }

    public void Bindcontest() {
//        task = new AsyncCallWS();
//        task.execute();

        progressbar.setContentView(R.layout.progresbarlayout);
        progressbar.setCancelable(false);
        RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader)progressbar.findViewById(R.id.progress);

        RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(getActivity(),
                20, 60, ContextCompat.getColor(getActivity(), R.color.white));
        loader.setAnimDuration(3000);

        rotatingCircularDotsLoader.addView(loader);

        progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
        progressbar.show();

        MyContestAPI();

    }

    private void MyContestAPI() {
        SharedPreferences prefs = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);

        String Password = prefs.getString("Password", null);
        String Usernames = prefs.getString("Name", null);
        String Loginid = prefs.getString("Loginid", null);


        final MycontestRequestJson request = new MycontestRequestJson();
        request.setLoginid(decrypt(good.key, Loginid));

        UserService service = ServiceGenerator.createService(UserService.class, decrypt(good.key, Usernames), decrypt(good.key, Password));
        service.mycontest(request).enqueue(new Callback<MycontestResponseJson>() {
            @Override
            public void onResponse(Call<MycontestResponseJson> call, Response<MycontestResponseJson> response) {
                if (response.isSuccessful()) {
                    productList = new ArrayList<>();
                    if (productList.size() > 0){
                        productList.clear();
                    }

                    Arrcontestid = new String[response.body().data.size()];
                    for (int z=0; z<response.body().data.size(); z++){

                        Contestid = response.body().data.get(z).Contestid;
                        Contestname = response.body().data.get(z).Contestname;
                        Entryfees = response.body().data.get(z).Entryfees;
                        Winningamount = response.body().data.get(z).Winningamount;
                        Expiry = response.body().data.get(z).Expiry;
                        Status = response.body().data.get(z).Status;
                        Chassing = response.body().data.get(z).Chasing;
                        Count = response.body().data.get(z).Count;
                        Result = response.body().data.get(z).Result;
                        refend = response.body().data.get(z).refund;
                        Img = response.body().data.get(z).gameimg;
                        gamename = response.body().data.get(z).gamename;

                        Arrcontestid[z] = Contestid;


                        Contestid = encrypt(good.key, good.initVector, Contestid);
                        Contestname = encrypt(good.key, good.initVector, Contestname);
                        Entryfees = encrypt(good.key, good.initVector, Entryfees);
                        Winningamount = encrypt(good.key, good.initVector, Winningamount);
                        Expiry = encrypt(good.key, good.initVector, Expiry);
                        Status = encrypt(good.key, good.initVector, Status);
                        Chassing = encrypt(good.key, good.initVector, Chassing);
                        Count = encrypt(good.key, good.initVector, Count);
                        Result = encrypt(good.key, good.initVector, Result);
                        refend = encrypt(good.key, good.initVector, refend);
                        Img = encrypt(good.key, good.initVector, Img);
                        gamename = encrypt(good.key, good.initVector, gamename);




                        getcontestdata = new Getmycontestdata();
                        getcontestdata.setNickname(Contestname);
                        getcontestdata.setPoints(Chassing);
                        getcontestdata.setWinningamount(Winningamount);
                        getcontestdata.setTimer(Expiry);
                        getcontestdata.setEntryfees(Entryfees);
                        getcontestdata.setContestid(Contestid);
                        getcontestdata.setCount(Count);
                        getcontestdata.setResult(Result);
                        getcontestdata.setRefended(refend);
                        getcontestdata.setImg(Img);
                        getcontestdata.setGamename(gamename);
                        productList.add(getcontestdata);
                    }

                    if (response.body().data != null) {

                        if (productList.size() > 0) {

                            adapter = new Mycontestadapter(getActivity(), productList,Fragmentmycontest.this);
                            mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                            mRecyclerView.setLayoutManager(mLayoutManager);
                            mRecyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

                        } else if (response.body().data.size() == 0) {
                            productList.clear();
                            Toast.makeText(getActivity(), "No detail found", Toast.LENGTH_LONG).show();
                        } else {
                            productList.clear();
                            Toast.makeText(getActivity(), "Try Again One", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(getActivity(), "Try Again Two", Toast.LENGTH_LONG).show();
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

                }
                progressbar.dismiss();

            }

            @Override
            public void onFailure(Call<MycontestResponseJson> call, Throwable t) {
                t.printStackTrace();


                Log.e("System error:", t.getLocalizedMessage());

            }
        });
    }

//
//    public class AsyncCallWS extends AsyncTask<String, Void, Void> {
//        String displayText;
//        String Contestid, Contestname, Entryfees, Winningamount, Expiry, Status, Chassing, Count, Result, refend, Img, gamename;
//        private KProgressHUD hud;
//        @Override
//        protected Void doInBackground(String... params) {
//            progressbar.dismiss();
//            SharedPreferences prefs = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
//            String Loginid = prefs.getString("Loginid", null);
//

//
//            displayText = WebService.Selectmycontest(Loginid,  "mycontest");
//            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                try {
//                    jasonObject = new JSONObject(displayText);
//                    jsonArray = jasonObject.getJSONArray("Response");
//                    productList = new ArrayList<>();
//                    Arrcontestid = new String[jsonArray.length()];
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
//
//                        Contestid = jsonrowdata.getString("Contestid");
//                        Contestname = jsonrowdata.getString("Contestname");
//                        Entryfees = jsonrowdata.getString("Entryfees");
//                        Winningamount = jsonrowdata.getString("Winningamount");
//                        Expiry = jsonrowdata.getString("Expiry");
//                        Status = jsonrowdata.getString("Status");
//                        Chassing = jsonrowdata.getString("Chasing");
//                        Count = jsonrowdata.getString("Count");
//                        Result = jsonrowdata.getString("Result");
//                        refend = jsonrowdata.getString("refund");
//                        Img = jsonrowdata.getString("game_img");
//                        gamename = jsonrowdata.getString("game_name");
//                        Arrcontestid[i] = Contestid;
//
//
//                        Contestid = encrypt(good.key, good.initVector, Contestid);
//                        Contestname = encrypt(good.key, good.initVector, Contestname);
//                        Entryfees = encrypt(good.key, good.initVector, Entryfees);
//                        Winningamount = encrypt(good.key, good.initVector, Winningamount);
//                        Expiry = encrypt(good.key, good.initVector, Expiry);
//                        Status = encrypt(good.key, good.initVector, Status);
//                        Chassing = encrypt(good.key, good.initVector, Chassing);
//                        Count = encrypt(good.key, good.initVector, Count);
//
//                        Result = encrypt(good.key, good.initVector, Result);
//                        refend = encrypt(good.key, good.initVector, refend);
//                        Img = encrypt(good.key, good.initVector, Img);
//                        gamename = encrypt(good.key, good.initVector, gamename);
//
//
//
//
//                        getcontestdata = new Getmycontestdata();
//                        getcontestdata.setNickname(Contestname);
//                        getcontestdata.setPoints(Chassing);
//                        getcontestdata.setWinningamount(Winningamount);
//                        getcontestdata.setTimer(Expiry);
//                        getcontestdata.setEntryfees(Entryfees);
//                        getcontestdata.setContestid(Contestid);
//                        getcontestdata.setCount(Count);
//                        getcontestdata.setResult(Result);
//                        getcontestdata.setRefended(refend);
//                        getcontestdata.setImg(Img);
//                        getcontestdata.setGamename(gamename);
//                        productList.add(getcontestdata);
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
//            progressbar.dismiss();
//            if (displayText.equalsIgnoreCase("connection fault")) {
//                Toast.makeText(getActivity(), "Please Check your internet connection", Toast.LENGTH_LONG).show();
//            } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                Toast.makeText(getActivity(), "Please try after some times...", Toast.LENGTH_LONG).show();
//            } else if (jsonArray.length() > 0) {
//                adapter = new Mycontestadapter(getActivity(), productList,Fragmentmycontest.this);
//                mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
//                mRecyclerView.setLayoutManager(mLayoutManager);
//                mRecyclerView.setAdapter(adapter);
//            } else if (jsonArray.length() == 0) {
//                Toast.makeText(getActivity(), "No detail found", Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(getActivity(), "Try Again", Toast.LENGTH_LONG).show();
//            }
//        }
//        @Override
//        protected void onPreExecute() {
//            progressbar.setContentView(R.layout.progresbarlayout);
//            progressbar.setCancelable(false);
//            RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader)progressbar.findViewById(R.id.progress);
//
//            RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(getActivity(),
//                    20, 60, ContextCompat.getColor(getActivity(), R.color.white));
//            loader.setAnimDuration(3000);
//
//            rotatingCircularDotsLoader.addView(loader);
//
//            progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
//            progressbar.show();
//        }
//        @Override
//        protected void onProgressUpdate(Void... values) {
//        }
//
//        @Override
//        protected void onCancelled() {
//            task.cancel(true);
//            super.onCancelled();
//        }
//    }


//    @Override
//    public void onResume() {
//        super.onResume();
//        getView().setFocusableInTouchMode(true);
//        getView().requestFocus();
//        getView().setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
//
//                    Intent intent=new Intent(getActivity(),Main2Activity.class);
//                    intent.putExtra("goto","home");
//                    intent.putExtra("Totalcontest",Main2Activity.Totalcontest);
//                    startActivity(intent);
//                    getActivity().finish();
//                    return true;
//                }
//                return false;
//            }
//        });
//    }

   

    

}
