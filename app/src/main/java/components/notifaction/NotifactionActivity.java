package components.notifaction;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.huangli.mydemos.R;

/**
 * Created by huangli on 16/6/13.
 */
public class NotifactionActivity extends Activity implements View.OnClickListener{
    private Button btnSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifaction);
        btnSend = (Button)findViewById(R.id.btn_send);
        btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_send:
                NotifactionUtil.sendNotifaction(this,NotifactionActivity.class);
                break;
        }
    }
}
