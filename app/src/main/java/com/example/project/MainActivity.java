package com.example.project;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import androidx.annotation.Nullable;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.WindowDecorActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.ui.main.SectionsPagerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener {

    FirebaseAuth firebaseAuth;
    DrawerLayout drawerLayout;
    TextView  textView;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        firebaseAuth=FirebaseAuth.getInstance();
        tabs.setupWithViewPager(viewPager);
        Toolbar toolbar=findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        drawerLayout=findViewById(R.id.drawer);
        navigationView=findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(this);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        if(item.getItemId()==R.id.no1)
        {
            Intent intent=new Intent(this,recentlywatched.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.no2) {
            Intent intent = new Intent(this, favourites.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.no3) {
            Intent intent = new Intent(this, search.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.no4) {
            firebaseAuth.signOut();
            startActivity(new Intent(MainActivity.this,login.class));
            finish();
        }
        else if(item.getItemId()==R.id.no5) {
            Intent intent = new Intent(this, genre.class);
            startActivity(intent);
        }
        return false;
    }

    private void checkUserStaus()
    {
        FirebaseUser user=firebaseAuth.getCurrentUser();
        if(user!=null)
        {
                user=null;
        }
        else
        {
            startActivity(new Intent(MainActivity.this,login.class));
            finish();
        }
    }
    protected void onStart() {
        checkUserStaus();
        super.onStart();

    }

}