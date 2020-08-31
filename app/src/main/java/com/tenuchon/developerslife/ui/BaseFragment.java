package com.tenuchon.developerslife.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.button.MaterialButton;
import com.tenuchon.developerslife.R;
import com.tenuchon.developerslife.model.Result;
import com.tenuchon.developerslife.utils.DataUtils;

import java.util.List;


public abstract class BaseFragment extends Fragment {

    private ImageView imageView;
    private ImageButton nextButton, backButton;
    private TextView descriptionTextView, errorEmptyTextView;
    private LinearLayout doneLayout, errorLayout;
    private MaterialButton repeatButton;
    private LottieAnimationView animationView;


    abstract int getCount();

    abstract void setCount(int i);

    abstract List<Result> getGifList();

    abstract String getKey();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_latest, container, false);
        imageView = view.findViewById(R.id.image_view);
        nextButton = view.findViewById(R.id.next);
        backButton = view.findViewById(R.id.back);
        descriptionTextView = view.findViewById(R.id.description);
        doneLayout = view.findViewById(R.id.done_layout);
        errorLayout = view.findViewById(R.id.error_layout);
        repeatButton = view.findViewById(R.id.repeat);
        errorEmptyTextView = view.findViewById(R.id.error_empty);
        animationView = view.findViewById(R.id.animationView);

        animationView.setVisibility(View.VISIBLE);
        DataUtils.getInstance().loadGifList(animationView, imageView, doneLayout, errorLayout,
                errorEmptyTextView, descriptionTextView, getCount(), getKey());

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getCount() > 0) {
                    setCount(getCount() - 1);
                    DataUtils.getInstance().loadGifIntoImageView(animationView, imageView, doneLayout,
                            errorLayout, descriptionTextView, getCount(), getKey());

                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getCount() < getGifList().size() - 1) {
                    setCount(getCount() + 1);
                    DataUtils.getInstance().loadGifIntoImageView(animationView, imageView, doneLayout,
                            errorLayout, descriptionTextView, getCount(), getKey());

                }
            }
        });

        repeatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                errorLayout.setVisibility(View.INVISIBLE);
                animationView.setVisibility(View.VISIBLE);
                DataUtils.getInstance().loadGifList(animationView, imageView, doneLayout, errorLayout,
                        errorEmptyTextView, descriptionTextView, getCount(), getKey());
            }
        });

        return view;
    }
}
