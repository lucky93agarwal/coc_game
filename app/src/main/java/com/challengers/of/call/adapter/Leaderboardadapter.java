package com.challengers.of.call.adapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import androidx.cardview.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.R;
import com.challengers.of.call.Winnerdetail;
import com.challengers.of.call.data.Getleaderboarddata;
import com.challengers.of.call.helper.GlideCircleTransformation;

import java.util.ArrayList;
import java.util.List;

import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.decrypt;
import static com.facebook.FacebookSdk.getApplicationContext;

public class Leaderboardadapter extends ArrayAdapter {
    List list = new ArrayList();
    private ljsflsj good;
    public Leaderboardadapter(Context context, int resource) {
        super(context, resource);
//        this.context = context;

    }
    public void add(Getleaderboarddata object) {

        super.add(object);
        list.add(object);
    }
    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        View view;
        view = convertView;
        viewHolder viewholder;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_leaderboard_row, parent, false);
            view = layoutInflater.inflate(R.layout.list_leaderboard_row, parent, false);
            viewholder = new viewHolder();
            viewholder.tvNickname = (TextView) view.findViewById(R.id.textView);
            viewholder.tvPoints = (TextView) view.findViewById(R.id.textView2);
            viewholder.tvWiningamount = (TextView) view.findViewById(R.id.textView3);
            viewholder.tvNaja = (TextView) view.findViewById(R.id.naja);
//            viewholder.tvUserid = (TextView) view.findViewById(R.id.textView4);
            viewholder.tvprize = (TextView) view.findViewById(R.id.prize);
            viewholder.cardView=view.findViewById(R.id.card);
            viewholder.ivuser=view.findViewById(R.id.ivuser);
            viewholder.l1=view.findViewById(R.id.l1);
            view.setTag(viewholder);
        } else {
            viewholder = (viewHolder) view.getTag();
        }
        final Getleaderboarddata getselfdata = (Getleaderboarddata) this.getItem(position);
        viewholder.tvNickname.setText(getselfdata.getSno());
        String Contestname = decrypt(good.key, getselfdata.getContestname());
        viewholder.tvPoints.setText(Contestname);
        String Points = decrypt(good.key, getselfdata.getPoints());
        viewholder.tvWiningamount.setText(Points);
        viewholder.tvNaja.setText(getselfdata.getNaja());

//        viewholder.tvUserid.setText( getselfdata.getUserid());
        String Imageurl = decrypt(good.key, getselfdata.getImageurl());
        if (getselfdata.getImage() <30) {
            if (Imageurl != null && Imageurl != "") {
                Glide.with(getApplicationContext()).load("http://callofchallengers.com/coc/images/users/" + Imageurl)
                        .crossFade()
                        .placeholder(R.drawable.logo)
                        .error(R.drawable.logo)
                        .bitmapTransform(new GlideCircleTransformation(getApplicationContext()))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .skipMemoryCache(false)
                        .into(viewholder.ivuser);
            }
        }else {
            Glide.with(getApplicationContext()).load(Imageurl)
                    .crossFade()
                    .placeholder(R.drawable.logo)
                    .error(R.drawable.logo)
                    .bitmapTransform(new GlideCircleTransformation(getApplicationContext()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(false)
                    .into(viewholder.ivuser);
        }
        viewholder.ivuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Winnerdetail.class);
                intent.putExtra("Winnerid",getselfdata.getUserid());
                intent.putExtra("Name",getselfdata.getContestname());
                intent.putExtra("goto","Leader");
                intent.putExtra("image",getselfdata.getImageurl());
                getContext().startActivity(intent);



            }
        });

        int srno=getselfdata.getColor();

        int lprize=getselfdata.getPrize();
        String Firstprize = decrypt(good.key, getselfdata.getFirstprize());
        viewholder.tvprize.setText("₹ "+Firstprize);

        if(lprize==1)
        {

            viewholder.tvNickname.setBackgroundResource(R.drawable.gold);
        }else if(lprize == 2){


            viewholder.tvNickname.setBackgroundResource(R.drawable.gold);

        }else if(lprize == 3){


            viewholder.tvNickname.setBackgroundResource(R.drawable.gold);

        }
        else{
            viewholder.tvNickname.setBackgroundResource(R.drawable.silver);


        }








        setFadeAnimation(view);
        return view;
    }

    static class viewHolder {
        public TextView tvNickname,tvPoints,tvWiningamount,tvTimer,tvUserid, tvprize, tvNaja;
        CardView cardView;
        LinearLayout l1;
        ImageView ivuser;
    }
    private void setFadeAnimation(View view) {
        Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.fade_in);


        view.startAnimation(animation);
    }
}