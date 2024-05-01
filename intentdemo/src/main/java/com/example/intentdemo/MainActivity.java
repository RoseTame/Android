package com.example.intentdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 显式 Intent
                Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
                startActivity(intent);

//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_SEND);
//                intent.setType("text/plain");
//                startActivity(intent);

//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_SEND);
//                startActivity(intent);

//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_VIEW);
//                startActivity(intent);

                // 隐式 Intent
                // 1. 启动浏览器打开一个网站：
//                Uri page = Uri.parse("https://baidu.com");
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_VIEW);
//                intent.setData(page);
//                startActivity(intent);

                // 2. 打开拨号面板
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:110"));
//                startActivity(intent);

                // 3. 返回 Home
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_MAIN);
//                intent.addCategory(Intent.CATEGORY_HOME);
//                startActivity(intent);

                // 4. 发送短信
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_SENDTO);
//                intent.setData(Uri.parse("smsto:18908643860"));
//                intent.putExtra("sms_body","hello");
//                startActivity(intent);

                // 5. 发送邮件
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_SENDTO);
//                intent.setData(Uri.parse("mailto:heyuxinzww@wust.edu.cn"));
//                intent.putExtra(Intent.EXTRA_SUBJECT,"来自yuxin的问候");
//                intent.putExtra(Intent.EXTRA_TEXT,"你好，朋友...");
//                startActivity(intent);

                // 6. 图片分享
//                Intent intent = new Intent();
//                intent.setAction( Intent.ACTION_SEND );
//                //先将图片复制到相册（关键步骤）
//                Bitmap bitmap = BitmapFactory.decodeResource( getResources(), R.raw.gu );
//                Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(),bitmap,"hi",""));
//                intent.setType("image/*"); //设置数据MIME类型
//                intent.putExtra( Intent.EXTRA_STREAM, uri ); // Intent.EXTRA_STREAM是附件信息，图片放在此处
//                if(intent.resolveActivity(getPackageManager())!= null) {
//                    startActivity(intent);
//                }


                // 延迟 Intent
//                Calendar calender = Calendar.getInstance();
//                // 时间选择对话框
//                TimePickerDialog tpd = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                        // ① 创建Intent
//                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                        // ② 创建PendingIntent
//                        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 100, intent, PendingIntent.FLAG_IMMUTABLE);
//                        // 创建临时Calendar对象，目的：将设置的时:分转为毫秒数给闹钟设置定时时间
//                        // 临时Calendar对象的值来自TimePickerDialog
//                        Calendar tmp = Calendar.getInstance();
//                        tmp.set(Calendar.HOUR_OF_DAY, hourOfDay); //设置时
//                        tmp.set(Calendar.MINUTE, minute); //设置分
//                        tmp.set(Calendar.SECOND, 0); //设置秒
//                        // ③ 创建闹钟（一次性任务）
//                        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
//                        manager.set(AlarmManager.RTC_WAKEUP, tmp.getTimeInMillis(), pendingIntent);
//                        Toast.makeText(MainActivity.this, "闹钟设置成功", Toast.LENGTH_SHORT).show();
//                    } //end onTimeSet
//                }, calender.get(Calendar.HOUR_OF_DAY), calender.get(Calendar.MINUTE), true); //end TimePickerDialog
//                tpd.show(); //显示日期选择对话框
            }
        });
    }
}