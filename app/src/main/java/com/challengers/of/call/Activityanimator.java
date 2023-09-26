package com.challengers.of.call;
import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
public class Activityanimator extends AppCompatActivity {
    TextView anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityanimator);
        anim=findViewById(R.id.anim);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Path path = new Path();
            path.lineTo(100f,100f);
            path.lineTo(1f,1f);
            path.lineTo(150f,2f);
            ObjectAnimator animator = ObjectAnimator.ofFloat(anim, anim.X, anim.Y, path);
//            ObjectAnimator animator=new ObjectAnimator();
//            animator.addListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationCancel(Animator animation) {
//                    super.onAnimationCancel(animation);
//                }
//
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    super.onAnimationEnd(animation);
//                    Toast.makeText(Activityanimator.this,"Animation Finshed",Toast.LENGTH_LONG).show();
//                }
//
//                @Override
//                public void onAnimationRepeat(Animator animation) {
//                    super.onAnimationRepeat(animation);
//                }
//
//                @Override
//                public void onAnimationStart(Animator animation) {
//                    super.onAnimationStart(animation);
//                    Path path = new Path();
//                    path.lineTo(100f,100f);
//
//                }
//
//                @Override
//                public void onAnimationPause(Animator animation) {
//                    super.onAnimationPause(animation);
//                }
//
//                @Override
//                public void onAnimationResume(Animator animation) {
//                    super.onAnimationResume(animation);
//                }
//            });
            animator.setDuration(5000);
            animator.start();
        } else {
            // Create animator without using curved path
        }
    }
}
