package com.challengers.of.call;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import static android.content.Context.VIBRATOR_SERVICE;

public class errorpopuplayout {
    public Dialog epicDialog, unepicDialog, progressbar, withdraw;

    private final Context _context;

    private Vibrator vibrator;
    public Button closepopupbtn;
    public Animation myAnim;

    public errorpopuplayout(Context context) {
        this._context = context;
        unepicDialog = new Dialog( context, R.style.PauseDialog);
        epicDialog = new Dialog(context, R.style.PauseDialog); // for popup Dialog
        progressbar = new Dialog(context);



        vibrator= (Vibrator)context.getSystemService(VIBRATOR_SERVICE);
        myAnim = AnimationUtils.loadAnimation(context, R.anim.buttonanimation);
    }

    public void error(){
        epicDialog.setContentView(R.layout.errorpopup);
        epicDialog.setCancelable(false);

        closepopupbtn = (Button)epicDialog.findViewById(R.id.closePopup);// popup Close button

        closepopupbtn.setOnClickListener(new View.OnClickListener() {// popup Close button working
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();
            }
        });

        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
        epicDialog.show();
    }
}
