package com.example.lab9;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import java.security.Provider;

public class MyService extends Service {
    static Boolean flag=false;
    private int h=0,m=0,s=0;
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implement");
    }
    public int onStartCommand(Intent intent,int flags,int startID){
        flag= intent.getBooleanExtra("flag",false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag) {
                    try {
                        Thread.sleep(1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    s=s+1;
                    if (s >= 60) {
                        s = 0;
                        m++;
                        if (m >= 60) {
                            m = 0;
                            h++;
                        }
                    }
                    Intent intent = new Intent("MyMessage");
                    Bundle bundle = new Bundle();
                    bundle.putInt("H", h);
                    bundle.putInt("M", m);
                    bundle.putInt("S", s);
                    intent.putExtras(bundle);

                    sendBroadcast(intent);
                }
            }
        }).start();
        return Service.START_STICKY;
    }
}

