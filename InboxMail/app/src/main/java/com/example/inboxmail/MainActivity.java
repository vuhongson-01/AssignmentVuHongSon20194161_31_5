package com.example.inboxmail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.inboxmail.fragment.InboxHome;
import com.google.android.material.navigation.NavigationView;

import com.example.inboxmail.Data.*;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private final int FRAGMENT_INBOX = 0;
    private int currentFragment = FRAGMENT_INBOX;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ArrayList<MailObject> lm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Data data = new Data();
        lm = data.ListMail;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new InboxHome());
        navigationView.getMenu().findItem(R.id.inbox_home).setChecked(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.inbox_home){
            if (currentFragment != FRAGMENT_INBOX){
                replaceFragment(new InboxHome());
                currentFragment = FRAGMENT_INBOX;
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_fragment, fragment);
        transaction.commit();
    }
}