package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.calculator.fragment.CalculatorFragment;
import com.example.calculator.fragment.CurrencyFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.Stack;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final int FRAGMENT_CALCULATOR = 0;
    private static final int FRAGMENT_CURRENCY = 1;

    private int currentFragment = FRAGMENT_CALCULATOR;

    private DrawerLayout mDrawerLayout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mDrawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        replaceFragment(new CalculatorFragment());
        toolbar.setTitle("Calculator");
        navigationView.getMenu().findItem(R.id.calculate_home).setChecked(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.calculate_home){
            if (currentFragment != FRAGMENT_CALCULATOR){
                replaceFragment(new CalculatorFragment());
                toolbar.setTitle("Calculator");
                currentFragment = FRAGMENT_CALCULATOR;
            }
        }
        if (id == R.id.currency_home){
            if (currentFragment != FRAGMENT_CURRENCY){
                replaceFragment(new CurrencyFragment());
                toolbar.setTitle("Currency");
                currentFragment = FRAGMENT_CURRENCY;
            }
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }
  
}