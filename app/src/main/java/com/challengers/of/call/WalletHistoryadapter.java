package com.challengers.of.call;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.data.GetwalletHistory;

import java.util.ArrayList;
import java.util.List;

import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.decrypt;

public class WalletHistoryadapter extends ArrayAdapter {
    List list = new ArrayList();
    private ljsflsj good;
    public WalletHistoryadapter(Context context, int resource) {
        super(context, resource);
    }
    public void add(GetwalletHistory object) {
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
            view = layoutInflater.inflate(R.layout.list_wallet_history_header, parent, false);
            viewholder = new viewHolder();
            viewholder.tvDate = (TextView) view.findViewById(R.id.datetv);
            viewholder.tvWallettype = (TextView) view.findViewById(R.id.wallettypetv);
            viewholder.tvrupess = (TextView) view.findViewById(R.id.rupesstv);
            viewholder.tvCreditDebit = (TextView) view.findViewById(R.id.creditanddebittv);

            view.setTag(viewholder);
        } else {
            viewholder = (viewHolder) view.getTag();
        }

        GetwalletHistory getselfdata = (GetwalletHistory) this.getItem(position);

        viewholder.tvDate.setText(decrypt(good.key, getselfdata.getDate()));
        viewholder.tvWallettype.setText(decrypt(good.key, getselfdata.getWalletType()));
        viewholder.tvrupess.setText("₹"+ decrypt(good.key, getselfdata.getRupess()));
        viewholder.tvCreditDebit.setText(decrypt(good.key, getselfdata.getCreditDebit()));

        return view;
    }

    static class viewHolder {
        public TextView tvDate,tvWallettype,tvrupess,tvCreditDebit;
    }
}
