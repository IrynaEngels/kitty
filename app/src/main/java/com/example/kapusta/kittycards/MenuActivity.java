package com.example.kapusta.kittycards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener{

    Button sum20;
    Button sum50;
    Button sum100;
    Button shop;
    Button shop1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_menu);

        sum20 = (Button) findViewById(R.id.sum20);
        sum50 = (Button) findViewById(R.id.sum50);
        sum100 = (Button) findViewById(R.id.sum100);
//        shop = (Button) findViewById(R.id.shop);
//        shop1 = (Button) findViewById(R.id.shop1);
        sum20.setOnClickListener(this);
        sum50.setOnClickListener(this);
        sum100.setOnClickListener(this);
//        shop.setOnClickListener(this);
//        shop1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sum20:
                Intent intent = new Intent(MenuActivity.this, TwentyDollar.class);
                startActivity(intent);
                break;
            case R.id.sum50:
                Intent intent1 = new Intent(MenuActivity.this, FiftyDollar.class);
                startActivity(intent1);
                break;
            case R.id.sum100:
                Intent intent2 = new Intent(MenuActivity.this, HundredDollar.class);
                startActivity(intent2);
                break;
//            case R.id.shop:
//                Intent intent2 = new Intent(MenuActivity.this, Shop.class);
//                startActivity(intent2);
//                break;
//            case R.id.shop1:
//                Intent intent3 = new Intent(MenuActivity.this, Shop1.class);
//                startActivity(intent3);
//                break;
        }

    }
}
