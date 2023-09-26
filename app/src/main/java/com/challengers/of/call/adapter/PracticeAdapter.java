package com.challengers.of.call.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.challengers.of.call.ItemClickListener;
import com.challengers.of.call.Main2Activity;
import com.challengers.of.call.R;
import com.challengers.of.call.TextinActivity;
import com.challengers.of.call.data.GetDataGameType;
import com.challengers.of.call.practice_webview.PracticeWebViewOne;
import com.challengers.of.call.practice_webview.PracticeWebViewTwo;
import com.challengers.of.call.testing.SettingsPreferences;
import com.challengers.of.call.testing.StaticUtils;
import com.challengers.of.call.testing.TextingtwoActivity;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class PracticeAdapter extends RecyclerView.Adapter<PracticeAdapter.ViewHolder> {
    public static Context mcontext;

    public boolean run = false;
    public static List<GetDataGameType> productList;
    public static boolean runtimer = true;
    public Context context;
    ViewHolder view;

    public GetDataGameType getselfdata;
    String Contest = "";


    ViewHolder viewHolder;

    public PracticeAdapter(Context context, List<GetDataGameType> productList) {
        super();
        this.context = context;
        this.productList = productList;
        mcontext = context;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.gametypelayout, viewGroup, false);
        viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        getselfdata = productList.get(i);
        view = viewHolder;


        viewHolder.tvname.setText(productList.get(i).getName());


        if (getselfdata.getImg() != null && getselfdata.getImg() != "") {
            Glide.with(getApplicationContext()).load("http://callofchallengers.com/coc/coc_arena/challengerGame/img/" + getselfdata.getImg())
                    .crossFade()
                    .placeholder(R.drawable.logo)
                    .error(R.drawable.logo)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(false)
                    .into(viewHolder.ivname);
        }


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView tvname, tvTimer, tvEntryfees;
        public TextView tvrupees;
        ImageView ivname;
        public CountDownTimer timer;

        private ItemClickListener clickListener;
        //        Activity activity = (Activity) mcontext;
        public Animation myAnim;
        public LinearLayout linearlayout;


        public ViewHolder(View itemView) {
            super(itemView);


            tvname = (TextView) itemView.findViewById(R.id.name);
            linearlayout = (LinearLayout) itemView.findViewById(R.id.linearlaynew);
            ivname = (ImageView) itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            tvname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (SettingsPreferences.getVibration(getApplicationContext())) {
                        StaticUtils.vibrate(getApplicationContext(), StaticUtils.VIBRATION_DURATION);
                    }
                    if (SettingsPreferences.getSoundEnableDisable(getApplicationContext())) {
                        StaticUtils.backSoundonclick(getApplicationContext());
                    }
                    SharedPreferences lucky = mcontext.getSharedPreferences("coding",Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = lucky.edit();
                    edit.putString("gametype",productList.get(getAdapterPosition()).getUrl());
                    edit.putString("gameid",productList.get(getAdapterPosition()).getId());
                    edit.putString("gamelayout",productList.get(getAdapterPosition()).getLayout());
                    edit.apply();
                    String GameLayout = productList.get(getAdapterPosition()).getLayout();
                    if (GameLayout.equalsIgnoreCase("0")) {
                        Intent intent = new Intent(mcontext, PracticeWebViewOne.class);

                        mcontext.startActivity(intent);
                    }else if (GameLayout.equalsIgnoreCase("1")) {
                        Intent intent = new Intent(mcontext, PracticeWebViewTwo.class);

                        mcontext.startActivity(intent);
                    }
//                    activity.overridePendingTransition(R.anim.slide_from_right, R.anim.slideout_from_left);
                }
            });

            ivname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (SettingsPreferences.getVibration(getApplicationContext())) {
                        StaticUtils.vibrate(getApplicationContext(), StaticUtils.VIBRATION_DURATION);
                    }
                    if (SettingsPreferences.getSoundEnableDisable(getApplicationContext())) {
                        StaticUtils.backSoundonclick(getApplicationContext());
                    }
                    SharedPreferences lucky = mcontext.getSharedPreferences("coding",Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = lucky.edit();
                    edit.putString("gametype",productList.get(getAdapterPosition()).getUrl());
                    edit.putString("gameid",productList.get(getAdapterPosition()).getId());
                    edit.putString("gamelayout",productList.get(getAdapterPosition()).getLayout());
                    edit.apply();
                    String GameLayout = productList.get(getAdapterPosition()).getLayout();
                    if (GameLayout.equalsIgnoreCase("0")) {
                        Intent intent = new Intent(mcontext, PracticeWebViewOne.class);

                        mcontext.startActivity(intent);
                    }else if (GameLayout.equalsIgnoreCase("1")) {
                        Intent intent = new Intent(mcontext, PracticeWebViewTwo.class);

                        mcontext.startActivity(intent);
                    }
                }
            });


            linearlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (SettingsPreferences.getVibration(getApplicationContext())) {
                        StaticUtils.vibrate(getApplicationContext(), StaticUtils.VIBRATION_DURATION);
                    }
                    if (SettingsPreferences.getSoundEnableDisable(getApplicationContext())) {
                        StaticUtils.backSoundonclick(getApplicationContext());
                    }
                    SharedPreferences lucky = mcontext.getSharedPreferences("coding",Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = lucky.edit();
                    edit.putString("gametype",productList.get(getAdapterPosition()).getUrl());
                    edit.putString("gameid",productList.get(getAdapterPosition()).getId());
                    edit.putString("gamelayout",productList.get(getAdapterPosition()).getLayout());
                    edit.apply();
                    String GameLayout = productList.get(getAdapterPosition()).getLayout();

                    if (GameLayout.equalsIgnoreCase("0")) {
                        Intent intent = new Intent(mcontext, PracticeWebViewOne.class);

                        mcontext.startActivity(intent);
                    }else if (GameLayout.equalsIgnoreCase("1")) {
                        Intent intent = new Intent(mcontext, PracticeWebViewTwo.class);

                        mcontext.startActivity(intent);
                    }

                }
            });

        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
//            clickListener.onClick(view, getPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onClick(view, getPosition(), true);
            return true;
        }
    }

}


