package com.challengers.of.call.adapter;
import android.content.Context;
import android.os.CountDownTimer;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.challengers.of.call.ItemClickListener;
import com.challengers.of.call.R;
import com.challengers.of.call.data.Getquestionwiseresultdata;

import java.util.List;

public class QuestionwiseresultAdapter extends RecyclerView.Adapter<QuestionwiseresultAdapter.ViewHolder> {
    List<Getquestionwiseresultdata> productList;
    CountDownTimer cTimer = null;
    Context context;
    ViewHolder view;
    long timertime=0;

    public QuestionwiseresultAdapter(Context context, List<Getquestionwiseresultdata> productList)
    {
        super();
        this.context = context;
        this.productList = productList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_questionwise_result_row, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder,final int i) {
        final Getquestionwiseresultdata getselfdata = productList.get(i);
        view=viewHolder;
        viewHolder.tvNickname.setText( getselfdata.getQuestionno());
        viewHolder.tvPoints.setText( getselfdata.getAnswer());
        viewHolder.tvWiningamount.setText( getselfdata.getTotalpoints());
        viewHolder.tvEntryfees.setText( getselfdata.getTime());
        viewHolder.tvTimer.setText(getselfdata.getPoints());
        //viewHolder.tvEntryfees.setText( getselfdata.getEntryfees());
        viewHolder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                }
                else
                    {
                }
            }
        });
    }




    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public ImageView imgThumbnail;
        public TextView tvNickname,tvPoints,tvWiningamount,tvTimer,tvEntryfees;
        private ItemClickListener clickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            //imgThumbnail = (ImageView) itemView.findViewById(R.id.ivImg);
            tvNickname = (TextView) itemView.findViewById(R.id.textView);
            tvPoints = (TextView) itemView.findViewById(R.id.textView2);
            tvWiningamount = (TextView) itemView.findViewById(R.id.textView3);
            tvTimer = (TextView) itemView.findViewById(R.id.textView4);
            tvEntryfees = (TextView) itemView.findViewById(R.id.textView5);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onClick(view, getPosition(), true);
            return true;
        }
    }

    void startTimer(final TextView textView) {

        cTimer = new CountDownTimer(timertime, 1) {
            public void onTick(long millisUntilFinished)
            {
                int seconds = (int) (millisUntilFinished / 1000) % 60 ;
                int minutes = (int) ((millisUntilFinished / (1000*60)) % 60);
                int hours   = (int) ((millisUntilFinished / (1000*60*60)) % 24);

                if(hours<10)
                {
                    hours= Integer.valueOf("0"+hours);
                }
                if(minutes<10)
                {
                    minutes= Integer.valueOf("0"+minutes);
                }
                if(seconds<10)
                {
                    seconds= Integer.valueOf("0"+seconds);
                }
                textView.setText(hours+":"+minutes+":"+seconds);
                //tvtimer.setText(TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)+"");
            }
            public void onFinish() {
                //Toast.makeText(Activityquiz.this,"Your Time has been finished",Toast.LENGTH_LONG).show();
            }
        };
        cTimer.start();
    }

    void cancelTimer() {
        if(cTimer!=null)
            cTimer.cancel();
    }

}

