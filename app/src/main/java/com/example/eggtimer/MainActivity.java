package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar timerseekbar;
    TextView countdowntextview;
    boolean isactive=false;
    CountDownTimer timer;
    Button gobutton;
    public void reset()
    {
        timerseekbar.setProgress(30);
        isactive=false;
        timerseekbar.setEnabled(true);
        gobutton.setText("GO!");
        timer.cancel();

    }
    public void startstop(View view)
    {
        if(isactive)
        {
            reset();
        }
        else
        {
            isactive=true;
            timerseekbar.setEnabled(false);
            gobutton.setText("STOP!");
            timer=new CountDownTimer(timerseekbar.getProgress()*1000+100,1000) {
                @Override
                public void onTick(long tl) {
                    update((int)(tl/1000));
                }

                @Override
                public void onFinish() {
                    MediaPlayer mplayer=MediaPlayer.create(getApplicationContext(),R.raw.airhorn);
                    mplayer.start();
                    reset();
                }
            }.start();
        }
    }
    public void update(int timercount)
    {
        int minutes=(timercount/60);
        int seconds=timercount-(minutes*60);
        String minute=" ",second=" ";
        if(minutes==0 && seconds==0)
        {
            minute="00";
            second="00";
        }
        if(minutes==0)
        {
            minute="00";
        }
        else if((minutes/10)<=0.9)
        {
            minute="0"+Integer.toString(minutes);
        }
        else
        {
            minute=Integer.toString(minutes);
        }
        if((seconds/10)<=0.9)
        {
            second="0"+Integer.toString(seconds);
        }
        else if(seconds==0)
        {
            second="00";
        }
        else
        {
            second=Integer.toString(seconds);
        }
        countdowntextview.setText(minute+":"+second);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countdowntextview=(TextView)findViewById(R.id.countdowntextview);
        gobutton=(Button)findViewById(R.id.gobutton);
        timerseekbar=(SeekBar)findViewById(R.id.timerseekbar);
        timerseekbar.setMax(600);
        timerseekbar.setProgress(30);
        timerseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                update(i);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}