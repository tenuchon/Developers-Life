package com.tenuchon.developerslife.ui;

import com.tenuchon.developerslife.model.Result;

import java.util.ArrayList;
import java.util.List;

public class HotFragment extends BaseFragment {
    private static final String KEY = "hot";

    private static int count = 0;
    private static ArrayList<Result> hotList;

    public static void initList(ArrayList<Result> list) {
        hotList = list;
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
        return hotList;
    }

    @Override
    String getKey() {
        return KEY;
    }

}