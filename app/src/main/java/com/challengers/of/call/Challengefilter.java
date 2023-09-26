package com.challengers.of.call;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.RotatingCircularDotsLoader;
import com.challengers.of.call.Banned_APP.bannedapp;
import com.challengers.of.call.ChallengersAPI.ChallengersRequestJson;
import com.challengers.of.call.Contest.ContestListResponseJson;
import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.Retrofit.ServiceGenerator;
import com.challengers.of.call.Retrofit.UserService;
import com.challengers.of.call.adapter.Filteradapter;
import com.challengers.of.call.adapter.HLVAdapter;
import com.challengers.of.call.data.Getcontestdata;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.decrypt;
import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.encrypt;

public class Challengefilter extends AppCompatActivity {
    private static bannedapp bannedapps;
    JSONObject jasonObject;
    JSONArray jsonArray = null;
    public  ArrayList<Getcontestdata> productList;
    Filteradapter adapter;
    RecyclerView.LayoutManager mLayoutManager;
    Getcontestdata getcontestdata;
    String Contestid, Contestname, Entryfees, Winningamount, Expiry,Challengername,Challengerid,Challengerimage,Winneridnew, Status,Chassing;
    private RecyclerView mRecyclerView;
    LinearLayout l1;
    public static String[] Arrcontestid;
    int image;
    public EditText editText;
    public String GameName,Gameid;
    private ljsflsj good;
    public Dialog unepicDialog, progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_challenge_filter);
        progressbar = new Dialog(Challengefilter.this);
        mRecyclerView = findViewById(R.id.recyclerview);
        bannedapps = new bannedapp(Challengefilter.this);
        bannedapps.bannedbig();
        SharedPreferences sharedPreferences = getSharedPreferences("coding", Context.MODE_PRIVATE);
        GameName = sharedPreferences.getString("gametype",null);
        SharedPreferences lucky = getSharedPreferences("coding",0);
        Gameid = lucky.getString("gameid", null);
//        Gameid = encrypt(good.key, good.initVector, Gameid);
        l1=findViewById(R.id.l1);
        //productList = (ArrayList<Getcontestdata>) args.getSerializable("ArrayList");
        AppCompatImageView ivback=findViewById(R.id.ivback);
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Challengefilter.this, Main2Activity.class);
                intent.putExtra("goto", "challenger");
                intent.putExtra("Totalcontest", Main2Activity.Totalcontest);
                startActivity(intent);
                finish();
            }
        });
        editText = findViewById(R.id.editText);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });



