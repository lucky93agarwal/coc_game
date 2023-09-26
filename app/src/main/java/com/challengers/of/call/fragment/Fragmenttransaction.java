package com.challengers.of.call.fragment;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.challengers.of.call.R;

public class Fragmenttransaction extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView;
        rootView = inflater.inflate(R.layout.activity_transparent, container,false);
        return rootView;
    }
}
