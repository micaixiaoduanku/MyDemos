package views.whewview;

import android.app.Activity;
import android.os.Bundle;

import com.example.huangli.mydemos.R;

/**
 * Created by huangli on 16/6/10.
 */
public class WhewviewActivity extends Activity{
    private WhewView whewView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whewview);
        whewView = (WhewView)findViewById(R.id.whewview);
        whewView.startAnim(1000 * 5);
    }
}
