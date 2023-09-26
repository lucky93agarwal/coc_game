package com.challengers.of.call;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
public class Earnmore extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earnmore);
        ImageView ivback = findViewById(R.id.ivback);
        SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
        String Loginid = prefs.getString("Loginid", null);
        String Name = prefs.getString("Name", null);
        String Password = prefs.getString("Password", null);
        TextView textView = findViewById(R.id.username);
        textView.setText(Name);
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
