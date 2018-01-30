package com.example.cezar.e_conomic;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class TestActivity extends AppCompatActivity {
    private Button startButton;
    private Button pauseButton;
    private Button stopButton;
    private Button resetButton;

    private Chronometer mchronometer;

    private long lastPause;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        startButton = (Button) findViewById(R.id.buttonStart);
        pauseButton = (Button) findViewById(R.id.buttonPause);
        stopButton = (Button) findViewById(R.id.buttonStop);
        resetButton = (Button) findViewById(R.id.buttonReset);
        mchronometer = (Chronometer) findViewById(R.id.chronometer);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastPause != 0){
                    mchronometer.setBase(mchronometer.getBase() + SystemClock.elapsedRealtime() - lastPause);
                }
                else{
                    mchronometer.setBase(SystemClock.elapsedRealtime());
                }
                mchronometer.start();
                startButton.setEnabled(false);
                pauseButton.setEnabled(true);
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastPause = SystemClock.elapsedRealtime();
                mchronometer.stop();
                pauseButton.setEnabled(false);
                startButton.setEnabled(true);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mchronometer.stop();
                mchronometer.setBase(SystemClock.elapsedRealtime());
                lastPause = 0;
                startButton.setEnabled(true);
                pauseButton.setEnabled(false);

            }
        });
    }
}
