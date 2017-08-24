package com.example.kapusta.kittycards;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.content.SharedPreferences.Editor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TwentyDollar extends AppCompatActivity implements View.OnClickListener {

    ImageView imgLeft;
    ImageView imgRight;
    ImageView toStore;
    TextView scoreShow;
    TextView comboShow;
    int score = 20;
    int combo = 0;
    Kitty kitty = new Kitty();
    int cat;
    SharedPreferences scoreSave;
    SharedPreferences image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twenty_dollar);

        image = getSharedPreferences("Prefs1", Context.MODE_WORLD_READABLE);
        cat = image.getInt("Image", R.drawable.kitty);

//        cat = getIntent().getIntExtra("cat1", R.drawable.kitty);

        imgLeft =(ImageView) findViewById(R.id.imageLeft);
        imgRight =(ImageView) findViewById(R.id.imageRight);
        scoreShow = (TextView) findViewById(R.id.scoreShow);
        comboShow = (TextView) findViewById(R.id.comboShow);
        toStore = (ImageView) findViewById(R.id.toStore);

        imgLeft.setOnClickListener(this);
        imgRight.setOnClickListener(this);
        scoreShow.setText(" " + score);
        comboShow.setText(" " + combo);
    }

    public void toStore(View v){
        Intent intent = new Intent(TwentyDollar.this, Shop1.class);
        intent.putExtra("score", score);
        startActivity(intent);
    }
    @Override
    public void onClick(View v) {
        MediaPlayer meow= MediaPlayer.create(this,R.raw.meow);
        MediaPlayer mrrr= MediaPlayer.create(this,R.raw.mrrrr);

        int random = (int) (Math.random() * 10);
        Bitmap left = ((BitmapDrawable) imgLeft.getDrawable()).getBitmap();
        Bitmap right = ((BitmapDrawable) imgRight.getDrawable()).getBitmap();
        if (score >= 10) {
            score -= 10;
            scoreShow.setText("Score: " + score);
            if (left.equals(right)) {
                switch (v.getId()) {
                    case R.id.imageLeft:

                        if (random % 2 == 0) {
                            score += 20;
                            combo++;
                            imgLeft.setImageResource(cat);
                            mrrr.start();

                            comboMultipiesScore();
                        } else {
                            imgRight.setImageResource(cat);
                            combo = 0;
                            meow.start();
                        }

                        break;
                    case R.id.imageRight:

                        if (random % 2 == 0) {
                            combo = 0;
                            imgLeft.setImageResource(cat);
                            meow.start();
                        } else {
                            imgRight.setImageResource(cat);
                            score += 20;
                            combo++;
                            comboMultipiesScore();
                            mrrr.start();
                        }
                        break;

                }
            }
            kitty.setBackground(imgLeft, imgRight);

            scoreShow.setText(" " + score);
            comboShow.setText(" " + combo);

        }
        else {
            Toast toast = Toast.makeText(this, "Sorry, you don't have enough money", Toast.LENGTH_SHORT);
            toast.show();
        }
        saveScore();
    }

    public void saveScore() {
        scoreSave = getSharedPreferences("Prefs", Context.MODE_WORLD_WRITEABLE);
        Editor ed = scoreSave.edit();
        ed.putInt("Score", score);
        ed.commit();
    }

    public void comboMultipiesScore(){
        if(combo % 5 == 0){
            score+=combo*10;
            Toast toast = Toast.makeText(this, "Score multiplied", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            scoreShow.setText("Score: " + score);
        }
    }
}
