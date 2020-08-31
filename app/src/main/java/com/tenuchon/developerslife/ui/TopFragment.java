package com.tenuchon.developerslife.ui;


import com.tenuchon.developerslife.model.Result;

import java.util.ArrayList;
import java.util.List;

public class TopFragment extends BaseFragment {

    private static final String KEY = "top";
    private static ArrayList<Result> topList;
    private static int count = 0;

    public static void initList(ArrayList<Result> list) {
        topList = list;
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
        return topList;
    }

    @Override
    String getKey() {
        return KEY;
    }
}