package com.example.quoctuan.lazadaapp.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.quoctuan.lazadaapp.view.login.fragment.SignInFragment;
import com.example.quoctuan.lazadaapp.view.login.fragment.SignUpFragment;

/**
 * Created by quoctuan on 16/03/2018.
 */

public class ViewPagerAdapterLogin extends FragmentPagerAdapter{



    public ViewPagerAdapterLogin(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new SignInFragment();
            case 1:
                return new SignUpFragment();

            default: return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "Đăng nhập";
            case 1:
                return "Đăng ký";

            default: return null;
        }

    }
}
