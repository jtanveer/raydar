package com.jtanveer.raydar.root;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jtanveer.raydar.login.LoginFragment;
import com.jtanveer.raydar.signup.SignupFragment;

public class TabPagerAdapter extends FragmentPagerAdapter {

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return LoginFragment.newInstance();
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return SignupFragment.newInstance();
            default:
                return LoginFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}
