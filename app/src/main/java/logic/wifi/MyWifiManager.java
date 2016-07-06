package logic.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import java.util.List;

/**
 * Created by huangli on 16/3/15.
 */
public class MyWifiManager {
    interface WifiPointsStateCallBack{
        void scanresult(List<ScanResult> list);
    }
    private WifiPointsStateCallBack wifiPointsStateCallBack;
    private WifiManager wifiManager;
    public MyWifiManager(Context context,WifiPointsStateCallBack wifiPointsStateCallBack){
        this.wifiPointsStateCallBack = wifiPointsStateCallBack;
        wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        registerAllReceiver(context);
    }
    private void registerAllReceiver(Context context) {
        IntentFilter wifiStateFilter = new IntentFilter();
        wifiStateFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        wifiStateFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        wifiStateFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        wifiStateFilter.addAction(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION);
        wifiStateFilter.addAction(WifiManager.RSSI_CHANGED_ACTION);
        wifiStateFilter.addAction(WifiManager.NETWORK_IDS_CHANGED_ACTION);
        context.registerReceiver(mWifiStateReceiver, wifiStateFilter);
    }
    private final BroadcastReceiver mWifiStateReceiver = new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (intent.getAction().equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
                handleScanResultsAvailable();
            }
        }
    };
    private void handleScanResultsAvailable(){
        List<ScanResult> list = wifiManager.getScanResults();
        if (wifiPointsStateCallBack != null){
            wifiPointsStateCallBack.scanresult(list);
        }
    }
    public void startScan(){
        wifiManager.startScan();
    }
}
