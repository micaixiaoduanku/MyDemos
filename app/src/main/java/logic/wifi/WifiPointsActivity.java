package logic.wifi;

import android.app.Activity;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.huangli.mydemos.R;

import java.util.List;

/**
 * Created by huangli on 16/3/15.
 */
public class WifiPointsActivity extends Activity implements MyWifiManager.WifiPointsStateCallBack{
    public String TAG = "WifiPointsActivity";
    private MyWifiManager myWifiManager;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifipoints);
        btn = (Button)findViewById(R.id.btnstart);
        myWifiManager = new MyWifiManager(this,this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myWifiManager.startScan();
            }
        });
    }

    @Override
    public void scanresult(List<ScanResult> list) {
        for (ScanResult scanResult : list){
            Log.i(TAG,scanResult.SSID);
        }
    }
}
