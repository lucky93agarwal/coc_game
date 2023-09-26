package com.challengers.of.call.fragment;
import android.os.Bundle;

import com.challengers.of.call.Banned_APP.bannedapp;
import com.challengers.of.call.testing.SettingsPreferences;
import com.challengers.of.call.testing.StaticUtils;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.challengers.of.call.R;

public class Fragmenttab extends Fragment {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    public static ViewPager mViewPager;
    public static TabLayout tabLayout;
    private static bannedapp bannedapps;
    String go;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView;
        rootView = inflater.inflate(R.layout.fragmenttab, container, false);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager = (ViewPager) rootView.findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(3);
        bannedapps = new bannedapp(getActivity());
        bannedapps.bannedbig();
//        mViewPager.setCurrentItem(1);
        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        go= getArguments().getString("goto");
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        if(go.equalsIgnoreCase("challenger")) {
            tabLayout.getTabAt(1).select();
        }
        else if(go.equalsIgnoreCase("Result"))
        {
            tabLayout.getTabAt(2).select();
        }
        return rootView;
    }
    public void settab(int index)
    {
        tabLayout.getTabAt(index).select();
    }
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:

                    Fragmentcontest fragmentcontest= new Fragmentcontest();
                    return fragmentcontest;
                case 1:


                    
                    Fragmentchallenges fragmentchallenges= new Fragmentchallenges();
                    return fragmentchallenges;
                case 2:


                    return new Fragmentresult();
            }
            return null;
        }
        @Override
        public int getCount() {
            return 3;
        }
    }

    public void addcounter(int index,int counter)
    {
        View child = getLayoutInflater().inflate(R.layout.tab_notification_badge, null);
        TabLayout.Tab tab=tabLayout.getTabAt(2);
        ViewGroup.LayoutParams params = tabLayout.getLayoutParams();
        params.height = 70;
        tabLayout.setLayoutParams(params);
        ViewGroup view=tab.view;
        view.addView(child);
    }
}
