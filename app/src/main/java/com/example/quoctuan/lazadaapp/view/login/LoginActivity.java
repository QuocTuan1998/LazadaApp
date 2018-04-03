package com.example.quoctuan.lazadaapp.view.login;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;

import com.example.quoctuan.lazadaapp.R;
import com.example.quoctuan.lazadaapp.adapter.ViewPagerAdapterLogin;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.quoctuan.lazadaapp",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

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
