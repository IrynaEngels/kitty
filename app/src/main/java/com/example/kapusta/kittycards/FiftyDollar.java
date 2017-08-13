package com.example.kapusta.kittycards;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FiftyDollar extends AppCompatActivity implements View.OnClickListener {

    ImageView imgLeftTop;
    ImageView imgLeftBottom;
    ImageView imgRightTop;
    ImageView imgRightBottom;
    TextView scoreShow;
    TextView comboShow;
    int score = 50;
    int combo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifty_dollar);

        imgLeftTop =(ImageView) findViewById(R.id.imageLeftTop);
        imgLeftBottom =(ImageView) findViewById(R.id.imageLeftBottom);
        imgRightTop =(ImageView) findViewById(R.id.imageRightTop);
        imgRightBottom =(ImageView) findViewById(R.id.imageRightBottom);
        scoreShow = (TextView) findViewById(R.id.scoreShow);
        comboShow = (TextView) findViewById(R.id.comboShow);

        imgLeftTop.setOnClickListener(this);
        imgLeftBottom.setOnClickListener(this);
        imgRightTop.setOnClickListener(this);
        imgRightBottom.setOnClickListener(this);
        scoreShow.setText("Score: " + score);
        comboShow.setText("Combo: " + combo);
    }

    @Override
    public void onClick(View v) {
        MediaPlayer meow= MediaPlayer.create(this,R.raw.meow);
        MediaPlayer mrrr= MediaPlayer.create(this,R.raw.mrrrr);
        int random = (int) (Math.random() * 10);
        Bitmap leftTop = ((BitmapDrawable) imgLeftTop.getDrawable()).getBitmap();
        Bitmap leftBottom = ((BitmapDrawable) imgLeftBottom.getDrawable()).getBitmap();
        Bitmap rightTop = ((BitmapDrawable) imgRightTop.getDrawable()).getBitmap();
        Bitmap rightBottom = ((BitmapDrawable) imgRightBottom.getDrawable()).getBitmap();
        if (score >= 25) {
            score -= 25;
            scoreShow.setText("Score: " + score);
            if(leftTop.equals(leftBottom)&&leftBottom.equals(rightTop)&&rightTop.equals(rightBottom)) {
                switch (v.getId()) {
                    case R.id.imageLeftTop:
                        if (random % 4 == 0) {
                            score += 50;
                            combo++;
                            mrrr.start();
                            imgLeftTop.setImageResource(R.drawable.kittysecond);
                        } else if (random % 3 == 0) {
                            imgLeftBottom.setImageResource(R.drawable.kittysecond);
                            combo = 0;
                            meow.start();
                        } else if (random % 2 == 0) {
                            imgRightTop.setImageResource(R.drawable.kittysecond);
                            combo = 0;
                            meow.start();
                        } else {
                            imgRightBottom.setImageResource(R.drawable.kittysecond);
                            combo = 0;
                            meow.start();
                        }

                        break;
                    case R.id.imageLeftBottom:
                        if (random % 4 == 0) {
                            combo = 0;
                            meow.start();
                            imgLeftTop.setImageResource(R.drawable.kittysecond);
                        } else if (random % 3 == 0) {
                            imgLeftBottom.setImageResource(R.drawable.kittysecond);
                            score += 50;
                            combo++;
                            mrrr.start();
                        } else if (random % 2 == 0) {
                            imgRightTop.setImageResource(R.drawable.kittysecond);
                            combo = 0;
                            meow.start();
                        } else {
                            imgRightBottom.setImageResource(R.drawable.kittysecond);
                            combo = 0;
                            meow.start();
                        }
                        break;
                    case R.id.imageRightTop:
                        if (random % 4 == 0) {
                            combo = 0;
                            meow.start();
                            imgLeftTop.setImageResource(R.drawable.kittysecond);
                        } else if (random % 3 == 0) {
                            imgLeftBottom.setImageResource(R.drawable.kittysecond);
                            combo = 0;
                            meow.start();
                        } else if (random % 2 == 0) {
                            imgRightTop.setImageResource(R.drawable.kittysecond);
                            score += 50;
                            combo++;
                            mrrr.start();
                        } else {
                            imgRightBottom.setImageResource(R.drawable.kittysecond);
                            combo = 0;
                            meow.start();
                        }
                        break;
                    case R.id.imageRightBottom:
                        if (random % 4 == 0) {
                            combo = 0;
                            meow.start();
                            imgLeftTop.setImageResource(R.drawable.kittysecond);
                        } else if (random % 3 == 0) {
                            imgLeftBottom.setImageResource(R.drawable.kittysecond);
                            combo = 0;
                            meow.start();
                        } else if (random % 2 == 0) {
                            imgRightTop.setImageResource(R.drawable.kittysecond);
                            combo = 0;
                            meow.start();
                        } else {
                            imgRightBottom.setImageResource(R.drawable.kittysecond);
                            score += 50;
                            combo++;
                            mrrr.start();
                        }
                        break;
                }
            }
            Runnable runnable1 = new Runnable() {
                @Override
                public void run() {
                    imgLeftTop.setImageResource(R.drawable.backgroundsecond);
                    imgLeftBottom.setImageResource(R.drawable.backgroundsecond);
                    imgRightTop.setImageResource(R.drawable.backgroundsecond);
                    imgRightBottom.setImageResource(R.drawable.backgroundsecond);
                }
            };
            final Handler handler1 = new Handler();
            handler1.postDelayed(runnable1, 1000);
        }
        else {
            Toast toast = Toast.makeText(this, "Sorry, you don't have enough money", Toast.LENGTH_SHORT);
            toast.show();
        }
        scoreShow.setText("Score: " + score);
        comboShow.setText("Combo: " + combo);

    }
}
