package views.refreshlayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.example.huangli.mydemos.R;

/**
 * Created by huangli on 16/5/16.
 */
public class RefreshActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean result = super.dispatchTouchEvent(ev);
        //result是false,那么说明它的decorview没有消耗该touch事件,activity本身的onTouchEvent会被调用
        //result是true,那么说明它的decorview消耗该touch事件
        return result;
    }


}
