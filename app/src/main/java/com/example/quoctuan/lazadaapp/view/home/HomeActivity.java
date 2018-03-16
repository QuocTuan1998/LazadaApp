package com.example.quoctuan.lazadaapp.view.home;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import com.example.quoctuan.lazadaapp.R;
import com.example.quoctuan.lazadaapp.adapter.ExpandAdapter;
import com.example.quoctuan.lazadaapp.adapter.ViewPagerAdapterHome;
import com.example.quoctuan.lazadaapp.model.object.LoaiSanPham;
import com.example.quoctuan.lazadaapp.presenter.home.handleMenu.PresenterLogicHandleMenu;
import com.example.quoctuan.lazadaapp.presenter.home.view.ViewHandleMenu;
import com.example.quoctuan.lazadaapp.view.login.LoginActivity;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements ViewHandleMenu {

    Toolbar toolbar;
    TabLayout tab;
    ViewPager view_pager;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    ExpandableListView expandableListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();

        //Handle Menu
        PresenterLogicHandleMenu logicHandleMenu = new PresenterLogicHandleMenu(this);
        logicHandleMenu.getListMenu();

    }

    private void initView() {

        // init view
        toolbar = findViewById(R.id.toolbar);
        tab = findViewById(R.id.tab);
        view_pager = findViewById(R.id.view_pager);
        drawerLayout = findViewById(R.id.drawer_layout);
        expandableListView = findViewById(R.id.expand_menu);

        // init Toolbar
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        // init DrawerLayout
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();


        //init TabLayout, ViewPager
        ViewPagerAdapterHome adapter = new ViewPagerAdapterHome(getSupportFragmentManager());
        view_pager.setAdapter(adapter);
        tab.setupWithViewPager(view_pager);

        // init expand listview

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.homemenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        int id = item.getItemId();

        switch (id) {
            case R.id.item_login:
                Intent iLogin = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(iLogin);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showMenu(List<LoaiSanPham> listLoaiSP) {
        ExpandAdapter adapter = new ExpandAdapter(HomeActivity.this, listLoaiSP);
        expandableListView.setAdapter(adapter);
        expandableListView.deferNotifyDataSetChanged();
    }
}
