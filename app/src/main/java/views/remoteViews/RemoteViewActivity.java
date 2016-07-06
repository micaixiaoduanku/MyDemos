package views.remoteViews;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.huangli.mydemos.R;

/**
 * Created by huangli on 16/5/19.
 */
public class RemoteViewActivity extends Activity{
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remoteview);
        btn = (Button)findViewById(R.id.btn_notify);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private void popupNotification(){
        Notification notification = new Notification();
        notification.icon = R.drawable.icon_seek_avilable;
        notification.tickerText = "hello world";
        notification.when = System.currentTimeMillis();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        Intent intent = new Intent(this,RemoteViewActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1,notification);
    }
}
