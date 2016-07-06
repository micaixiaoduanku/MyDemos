package views.floatview;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by huangli on 16/3/11.
 */
public class BackgroundService extends Service {
    FloatWindowManager floatWindowManager;

    @Override
    public void onCreate() {
        super.onCreate();
        floatWindowManager = new FloatWindowManager(getApplicationContext());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        floatWindowManager.onStart();
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        floatWindowManager.onDestroy();
    }
}
