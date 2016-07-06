package logic.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by huangli on 16/5/30.
 */
public class MessengerService extends Service{
    private static final String TAG = "MessengerService";

    private static class MessengerHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    Log.i(TAG,"receive msg from client : "+msg.getData());
                    break;
            }
        }
    }

    private final Messenger messenger = new Messenger(new MessengerHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}
