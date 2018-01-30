package com.example.cezar.e_conomic;

import android.content.Intent;
import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

public class WorkActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    private Button startButton;
    private Button pauseButton;
    private Button stopButton;
    private Button resetButton;

    private Chronometer mchronometer;

    private long lastPause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        setContentView(R.layout.frag1_layout);


        startButton = (Button) findViewById(R.id.buttonStart);
        pauseButton = (Button) findViewById(R.id.buttonPause);
        stopButton = (Button) findViewById(R.id.buttonStop);
        resetButton = (Button) findViewById(R.id.buttonReset);

        mchronometer = (Chronometer) findViewById(R.id.chronometer);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastPause != 0){
                    mchronometer.setBase(mchronometer.getBase() + SystemClock.elapsedRealtime() - lastPause);
                }
                else{
                    mchronometer.setBase(SystemClock.elapsedRealtime());
                }
                mchronometer.start();
                startButton.setEnabled(false);
                pauseButton.setEnabled(true);
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastPause = SystemClock.elapsedRealtime();
                mchronometer.stop();
                pauseButton.setEnabled(false);
                startButton.setEnabled(true);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mchronometer.stop();
                mchronometer.setBase(SystemClock.elapsedRealtime());
                lastPause = 0;
                startButton.setEnabled(true);
                pauseButton.setEnabled(false);

            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        //mViewPager = (ViewPager) findViewById(R.id.container);
        //mViewPager.setAdapter(mSectionsPagerAdapter);

        //TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        //mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_work, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.id_language) {
            Toast.makeText(this, "Language is clicked", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.id_logout) {
            Intent intent = new Intent(WorkActivity.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    Frag1 frag_1 = new Frag1();
                    return frag_1;
                case 1:
                    Frag2 frag_2 = new Frag2();
                    return frag_2;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        public CharSequence getPageTitle(int position){
            switch (position){
                case 0:
                    return "Record now";
                case 1:
                    return "Past records";
            }
            return null;
        }
    }
}
