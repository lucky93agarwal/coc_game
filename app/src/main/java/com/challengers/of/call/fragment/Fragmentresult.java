package com.challengers.of.call.fragment;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.RotatingCircularDotsLoader;
import com.challengers.of.call.Activityquiz;
import com.challengers.of.call.Banned_APP.bannedapp;
import com.challengers.of.call.Contest.ContestRequestJson;
import com.challengers.of.call.Contest.ContestResponseJson;
import com.challengers.of.call.Dashboard;
import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.GameTypes.GametypesActivity;
import com.challengers.of.call.Main2Activity;
import com.challengers.of.call.R;
import com.challengers.of.call.ResultApi.ShowResultRequestJson;
import com.challengers.of.call.ResultApi.ShowResultResponseJson;
import com.challengers.of.call.Retrofit.ServiceGenerator;
import com.challengers.of.call.Retrofit.UserService;
import com.challengers.of.call.TextinActivity;
import com.challengers.of.call.WebService;
import com.challengers.of.call.adapter.ResultAdapter;
import com.challengers.of.call.data.Getmyresultdata;
import com.challengers.of.call.testing.TextingtwoActivity;
import com.challengers.of.call.testing.Utils;

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

public class Fragmentresult extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    public List<Getmyresultdata> productList;
    private static bannedapp bannedapps;
    JSONObject jasonObject;
    public Dialog unepicDialog, progressbar, information;
    JSONArray jsonArray = null;
    int lengthwin,lengthlos;
    ResultAdapter adapter;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    Getmyresultdata getcontestdata;
    SwipeRefreshLayout swipe_refresh_layout;
//    AsyncCallWS task;
    public String Loginid,Name;
    String Contestid, Contestname,fromwinnerimage,fromlosserimage, Entryfees, Winningamount, Expiry, Status, Chassing, Result, Winnername,
            Winnerscore, Newcontest, Totalcontest,Totalcontestwin,Totalchallengewin,Totalamountwin,Looserid,Winnerid,Loosername,Winnerimage,Looserimage,Looserpoint;
    public String GameName, Gameid;
    private ljsflsj good;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView;
        rootView = inflater.inflate(R.layout.activity_result, container, false);
        mRecyclerView = rootView.findViewById(R.id.recyclerview);
