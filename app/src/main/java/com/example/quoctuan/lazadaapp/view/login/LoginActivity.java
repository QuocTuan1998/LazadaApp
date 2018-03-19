package com.example.quoctuan.lazadaapp.view.login;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.quoctuan.lazadaapp.R;
import com.example.quoctuan.lazadaapp.adapter.ViewPagerAdapterLogin;

public class LoginActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        tabLayout = findViewById(R.id.tab_login);
        viewPager = findViewById(R.id.view_pager_login);
        toolbar = findViewById(R.id.toolbar_login);

        setSupportActionBar(toolbar);
        ViewPagerAdapterLogin viewPagerAdapterLogin = new ViewPagerAdapterLogin(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapterLogin);
        viewPagerAdapterLogin.notifyDataSetChanged();

        tabLayout.setupWithViewPager(viewPager);
    }
}