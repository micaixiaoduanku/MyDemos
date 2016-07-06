package components.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

/**
 * Created by huangli on 16/3/17.
 */
public class ConfigChangeReceiver extends BroadcastReceiver {
    public static final String APP_CONFIG_LOADED = "mobi.wifi.abc.APP_CONFIG_LOADED";
    private static final String TAG = "ConfigChangeReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "收到App配置改变的广播了");
        if (intent == null) {
            return;
        }
        if (intent.getAction().equals(APP_CONFIG_LOADED)) {
            Log.i(TAG, "收到App配置改变的广播了 APP_CONFIG_CHANGE APP_CONFIG_LOADED");
        }
    }

    public static void register(Context context,ConfigChangeReceiver configChangeReceiver){
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(context);
        IntentFilter filter = new IntentFilter();
        filter.addAction(APP_CONFIG_LOADED);
        lbm.registerReceiver(configChangeReceiver, filter);
    }

    public static void sendTest(Context context){
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(context);
        Intent intent = new Intent(APP_CONFIG_LOADED);
        lbm.sendBroadcast(intent);
    }

}
