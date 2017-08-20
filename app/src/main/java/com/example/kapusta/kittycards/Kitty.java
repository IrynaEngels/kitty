package com.example.kapusta.kittycards;

import android.os.Handler;
import android.widget.ImageView;

/**
 * Created by Kapusta on 13.06.2017.
 */

public class Kitty {
    int card = R.drawable.kitty;
    public Kitty() {
    }

    public int getCard() {
        return card;
    }

    public void setCard(int card) {
        this.card = card;
    }

    public void setBackground(final ImageView iv1, final ImageView iv2){
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                iv1.setImageResource(R.drawable.background);
                iv2.setImageResource(R.drawable.background);
            }
        };
        final Handler handler1 = new Handler();
        handler1.postDelayed(runnable1, 1000);
    }
}