//        mRecyclerView.setNestedScrollingEnabled(false);
        bannedapps = new bannedapp(getActivity());
        bannedapps.bannedbig();
        progressbar = new Dialog(getActivity());
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("coding",Context.MODE_PRIVATE
        );
        GameName = sharedPreferences.getString("gametype",null);
        SharedPreferences lucky = getActivity().getSharedPreferences("coding", Context.MODE_PRIVATE
        );
        Gameid = lucky.getString("gameid", null);


        swipe_refresh_layout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeToRefresh);
        swipe_refresh_layout.setOnRefreshListener(this);

        SharedPreferences prefs = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE
        );
        Loginid = prefs.getString("Loginid", null);





        if (Utils.isNetworkAvailable(getActivity())) {

//            task = new AsyncCallWS();
//            task.execute();
            
            
            progressbar.setContentView(R.layout.progresbarlayout);
            progressbar.setCancelable(false);
            RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader)progressbar.findViewById(R.id.progress);

            RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(getActivity(),
                    20, 60, ContextCompat.getColor(getActivity(), R.color.white));
            loader.setAnimDuration(3000);

            rotatingCircularDotsLoader.addView(loader);

            progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
            progressbar.show();
            
            ResultAPI();

        } else {
            Toast.makeText(getActivity(), "No internet connection!", Toast.LENGTH_LONG).show();

        }



        //Name = prefs.getString("Name", null);

        return rootView;
    }

    

    public void Refresh(int position) {
    }

    @Override
    public void onRefresh() {
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
        ResultAPI();
        swipe_refresh_layout.setRefreshing(false);
    }

    private void ResultAPI() {
        SharedPreferences prefs = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);

        String Password = prefs.getString("Password", null);
        String Usernames = prefs.getString("Name", null);
        String Loginid = prefs.getString("Loginid", null);


        final ShowResultRequestJson request = new ShowResultRequestJson();

        request.setLoginid(decrypt(good.key, Loginid));
        request.setGameid(decrypt(good.key, Gameid));

        UserService service = ServiceGenerator.createService(UserService.class, decrypt(good.key, Usernames), decrypt(good.key, Password));
        service.resultshow(request).enqueue(new Callback<ShowResultResponseJson>() {
            @Override
            public void onResponse(Call<ShowResultResponseJson> call, Response<ShowResultResponseJson> response) {
                if (response.isSuccessful()) {
                    productList = new ArrayList<>();
                    for (int z=0; z<response.body().data.size(); z++){

                        Contestid = response.body().data.get(z).Contestid;
                        Contestname = response.body().data.get(z).Contestname;
                        Entryfees = response.body().data.get(z).Entryfees;
                        Winningamount = response.body().data.get(z).Winningamount;
                        Expiry = response.body().data.get(z).Expiry;
                        Status = response.body().data.get(z).Status;
                        Chassing = response.body().data.get(z).Chasing;
                        Result = response.body().data.get(z).Result;
                        Winnername = response.body().data.get(z).Winnername;
                        Winnerscore = response.body().data.get(z).Winnerscore;
                        Newcontest = response.body().data.get(z).Newcontest;
                        Winnerid = response.body().data.get(z).Winnerid;
                        Loosername = response.body().data.get(z).Loosername;
                        Looserpoint = response.body().data.get(z).Looserpoint;
                        Winnerimage = response.body().data.get(z).Winnerimage;
                        Looserimage = response.body().data.get(z).Looserimage;
                        Looserid = response.body().data.get(z).Looserid;





                        String myString = null;
                        if (Winnerimage == null || Winnerimage.isEmpty() || Winnerimage.equals(myString)) {
                            lengthwin = 0;
                            Winnerimage = "null";
                        }else {
                            lengthwin = Winnerimage.length();
                        }

                        if (Looserimage == null || Looserimage.isEmpty() || Looserimage.equals(myString)) {
                            lengthlos = 0;
                            Looserimage = "null";
                        }else {
                            lengthlos = Looserimage.length();
                        }



                        Contestid = encrypt(good.key, good.initVector, Contestid);
                        Contestname = encrypt(good.key, good.initVector, Contestname);
                        Entryfees = encrypt(good.key, good.initVector, Entryfees);
                        Winningamount = encrypt(good.key, good.initVector, Winningamount);
                        Expiry = encrypt(good.key, good.initVector, Expiry);
                        Status = encrypt(good.key, good.initVector, Status);
                        Chassing = encrypt(good.key, good.initVector, Chassing);
                        Result = encrypt(good.key, good.initVector, Result);
                        Winnername = encrypt(good.key, good.initVector, Winnername);
                        Winnerscore = encrypt(good.key, good.initVector, Winnerscore);
                        Newcontest = encrypt(good.key, good.initVector, Newcontest);
                        Winnerid = encrypt(good.key, good.initVector, Winnerid);
                        Loosername = encrypt(good.key, good.initVector, Loosername);
                        Looserpoint = encrypt(good.key, good.initVector, Looserpoint);
                        Winnerimage = encrypt(good.key, good.initVector, Winnerimage);
                        Looserimage = encrypt(good.key, good.initVector, Looserimage);
                        Looserid = encrypt(good.key, good.initVector, Looserid);



                        getcontestdata = new Getmyresultdata();
                        getcontestdata.setNickname(Contestname);
                        getcontestdata.setPoints(Chassing);
                        getcontestdata.setWinningamount(Winningamount);
                        getcontestdata.setTimer(Status);
                        getcontestdata.setEntryfees(Entryfees);
                        getcontestdata.setContestid(Contestid);
                        getcontestdata.setResult(Result);
                        getcontestdata.setWinnername(Winnername);
                        getcontestdata.setWinnerscore(Winnerscore);
                        getcontestdata.setNewcontest(Newcontest);
                        getcontestdata.setTotalcontestwin(Totalcontestwin);
                        getcontestdata.setTotalchallengewin(Totalchallengewin);
                        getcontestdata.setTotalamountwin(Totalamountwin);
                        getcontestdata.setWinnerid(Winnerid);
                        getcontestdata.setLoosername(Loosername);
                        getcontestdata.setLooserpoint(Looserpoint);
                        getcontestdata.setWinnerimage(Winnerimage);
                        getcontestdata.setLooserimage(Looserimage);
                        getcontestdata.setLooserid(Looserid);
                        getcontestdata.setLengthwin(lengthwin);
                        getcontestdata.setLengthlos(lengthlos);
                        productList.add(getcontestdata);

                    }

                    if (response.body().data.size() > 0){
                        adapter = new ResultAdapter(getActivity(), productList);
                        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                        mRecyclerView.setLayoutManager(mLayoutManager);
                        mRecyclerView.setAdapter(adapter);

                    } else if (response.body().data.size() == 0) {
                        Toast.makeText(getActivity(), "No detail found", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getActivity(), "Try Again", Toast.LENGTH_LONG).show();
                    }


                }
                progressbar.dismiss();

            }

            @Override
            public void onFailure(Call<ShowResultResponseJson> call, Throwable t) {
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
//                    Fragmenttab fragmenttab=new Fragmenttab();
//                    Fragmenttab.mViewPager.setCurrentItem(1);

                    Intent intent = new Intent(getActivity(), GametypesActivity.class);
                    intent.putExtra("goto", "challenger");
                    intent.putExtra("Totalcontest", Main2Activity.Totalcontest);
                    startActivity(intent);
                    getActivity().finish();
//                    Dashboard.dashboard.finish();
                }
                return false;
            }
        });
        super.onResume();
    }

    @Override
    public void onPause() {

        super.onPause();
    }


   

}

