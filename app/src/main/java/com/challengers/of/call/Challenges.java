package com.challengers.of.call;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.challengers.of.call.adapter.HLVAdapter;
import com.challengers.of.call.data.Getcontestdata;

import java.util.ArrayList;
import java.util.List;
public class Challenges extends AppCompatActivity {
    HLVAdapter adapter;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    public List<Getcontestdata> productList;
    Getcontestdata getcontestdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenges);
        mRecyclerView = findViewById(R.id.recyclerview);
        productList=new ArrayList<>();
//        for(int i=0;i<10;i++) {
//            getcontestdata = new Getcontestdata();
//            getcontestdata.setNickname("Umesh");
//            getcontestdata.setPoints("55");
//            getcontestdata.setWinningamount("70");
//            getcontestdata.setTimer("24");
//            getcontestdata.setEntryfees("12");
//            productList.add(getcontestdata);
//        }
        adapter = new HLVAdapter(Challenges.this, productList);
        mLayoutManager = new LinearLayoutManager(Challenges.this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);
    }
}
