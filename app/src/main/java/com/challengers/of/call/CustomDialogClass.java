package com.challengers.of.call;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public TextView yes, no;

    public CustomDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.alert_view);
        yes = (TextView) findViewById(R.id.txtok);
        no = (TextView) findViewById(R.id.txtcancle);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtok:
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    c.finishAndRemoveTask();
//                } else {
//                    c.moveTaskToBack(true);
//                    android.os.Process.killProcess(android.os.Process.myPid());
//                    System.exit(1);
                    c.finish();
//                    int pid = android.os.Process.myPid();
//                    android.os.Process.killProcess(pid);
//                }
                break;
            case R.id.txtcancle:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}