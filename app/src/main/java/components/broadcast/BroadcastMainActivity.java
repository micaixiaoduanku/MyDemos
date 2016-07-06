package components.broadcast;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.huangli.mydemos.R;

/**
 * Created by huangli on 16/3/17.
 * 这个demo用于测试LocalBroadcastManager发送和接收广播和普通的发送接收的区别
 */
public class BroadcastMainActivity extends Activity{
    private Button btn_main,btn_thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
//        ConfigChangeReceiver.register(getApplicationContext(),new ConfigChangeReceiver());
        btn_main = (Button)findViewById(R.id.btn_send_from_main);
        btn_thread = (Button)findViewById(R.id.btn_send_from_thread);
        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfigChangeReceiver.sendTest(getApplicationContext());
            }
        });
        btn_thread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThreadManager.executeInBackground(loadConfigTask);
            }
        });
    }

    private Runnable loadConfigTask = new Runnable() {
        public void run() {
            ConfigChangeReceiver.sendTest(getApplicationContext());
        }
    };
}
