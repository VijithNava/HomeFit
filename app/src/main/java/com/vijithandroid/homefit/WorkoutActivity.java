package com.vijithandroid.homefit;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class WorkoutActivity extends AppCompatActivity {

    private static final int SECONDS_TO_MILLI = 1000;
    TextView circuitNumber;
    TextView exerciseName;
    //ImageView exerciseImg;
    TextView timeRemaining;
    ImageButton pausePlayBtn;
    boolean isPaused = true;
    SharedPreferences pref;
    String numOfCircuits;

    long exerciseLength;

    MyCount cdTimer;
    long totaltimeLeft;

    int currentCircuitNumber = 1;
    int currentExerciseIndex = 0;
    boolean firstTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pref = PreferenceManager.getDefaultSharedPreferences(this);

        circuitNumber = (TextView) findViewById(R.id.circuit_number);
        circuitNumber.setText("Circuit 1");
        numOfCircuits = pref.getString("num_of_circuits", "5");

        exerciseName = (TextView) findViewById(R.id.exercise_name);

        timeRemaining = (TextView) findViewById(R.id.time_remaining);
        exerciseLength = Long.valueOf(pref.getString("exercise_length", "90")) * SECONDS_TO_MILLI;
        //totaltimeLeft = 7000;
        totaltimeLeft = exerciseLength;
        //timeRemaining.setText(Integer.toString(exerciseLength));


        pausePlayBtn = (ImageButton) findViewById(R.id.pause_play_button);

        cdTimer = new MyCount(totaltimeLeft, SECONDS_TO_MILLI);
    }

    @Override
    protected void onStart() {

        pausePlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstTime){
                    exerciseName.setText("SQUAT");
                    firstTime = false;
                }
                if(isPaused){
                    startCountDownTimer();
                } else {
                    cdTimer.cancel();
                }
                isPaused = !isPaused;
                pausePlayBtn.setBackgroundResource(isPaused ? R.drawable.ic_play : R.drawable.ic_pause);
            }
        });
        super.onStart();
    }

    private String formatTime(long seconds){
        StringBuffer time = new StringBuffer();
        if(seconds > 60){
            time.append((seconds/60) + ":");
        }
        time.append(seconds%60);
        return time.toString();
    }

    private void startCountDownTimer() {
        cdTimer = new MyCount(totaltimeLeft, SECONDS_TO_MILLI);
        cdTimer.start();
    }

   private void runCircuit(CountDownTimer timer, int currentCircuit, int currentExercise) {

       switch (currentExercise) {
           case 0:
               exerciseName.setText("SQUAT");
               break;
           case 1:
               exerciseName.setText("PUSH UP");
               break;
           case 2:
               exerciseName.setText("MOUNTAIN CLIMBER");
               break;
           case 3:
               exerciseName.setText("BURPEE");
               break;
           case 4:
               exerciseName.setText("PLANK");
               break;
       }
       circuitNumber.setText("Circuit " + currentCircuit);

       timer.start();
   }

    @Override
    protected void onStop() {
        super.onStop();
        cdTimer.cancel();
    }

    public class MyCount extends CountDownTimer
    {
        public MyCount(long millisInFuture, long countDownInterval)
        {
            super(millisInFuture, countDownInterval);
        }

        @Override     public void onTick(long millisUntilFinished)
        {
            totaltimeLeft = millisUntilFinished;
            timeRemaining.setText(formatTime(millisUntilFinished / 1000));
        }

        @Override     public void onFinish()
        {
            try {
                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                if(pref.getBoolean("Sound_Alert", true)){
                    r.play();
                }
                if(pref.getBoolean("Vibration_Alert", true)){
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    // Vibrate for 500 milliseconds (half a second)
                    v.vibrate(500);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //timeRemaining.setText("done!");
            totaltimeLeft = exerciseLength;
            currentExerciseIndex = (currentExerciseIndex + 1) % 5;
            if (currentExerciseIndex == 0) currentCircuitNumber++;
            if(Integer.valueOf(numOfCircuits) < currentCircuitNumber){ // all the circuits are done
                return;
            } else {
                cdTimer = new MyCount(totaltimeLeft, SECONDS_TO_MILLI);
                runCircuit(cdTimer, currentCircuitNumber, currentExerciseIndex);
            }
        }
    }

}
