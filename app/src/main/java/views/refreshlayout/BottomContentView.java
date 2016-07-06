package views.refreshlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by huangli on 16/5/18.
 */
public class BottomContentView extends View{

    private final String TAG = "BottomContentView";

    public BottomContentView(Context context) {
        super(context);
    }

    public BottomContentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BottomContentView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        Log.i(TAG,"BottomContentView onTouchEvent 收到");
        return true;
    }
}
