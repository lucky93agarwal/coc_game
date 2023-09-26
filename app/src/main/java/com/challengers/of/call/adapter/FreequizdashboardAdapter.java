package com.challengers.of.call.adapter;
import android.content.Context;
import androidx.cardview.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.challengers.of.call.R;
import com.challengers.of.call.data.Getfreequizdashboarddata;
import java.util.ArrayList;
import java.util.List;
public class FreequizdashboardAdapter extends ArrayAdapter {
    List list = new ArrayList();
    public FreequizdashboardAdapter(Context context, int resource) {
        super(context, resource);
    }
    public void add(Getfreequizdashboarddata object) {
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
            view = layoutInflater.inflate(R.layout.list_freequizdashboard_row, parent, false);
            viewholder = new viewHolder();
            viewholder.tvNickname = (TextView) view.findViewById(R.id.textView);
            viewholder.tvPoints = (TextView) view.findViewById(R.id.textView2);
            viewholder.cardView = view.findViewById(R.id.card);
            viewholder.imageView=view.findViewById(R.id.image);
            view.setTag(viewholder);
        } else {
            viewholder = (viewHolder) view.getTag();
        }
        Getfreequizdashboarddata getselfdata = (Getfreequizdashboarddata) this.getItem(position);
        viewholder.tvNickname.setText(getselfdata.getFrom() + "-" + getselfdata.getTo());
        if (getselfdata.getType().equalsIgnoreCase("R")) {
            viewholder.tvPoints.setText(getselfdata.getPoint());
            viewholder.imageView.setImageResource(R.drawable.ic_rupeered);
        } else if (getselfdata.getType().equalsIgnoreCase("L")) {
            viewholder.tvPoints.setText(getselfdata.getPoint());
            viewholder.imageView.setImageResource(R.drawable.hearicon);
        }
        return view;
    }
    static class viewHolder {
        public TextView tvNickname, tvPoints, tvTimer;
        public CardView cardView;
        public ImageView imageView;
    }
}