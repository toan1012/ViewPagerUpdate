package com.example.anonymous.viewpagertutorial.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.anonymous.viewpagertutorial.R;
import com.example.anonymous.viewpagertutorial.fragments.FilterFrg;
import com.example.anonymous.viewpagertutorial.fragments.HomeFrg;
import com.example.anonymous.viewpagertutorial.fragments.LikeFrg;
import com.example.anonymous.viewpagertutorial.fragments.SearchFrg;
import com.example.anonymous.viewpagertutorial.fragments.SettingFrg;

/**
 * Created by Anonymous on 12/1/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter{

    public static final int NUMBER = 5;
    public static final String[] TITLE = {"HOME","LIKE","FILTER","SEARCH","SETTING"};
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                 return new HomeFrg();
            case 1:
                return new LikeFrg();
            case 2:
                return new FilterFrg();
            case 3:
                 return new SearchFrg();
            case 4:
                return new SettingFrg();
        }
        return new HomeFrg();
    }

    @Override
    public int getCount() {
        return NUMBER;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLE[position];
    }


}
