package com.example.mohitgarg.french;//my package name

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;
// end of header files of the activity
//starting splash class
public class SplashActivity extends Activity
{
    static long waitingTime = 2200;//this is waiting time in millisecond by default
    boolean scheduled = false;
    Timer splashTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);//connecting the splash activity layout to the java file

        splashTimer = new Timer();
        splashTimer.schedule(new TimerTask()
        {
            @Override
            public void run()//defining the override run method for splash
            {
                SplashActivity.this.finish();//setting the activity completion action
                Intent i=new Intent(SplashActivity.this, MainActivity.class);//intent to the main activity from splash
                startActivity(i);//initializing the intent object
            }
        }, waitingTime);
        scheduled = true;//setting the destroy method to avoid multiple calls after completion
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (scheduled)
            splashTimer.cancel();
        splashTimer.purge();
    }
}
