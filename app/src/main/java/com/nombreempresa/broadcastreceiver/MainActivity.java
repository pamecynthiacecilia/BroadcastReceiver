package com.nombreempresa.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;

public class MainActivity extends AppCompatActivity {
    private MensajeRecibido mensajeRecibido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},1000);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        mensajeRecibido= new MensajeRecibido();
        registerReceiver(mensajeRecibido, new IntentFilter("android.provider.Telephony.SMS_RECEIVED" ));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mensajeRecibido);
    }
}