package logic.ipc;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;

import com.example.huangli.mydemos.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by huangli on 16/5/28.
 */
public class IPCActivityMain extends Activity{
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipc_main);
        btn = (Button)findViewById(R.id.btn_switch);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(IPCActivityMain.this,IPCActivitySecond.class);
//                startActivity(intent);
            }
        });
    }

    private IMyBookManager iMyBookManager;
    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient(){
        @Override
        public void binderDied() {
            if (iMyBookManager == null){
                return;
            }
            iMyBookManager.asBinder().unlinkToDeath(mDeathRecipient,0);
            iMyBookManager = null;
            //TODO: 这里重新绑定远程service
        }
    };

    private void bindMessagerService(){
        ServiceConnection mConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Messenger mService = new Messenger(service);
                Message msg = Message.obtain(null,0);
                Bundle data = new Bundle();
                data.putString("msg","hello,this is client");
                msg.setData(data);
                try{
                    mService.send(msg);
                }catch (RemoteException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        Intent intent = new Intent(this,MessengerService.class);
        bindService(intent,mConnection,BIND_AUTO_CREATE);
    }

    private void bindService(){
        Intent intent = new Intent(this,LibService.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                iMyBookManager = BookManagerImpl.asInterface(service);
                try {
                    service.linkToDeath(mDeathRecipient,0);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        },BIND_AUTO_CREATE);
    }

    private void serializableTest() throws IOException, ClassNotFoundException {
        //序列化过程
        UserSerializable userSerializable = new UserSerializable(0,"jake",true);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("cache.txt"));
        out.writeObject(userSerializable);
        out.close();
        //反序列化过程
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("cache.txt"));
        UserSerializable newUserSerializable = (UserSerializable)in.readObject();
        in.close();
    }
}
