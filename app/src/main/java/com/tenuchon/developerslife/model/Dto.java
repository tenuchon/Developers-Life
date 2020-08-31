package com.tenuchon.developerslife.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Dto {
    @SerializedName("result")
    private List<Result> result = null;

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }
}
