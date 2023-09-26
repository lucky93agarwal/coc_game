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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.RotatingCircularDotsLoader;
import com.challengers.of.call.Activityquiz;
import com.challengers.of.call.Banned_APP.bannedapp;
import com.challengers.of.call.Challengefilter;
import com.challengers.of.call.ChallengersAPI.ChallengersRequestJson;
import com.challengers.of.call.Contest.ContestListResponseJson;
import com.challengers.of.call.Contest.ContestRequestJson;
import com.challengers.of.call.Contest.ContestResponseJson;
import com.challengers.of.call.Dashboard;
import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.GameTypes.GametypesActivity;
import com.challengers.of.call.Main2Activity;
import com.challengers.of.call.R;
import com.challengers.of.call.Retrofit.ServiceGenerator;
import com.challengers.of.call.Retrofit.UserService;
import com.challengers.of.call.Splash;
import com.challengers.of.call.TextinActivity;
import com.challengers.of.call.WebService;
import com.challengers.of.call.adapter.HLVAdapter;
import com.challengers.of.call.data.Getcontestdata;
import com.challengers.of.call.testing.TextingtwoActivity;
import com.challengers.of.call.testing.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.decrypt;
import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.encrypt;

public class Fragmentchallenges extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    public static String[] Arrcontestid;
    int image;
    public ArrayList<Getcontestdata> productList;
    JSONObject jasonObject;
    JSONArray jsonArray = null;
    private static bannedapp bannedapps;
    HLVAdapter adapter;
    RecyclerView.LayoutManager mLayoutManager;
    Getcontestdata getcontestdata;
    boolean ascname = false, ascchasing = false, ascwinning = false;
    SwipeRefreshLayout swipe_refresh_layout;
    LinearLayout l1;
    //    AsyncCallWS task;
    String Contestid, Contestname, Entryfees, TotalWalletnew, Winningamount, Expiry, Status, Winneridnew, Challengerimage, Challengername, Challengerid, Winnernamenew, Chassing;
    ImageView ivnameasc, ivnamedsc;
    String Loginid = "";
    public String GameName, Gameid;
    private RecyclerView mRecyclerView;
    public Dialog unepicDialog, progressbar;
    private ljsflsj good;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView;
        rootView = inflater.inflate(R.layout.fragment_main2, container, false);

        bannedapps = new bannedapp(getActivity());
        bannedapps.bannedbig();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("coding", Context.MODE_PRIVATE);
        SharedPreferences lucky = getActivity().getSharedPreferences("coding", 0);
        GameName = sharedPreferences.getString("gametype", null);
        Gameid = lucky.getString("gameid", null);
//        Gameid = encrypt(good.key, good.initVector, Gameid);





//        Gameid = encrypt(good.key, good.initVector, Gameid);

        progressbar = new Dialog(getActivity());


        SharedPreferences prefs = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        Loginid = prefs.getString("Loginid", null);


        mRecyclerView = rootView.findViewById(R.id.recyclerview);

