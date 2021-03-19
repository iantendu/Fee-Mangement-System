package com.example.groupcat2;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    public MyAdapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {


        Fragment fragment = null;
        if (position == 0) {
            fragment = new Registration();
        } else if (position == 1) {
            fragment = new Fees();
        }
        else  if(position == 2)
        {
            fragment = new Unitselection();

        }

            return fragment;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
