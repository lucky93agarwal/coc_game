package com.challengers.of.call.testing;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;

import com.challengers.of.call.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class textingActivity extends AppCompatActivity {

    // Save short message command text in item list.
    private String smsCmdArr[] = {"New", "Delete", "Settings", "BookMark", "Block"};
    // Save short message command item image.
    private int smsCmdImgArr[] = {R.drawable.message_new, R.drawable.message_delete, R.drawable.message_settings, R.drawable.message_bookmark, R.drawable.message_block};

    // SimpleAdapter list item key.
    final private String LIST_ITEM_KEY_IMAGE = "image";
    final private String LIST_ITEM_KEY_TEXT = "text";

    // Save phone command text.
    private String phoneCmdArr[] = {"Dial", "Contacts", "Audio Record"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texting);

        setTitle("dev2qa.com --- Popup Window");

        this.createSmsPopup();
    }

    private void createSmsPopup() {

        final Button smsButton = (Button)findViewById(R.id.smsButton);
        smsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initialize short message list item data.
                List<Map<String, Object>> itemList = new ArrayList<Map<String, Object>>();
                int itemLen = smsCmdArr.length;

                for(int i=0;i<itemLen;i++)
                {
                    Map<String,Object> itemMap = new HashMap<String, Object>();
                    itemMap.put(LIST_ITEM_KEY_IMAGE, smsCmdImgArr[i]);
                    itemMap.put(LIST_ITEM_KEY_TEXT, smsCmdArr[i]);

                    itemList.add(itemMap);
                }

                // Create SimpleAdapter that will be used by short message list view.
                SimpleAdapter simpleAdapter = new SimpleAdapter(textingActivity.this, itemList, R.layout.activity_popup_window_sms,
                        new String[]{LIST_ITEM_KEY_IMAGE, LIST_ITEM_KEY_TEXT},
                        new int[]{R.id.smsListItemImage, R.id.smsListItemText});
                // Get short message popup view.
                View popupView = getLayoutInflater().inflate(R.layout.activity_popup_window_sms, null);
                ListView smsListView = (ListView) popupView.findViewById(R.id.popupWindowSmsList);
                smsListView.setAdapter(simpleAdapter);
                smsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int itemIndex, long l) {
                        AlertDialog alertDialog = new AlertDialog.Builder(textingActivity.this).create();
                        alertDialog.setTitle("Short Message Function");
                        alertDialog.setMessage("You select command " + smsCmdArr[itemIndex]);
                        alertDialog.show();
                    }
                });

                // Create popup window.
                PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                // Set popup window animation style.
                popupWindow.setAnimationStyle(R.style.popup_window_animation_sms);

                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

                popupWindow.setFocusable(true);

                popupWindow.setOutsideTouchable(true);

                popupWindow.update();

                // Show popup window offset 1,1 to smsBtton.
                popupWindow.showAsDropDown(smsButton, 1, 1);

            }
        });
    }
}
