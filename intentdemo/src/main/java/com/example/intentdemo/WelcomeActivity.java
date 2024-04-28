package com.example.intentdemo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String phone = bundle.getString("phone").substring(0,3);
        String password = bundle.getString("password").toUpperCase();

        bundle.putString("info",phone+password);
        intent.putExtras(bundle);

        setResult(RESULT_OK,intent);
        finish();

//        Snackbar.make(
//                findViewById(R.id.contraintLayout),
//                "登录成功！tel:" + phone + " pwd:" + password,
//                Snackbar.LENGTH_SHORT
//                ).show();



    }
}