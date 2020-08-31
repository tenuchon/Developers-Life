package com.tenuchon.developerslife.ui;


import com.tenuchon.developerslife.model.Result;


import java.util.ArrayList;
import java.util.List;


public class LatestFragment extends BaseFragment {
    private static final String KEY = "latest";
    private static ArrayList<Result> latestList;
    private static int count = 0;


    public LatestFragment() {
    }

    public static void initList(ArrayList<Result> list) {
        latestList = list;
    }

    @Override
    int getCount() {
        return count;
    }

    @Override
    void setCount(int i) {
        count = i;
    }

    @Override
    List<Result> getGifList() {
        return latestList;
    }

    @Override
    String getKey() {
        return KEY;
    }

}