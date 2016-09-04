package com.example.nsolanki.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import BroadcastReceivers.WifiDirectBroadcastReceiver;

public class MyWifiActivity extends AppCompatActivity {

    WifiP2pManager mManager;
    WifiP2pManager.Channel channel;
    BroadcastReceiver mReceiever;
    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        channel = mManager.initialize(this,getMainLooper(),null);
        mReceiever = new WifiDirectBroadcastReceiver(mManager,channel,this);

        setUpListeners();
        intentFilter = new IntentFilter();
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mReceiever,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiever);
    }

    public void setUpListeners(){
        mManager.discoverPeers(channel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                Log.e("nikhil","Inside on success case");
            }

            @Override
            public void onFailure(int reason) {
                Log.e("nikhil","Inside on failure case");
            }
        });
    }
}
