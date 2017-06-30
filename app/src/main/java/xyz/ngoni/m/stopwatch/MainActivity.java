package xyz.ngoni.m.stopwatch;

import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int seconds =0;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
       seconds=     savedInstanceState.getInt("seconds");
          running =  savedInstanceState.getBoolean("running");
        }
        runTimer();
    }

    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("seconds",seconds);
        savedInstanceState.putBoolean("running",running);

    }

    public void onStartClick(View view) {

        running = true;
    }

    public void onStopClick(View view) {
        running = false;
    }

    public void onResetClick(View view) {
        seconds=0;
        running= false;
    }

    private void runTimer(){

        final TextView textView = (TextView)findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                int hours = seconds/3600;
                int minutes =(seconds%3600)/60;
                int secs = seconds;

                String time = String.format("%d:%02d:%02d",hours,minutes,secs);
                textView.setText(time);
                if(running){
                    seconds++;

                }

                handler.postDelayed(this,1000);


            }
        });

    }


}
