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
import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.ItemClickListener;
import com.challengers.of.call.Main2Activity;
import com.challengers.of.call.R;
import com.challengers.of.call.data.GetDataGameType;
import com.challengers.of.call.testing.SettingsPreferences;
import com.challengers.of.call.testing.StaticUtils;

import java.util.List;

import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.decrypt;
import static com.facebook.FacebookSdk.getApplicationContext;

public class gametypeAdapter extends RecyclerView.Adapter<gametypeAdapter.ViewHolder> {
    public static Context mcontext;

    public boolean run = false;
    public static List<GetDataGameType> productList;
    public static boolean runtimer = true;
    public Context context;
    ViewHolder view;
    public String imgs,names;

    public GetDataGameType getselfdata;
    String Contest = "";
    private ljsflsj good;


    ViewHolder viewHolder;

    public gametypeAdapter(Context context, List<GetDataGameType> productList) {
        super();
        this.context = context;
        this.productList = productList;
        mcontext = context;
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


        names = decrypt(good.key, productList.get(i).getName());
        viewHolder.tvname.setText(names);


        if (getselfdata.getImg() != null && getselfdata.getImg() != "") {
            imgs = decrypt(good.key, productList.get(i).getImg());
            Glide.with(getApplicationContext()).load("http://callofchallengers.com/coc/coc_arena/challengerGame/img/" + imgs)
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
                    SharedPreferences lucky = mcontext.getSharedPreferences("coding",Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = lucky.edit();
                    edit.putString("gametype",productList.get(getAdapterPosition()).getUrl());
                    edit.putString("gameid",productList.get(getAdapterPosition()).getId());
                    edit.putString("gamelayout",productList.get(getAdapterPosition()).getLayout());
                    edit.putString("name",productList.get(getAdapterPosition()).getName());
                    edit.apply();
                    if (SettingsPreferences.getVibration(mcontext)) {
                        StaticUtils.vibrate(mcontext, StaticUtils.VIBRATION_DURATION);
                    }
                    if (SettingsPreferences.getSoundEnableDisable(mcontext)) {
                        StaticUtils.backSoundonclick(mcontext);
                    }

                    Intent intent = new Intent(mcontext, Main2Activity.class);
                    intent.putExtra("goto", "challenger");

                    intent.putExtra("Totalcontest", productList.get(getAdapterPosition()).getTotalcontest());
                    intent.putExtra("gamename",productList.get(getAdapterPosition()).getName());
                    mcontext.startActivity(intent);
//                    activity.overridePendingTransition(R.anim.slide_from_right, R.anim.slideout_from_left);
                }
            });

            ivname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (SettingsPreferences.getVibration(mcontext)) {
                        StaticUtils.vibrate(mcontext, StaticUtils.VIBRATION_DURATION);
                    }
                    if (SettingsPreferences.getSoundEnableDisable(mcontext)) {
                        StaticUtils.backSoundonclick(mcontext);
                    }
                    SharedPreferences lucky = mcontext.getSharedPreferences("coding",Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = lucky.edit();
                    edit.putString("gametype",productList.get(getAdapterPosition()).getUrl());
                    edit.putString("gameid",productList.get(getAdapterPosition()).getId());
                    edit.putString("gamelayout",productList.get(getAdapterPosition()).getLayout());
                    edit.putString("name",productList.get(getAdapterPosition()).getName());
                    edit.apply();

                    Intent intent = new Intent(mcontext, Main2Activity.class);
                    intent.putExtra("goto", "challenger");
                    intent.putExtra("Totalcontest", productList.get(getAdapterPosition()).getTotalcontest());
                    intent.putExtra("gamename",productList.get(getAdapterPosition()).getName());
                    mcontext.startActivity(intent);
                }
            });


            linearlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (SettingsPreferences.getVibration(mcontext)) {
                        StaticUtils.vibrate(mcontext, StaticUtils.VIBRATION_DURATION);
                    }
                    if (SettingsPreferences.getSoundEnableDisable(mcontext)) {
                        StaticUtils.backSoundonclick(mcontext);
                    }
                    SharedPreferences lucky = mcontext.getSharedPreferences("coding",Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = lucky.edit();
                    edit.putString("gametype",productList.get(getAdapterPosition()).getUrl());
                    edit.putString("gameid",productList.get(getAdapterPosition()).getId());
                    edit.putString("gamelayout",productList.get(getAdapterPosition()).getLayout());
                    edit.putString("name",productList.get(getAdapterPosition()).getName());
                    edit.apply();

                    Intent intent = new Intent(mcontext, Main2Activity.class);
                    intent.putExtra("goto", "challenger");
                    intent.putExtra("Totalcontest", productList.get(getAdapterPosition()).getTotalcontest());
                    intent.putExtra("gamename",productList.get(getAdapterPosition()).getName());
                    mcontext.startActivity(intent);

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

