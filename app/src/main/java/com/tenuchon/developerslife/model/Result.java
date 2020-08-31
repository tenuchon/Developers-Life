package com.tenuchon.developerslife.model;

import com.google.gson.annotations.SerializedName;


public class Result {
    @SerializedName("id")
    private int id;

    @SerializedName("description")
    private String description;

    @SerializedName("gifURL")
    private String url;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
