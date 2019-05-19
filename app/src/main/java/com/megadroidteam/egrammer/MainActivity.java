package com.megadroidteam.egrammer;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.megadroidteam.egrammer.Database.DatabaseAssets;
import com.megadroidteam.egrammer.activities.SettingActivity;
import com.megadroidteam.egrammer.fragment.FavoriteIdiomsFragment;
import com.megadroidteam.egrammer.fragment.FavoriteTopicsFragment;
import com.megadroidteam.egrammer.fragment.HomeFragment;
import com.megadroidteam.egrammer.fragment.IrregularVerbsFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    SlidingPaneLayout mSlidingPanel;
    Toolbar toolbar;
    BottomNavigationView bottomNav;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
   // AppCompatImageView to;
    DatabaseAssets databaseAssets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        databaseAssets = new DatabaseAssets(getApplicationContext());
       // to=findViewById(R.id.to);
        /*toggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,toolbar,R.string.open,R.string.close);
        toggle.syncState()*/;
       // navigationView =  findViewById(R.id.navigation);
        mSlidingPanel = (SlidingPaneLayout) findViewById(R.id.SlidingPanel);
        mSlidingPanel.setPanelSlideListener(panelListener);
        mSlidingPanel.setParallaxDistance(100);
        mSlidingPanel.setSliderFadeColor(ContextCompat.getColor(this, android.R.color.transparent));
       /* to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlidingPanel.openPane();
            }
        });*/

        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();

      /*  navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()){

                    case R.id.itm_home:
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.itm_setting:
                        Intent intent = new Intent(getApplicationContext(),SettingActivity.class);
                        startActivity(intent);
                        break;


                }


                return false;
            }
        });*/
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener;

            {
                navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.nav_favorite_topic:
                        selectedFragment = new FavoriteTopicsFragment();
                        break;
                    case R.id.nav_favorite_idioms:
                        selectedFragment = new FavoriteIdiomsFragment();
                        break;
                    case R.id.nav_irregular_verbs:

                        selectedFragment = new IrregularVerbsFragment();
                        break;


                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        selectedFragment).commit();
                return true;

            }
        };

    }
    SlidingPaneLayout.PanelSlideListener panelListener = new SlidingPaneLayout.PanelSlideListener(){

        @Override
        public void onPanelClosed(View arg0) {
            // TODO Auto-genxxerated method stub

        }

        @Override
        public void onPanelOpened(View arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPanelSlide(View arg0, float arg1) {
            // TODO Auto-generated method stub

        }

    };


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                mSlidingPanel.closePane();
           //     setButtonSelected(v);
                Toast.makeText(this, "Button explore clicked!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:
                mSlidingPanel.closePane();
                Toast.makeText(this, "Button timeline clicked!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button3:
                mSlidingPanel.closePane();
                Toast.makeText(this, "Button photos clicked!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button4:
                mSlidingPanel.closePane();
                Toast.makeText(this, "Button videos clicked!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button5:
                mSlidingPanel.closePane();
                Toast.makeText(this, "Button messages clicked!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnLogout:
                mSlidingPanel.closePane();
                Toast.makeText(this, "Button logout clicked!", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.btnSetting:
                mSlidingPanel.closePane();
                Toast.makeText(this, "Button setting clicked!", Toast.LENGTH_SHORT).show();

                break;
            default:
                break;
        }

    }
    private void setButtonSelected(final View v){
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.menuContainer);
        for(int index = 0; index<(viewGroup).getChildCount(); ++index) {
            View nextChild = (viewGroup).getChildAt(index);
            nextChild.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.menunavigation4_buttonmenu));
        }

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                v.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.menuNavigation4menuSelected));
            }
        }, 200);
    }
}
