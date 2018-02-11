package com.example.mohitgarg.french;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    public GestureDetector gd;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please press twice to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gd = new GestureDetector(this, this);

        ImageView tf1 = (ImageView) findViewById(R.id.number);
        ImageView tf2 = (ImageView) findViewById(R.id.family);
        ImageView tf3 = (ImageView) findViewById(R.id.color);
        ImageView tf4 = (ImageView) findViewById(R.id.phrases);
        Button transbut=(Button)findViewById(R.id.transbut);
        tf1.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent i = new Intent(MainActivity.this, NumbersActivity.class);
                        startActivity(i);
                    }
                }

        );
        tf2.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent i = new Intent(MainActivity.this, FamilyActivity.class);
                        startActivity(i);
                    }
                }

        );
        tf3.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent i = new Intent(MainActivity.this, ColorsActivity.class);
                        startActivity(i);
                    }
                }

        );
        tf4.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent i = new Intent(MainActivity.this, PhrasesActivity.class);
                        startActivity(i);
                    }
                }

        );
        transbut.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent i = new Intent(MainActivity.this, translate.class);
                        startActivity(i);
                    }
                }

        );
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gd.onTouchEvent(event);
        return super.onTouchEvent(event);
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
        float ydistance = (e1.getY() - e2.getY());
        float xdistance = (e1.getX() - e2.getX());
        if (ydistance > 0 && (Math.abs(ydistance) > Math.abs(xdistance))) {
            String url = "https://www.google.co.in/search?q=english+to+french&rlz=1C1RLNS_enIN734IN734&oq=en&aqs=chrome.0.69i59j69i60l3j69i57j69i59.2201j0j7&sourceid=chrome&ie=UTF-8";
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.setPackage("com.android.chrome");
            try {
                startActivity(i);
            } catch (ActivityNotFoundException ex) {
                //In case of not having a chrome browser
                i.setPackage(null);
                startActivity(i);
            }
            return false;
        } else if (ydistance < 0 && (Math.abs(ydistance) > Math.abs(xdistance))) {
            Toast.makeText(getApplicationContext(), "i said swipe up not down", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}