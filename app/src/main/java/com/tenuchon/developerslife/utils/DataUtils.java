package com.tenuchon.developerslife.utils;


import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.tenuchon.developerslife.model.Dto;
import com.tenuchon.developerslife.model.Result;
import com.tenuchon.developerslife.ui.HotFragment;
import com.tenuchon.developerslife.ui.LatestFragment;
import com.tenuchon.developerslife.ui.TopFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DataUtils {
    private static DataUtils instance;
    private static final String JSON_FORMAT = "true";
    private static final String SIZE_PAGE = "50";
    private static ArrayList<Result> latestList;
    private static ArrayList<Result> hotList;
    private static ArrayList<Result> topList;

    public static DataUtils getInstance() {
        if (instance == null) {
            synchronized (DataUtils.class) {
                if (instance == null)
                    instance = new DataUtils();
            }
        }
        return instance;
    }

    public void loadGifList(final LottieAnimationView animationView, final ImageView imageView,
                            final LinearLayout doneLayout, final LinearLayout errorLayout, final TextView errorEmptyTextView,
                            final TextView descriptionTextView, final int count, final String key) {

        NetworkService service = NetworkService.getInstance();
        GifApi gifApi = service.getGifApi();
        Call<Dto> call;
        switch (key) {
            case "latest":
                call = gifApi.getLatestGif(JSON_FORMAT, SIZE_PAGE);
                break;
            case "hot":
                call = gifApi.getHotGif(JSON_FORMAT, SIZE_PAGE);
                break;
            default:
                call = gifApi.getTopGif(JSON_FORMAT, SIZE_PAGE);
        }

        call.enqueue(new Callback<Dto>() {
            @Override
            public void onResponse(@NonNull Call<Dto> call, @NonNull Response<Dto> response) {
                if (response.isSuccessful()) {
                    Dto dto = response.body();
                    ArrayList<Result> results = (ArrayList<Result>) dto.getResult();
                    if (results != null && results.size() != 0) {
                        switch (key) {
                            case "latest":
                                latestList = results;
                                LatestFragment.initList(latestList);
                                break;
                            case "hot":
                                hotList = results;
                                HotFragment.initList(hotList);
                                break;
                            case "top":
                                topList = results;
                                TopFragment.initList(topList);
                        }
                        loadGifIntoImageView(animationView, imageView, doneLayout, errorLayout,
                                descriptionTextView, count, key);
                        doneLayout.setVisibility(View.VISIBLE);
                    } else {
                        errorEmptyTextView.setVisibility(View.VISIBLE);
                        doneLayout.setVisibility(View.INVISIBLE);
                        animationView.setVisibility(View.INVISIBLE);
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<Dto> call, @NonNull Throwable t) {
                t.printStackTrace();
                animationView.setVisibility(View.INVISIBLE);
                doneLayout.setVisibility(View.INVISIBLE);
                errorLayout.setVisibility(View.VISIBLE);
            }
        });

    }

    public void loadGifIntoImageView(final LottieAnimationView animationView, ImageView imageView,
                                     final LinearLayout doneLayout, final LinearLayout errorLayout,
                                     final TextView descriptionTextView, int count, String key) {
        ArrayList<Result> list;
        switch (key) {
            case "latest":
                list = latestList;
                break;
            case "hot":
                list = hotList;
                break;
            default:
                list = topList;
        }
        animationView.setVisibility(View.VISIBLE);

        Glide.with(imageView.getContext())
                .load(list.get(count).getUrl())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                Target<Drawable> target, boolean isFirstResource) {
                        animationView.setVisibility(View.INVISIBLE);
                        doneLayout.setVisibility(View.INVISIBLE);
                        errorLayout.setVisibility(View.VISIBLE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
                                                   DataSource dataSource, boolean isFirstResource) {
                        animationView.setVisibility(View.INVISIBLE);
                        return false;
                    }
                })
                .centerCrop()
                .into(imageView);

        descriptionTextView.setText(list.get(count).getDescription());
    }
}
