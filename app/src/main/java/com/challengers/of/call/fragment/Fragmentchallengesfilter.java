package com.challengers.of.call.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.challengers.of.call.Banned_APP.bannedapp;
import com.challengers.of.call.R;
import com.challengers.of.call.Signin;
import com.challengers.of.call.WebService;
import com.challengers.of.call.adapter.HLVAdapter;
import com.challengers.of.call.data.Getcontestdata;
import com.challengers.of.call.testing.SettingsPreferences;
import com.challengers.of.call.testing.StaticUtils;
import com.challengers.of.call.testing.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Fragmentchallengesfilter extends Fragment {
    private static bannedapp bannedapps;
    public static String[] Arrcontestid;
    public List<Getcontestdata> productList;
    JSONObject jasonObject;
    JSONArray jsonArray = null;
    HLVAdapter adapter;
    RecyclerView.LayoutManager mLayoutManager;
    Getcontestdata getcontestdata;
    LinearLayout l1;
    private RecyclerView mRecyclerView;
    AsyncCallWS task;
    String Loginid = "";
    public String GameName, Gameid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView;
        rootView = inflater.inflate(R.layout.fragment_main2, container, false);
        mRecyclerView = rootView.findViewById(R.id.recyclerview);
        mRecyclerView.setNestedScrollingEnabled(false);
        bannedapps = new bannedapp(getActivity());
        bannedapps.bannedbig();
        l1 = rootView.findViewById(R.id.l1);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("coding", Context.MODE_PRIVATE);
        GameName = sharedPreferences.getString("gametype",null);
        SharedPreferences lucky = getActivity().getSharedPreferences("coding",Context.MODE_PRIVATE);
        Gameid = lucky.getString("gameid", null);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    // Scrolling up
                    l1.setVisibility(View.GONE);
                } else {
                    // Scrolling down
                    l1.setVisibility(View.VISIBLE);
                }
            }
        });

        if (Utils.isNetworkAvailable(getActivity())) {

            task = new AsyncCallWS();
            task.execute();
            if (SettingsPreferences.getVibration(getActivity())) {
                StaticUtils.vibrate(getActivity(), StaticUtils.VIBRATION_DURATION);
            }
            if (SettingsPreferences.getSoundEnableDisable(getActivity())) {
                StaticUtils.backSoundonclick(getActivity());
            }
        } else {
            Toast.makeText(getActivity(), "No internet connection!", Toast.LENGTH_LONG).show();
            if (SettingsPreferences.getVibration(getActivity())) {
                StaticUtils.vibrate(getActivity(), StaticUtils.VIBRATION_DURATION);
            }
            if (SettingsPreferences.getSoundEnableDisable(getActivity())) {
                StaticUtils.setwronAnssound(getActivity());
            }
        }


        return rootView;
    }

    public void Refresh(int position) {
    }

    public class AsyncCallWS extends AsyncTask<String, Void, Void> {
        String displayText;
        String Contestid, Contestname, Entryfees, Winningamount, Expiry, Status, Winneridnew, Challengerimage, Challengername, Challengerid, Winnernamenew, Chassing;

        @Override
        protected Void doInBackground(String... params) {

            displayText = WebService.Selectchallenge(Loginid, Gameid, "Selectchallenge");
            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
                try {
                    jasonObject = new JSONObject(displayText);
                    jsonArray = jasonObject.getJSONArray("Response");
                    productList = new ArrayList<>();
                    Arrcontestid = new String[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
                        Contestid = jsonrowdata.getString("Contestid");
                        Contestname = jsonrowdata.getString("Contestname");
                        Entryfees = jsonrowdata.getString("Entryfees");
                        Winningamount = jsonrowdata.getString("Winningamount");
                        Expiry = jsonrowdata.getString("Expiry");
                        Status = jsonrowdata.getString("Status");
                        Chassing = jsonrowdata.getString("Chasing");


                        Winneridnew = jsonrowdata.getString("Winnerid");
                        Challengerimage = jsonrowdata.getString("Challengerimage");
                        Challengername = jsonrowdata.getString("Challengername");
                        Challengerid = jsonrowdata.getString("Challengerid");
                        Winnernamenew = jsonrowdata.getString("Winnername");

                        Arrcontestid[i] = Contestid;
                        getcontestdata = new Getcontestdata();
                        getcontestdata.setNickname(Contestname);
                        getcontestdata.setPoints(Chassing);
                        getcontestdata.setWinningamount(Double.parseDouble(Winningamount));
                        getcontestdata.setTimer(Expiry);
                        getcontestdata.setEntryfees(Entryfees);
                        getcontestdata.setContestid(Contestid);

                        getcontestdata.setWinnerid(Winneridnew);
//                        getcontestdata.setWinnername(Winnernamenew);
                        getcontestdata.setChallengerimage(Challengerimage);
                        getcontestdata.setChallengername(Challengername);
                        getcontestdata.setChallengerid(Challengerid);

                        productList.add(getcontestdata);
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
            if (displayText != null) {
                if (displayText.equalsIgnoreCase("connection fault")) {
                    Toast.makeText(getActivity(), "Please Check your internet connection", Toast.LENGTH_LONG).show();
                } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
                    Toast.makeText(getActivity(), "Please try after some times...", Toast.LENGTH_LONG).show();
                } else if (jsonArray != null) {
                    if (jsonArray.length() > 0) {
                        adapter = new HLVAdapter(getActivity(), productList);
                        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                        mRecyclerView.setLayoutManager(mLayoutManager);
                        mRecyclerView.setAdapter(adapter);

                    } else if (jsonArray.length() == 0) {
                        Toast.makeText(getActivity(), "No detail found", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getActivity(), "Try Again One", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Try Again Two", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getActivity(), "Try Again Three", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }


        @Override
        protected void onCancelled() {
            task.cancel(true);
            super.onCancelled();
        }
    }
}
