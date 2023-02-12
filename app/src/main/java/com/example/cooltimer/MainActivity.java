package com.example.cooltimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private TextView textView;
    private Button button;
    private boolean isStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        isStarted = false;

        seekBar.setMax(600);
        seekBar.setProgress(60);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i * 1000);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void startstop(View view) {
        if (isStarted) {
            seekBar.setEnabled(true);
            button.setText("start");
            isStarted = false;
        }
        else {
            seekBar.setEnabled(false);
            button.setText("stop");
            isStarted = true;
        }

        new CountDownTimer(seekBar.getProgress() * 1000L, 1000){

            @Override
            public void onTick(long l) {

                updateTimer((int)l);
            }

            @Override
            public void onFinish() {
                seekBar.setEnabled(true);
                isStarted = false;
                button.setText("start");

                Log.d("onFinish: ", "Finish!");

                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.bell_sound);
                mediaPlayer.start();

            }
        }.start();
    }

    private void updateTimer(int millisUntilFinished){
        int minutes = millisUntilFinished /1000/60;
        int seconds = millisUntilFinished /1000 - (minutes * 60);

        textView.setText(String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
    }
}