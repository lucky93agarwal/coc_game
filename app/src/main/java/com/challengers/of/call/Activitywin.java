package com.challengers.of.call;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.challengers.of.call.Banned_APP.bannedapp;

public class Activitywin extends AppCompatActivity {
    boolean stop = true;
    public TextView winning;
    public Double winningamount;
    private static bannedapp bannedapps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitywin);

        bannedapps = new bannedapp(Activitywin.this);
        bannedapps.bannedbig();

//        winning = (TextView)findViewById(R.id.winning);
//        winningamount = getIntent().getDoubleExtra("winning",0.0);
//        String stringdouble= Double.toString(winningamount);
//        winning.setText(stringdouble);
//
//        DisplayMetrics dm = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
//        int width = dm.widthPixels;
//        int height = dm.heightPixels;
//
//        getWindow().setLayout((int)(width*.8),(int)(height*.7));
//
//        WindowManager.LayoutParams params = getWindow().getAttributes();
//        params.gravity = Gravity.CENTER;
//        params.x = 0;
//        params.y = -20;
//
//        getWindow().setAttributes(params);
//
//
//
//        Thread td = new Thread(){
//            public void run(){
//                try{
//                    sleep(6000);
//                }catch (Exception ex){
//                    ex.printStackTrace();
//                }finally
//                {
//                    Intent intent = new Intent(Activitywin.this, Main2Activity.class);
//                    intent.putExtra("goto", "Result");
//                    intent.putExtra("Totalcontest", Main2Activity.Totalcontest);
//                    startActivity(intent);
//                    finish();
//                }
//            }
//
//        };td.start();

        final ImageView imageView = findViewById(R.id.imageView);
        Glide.with(Activitywin.this)
                .load(R.raw.animation)
                .asGif()
                .crossFade()
                .listener(new RequestListener<Integer, GifDrawable>() {
                    @Override
                    public boolean onException(Exception e, Integer model, Target<GifDrawable> target, boolean isFirstResource) {
                        return false;
                    }
                    @Override
                    public boolean onResourceReady(GifDrawable resource, Integer model, Target<GifDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        MediaPlayer mediaPlayer = MediaPlayer.create(Activitywin.this, R.raw.win);
                        mediaPlayer.start();
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(Activitywin.this, Main2Activity.class);
                                intent.putExtra("goto", "Result");
                                intent.putExtra("Totalcontest", Main2Activity.Totalcontest);
                                startActivity(intent);
                                finish();
                            }
                        }, 6000);
                        return false;
                    }
                })
                .into(imageView);
        playsound(R.raw.win);
    }

    @Override
    public void onBackPressed() {
    }
    private void playsound(int sound) {
        MediaPlayer mediaPlayer = MediaPlayer.create(Activitywin.this, sound);
        mediaPlayer.start();
    }
}
