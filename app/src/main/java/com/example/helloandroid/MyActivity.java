package com.example.helloandroid;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Timer;
import java.util.TimerTask;

public class MyActivity extends AppCompatActivity {

    private ConstraintLayout layout;
    private int idx = 0;
    int[] resIds = new int[]{
            R.drawable.bg1080,
            R.drawable.bg1080_2
    };
    Timer timer;
    TimerTask task;

    public void init() {
        layout = (ConstraintLayout) findViewById( R.id.constrainLayout2 );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        init();

       timer = new Timer();
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
        ActionBar bar = getSupportActionBar();
        bar.setTitle("欢迎使用");
        bar.setDisplayHomeAsUpEnabled(true);

        //设置logo (3行代码)
//        bar.setDisplayShowHomeEnabled(true);
//        bar.setLogo( R.drawable.twitter );
//        bar.setDisplayUseLogoEnabled(true);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu,menu);
//
//        MenuItem menuItem = menu.findItem(R.id.menu_search);
//        SearchView searchView = (SearchView) menuItem.getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
////                Toast.makeText(MyActivity.this, s, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent();
//                intent.setAction( Intent.ACTION_VIEW );
//                // https://so.csdn.net/so/search?spm=1000.2115.3001.4498&q=Open%20ai
//                intent.setData( Uri.parse("https://www.baike.com/search?keyword=" +s) ); startActivity( intent );
//                return false;
//            }
//            @Override
//            public boolean onQueryTextChange(String s) {
//                return false;
//            }
//        });
//
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch(item.getItemId()) {
//            case R.id.menu_login:
//                Toast.makeText( MyActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.menu_exit:
//                finish();
//            case android.R.id.home:
//                finish();
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("flag","结束");
    }
}