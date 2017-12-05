package com.example.anonymous.viewpagertutorial;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.anonymous.viewpagertutorial.adapters.ViewPagerAdapter;
import com.example.anonymous.viewpagertutorial.constants.Constant;
import com.example.anonymous.viewpagertutorial.fragments.HomeFrg;
import com.example.anonymous.viewpagertutorial.fragments.LikeFrg;
import com.example.anonymous.viewpagertutorial.model.Follower_Item;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static com.example.anonymous.viewpagertutorial.constants.Constant.MyPREFERENCES;

public class MainActivity extends AppCompatActivity implements HomeFrg.OnSendToActivity{
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ViewPagerAdapter adapter;
    private List<Follower_Item> listFlower = new ArrayList<Follower_Item>();
    SharedPreferences shref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        setIconTab();
        //savePreference();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==1){


                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void savePreference() {
        shref = getApplicationContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = gson.toJson(listFlower);
        Log.e("json",json);
        editor = shref.edit();
        editor.putString(Constant.MY_KEY, json);
        editor.apply();
        Log.e("lan1","savePreference duoc goi truoc");
    }

    private void initComponents() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void setIconTab(){
        tabLayout.getTabAt(0).setIcon(Constant.THUMNAIL[0]);
        tabLayout.getTabAt(1).setIcon(Constant.THUMNAIL[1]);
        tabLayout.getTabAt(2).setIcon(Constant.THUMNAIL[2]);
        tabLayout.getTabAt(3).setIcon(Constant.THUMNAIL[3]);
        tabLayout.getTabAt(4).setIcon(Constant.THUMNAIL[4]);
    }

    @Override
    public void senListDataToActivity(List<Follower_Item> list) {
        listFlower = list;
        String tag = "android:switcher:" + R.id.viewPager + ":" + 1;
        LikeFrg likeFrg = (LikeFrg) getSupportFragmentManager().findFragmentByTag(tag);
        likeFrg.receiverData(list);
        Log.e("lan1","senListDataToActivity duoc goi truoc");
        savePreference();
    }
}
