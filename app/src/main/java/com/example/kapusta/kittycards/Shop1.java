package com.example.kapusta.kittycards;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class Shop1 extends AppCompatActivity implements ViewSwitcher.ViewFactory, GestureDetector.OnGestureListener {

    private ImageSwitcher mImageSwitcher;
    int position = 0;
    private int mImageIds[] = new int[]{ R.drawable.lockcat1, R.drawable.lockcat2,
            R.drawable.lockcat3};
    private boolean bought[] = new boolean[3];
    private int mImageIdsBought[] = new int[]{ R.drawable.cat1, R.drawable.cat2,
            R.drawable.cat3};
    Button buy;
    TextView showScore;
    int score;
    SharedPreferences scoreSave;

    private GestureDetector mGestureDetector;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop1);

//        score = getIntent().getIntExtra("score", 0);

        mImageSwitcher = (ImageSwitcher)findViewById(R.id.imageSwitcher);
        mImageSwitcher.setFactory(this);


        mImageSwitcher.setImageResource(mImageIds[0]);

        mGestureDetector = new GestureDetector(this, this);
        buy = (Button) findViewById(R.id.buy);
        showScore = (TextView) findViewById(R.id.showScore);
        loadScore();
    }
    public  void loadScore() {
        scoreSave = getSharedPreferences("Prefs", Context.MODE_WORLD_READABLE);
        score = scoreSave.getInt("Score", 0);
        showScore.setText("Your score is " + score);
    }

    public void setPositionNext() {
        position++;
        if (position > mImageIds.length - 1) {
            position = 0;
        }
    }

    public void setPositionPrev() {
        position--;
        if (position < 0) {
            position = mImageIds.length - 1;
        }
    }
    public void onClickBuy(View v){
        switch (v.getId()) {
            case R.id.buy:
                if (score >= 50) {
                    score -= 50;
                    showScore.setText("Your score is " + score);
                    bought[position] = true;
                    mImageSwitcher.setImageResource(mImageIdsBought[position]);
                }
                else  {
                    Toast toast = Toast.makeText(this, "Sorry, you don't have enough money", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
            case R.id.set:
                if (bought[position]) {
                    Intent intent = new Intent(Shop1.this, TwentyDollar.class);
                    intent.putExtra("cat1", mImageIdsBought[position]);
                    startActivity(intent);
                }
                break;


        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }
    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        try {
            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                return false;
            // справа налево
            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                mImageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.go_next_in));
                mImageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.go_next_out));
                setPositionNext();
                if (bought[position]) {
                    mImageSwitcher.setImageResource(mImageIdsBought[position]);
                } else {
                    mImageSwitcher.setImageResource(mImageIds[position]);
                }
            } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                // слева направо
                mImageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.go_prev_in));
                mImageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.go_prev_out));
                setPositionPrev();
                if (bought[position]) {
                    mImageSwitcher.setImageResource(mImageIdsBought[position]);
                } else {
                    mImageSwitcher.setImageResource(mImageIds[position]);
                }
            }
        } catch (Exception e) {
            // nothing
            return true;
        }
        return true;
    }

    @Override
    public View makeView() {
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//        imageView.setBackgroundColor(0xFFFFFFFF);
        return imageView;
    }
}
