package com.challengers.of.call;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.challengers.of.call.data.Getprductdata;

import java.util.ArrayList;
import java.util.List;
public class ListAdapter extends ArrayAdapter {
    List list = new ArrayList();
    public ListAdapter(Context context, int resource) {
        super(context, resource);
    }
    public void add(Getprductdata object) {
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
            view = layoutInflater.inflate(R.layout.list_challenger_row, parent, false);
            if (position % 2 == 1) {
                view.setBackgroundColor(Color.parseColor("#FFFFFF"));
            } else {
                view.setBackgroundColor(Color.parseColor("#E6E7E9"));
            }


            viewholder = new viewHolder();
            viewholder.sponsorid = (TextView) view.findViewById(R.id.textView);
            viewholder.agentid = (TextView) view.findViewById(R.id.textView2);
            viewholder.agentname = (TextView) view.findViewById(R.id.textView3);
            viewholder.rank = (TextView) view.findViewById(R.id.textView4);

            view.setTag(viewholder);
        } else {
            viewholder = (viewHolder) view.getTag();
        }
        Getprductdata getprductdata = (Getprductdata) this.getItem(position);
        viewholder.sponsorid.setText(getprductdata.getWinningamount());
        viewholder.agentid.setText(getprductdata.getEntryfees());
        viewholder.agentname.setText(getprductdata.getPoints());
        viewholder.rank.setText(getprductdata.getChase());
//        viewholder.agentname.setText(getprductdata.getAppid());
//        viewholder.rank.setText(getprductdata.getAppimage());
//        viewholder.challengeid.setText(getprductdata.getChallengeid());
//        Picasso.with(getContext())
//                .load(getprductdata.getAppimage())
//                .error(R.drawable.ic_launcher)
//                .into(viewholder.imageView);
//        if(getprductdata.getAction()!=null) {
//            if (getprductdata.getAction().equalsIgnoreCase("subscribe")) {
//                viewholder.imageView2.setVisibility(View.VISIBLE);
//                viewholder.imageView3.setVisibility(View.GONE);
//            } else {
//                viewholder.imageView2.setVisibility(View.GONE);
//                viewholder.imageView3.setVisibility(View.VISIBLE);
//            }
//        }
        return view;
    }
    static class viewHolder {
        ImageView imageView,imageView2,imageView3;
        TextView sponsorid, agentid, agentname, rank, date, challengeid;
    }
}