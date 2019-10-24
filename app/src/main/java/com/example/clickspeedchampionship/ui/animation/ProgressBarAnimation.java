package com.example.clickspeedchampionship.ui.animation;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressBarAnimation extends Animation {

    private ProgressBar progressBar;
    private TextView textView;
    private float from;
    private float to;
    private ProgressControlCallback callback;

    public ProgressBarAnimation( ProgressBar progressBar, TextView textView, float from, float to, ProgressControlCallback callback){
        this.progressBar = progressBar;
        this.textView = textView;
        this.from = from;
        this.to = to;
        this.callback = callback;

    }


    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);

        float value = from + (to - from) * interpolatedTime;
        progressBar.setProgress((int) value);

        textView.setText(value + " %");

        if (value == to){
            callback.onProgressFinished();
        }
    }

    public interface ProgressControlCallback{
        void onProgressFinished();
    }

}