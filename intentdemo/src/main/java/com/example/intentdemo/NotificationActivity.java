package com.example.intentdemo;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class NotificationActivity extends AppCompatActivity {
    //① 相关成员定义
    private static final int NOTIFY_ID = 1; //通知的id (自拟)
    private static final String CHANNEL_ID = "123"; //通知渠道的id (自拟)
    private static final String CHANNEL_Name = "mychannel"; //通知渠道的名称(自拟)
    NotificationManager manager; //通知管理类
    Notification notification; //通知类
    NotificationChannel channel; //通知渠道
    PendingIntent pendingIntent; //延迟Intent
    Bitmap bitmap; //通知的LargeIcon
    // (1) 创建权限申请的启动器
    private final ActivityResultLauncher permissionLauncher = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(),
            new ActivityResultCallback<Boolean>() {
                @Override
                public void onActivityResult(Boolean result) {
                    if (result) { // true表示申请成功
                        sendNotify(); //发送通知
                    }
                }
            }
    );

    // (2) 在需要的时候启动权限请求
    private void requestPermission() {
        permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
    }

    public void sendNotify() {
        // ② 创建通知管理器
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // ③ 创建通知渠道
        channel = new NotificationChannel(CHANNEL_ID, CHANNEL_Name,
                NotificationManager.IMPORTANCE_HIGH);
        manager.createNotificationChannel(channel);
        // ④ 创建PendingIntent
        Intent intent = new Intent(NotificationActivity.this, LoginActivity.class);
        pendingIntent = PendingIntent.getActivity(NotificationActivity.this, 0,
                intent, PendingIntent.FLAG_IMMUTABLE);
        // ⑤ 创建大图标的Bitmap
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gu);
        // ⑥ 创建通知，注：第二个参数是Channel的id，
        // 一定要和通知渠道的id保持一致，否则不然通知不会显示
        notification = new Notification.Builder(NotificationActivity.this, CHANNEL_ID)
                .setContentTitle("提醒") //通知标题
                .setContentText("通知内容…") //通知内容
                .setWhen(System.currentTimeMillis()) //通知产生的时间
                .setShowWhen(true) //显示时间
                .setSmallIcon(R.drawable.twitter) //小图标
                .setLargeIcon(bitmap) //大图标Bitmap
                .setAutoCancel(true) //通知点击后自动删除
                .setContentIntent(pendingIntent) //设置通知点击的Intent
                .build(); //构建通知
        // ⑦ 发送通知
        manager.notify(NOTIFY_ID, notification);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        // 假设用按钮来发送通知
        Button btn = (Button) findViewById(R.id.btn_send);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission(); //申请权限
            }
        });
    }
} //end activity