package com.challengers.of.call;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import java.util.List;

public class Startquiz extends AppCompatActivity {
    String Contestid = "";
    boolean stop = true;
    String From = "";
    MediaPlayer mediaPlayer;
    CountDownTimer timer;
    private boolean RESUME=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startquiz);
        Contestid = getIntent().getStringExtra("Contestid");
        From = getIntent().getStringExtra("From");
        final TextView textView = findViewById(R.id.textView);
        timer=new CountDownTimer(6000, 1000) {
            public void onTick(long millisUntilFinished) {
                playsound(R.raw.timersound);
                String second = (millisUntilFinished / 1000) + "";
                textView.setText(second);
                textView.setTextSize(150);
            }
            public void onFinish() {
                playsound(R.raw.playsound);
                textView.setText("P L A Y");
                textView.setTextSize(90);
                AsyncCallWSwait task = new AsyncCallWSwait();
                task.execute();
            }
        }.start();
    }
    @Override
    public void onBackPressed() {
    }
    private void playsound(int sound) {
        mediaPlayer = MediaPlayer.create(Startquiz.this, sound);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
    }



    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        super.onDestroy();
    }
    public class AsyncCallWSwait extends AsyncTask<String, Void, Void> {
        String displayText;
        @Override
        protected Void doInBackground(String... params) {
            try {
                if (stop) {
                    if (mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                    }
                }
                Thread.currentThread();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            if(!isApplicationSentToBackground(Startquiz.this)) {
                Intent intent = new Intent(Startquiz.this, Activityquiz.class);
                intent.putExtra("Contestid", Contestid);
                intent.putExtra("From", From);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
            }

//            else
//            {
//                Intent intent = new Intent(Startquiz.this, Activityquiz.class);
//                intent.putExtra("Contestid", Contestid);
//                intent.putExtra("From", From);
//                startActivity(intent);
//                finish();
//                overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
//            }
        }
        @Override
        protected void onPreExecute() {
        }
        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
    public boolean isApplicationSentToBackground(final Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onPause() {
        Intent intent = new Intent(Startquiz.this, Activityquiz.class);
        intent.putExtra("Contestid", Contestid);
        intent.putExtra("From", From);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        super.onPause();
    }


    @Override
    protected void onResume() {
        RESUME=true;
        super.onResume();
    }

    @Override
    protected void onRestart() {
        if (RESUME) {
            if (timer != null) {
                timer.cancel();
            }
            Intent intent = new Intent(Startquiz.this, Activityquiz.class);
            intent.putExtra("Contestid", Contestid);
            intent.putExtra("From", From);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
            super.onRestart();
        }
    }
}
