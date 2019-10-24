package com.example.clickspeedchampionship.ui.animation;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.clickspeedchampionship.R;
import com.example.clickspeedchampionship.ui.NameInputActivity;

public class LoadingStartActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_start);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        progressBar = findViewById(R.id.progress_bar);
        textView = findViewById(R.id.text_view);

        progressBar.setMax(100);
        progressBar.setScaleY(3f);

        progressAnimation();
    }

    public void progressAnimation(){
        ProgressBarAnimation amin = new ProgressBarAnimation(progressBar, textView, 0f, 100f, new ProgressBarAnimation.ProgressControlCallback() {
            @Override
            public void onProgressFinished() {
                startActivityForResult(new Intent(LoadingStartActivity.this, NameInputActivity.class), 1);
            }
        });
        amin.setDuration(8000);
        progressBar.setAnimation(amin);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        finish();
    }
}
