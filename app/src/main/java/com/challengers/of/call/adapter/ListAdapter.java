package com.challengers.of.call.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.R;
import com.challengers.of.call.data.Getquestionwiseresultdata;
import java.util.ArrayList;
import java.util.List;

import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.decrypt;

public class ListAdapter extends ArrayAdapter {
    private ljsflsj good;
    List list = new ArrayList();
    public ListAdapter(Context context, int resource) {
        super(context, resource);
    }
    public void add(Getquestionwiseresultdata object) {
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
        viewHolder viewholder;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_questionwise_result_row, parent, false);
            viewholder = new viewHolder();
            viewholder.tvNickname = (TextView) view.findViewById(R.id.textView);
            viewholder.tvPoints = (TextView) view.findViewById(R.id.textView2);
            viewholder.tvWiningamount = (TextView) view.findViewById(R.id.textView3);
            viewholder.tvTimer = (TextView) view.findViewById(R.id.textView4);
            viewholder.tvEntryfees = (TextView) view.findViewById(R.id.textView5);
            view.setTag(viewholder);
        } else {
            viewholder = (viewHolder) view.getTag();
        }
        Getquestionwiseresultdata getselfdata = (Getquestionwiseresultdata) this.getItem(position);
        viewholder.tvNickname.setText(decrypt(good.key,getselfdata.getQuestionno()));
        viewholder.tvPoints.setText(decrypt(good.key, getselfdata.getAnswer()));
        viewholder.tvWiningamount.setText(decrypt(good.key,getselfdata.getPoints()));
        viewholder.tvEntryfees.setText(decrypt(good.key, getselfdata.getTotalpoints()));
        String Time=decrypt(good.key, getselfdata.getTime());

        viewholder.tvTimer.setText(Time);
        return view;
    }

    static class viewHolder {
        ImageView imageView,imageView2,imageView3;
        public TextView tvNickname,tvPoints,tvWiningamount,tvTimer,tvEntryfees;
    }
}