package com.example.broadcasttext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity  {

    private IntentFilter intentFilter;
    private NetworkChangeReciver network;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intentFilter=new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        network=new NetworkChangeReciver();
        registerReceiver(network, intentFilter);

    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        unregisterReceiver(network);

    }
    class NetworkChangeReciver extends BroadcastReceiver{

        @Override
        public void onReceive(Context arg0, Intent arg1) {
            // TODO Auto-generated method stub
            ConnectivityManager connectivityManager=(ConnectivityManager) getSystemService(arg0.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            if (networkInfo!=null&&networkInfo.isAvailable()) {
                Toast.makeText(arg0, "network is aviliable", Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(arg0, "network is unaviliable", Toast.LENGTH_SHORT).show();
            }

        }

    }

}