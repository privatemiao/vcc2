package com.easyway.vcc;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.easyway.vcc.serial.SerialPortActivity;

public class SettingActivity extends SerialPortActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("VCC", "SettingActivity onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        findViewById(R.id.btn_video_chat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingActivity.this, VideoChatActivity.class));
            }
        });
    }

    @Override
    protected void onButtonUp() {
        onDestroy();
        startActivity(new Intent(SettingActivity.this, VideoChatActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("VCC", "SettingActivity onDestroy");
    }

    @Override
    protected void onResume() {
        super.onResume();
        final SerialPortActivity a = this;

        Utils.processDelay(new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.d("VCC", a.mSerialPort == null ? "NULL" : "NOT NULL");
                if (a.mSerialPort == null) {
                    a.init();
                }
            }
        }, 1000L);
    }
}
