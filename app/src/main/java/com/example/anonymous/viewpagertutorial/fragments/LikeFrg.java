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
import android.widget.TextView;

import com.example.anonymous.viewpagertutorial.R;
import com.example.anonymous.viewpagertutorial.adapters.LikeAdapter;
import com.example.anonymous.viewpagertutorial.constants.Constant;
import com.example.anonymous.viewpagertutorial.model.Follower_Item;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import static com.example.anonymous.viewpagertutorial.constants.Constant.MyPREFERENCES;

/**
 * Created by Anonymous on 12/1/2017.
 */

public class LikeFrg extends Fragment {
    List<Follower_Item> listLikeFollower = new ArrayList<Follower_Item>();
    LikeAdapter likeAdapter;
    RecyclerView recyclerView;
    TextView textView;
    SharedPreferences shref ;
    SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_like,container,false);
        textView= view.findViewById(R.id.tvLike);
        shref= getActivity().getApplicationContext().getSharedPreferences(Constant.MyPREFERENCES,0);
        listLikeFollower = loadPref();
        setupRecycler(view);
        likeAdapter.setOnListDeleteItem(new LikeAdapter.OnListDeleteItem() {
            @Override
            public void sendItem(Follower_Item itemDeleted, int position) {
                listLikeFollower.remove(listLikeFollower.get(position));
                updatePref(listLikeFollower);
                likeAdapter.updateList(listLikeFollower);
            }

        });
        return view;
    }

    private void updatePref(List<Follower_Item> listFlower) {
            shref = getActivity().getApplicationContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            Gson gson = new Gson();
            String json = gson.toJson(listFlower);
            Log.e("json",json);
            editor = shref.edit();
            editor.putString(Constant.MY_KEY, json);
            editor.apply();
            Log.e("lan1","savePreference duoc goi truoc");
    }

    private void setupRecycler(View view) {
        recyclerView = view.findViewById(R.id.recycleList);
        likeAdapter = new LikeAdapter(getContext(),listLikeFollower);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(likeAdapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    public List<Follower_Item> loadPref(){
        Gson gson = new Gson();
        String response=shref.getString(Constant.MY_KEY , "");
        List<Follower_Item> list = gson.fromJson(response,
                new TypeToken<List<Follower_Item>>(){}.getType());
        return list;
    }

    public LikeFrg newInstance(List<Follower_Item> list){
        LikeFrg fragment = new LikeFrg();
        //load listLikeFollower when not using sp
        listLikeFollower = list;
        //listLikeFollower = loadPreferece();
        return fragment;
    }
    public void receiverData(List<Follower_Item> items){
//        listLikeFollower = items;
//        likeAdapter.updateList(listLikeFollower);
//        textView.setText(items.get(0).getNameItem());
    }
}