//        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
////            @Override
////            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//////                super.onScrollStateChanged(recyclerView, newState);
////            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if (dy > 0) {
//                    // Scrolling up
//                    l1.setVisibility(View.GONE);
//                } else {
//                    // Scrolling down
//                    l1.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//        AsyncCallWS task=new AsyncCallWS();
//        task.execute();
        progressbar.setContentView(R.layout.progresbarlayout);
        progressbar.setCancelable(false);
        RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader)progressbar.findViewById(R.id.progress);

        RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Challengefilter.this,
                20, 60, ContextCompat.getColor(Challengefilter.this, R.color.white));
        loader.setAnimDuration(3000);

        rotatingCircularDotsLoader.addView(loader);

        progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
        progressbar.show();
        Challengersss();
    }

    private void Challengersss() {
        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);

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
//                    if (productList.size() > 0){
//                        productList.clear();
//                    }

                    productList = new ArrayList<>();
                    Arrcontestid = new String[response.body().data.size()];

                    for (int z=0; z<response.body().data.size(); z++){

                        Contestid = response.body().data.get(z).Contestid;
                        Contestname = response.body().data.get(z).Contestname;
                        Entryfees = response.body().data.get(z).Entryfees;
                        Winningamount = response.body().data.get(z).Winningamount;
                        Expiry = response.body().data.get(z).Expiry;
                        Status = response.body().data.get(z).Status;
                        Chassing = response.body().data.get(z).Chasing;
                        Winneridnew = response.body().data.get(z).Winnerid;
                        Challengerimage = response.body().data.get(z).Challengerimage;
                        Challengername = response.body().data.get(z).Challengername;
                        Challengerid = response.body().data.get(z).Challengerid;

//                        image = Challengerimage.length();


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
                    if (productList.size() > 0){
                        adapter = new Filteradapter(Challengefilter.this, productList);
                        mLayoutManager = new LinearLayoutManager(Challengefilter.this, LinearLayoutManager.VERTICAL, false);
                        mRecyclerView.setLayoutManager(mLayoutManager);
                        mRecyclerView.setAdapter(adapter);
                        editText.setEnabled(true);
                    }




                } else {
                    switch (response.code()) {
                        case 401:
                            Toast.makeText(Challengefilter.this, "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
                            Toast.makeText(Challengefilter.this, "ForbiddenException", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
                            Toast.makeText(Challengefilter.this, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(Challengefilter.this, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(Challengefilter.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;

                    }

                }
                progressbar.dismiss();

            }

            @Override
            public void onFailure(Call<ContestListResponseJson> call, Throwable t) {
                t.printStackTrace();


                Log.e("System error:", t.getLocalizedMessage());

            }
        });
    }

//    public class AsyncCallWS extends AsyncTask<String, Void, Void> {
//        String displayText;
//        String Contestid, Contestname, Entryfees, Winningamount, Expiry,Challengername,Challengerid,Challengerimage,Winneridnew, Status,Chassing;
//        @Override
//        protected Void doInBackground(String... params) {
//            SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
//            String Loginid = prefs.getString("Loginid", null);

//            int image;
//            displayText = WebService.Selectchallenge(Loginid,Gameid,"ChallengerList");
//            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                try {
//                    jasonObject = new JSONObject(displayText);
//                    jsonArray = jasonObject.getJSONArray("Response");
//                    productList = new ArrayList<>();
//                    Arrcontestid=new String[jsonArray.length()];
//                    for (int i = 0; i < jsonArray.length(); i++)
//                    {
//                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
//                        Contestid = jsonrowdata.getString("Contestid");
//                        Contestname = jsonrowdata.getString("Contestname");
//                        Entryfees = jsonrowdata.getString("Entryfees");
//                        Winningamount = jsonrowdata.getString("Winningamount");
//                        Expiry = jsonrowdata.getString("Expiry");
//                        Status = jsonrowdata.getString("Status");
//                        Chassing = jsonrowdata.getString("Chasing");
//                        Winneridnew = jsonrowdata.getString("Winnerid");
//                        Challengerimage = jsonrowdata.getString("Challengerimage");
//                        Challengername = jsonrowdata.getString("Challengername");
//                        Challengerid = jsonrowdata.getString("Challengerid");
//
//
//                        image =Challengerimage.length();
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
//
//                        Arrcontestid[i]=Contestid;
//                        getcontestdata = new Getcontestdata();
//                        getcontestdata.setNickname(Contestname);
//                        getcontestdata.setPoints(Chassing);
//                        getcontestdata.setWinningamount(Double.parseDouble(Winningamount));
//                        getcontestdata.setTimer(Expiry);
//                        getcontestdata.setEntryfees(Entryfees);
//                        getcontestdata.setContestid(Contestid);
//
//                        getcontestdata.setWinnerid(Winneridnew);
//                        getcontestdata.setChallengerimage(Challengerimage);
//                        getcontestdata.setChallengername(Challengername);
//                        getcontestdata.setChallengerid(Challengerid);
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
//        @RequiresApi(api = Build.VERSION_CODES.N)
//        @Override
//        protected void onPostExecute(Void result) {
//
//            if (displayText.equalsIgnoreCase("connection fault")) {
//                Toast.makeText(Challengefilter.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
//            } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                Toast.makeText(Challengefilter.this, "Please try after some times...", Toast.LENGTH_LONG).show();
//            } else if (jsonArray.length() > 0) {
//                adapter = new Filteradapter(Challengefilter.this, productList);
//                mLayoutManager = new LinearLayoutManager(Challengefilter.this, LinearLayoutManager.VERTICAL, false);
//                mRecyclerView.setLayoutManager(mLayoutManager);
//                mRecyclerView.setAdapter(adapter);
//                editText.setEnabled(true);
//
//
//
//            }
//            else if(jsonArray.length()==0)
//            {
//                Toast.makeText(Challengefilter.this, "No detail found", Toast.LENGTH_LONG).show();
//            }
//            else {
//                Toast.makeText(Challengefilter.this, "Try Again", Toast.LENGTH_LONG).show();
//            }
//        }
//        @Override
//        protected void onPreExecute() {
//        }
//        @Override
//        protected void onProgressUpdate(Void... values) {
//        }
//    }
    public  void Refresh(int position)
    {
    }
    private void filter(String text) {
        ArrayList<Getcontestdata> filteredList = new ArrayList<>();
        for (Getcontestdata item : productList) {
            String Nickname = decrypt(good.key, item.getNickname());
            String Entryfees = decrypt(good.key, item.getEntryfees());
            if (Nickname.toLowerCase().contains(text.toLowerCase())||Entryfees.toLowerCase().contains(text.toLowerCase()) ) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Challengefilter.this, Main2Activity.class);
        intent.putExtra("goto", "challenger");
        intent.putExtra("Totalcontest", Main2Activity.Totalcontest);
        startActivity(intent);
        finish();
    }
}
