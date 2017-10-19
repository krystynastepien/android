package com.example.krystynastepien.stoper;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;



//   Przygotować interfejs użytkownika dla aplikacji Stoper. Aplikacja ta powinna umożliwiać/posiadać:
//   Przyciski: Start, Lap, Stop/Clear (przy czym powinien być jeden przycisk STOP / CLEAR (jeżeli stoper działa to widoczne jest Stop, a jeżeli nie to Clear)
//   Widoczność aktualnego czasu od początku oraz liste czasow poszczegolnych okrazen



public class MainActivity extends AppCompatActivity {

    Button btnStart, btnReset, btnLap, btnPause;
    TextView txtTimer, txtTimeRecorded;
   // ListView TimeList;
    Handler customHandler = new Handler();
   // LinearLayout Container;
    String tekst_koncowy="";

    long TimeStart=0L, TimeMilisec = 0L, TimeBuff = 0L, TimeUpdate = 0L;

    Runnable updateTimerThread = new Runnable() {
        @Override
        public void run(){
            TimeMilisec = SystemClock.uptimeMillis()-TimeStart;
            TimeUpdate = TimeMilisec + TimeBuff;
            int sekundy = (int)(TimeUpdate/1000);
            int milisekundy = (int)(TimeUpdate%1000);
            int minuty = sekundy/60;
            sekundy = sekundy % 60;
            txtTimer.setText(" "+String.format("%2d",minuty)+":"+
                                 String.format("%2d",sekundy)+":"+
                                 String.format("%4d",milisekundy));
            customHandler.postDelayed(this,0);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnStart = (Button)findViewById(R.id.btnStart);
        btnReset = (Button)findViewById(R.id.btnReset);
                     btnReset.setVisibility(View.GONE);
        btnLap = (Button)findViewById(R.id.btnLap);
        btnPause = (Button)findViewById(R.id.btnPause);

        txtTimer = (TextView) findViewById(R.id.TimerValue);
        txtTimeRecorded = (TextView) findViewById(R.id.TimeRecorded);
        //Container = (LinearLayout) findViewById(R.id.Container);
        txtTimeRecorded.setMovementMethod(new ScrollingMovementMethod());


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeStart = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimerThread,0);
                btnPause.setText("PAUZA");


            }
        });

        btnLap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tekst = String.valueOf(txtTimer.getText());
                tekst_koncowy +="\n" + tekst;
                txtTimeRecorded.setText("\n"+tekst_koncowy+"\n");

                }
        });


            btnPause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (btnPause.getText()=="PAUZA") {
                        btnPause.setText("RESET");
                        TimeBuff = TimeBuff + TimeMilisec;
                        customHandler.removeCallbacks(updateTimerThread);
                        //btnPause.setVisibility(View.GONE);
                        //btnReset.setVisibility(View.VISIBLE);
                    }  else {

                        customHandler.removeCallbacks(updateTimerThread);
                        TimeStart=0L;
                        TimeMilisec = 0L;
                        TimeBuff = 0L;
                        TimeUpdate = 0L;
                        txtTimeRecorded.setText("");
                        txtTimer.setText("00:00:00");
                        tekst_koncowy="";
                    }

                }
             });

            /*
                    btnReset.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            customHandler.removeCallbacks(updateTimerThread);
                            TimeStart=0L;
                            TimeMilisec = 0L;
                            TimeBuff = 0L;
                            TimeUpdate = 0L;
                            txtTimeRecorded.setText("");
                            txtTimer.setText("00:00:00");
                            tekst_koncowy="";
                        }
                    });
            */
    }
}
