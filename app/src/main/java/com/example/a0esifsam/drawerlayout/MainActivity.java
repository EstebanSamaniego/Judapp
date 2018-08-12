package com.example.a0esifsam.drawerlayout;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.a0esifsam.drawerlayout.model.DbHandler;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
            implements  Activity.OnFragmentInteractionListener,
                        Dashboard.OnFragmentInteractionListener,
                        Events.OnFragmentInteractionListener,
                        Logout.OnFragmentInteractionListener,
                        Search.OnFragmentInteractionListener,
                        Settings.OnFragmentInteractionListener{

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.navView);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupDrawerContent(navigationView);
    }

    public void selectItemDrawer(MenuItem menuItem){
        Fragment myFragment = null;
        Class fragmentClass;

        //Checamos que opcion entre los framgmentos hay que usar
        switch (menuItem.getItemId()){
            case R.id.db:
                fragmentClass = Dashboard.class;
                break;
            case R.id.event:
                fragmentClass = Events.class;
                break;
            case R.id.search:
                fragmentClass = Search.class;
                break;
            case R.id.settings:
                fragmentClass = Settings.class;
                break;
            case R.id.activity:
                fragmentClass = Activity.class;
                break;
            case R.id.logout:
                fragmentClass = Logout.class;
                break;
            default:
                fragmentClass = Dashboard.class;
                break;
        }

        //Instanciamos la clase especifica que se eligio
        try{
            myFragment = (Fragment) fragmentClass.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }

        //Abrimos el fragment correspondiente
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentLayout, myFragment).commit();

        //Seteamos algunas cosillas ya que el fragment quedo listo
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawerLayout.closeDrawers();
    }

    private void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                selectItemDrawer(menuItem);
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
