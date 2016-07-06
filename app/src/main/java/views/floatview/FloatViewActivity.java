package views.floatview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.huangli.mydemos.R;

/**
 * Created by huangli on 16/3/11.
 */
public class FloatViewActivity extends Activity{
    private Button btn = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floatview);
        btn = (Button)findViewById(R.id.open_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(FloatViewActivity.this, BackgroundService.class));
            }
        });
    }
}