//        mRecyclerView.setNestedScrollingEnabled(false);


        ivnameasc = rootView.findViewById(R.id.ivnameasc);
        ivnamedsc = rootView.findViewById(R.id.ivnamedsc);
        ivnamedsc.setVisibility(View.GONE);
        swipe_refresh_layout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeToRefresh);
        swipe_refresh_layout.setOnRefreshListener(this);
        l1 = rootView.findViewById(R.id.l1);
        AppCompatImageView ivfilter = rootView.findViewById(R.id.ivfilterview);
        ivfilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Challengefilter.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Challengefilter.class);
                startActivity(intent);
                getActivity().finish();
            }
        });


        if (Utils.isNetworkAvailable(getActivity())) {
            progressbar.setContentView(R.layout.progresbarlayout);
            progressbar.setCancelable(false);
            RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);
            RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(getActivity(),
                    20, 60, ContextCompat.getColor(getActivity(), R.color.white));
            loader.setAnimDuration(3000);
            rotatingCircularDotsLoader.addView(loader);
            progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
            progressbar.show();
            Challenger();

        } else {
            Toast.makeText(getActivity(), "No internet connection!", Toast.LENGTH_LONG).show();

        }
        final TextView txtsortbyname = rootView.findViewById(R.id.txtname);
        final TextView txtsortbyentryfees = rootView.findViewById(R.id.txtentryfee);
        final TextView txtsortbyamount = rootView.findViewById(R.id.txtamount);
        txtsortbyname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtsortbyname.setTextColor(Color.parseColor("#9b0000"));
                txtsortbyentryfees.setTextColor(Color.parseColor("#77000000"));
                txtsortbyamount.setTextColor(Color.parseColor("#77000000"));

                if (ascname == false) {

                    ivnameasc.setVisibility(View.VISIBLE);
                    ivnamedsc.setVisibility(View.GONE);
                    if (productList != null) {
                        if (productList.size() > 0) {
                            Collections.sort(productList, new Comparator<Getcontestdata>() {
                                public int compare(Getcontestdata s1, Getcontestdata s2) {
                                    String point1 = decrypt(good.key, s1.getNickname());
                                    String point2 = decrypt(good.key, s2.getNickname());
                                    return point1.compareToIgnoreCase(point2);
                                }
                            });
                            adapter = new HLVAdapter(getActivity(), productList);
                            mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                            mRecyclerView.setLayoutManager(mLayoutManager);
                            mRecyclerView.setAdapter(adapter);
                        }
                    }
                    ascname = true;
                } else if (ascname == true) {
                    ivnameasc.setVisibility(View.GONE);
                    ivnamedsc.setVisibility(View.VISIBLE);
                    if (productList != null) {
                        if (productList.size() > 0) {
                            Collections.sort(productList, new Comparator<Getcontestdata>() {
                                public int compare(Getcontestdata s1, Getcontestdata s2) {
                                    String point1 = decrypt(good.key, s1.getNickname());
                                    String point2 = decrypt(good.key, s2.getNickname());
                                    return point2.compareToIgnoreCase(point1);
                                }
                            });
                            adapter = new HLVAdapter(getActivity(), productList);
                            mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                            mRecyclerView.setLayoutManager(mLayoutManager);
                            mRecyclerView.setAdapter(adapter);
                        }
                    }
                    ascname = false;
                }
            }
        });


        txtsortbyentryfees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txtsortbyentryfees.setTextColor(Color.parseColor("#9b0000"));
                txtsortbyname.setTextColor(Color.parseColor("#77000000"));
                txtsortbyamount.setTextColor(Color.parseColor("#77000000"));
                ImageView ivchasingasc = rootView.findViewById(R.id.ivchasingasc);
                ImageView ivchasingdsc = rootView.findViewById(R.id.ivchasingdsc);

                if (ascchasing == false) {
                    ivchasingasc.setVisibility(View.VISIBLE);
                    ivchasingdsc.setVisibility(View.GONE);
                    if (productList != null) {
                        if (productList.size() > 0) {
                            Collections.sort(productList, new Comparator<Getcontestdata>() {
                                public int compare(Getcontestdata s1, Getcontestdata s2) {
                                    String point1 = decrypt(good.key, s1.getPoints());
                                    String point2 = decrypt(good.key, s2.getPoints());
                                    return Integer.compare(Integer.valueOf(point1), Integer.valueOf(point2));
                                }
                            });
                            adapter = new HLVAdapter(getActivity(), productList);
                            mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                            mRecyclerView.setLayoutManager(mLayoutManager);
                            mRecyclerView.setAdapter(adapter);
                        }
                    }
                    ascchasing = true;
                } else if (ascchasing == true) {
                    ivchasingasc.setVisibility(View.GONE);
                    ivchasingdsc.setVisibility(View.VISIBLE);
                    if (productList != null) {
                        if (productList.size() > 0) {
                            Collections.sort(productList, new Comparator<Getcontestdata>() {
                                public int compare(Getcontestdata s1, Getcontestdata s2) {
                                    String point1 = decrypt(good.key, s1.getPoints());
                                    String point2 = decrypt(good.key, s2.getPoints());
                                    return Integer.compare(Integer.valueOf(point2), Integer.valueOf(point1));
                                }
                            });
                            adapter = new HLVAdapter(getActivity(), productList);
                            mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                            mRecyclerView.setLayoutManager(mLayoutManager);
                            mRecyclerView.setAdapter(adapter);
                        }
                    }
                    ascchasing = false;
                }
            }
        });


        txtsortbyamount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtsortbyamount.setTextColor(Color.parseColor("#9b0000"));
                txtsortbyname.setTextColor(Color.parseColor("#77000000"));
                txtsortbyentryfees.setTextColor(Color.parseColor("#77000000"));

                ImageView ivwinningasc = rootView.findViewById(R.id.ivwinningasc);
                ImageView ivwinningdsc = rootView.findViewById(R.id.ivwinningdsc);

                if (ascwinning == false) {
                    ivwinningasc.setVisibility(View.VISIBLE);
                    ivwinningdsc.setVisibility(View.GONE);
                    if (productList != null) {
                        if (productList.size() > 0) {
                            Collections.sort(productList, new Comparator<Getcontestdata>() {
                                public int compare(Getcontestdata s1, Getcontestdata s2) {
                                    return Double.compare(s1.getWinningamount(), s2.getWinningamount());
                                }
                            });
                            adapter = new HLVAdapter(getActivity(), productList);
                            mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                            mRecyclerView.setLayoutManager(mLayoutManager);
                            mRecyclerView.setAdapter(adapter);
                        }
                    }
                    ascwinning = true;
                } else if (ascwinning == true) {
                    ivwinningasc.setVisibility(View.GONE);
                    ivwinningdsc.setVisibility(View.VISIBLE);
                    if (productList != null) {
                        if (productList.size() > 0) {
                            Collections.sort(productList, new Comparator<Getcontestdata>() {
                                public int compare(Getcontestdata s1, Getcontestdata s2) {
                                    return Double.compare(s2.getWinningamount(), s1.getWinningamount());
                                }
                            });
                            adapter = new HLVAdapter(getActivity(), productList);
                            mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                            mRecyclerView.setLayoutManager(mLayoutManager);
                            mRecyclerView.setAdapter(adapter);
                        }
                    }
                    ascwinning = false;
                }
            }
        });
        return rootView;
    }

    public void Refresh(int position) {
    }

    @Override
    public void onRefresh() {
//        if (task != null)
//            task.cancel(true);
//        task = new AsyncCallWS();
//        task.execute();
        progressbar.setContentView(R.layout.progresbarlayout);
        progressbar.setCancelable(false);
        RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);

        RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(getActivity(),
                20, 60, ContextCompat.getColor(getActivity(), R.color.white));
        loader.setAnimDuration(3000);

        rotatingCircularDotsLoader.addView(loader);

        progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
        progressbar.show();
        Challenger();
        swipe_refresh_layout.setRefreshing(false);
    }


    @Override
    public void onResume() {

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle back button's click listener

                    Intent intent = new Intent(getActivity(), GametypesActivity.class);
                    intent.putExtra("goto", "challenger");
                    intent.putExtra("Totalcontest", Main2Activity.Totalcontest);
                    startActivity(intent);
                    getActivity().finish();

                }
                return false;
            }
        });
        super.onResume();
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void Challenger() {
        SharedPreferences prefs = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);

        String Password = prefs.getString("Password", null);
        String Usernames = prefs.getString("Name", null);
        String Loginid = prefs.getString("Loginid", null);


        final ChallengersRequestJson request = new ChallengersRequestJson();

        request.setLoginid(decrypt(good.key, Loginid));

        request.setGameid(decrypt(good.key, Gameid));


        UserService service = ServiceGenerator.createService(UserService.class, decrypt(good.key, Usernames), decrypt(good.key, Password));
        service.challenger(request).enqueue(new Callback<ContestListResponseJson>() {
            @Override
            public void onResponse(Call<ContestListResponseJson> call, Response<ContestListResponseJson> response) {
                if (response.isSuccessful()) {
               
                    productList = new ArrayList<>();
                    Arrcontestid = new String[response.body().data.size()];

                    for (int z = 0; z < response.body().data.size(); z++) {

                        Contestid = response.body().data.get(z).Contestid;
                        Contestname = response.body().data.get(z).Contestname;
                        Entryfees = response.body().data.get(z).Entryfees;
                        Winningamount = response.body().data.get(z).Winningamount;
                        Expiry = response.body().data.get(z).Expiry;
                        Status = response.body().data.get(z).Status;
                        Chassing = response.body().data.get(z).Chasing;
                        Winneridnew = response.body().data.get(z).Winnerid;
                        Challengername = response.body().data.get(z).Challengername;
                        Challengerid = response.body().data.get(z).Challengerid;
                        Challengerimage = response.body().data.get(z).Challengerimage;
                        String myString = null;
                        if (Challengerimage == null || Challengerimage.isEmpty() || Challengerimage.equals(myString)) {
                            image = 0;
                            Challengerimage = "null";
                        } else {
                            image = Challengerimage.length();
                        }





                        Contestid = encrypt(good.key, good.initVector, Contestid);
                        Contestname = encrypt(good.key, good.initVector, Contestname);
                        Entryfees = encrypt(good.key, good.initVector, Entryfees);
                        Expiry = encrypt(good.key, good.initVector, Expiry);
                        Status = encrypt(good.key, good.initVector, Status);
                        Chassing = encrypt(good.key, good.initVector, Chassing);
                        Winneridnew = encrypt(good.key, good.initVector, Winneridnew);
                        Challengerimage = encrypt(good.key, good.initVector, Challengerimage);
                        Challengername = encrypt(good.key, good.initVector, Challengername);
                        Challengerid = encrypt(good.key, good.initVector, Challengerid);



                        Arrcontestid[z] = Contestid;
                        getcontestdata = new Getcontestdata();
                        getcontestdata.setNickname(Contestname);
                        getcontestdata.setPoints(Chassing);
                        getcontestdata.setWinningamount(Double.parseDouble(Winningamount));
                        getcontestdata.setTimer(Expiry);
                        getcontestdata.setEntryfees(Entryfees);
                        getcontestdata.setContestid(Contestid);
                        getcontestdata.setWinnerid(Winneridnew);
                        getcontestdata.setChallengerimage(Challengerimage);
                        getcontestdata.setChallengername(Challengername);
                        getcontestdata.setChallengerid(Challengerid);
                        getcontestdata.setImage(image);
                        productList.add(getcontestdata);


                    }

                    if (response.body().data != null) {

                        if (productList.size() > 0) {

                            adapter = new HLVAdapter(getActivity(), productList);
                            mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                            mRecyclerView.setLayoutManager(mLayoutManager);
                            mRecyclerView.setAdapter(adapter);

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
//                            Toast.makeText(getActivity(), "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
//                            Toast.makeText(getActivity(), "ForbiddenException", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
//                            Toast.makeText(getActivity(), "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
//                            Toast.makeText(getActivity(), "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
//                            Toast.makeText(getActivity(), "unknown error", Toast.LENGTH_SHORT).show();
                            break;

                    }
                    Toast.makeText(getActivity(), "Something error please try again", Toast.LENGTH_SHORT).show();
                }
                progressbar.dismiss();

            }

            @Override
            public void onFailure(Call<ContestListResponseJson> call, Throwable t) {
                t.printStackTrace();
                progressbar.dismiss();
//                Toast.makeText(getActivity(), "System error: "+ t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                Log.e("System error:", t.getLocalizedMessage());

            }
        });
    }

