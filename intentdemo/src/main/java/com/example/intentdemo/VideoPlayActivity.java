package com.example.intentdemo;

import android.app.Dialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.StyledPlayerView;

public class VideoPlayActivity extends AppCompatActivity {
    ExoPlayer player; // 播放器（播放视频）
    StyledPlayerView playerView; // 播放器视图（里面存放播放器）
    Dialog dialog; // 对话框（里面存放播放器视图）

    ActivityResultLauncher launcher4 = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    playVideo(result); //播放视频见后
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        Button btn = findViewById(R.id.btn_open);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player != null && player.isPlaying()) {
                    player.stop(); //如果视频已在播放则停止播放，重新选择视频加载
                }
                launcher4.launch("video/*"); //MIME是视频类型
            }
        });
    }

    //播放视频
    public void playVideo(Uri uri) {
        playerView = new StyledPlayerView(this); //创建播放器视图
        //创建播放器
        player = new ExoPlayer.Builder(VideoPlayActivity.this).build();
        player.setMediaItem(MediaItem.fromUri(uri)); //添加视频资源
        playerView.setPlayer(player); //将player加载到播放器视图中
        //创建全屏对话框
        dialog = new Dialog(VideoPlayActivity.this,
                android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(playerView); //窗口加载播放器视图

        //监听对话框关闭
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                player.stop(); //停止播放
                player.release(); //回收播放器
            }
        });
        dialog.show(); //显示对话框窗口
        player.prepare(); //准备播放
        player.play(); //播放
    }
} //end activity