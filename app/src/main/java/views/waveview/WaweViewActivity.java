package views.waveview;

import android.app.Activity;
import android.os.Bundle;

import com.example.huangli.mydemos.R;

/**
 * Created by huangli on 16/3/22.
 */
public class WaweViewActivity extends Activity{
    private WaveView waveView1,waveView2,waveView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waveview);
    }
}
