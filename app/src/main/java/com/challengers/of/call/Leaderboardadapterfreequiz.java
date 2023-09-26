package com.challengers.of.call;

import android.content.Context;
import android.graphics.Color;
import androidx.cardview.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.challengers.of.call.data.Getleaderboarddatafreequiz;
import com.challengers.of.call.helper.GlideCircleTransformation;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class Leaderboardadapterfreequiz extends ArrayAdapter {
    List list = new ArrayList();
    public Leaderboardadapterfreequiz(Context context, int resource) {
        super(context, resource);
    }
    public void add(Getleaderboarddatafreequiz object) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        view = convertView;
        Leaderboardadapterfreequiz.viewHolder viewholder;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_leaderboard_row, parent, false);
            viewholder = new viewHolder();
            viewholder.tvNickname = (TextView) view.findViewById(R.id.textView);
            viewholder.tvPoints = (TextView) view.findViewById(R.id.textView2);
            viewholder.tvWiningamount = (TextView) view.findViewById(R.id.textView3);
            viewholder.tvUserid = (TextView) view.findViewById(R.id.textView4);
            viewholder.cardView=view.findViewById(R.id.card);
            viewholder.tvprize = (TextView) view.findViewById(R.id.prize);
            viewholder.ivuser=view.findViewById(R.id.ivuser);
            viewholder.l1=view.findViewById(R.id.l1);
            view.setTag(viewholder);
        } else {
            viewholder = (Leaderboardadapterfreequiz.viewHolder) view.getTag();
        }
        final Getleaderboarddatafreequiz getselfdata = (Getleaderboarddatafreequiz) this.getItem(position);
        viewholder.tvNickname.setText(getselfdata.getSno());
        viewholder.tvPoints.setText( getselfdata.getContestname());
        viewholder.tvWiningamount.setText( getselfdata.getPoints());
        viewholder.tvUserid.setText( getselfdata.getUserid());
        if (getselfdata.getImage() <30) {
            if (getselfdata.getImageurl() != null && getselfdata.getImageurl() != "") {
                Glide.with(getApplicationContext()).load("http://callofchallengers.com/coc/images/users/" + getselfdata.getImageurl())
                        .crossFade()
                        .placeholder(R.drawable.logo)
                        .error(R.drawable.logo)
                        .bitmapTransform(new GlideCircleTransformation(getApplicationContext()))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .skipMemoryCache(false)
                        .into(viewholder.ivuser);
            }
        }else {
            Glide.with(getApplicationContext()).load(getselfdata.getImageurl())
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
//                Intent intent = new Intent(getContext(),Winnerdetail.class);
//                intent.putExtra("Winnerid",getselfdata.getUserid());
//                intent.putExtra("Name",getselfdata.getContestname());
//                intent.putExtra("image",getselfdata.getImageurl());
//                getContext().startActivity(intent);
            }
        });
        int srno=getselfdata.getColor();


        int lprize=getselfdata.getPrize();


        if(lprize==1)
        {
            viewholder.tvprize.setText("₹ "+getselfdata.getFirstprize());
            viewholder.tvprize.setBackgroundColor(Color.parseColor("#8BC34A"));
        }else if(lprize == 2){

            viewholder.tvprize.setText("₹ "+getselfdata.getSecondprize());
            viewholder.tvprize.setBackgroundColor(Color.parseColor("#8BC34A"));

        }else if(lprize == 3){

            viewholder.tvprize.setText("₹ "+getselfdata.getThirdprize());
            viewholder.tvprize.setBackgroundColor(Color.parseColor("#8BC34A"));

        }else if(lprize == 4){
            if (getselfdata.getFourprize().equalsIgnoreCase("0") ){
                viewholder.tvprize.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }else {
                viewholder.tvprize.setText("₹ "+getselfdata.getFourprize());
                viewholder.tvprize.setBackgroundColor(Color.parseColor("#8BC34A"));
            }


        }else if(lprize == 5){
            if (getselfdata.getFiveprize().equalsIgnoreCase("0") ){
                viewholder.tvprize.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }else {
                viewholder.tvprize.setText("₹ "+getselfdata.getFiveprize());
                viewholder.tvprize.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
        }else if(lprize == 6){
            if (getselfdata.getSixprize().equalsIgnoreCase("0") ){
                viewholder.tvprize.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }else {
                viewholder.tvprize.setText("₹ "+getselfdata.getSixprize());
                viewholder.tvprize.setBackgroundColor(Color.parseColor("#8BC34A"));
            }

        }else if(lprize == 7){
            if (getselfdata.getSevenprize().equalsIgnoreCase("0") ){
                viewholder.tvprize.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }else {

                viewholder.tvprize.setText("₹ "+getselfdata.getSevenprize());
                viewholder.tvprize.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
        }else if(lprize == 8){
            if (getselfdata.getErthprize().equalsIgnoreCase("0") ){
                viewholder.tvprize.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }else {
                viewholder.tvprize.setText("₹ "+getselfdata.getErthprize());
                viewholder.tvprize.setBackgroundColor(Color.parseColor("#8BC34A"));
            }

        }else if(lprize == 9){
            if (getselfdata.getNineprize().equalsIgnoreCase("0") ){
                viewholder.tvprize.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }else {

                viewholder.tvprize.setText("₹ "+getselfdata.getNineprize());
                viewholder.tvprize.setBackgroundColor(Color.parseColor("#8BC34A"));
            }

        }else if(lprize == 10){
            if (getselfdata.getTenprize().equalsIgnoreCase("0") ){
                viewholder.tvprize.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }else {


                viewholder.tvprize.setText("₹ "+getselfdata.getTenprize());
                viewholder.tvprize.setBackgroundColor(Color.parseColor("#8BC34A"));
            }

        }else{

            viewholder.tvprize.setText("");
            viewholder.tvprize.setBackgroundColor(Color.parseColor("#FFFFFF"));

        }

        if(srno==1)
        {
            //00BD53
            //00902E
            viewholder.l1.setBackgroundResource(R.drawable.grad3);
            viewholder.tvNickname.setTextColor(Color.parseColor("#FFFFFF"));
            viewholder.tvPoints.setTextColor(Color.parseColor("#000000"));
            viewholder.tvWiningamount.setTextColor(Color.parseColor("#000000"));
            //viewholder.cardView.setCardBackgroundColor(Color.parseColor("#00902E"));
        }
        else
        {
            viewholder.l1.setBackgroundResource(R.drawable.grad4);
            viewholder.tvNickname.setTextColor(Color.parseColor("#FFFFFF"));
            viewholder.tvPoints.setTextColor(Color.parseColor("#000000"));
            viewholder.tvWiningamount.setTextColor(Color.parseColor("#000000"));
            //viewholder.cardView.setCardBackgroundColor(0);
        }

        setFadeAnimation(view);
        return view;
    }

    static class viewHolder {
        public TextView tvNickname,tvPoints,tvWiningamount,tvTimer,tvUserid,tvprize;
        CardView cardView;
        LinearLayout l1;
        ImageView ivuser;
    }
    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(200);
        view.startAnimation(anim);
    }
}