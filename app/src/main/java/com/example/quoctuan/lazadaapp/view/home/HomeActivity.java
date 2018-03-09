package com.example.quoctuan.lazadaapp.view.home;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.quoctuan.lazadaapp.R;
import com.example.quoctuan.lazadaapp.adapter.ViewPagerAdapter;

public class HomeActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tab;
    ViewPager view_pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Toolbar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //TabLayout , ViewPager
        tab = findViewById(R.id.tab);
        view_pager = findViewById(R.id.view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        view_pager.setAdapter(adapter);
        tab.setupWithViewPager(view_pager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.homemenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
