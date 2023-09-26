package com.challengers.of.call;
import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
public class Alert {
    public void showDialog(final Activity activity, String title, String msg) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.alert_view);
        TextView txttitle = dialog.findViewById(R.id.txttitle);
        txttitle.setText(title);
        TextView text = (TextView) dialog.findViewById(R.id.txtmessage);
        text.setText(msg);
        TextView dialogBtn_cancel = (TextView) dialog.findViewById(R.id.txtcancle);
        dialogBtn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        TextView dialogBtn_okay = (TextView) dialog.findViewById(R.id.txtok);
        dialogBtn_okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dialog.cancel();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    activity.finishAndRemoveTask();
                } else {
                    activity.moveTaskToBack(true);
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                }
            }
        });

        dialog.show();
    }


}
