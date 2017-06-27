package com.easyway.vcc;

import android.os.Handler;
import android.os.Message;

/**
 * Created by Mel on 6/27/2017.
 */

public class Utils {
    public static void processDelay(final IProcess process, final Handler handler) {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        Message message = process.doProcess();
                        handler.sendMessage(message);
                    }
                }
        ).start();
    }

    public static void processDelay(final Handler handler, final long time){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.obj = "ok";
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendMessage(message);
            }
        }).start();
    }
}
