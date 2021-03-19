package com.example.groupcat2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements  Registration.SendMessage, Fees.SendMessage2,Fees.SendMessage3{
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        //tabLayout.setupWithViewPager(viewPager);
        tabLayout.addTab(tabLayout.newTab().setText("Register"));
        tabLayout.addTab(tabLayout.newTab().setText("Fees"));
        tabLayout.addTab(tabLayout.newTab().setText("Units"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final MyAdapter adapter = new MyAdapter(this,getSupportFragmentManager(),
                tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }


    @Override
    public void sendData2(String message2) {
        String tag2 = "android:switcher:" + R.id.viewPager + ":" + 2;
        Unitselection f = (Unitselection) getSupportFragmentManager().findFragmentByTag(tag2);
        f.displayReceivedData(message2);

    }

    @Override
    public void sendData(String message) {
            String tag = "android:switcher:" + R.id.viewPager + ":" + 1;
            Fees f = (Fees) getSupportFragmentManager().findFragmentByTag(tag);
            f.displayReceivedData(message);



    }

    @Override
    public void sendData3(int fee) {
        String tag2 = "android:switcher:" + R.id.viewPager + ":" + 2;
        Unitselection f = (Unitselection) getSupportFragmentManager().findFragmentByTag(tag2);
        f.displayfees(fee);
    }
}