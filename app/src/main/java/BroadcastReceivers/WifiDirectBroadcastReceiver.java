package BroadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;

import com.example.nsolanki.myapplication.MyWifiActivity;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by nsolanki on 7/8/2016.
 */
public class WifiDirectBroadcastReceiver extends BroadcastReceiver {

    private WifiP2pManager wifiP2pManager;
    private WifiP2pManager.Channel channel;
    private MyWifiActivity myWifiActivity;


    public WifiDirectBroadcastReceiver(WifiP2pManager manager, WifiP2pManager.Channel channel, MyWifiActivity activity) {
        this.wifiP2pManager = manager;
        this.channel = channel;
        this.myWifiActivity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                // Wifi P2P is enabled
            } else {
                // Wi-Fi P2P is not enabled
            }
        } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {

            if (wifiP2pManager != null) {
                wifiP2pManager.requestPeers(channel, new WifiP2pManager.PeerListListener() {
                    @Override
                    public void onPeersAvailable(WifiP2pDeviceList peers) {
                        for (WifiP2pDevice device : peers.getDeviceList()) {
                            Log.e("nikhil", "Peer name :" + device.deviceName);
                            WifiP2pConfig config = new WifiP2pConfig();
                            config.deviceAddress = device.deviceAddress;

                            wifiP2pManager.connect(channel, config, new WifiP2pManager.ActionListener() {
                                @Override
                                public void onSuccess() {
                                    //Call the async task from here to transfer the data//
                                }

                                @Override
                                public void onFailure(int reason) {

                                }
                            });
                            Log.e("nikhil", "Device name :" + device.deviceName);
                        }
                    }
                });
            }
            // Call WifiP2pManager.requestPeers() to get a list of current peers
        } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
            // Respond to new connection or disconnections
        } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {
            // Respond to this device's wifi state changing
        }
    }
}
