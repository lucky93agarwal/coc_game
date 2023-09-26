package com.challengers.of.call;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
public class Refer extends AppCompatActivity {
    private static String REFERAL_CODE = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Sponsorid
        setContentView(R.layout.activity_refer);

        Button button = findViewById(R.id.refer);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.buttonanimation);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                share();
                view.startAnimation(myAnim);
            }
        });
        Button buttoninvite = findViewById(R.id.invite);
        buttoninvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share();
            }
        });
        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        REFERAL_CODE = prefs.getString("Sponsorid", null);
        TextView tvreferal = findViewById(R.id.tvreferal);
        tvreferal.setText(REFERAL_CODE);
        ImageView imageView=findViewById(R.id.ivback);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void share() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBodyText = "Download COC at https://play.google.com/store/apps/details?id=com.callof.challengers.coc sponsor and win money for playing the ultimate quiz game . Apply my referral code " +REFERAL_CODE+ " to get extra lives.\n";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
        startActivity(Intent.createChooser(sharingIntent, "Sharing Option"));
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
