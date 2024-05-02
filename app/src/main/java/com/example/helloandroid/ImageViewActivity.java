package com.example.helloandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class ImageViewActivity extends AppCompatActivity {
    private int idx = 0;
    private ImageView layout;
    private Button btn_start;
    private Button btn_cancel;
    private Timer timer;
    private TimerTask task;
    private int[] resIds = new int[]{
            R.drawable.lstx_001,
            R.drawable.lstx_002,
            R.drawable.lstx_003,
            R.drawable.lstx_004,
            R.drawable.lstx_005,
            R.drawable.lstx_006,
    };
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_start:
                    start();
                    break;
                case R.id.btn_cancel:
                    task.cancel();
                    timer.cancel();
            }
        }
    };

    public void start() {
        task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        layout.setBackgroundResource(resIds[idx]);    //更换背景图
                        if (idx < resIds.length - 1) idx++;
                        else idx = 0;
                    }
                });
            }
        };
        timer.schedule(task, 3000, 5000);
    }
    public void init() {
        layout = (ImageView)findViewById(R.id.iv_photo);

        btn_start = (Button) findViewById(R.id.btn_start);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);

        btn_start.setOnClickListener(listener);
        btn_cancel.setOnClickListener(listener);

        timer = new Timer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        init();
    }
}