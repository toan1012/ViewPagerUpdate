package com.example.anonymous.viewpagertutorial.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.anonymous.viewpagertutorial.R;
import com.example.anonymous.viewpagertutorial.adapters.HomeAdapter;
import com.example.anonymous.viewpagertutorial.constants.Constant;
import com.example.anonymous.viewpagertutorial.model.Follower_Item;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import static com.example.anonymous.viewpagertutorial.constants.Constant.MyPREFERENCES;

/**
 * Created by Anonymous on 12/1/2017.
 */

public class HomeFrg extends Fragment {
    private HomeAdapter homeAdapter;
    private List<Follower_Item> items;
    private List<Follower_Item> listSentToActivity;
    private GridLayoutManager gridLayoutManager;
    SharedPreferences shref;
    SharedPreferences.Editor editor;
    private RecyclerView recyclerView;
    private OnSendToActivity onSendToActivity;
    Object a= new Object();
    private HomeAdapter.OnSentToHomeFrg onSentToHomeFrg = new HomeAdapter.OnSentToHomeFrg() {
        @Override
        public void sendData(Follower_Item item, int position) {
            listSentToActivity.add(item);
            onSendToActivity.senListDataToActivity(listSentToActivity);// sao o day ko co phuong thuc set, dat ngoai ham nay duoc khong?
            Log.e("LIST",listSentToActivity.get(0).getNameItem());
        }
    };

    public interface OnSendToActivity {
        void senListDataToActivity(List<Follower_Item> list);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSendToActivity){
            onSendToActivity = (OnSendToActivity) getActivity();
        } else {
            throw new RuntimeException(context.toString() + " must implement onViewSelected");
        }

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_home,container,false);
        recyclerView = view.findViewById(R.id.recycle);
        items = new ArrayList<Follower_Item>();
        listSentToActivity = new ArrayList<Follower_Item>();  //thu tu thuc hien code listSentToActivity?
        addItemToListFollower();
        setupRecycler();
        //savePreference();
        return view;
    }

    private void setupRecycler() {
        homeAdapter = new HomeAdapter(getActivity(),items);
        gridLayoutManager = new GridLayoutManager(getActivity(),2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        homeAdapter.setOnSentToHomeFrg(onSentToHomeFrg);
        recyclerView.setAdapter(homeAdapter);
    }

    private void savePreference() {
        shref = getActivity().getApplicationContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = gson.toJson(listSentToActivity);
        editor = shref.edit();
        editor.putString(Constant.MY_KEY, json);
        editor.apply();
        Log.e("Pre",json);
    }

    private void addItemToListFollower() {
        for (int i=0; i<1000;i++){
            int pos = i % Constant.THUMNAIL.length;
            items.add(new Follower_Item(Constant.ORIGINALPRICE[pos],Constant.NEWPRICE[pos],
                    Constant.PICTURE[pos],Constant.NAME_FOLLOWER[pos]));
        }
    }
}
