package com.example.hw04;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.widget.Toolbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
 implements NavigationView.OnNavigationItemSelectedListener {
    String TAG="";
    private Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        FragmentManager fm= getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        HomeFragment hf= new HomeFragment();
        ft.add(R.id.fragment_host,hf);
        ft.commit();

        toolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView = (NavigationView)findViewById( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener( this );

         drawer = (DrawerLayout) findViewById( R.id.drawer_layout );

        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this, drawer, toolbar,
                        R.string.nav_open_drawer, R.string.nav_close_drawer);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.app_bar_items, menu );
            return super.onCreateOptionsMenu( menu );
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.settings:
                Log.i(TAG, "Settings clicked");
                Toast toast = Toast.makeText(this,
                        "Settings clicked", Toast.LENGTH_SHORT);
                toast.show();
                return true;

            case R.id.share:
                Log.i(TAG, "Share clicked");
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment newFragment;
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        Log.i(TAG, "home clicked");
                        newFragment = new HomeFragment();
                        break;

                    case R.id.nav_help:
                        Log.i(TAG, "help clicked");
                        newFragment = new AboutFragment();
                        break;

                    default:
                        newFragment = new HomeFragment();
                        break;
                }

            drawer.closeDrawer( GravityCompat.START);

                FragmentTransaction transaction =
                        getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_host, newFragment);
                transaction.addToBackStack(null);

                transaction.commit();

                return false;
            }
}
