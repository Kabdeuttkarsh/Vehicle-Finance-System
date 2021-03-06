package com.solutionstouch.omsaifinance.adapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.solutionstouch.omsaifinance.ui.dashboard.home.HomeFragment;

public class ViewPagerAdapter  extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


@NonNull
@Override
public Fragment getItem(int position)
{ Fragment fragment=null;
    if (position == 0)
        fragment = new HomeFragment();

    return fragment;
}

    @Override
    public int getCount()
    {
        return 3;
    }


    @Override
    public CharSequence getPageTitle(int position)
    {
        String title = null;
        if (position == 0)
            title = "Gallery";
        return title;
    }
}