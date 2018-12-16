package com.example.stjester.myapplication;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView infotext;
    private LinearLayout LL;
    private float initialX, initialY;
    private float treshold = 100.0f;
    private float finalX, finalY;
    private boolean wasMoved = false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.infotext = (TextView) this.findViewById(R.id.info);
        this.LL = this.
                findViewById(R.id.LL);
        LL.setOnTouchListener(handleTouch);
    }

    private View.OnTouchListener handleTouch = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    initialX = event.getX();
                    initialY = event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    wasMoved = true;
                    break;
                case MotionEvent.ACTION_UP:
                    finalX = event.getX();
                    finalY = event.getY();

                    if (wasMoved) {
                        if (isAbsDelta(treshold, initialX, finalX)) {
                            if (isAbsDelta(treshold, initialY, finalY)) {
                                MainActivity.this.infotext.setText("diagonal");
                                MainActivity.this.LL.setBackgroundColor(Color.parseColor("#865DE8"));
                            } else {
                                MainActivity.this.infotext.setText("horizontal");
                                MainActivity.this.LL.setBackgroundColor(Color.parseColor("#1FFF26"));

                            }
                        } else if (isAbsDelta(treshold, initialY, finalY)) {
                            MainActivity.this.infotext.setText("vertical");
                            MainActivity.this.LL.setBackgroundColor(Color.parseColor("#FF7A45"));

                        }
                    }
                    break;
            }
            return true;
        }
    };

    private boolean isAbsDelta(float treshold, float start, float end) {
        return Math.abs(start - end) > treshold;
    }
}