//    public class AsyncCallWS extends AsyncTask<String, Void, Void> {
//        String displayText;
//        String Contestid, Contestname, Entryfees, TotalWalletnew, Winningamount, Expiry, Status, Winneridnew, Challengerimage, Challengername, Challengerid, Winnernamenew, Chassing;
//
//        int image;
//
//        @Override
//        protected Void doInBackground(String... params) {
//

//
//            displayText = WebService.Selectchallenge(Loginid, Gameid, "ChallengerList");
//            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                try {
//                    jasonObject = new JSONObject(displayText);
//                    jsonArray = jasonObject.getJSONArray("Response");

//                    productList = new ArrayList<>();
//                    Arrcontestid = new String[jsonArray.length()];
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
//                        Contestid = jsonrowdata.getString("Contestid");
//                        Contestname = jsonrowdata.getString("Contestname");
//                        Entryfees = jsonrowdata.getString("Entryfees");
//                        Winningamount = jsonrowdata.getString("Winningamount");
//                        Expiry = jsonrowdata.getString("Expiry");
//                        Status = jsonrowdata.getString("Status");
//                        Chassing = jsonrowdata.getString("Chasing");
//
//
//                        Winneridnew = jsonrowdata.getString("Winnerid");
//                        Challengerimage = jsonrowdata.getString("Challengerimage");
//                        Challengername = jsonrowdata.getString("Challengername");
//                        Challengerid = jsonrowdata.getString("Challengerid");
////                        TotalWalletnew = jsonrowdata.getString("Totalwallet");
////                        Winnernamenew = jsonrowdata.getString("Winnername");
//                        image = Challengerimage.length();
//
//                        Contestid = encrypt(good.key, good.initVector, Contestid);
//                        Contestname = encrypt(good.key, good.initVector, Contestname);
//                        Entryfees = encrypt(good.key, good.initVector, Entryfees);
//
//                        Expiry = encrypt(good.key, good.initVector, Expiry);
//                        Status = encrypt(good.key, good.initVector, Status);
//                        Chassing = encrypt(good.key, good.initVector, Chassing);
//                        Winneridnew = encrypt(good.key, good.initVector, Winneridnew);
//                        Challengerimage = encrypt(good.key, good.initVector, Challengerimage);
//                        Challengername = encrypt(good.key, good.initVector, Challengername);
//                        Challengerid = encrypt(good.key, good.initVector, Challengerid);
//
//
//                        Arrcontestid[i] = Contestid;
//                        getcontestdata = new Getcontestdata();
//                        getcontestdata.setNickname(Contestname);
//                        getcontestdata.setPoints(Chassing);
//                        getcontestdata.setWinningamount(Double.parseDouble(Winningamount));
//                        getcontestdata.setTimer(Expiry);
//                        getcontestdata.setEntryfees(Entryfees);
//                        getcontestdata.setContestid(Contestid);
//
//                        getcontestdata.setWinnerid(Winneridnew);
////                        getcontestdata.setWinnername(Winnernamenew);
//                        getcontestdata.setChallengerimage(Challengerimage);
//                        getcontestdata.setChallengername(Challengername);
//                        getcontestdata.setChallengerid(Challengerid);
////                        getcontestdata.setTotalwallet(TotalWalletnew);
//                        getcontestdata.setImage(image);
//
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
//            if (displayText != null) {
//                if (displayText.equalsIgnoreCase("connection fault")) {
//                    Toast.makeText(getActivity(), "Please Check your internet connection", Toast.LENGTH_LONG).show();
//                } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                    Toast.makeText(getActivity(), "Please try after some times...", Toast.LENGTH_LONG).show();
//                } else if (jsonArray != null) {
//                    if (jsonArray.length() > 0) {
//
//
//                        adapter = new HLVAdapter(getActivity(), productList);
//                        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
//                        mRecyclerView.setLayoutManager(mLayoutManager);
//                        mRecyclerView.setAdapter(adapter);
//
//                    } else if (jsonArray.length() == 0) {
//                        Toast.makeText(getActivity(), "No detail found", Toast.LENGTH_LONG).show();
//                    } else {
//                        Toast.makeText(getActivity(), "Try Again One", Toast.LENGTH_LONG).show();
//                    }
//                } else {
//                    Toast.makeText(getActivity(), "Try Again Two", Toast.LENGTH_LONG).show();
//                }
//            } else {
//                Toast.makeText(getActivity(), "Try Again Three", Toast.LENGTH_LONG).show();
//            }
//        }
//
//        @Override
//        protected void onPreExecute() {
//
//            progressbar.setContentView(R.layout.progresbarlayout);
//            progressbar.setCancelable(false);
//            RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);
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
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//        }
//
//
//        @Override
//        protected void onCancelled() {
//            task.cancel(true);
//            super.onCancelled();
//        }
//    }
}
