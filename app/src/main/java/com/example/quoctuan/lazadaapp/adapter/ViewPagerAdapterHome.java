package com.example.quoctuan.lazadaapp.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.quoctuan.lazadaapp.view.home.fragment.ElectronicFragment;
import com.example.quoctuan.lazadaapp.view.home.fragment.HotFragment;
import com.example.quoctuan.lazadaapp.view.home.fragment.HouseAndLifeFragment;
import com.example.quoctuan.lazadaapp.view.home.fragment.MakeUpFragment;
import com.example.quoctuan.lazadaapp.view.home.fragment.MomAndChildFragment;
import com.example.quoctuan.lazadaapp.view.home.fragment.SaleFragment;
import com.example.quoctuan.lazadaapp.view.home.fragment.SportAndTravelFragment;
import com.example.quoctuan.lazadaapp.view.home.fragment.StyleFragment;
import com.example.quoctuan.lazadaapp.view.home.fragment.TradeMarkFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quoctuan on 06/03/2018.
 */

public class ViewPagerAdapterHome extends FragmentPagerAdapter {

    List<Fragment> list_fragment = new ArrayList<Fragment>();
    List<String> title_fragment = new ArrayList<String>();

    public ViewPagerAdapterHome(FragmentManager fm) {
        super(fm);
        list_fragment.add(new ElectronicFragment());
        list_fragment.add(new HotFragment());
        list_fragment.add(new HouseAndLifeFragment());
        list_fragment.add(new MakeUpFragment());
        list_fragment.add(new MomAndChildFragment());
        list_fragment.add(new SaleFragment());
        list_fragment.add(new SportAndTravelFragment());
        list_fragment.add(new StyleFragment());
        list_fragment.add(new TradeMarkFragment());

        title_fragment.add("Điện tử");
        title_fragment.add("Nổi bật");
        title_fragment.add("Nhà cửa và đời sống");
        title_fragment.add("Làm đẹp");
        title_fragment.add("Mẹ và bé");
        title_fragment.add("Giảm giá");
        title_fragment.add("Thể thao và du lịch");
        title_fragment.add("Thời trang");
        title_fragment.add("Thương hiệu");
    }

    @Override
    public Fragment getItem(int position) {

        return list_fragment.get(position);
    }

    @Override
    public int getCount() {
        return list_fragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        return title_fragment.get(position);
    }
}
