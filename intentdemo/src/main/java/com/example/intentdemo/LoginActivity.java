package com.example.intentdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {
    Button btn;
    ImageView photo;

    public void init() {
        btn = (Button) findViewById(R.id.login_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                Bundle bundle = new Bundle();
                String s_phone = ((EditText) findViewById(R.id.phone)).getText().toString();
                String s_password = ((EditText) findViewById(R.id.password)).getText().toString();
                bundle.putString("phone", s_phone);
                bundle.putString("password", s_password);
                intent.putExtras(bundle);
                // startActivity(intent);
                // startActivityForResult(intent, 100);
                launcher1.launch(intent);
            }
        });

        photo = (ImageView) findViewById(R.id.photo);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_PICK);
//                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                intent.setType("image/*");
//                startActivityForResult(intent, 200);

//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                intent.setType("image/*");
//                startActivityForResult(intent, 200);

//                launcher2.launch("image/*");
//                launcher2_2.launch("image/*");
                launcher3.launch(null);
            }


        });
    }

    ActivityResultLauncher launcher1 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    int resultCode = result.getResultCode();
                    if (resultCode == RESULT_OK) {
                        Intent data = result.getData();
                        Bundle bundle = data.getExtras();
                        String info = bundle.getString("info");

                        Snackbar snackbar = Snackbar.make(
                                findViewById(R.id.login_btn),
                                "回传信息" + info,
                                Snackbar.LENGTH_INDEFINITE
                        );
                        snackbar.show();
                        snackbar.setAction("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                snackbar.dismiss();
                            }
                        });
                    }
                }


            }
    );

    ActivityResultLauncher launcher2 = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    photo.setImageURI(result);
                }
            }
    );

    ActivityResultLauncher launcher2_2 = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_SEND);
                    //先将图片复制到相册（关键步骤）
                    intent.setType("image/*"); //设置数据MIME类型
                    intent.putExtra(Intent.EXTRA_STREAM, result); // Intent.EXTRA_STREAM是附件信息，图片放在此处
                    startActivity(intent);
                }
            }
    );

    ActivityResultLauncher launcher3 = registerForActivityResult(
            new ActivityResultContracts.TakePicturePreview(),
            new ActivityResultCallback<Bitmap>() {
                @Override
                public void onActivityResult(Bitmap result) {
                    photo.setImageBitmap(result);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // 设置为全屏模式（隐藏状态条）
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // ActionBar显示返回按钮
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        init();
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 200) {
//            if(resultCode == RESULT_OK) {
//                photo.setImageURI(data.getData());
//            }
//        }
//        if (requestCode == 100) {
//            if (resultCode == RESULT_OK) {
//                Bundle bundle = data.getExtras();
//                String info = bundle.getString("info");
//
//                Snackbar snackbar = Snackbar.make(
//                        findViewById(R.id.login_btn),
//                        "回传信息" + info,
//                        Snackbar.LENGTH_INDEFINITE
//                );
//                snackbar.show();
//                snackbar.setAction("确定", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        snackbar.dismiss();
//                    }
//                });
//
////                Toast.makeText(
////                        LoginActivity.this,
////                        info,
////                        Toast.LENGTH_LONG
////                        ).show();
//            }
//        }
//    }

    // 返回按钮的功能
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}