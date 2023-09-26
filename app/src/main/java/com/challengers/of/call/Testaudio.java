package com.challengers.of.call;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
public class Testaudio extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testaudio);
        final ImageView imageView = findViewById(R.id.imageView);
        Glide.with(Testaudio.this)
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
                        //imageView.setImageDrawable(resource);
                        MediaPlayer mediaPlayer = MediaPlayer.create(Testaudio.this, R.raw.win);
                        mediaPlayer.start();
                        return false;
                    }
                })
                .into(imageView);
//        new Handler().post(new Runnable() {
//            @Override
//            public void run() {
//                // Code here will run in UI thread
//
//
//
//            }
//        });



    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
