package com.challengers.of.call;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

public class Startfreequiz extends AppCompatActivity {
    String Contestid="";
    Handler handler;
    boolean stop=true;
    String data="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startquiz);
        Contestid = getIntent().getStringExtra("Contestid");
        data=getIntent().getStringExtra("data");
        final TextView textView=findViewById(R.id.textView);
        new CountDownTimer(6000, 1000) {
            public void onTick(long millisUntilFinished) {
                MediaPlayer mediaPlayer=MediaPlayer.create( Startfreequiz.this,R.raw.timersound);
                mediaPlayer.start();
                String second=(millisUntilFinished / 1000)+"";
                textView.setText(second);
                textView.setTextSize(150);
            }
            public void onFinish() {
                textView.setText("P L A Y");
                textView.setTextSize(90);
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(stop) {
                            SharedPreferences shear = getSharedPreferences("fifty", Context.MODE_PRIVATE);
                            SharedPreferences.Editor edit = shear.edit();
                            edit.putString("fifty","true");
                            edit.apply();
                            Intent intent = new Intent(Startfreequiz.this, Activityfreequiz.class);
                            intent.putExtra("Contestid", Contestid);
                            intent.putExtra("data",data);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            handler.removeCallbacks(this);
                        }
                    }
                }, 2000);
            }
        }.start();
    }

    @Override
    public void onBackPressed() {
//        stop=false;
//        super.onBackPressed();
    }
}
