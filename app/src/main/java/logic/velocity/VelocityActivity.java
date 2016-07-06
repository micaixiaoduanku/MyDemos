package logic.velocity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by huangli on 16/6/15.
 */
public class VelocityActivity extends Activity{
    private TextView mInfo;
    private VelocityTracker mVelocityTracker;
    private int mMaxVelocity;
    private int mPointerId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInfo = new TextView(this);
        mInfo.setLines(4);
        mInfo.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
        mInfo.setTextColor(Color.WHITE);

        setContentView(mInfo);

        mMaxVelocity = ViewConfiguration.get(this).getMaximumFlingVelocity();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        if(null == mVelocityTracker) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        final VelocityTracker verTracker = mVelocityTracker;
        mVelocityTracker.addMovement(event);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                //求第一个触点的id， 此时可能有多个触点，但至少一个
                mPointerId = event.getPointerId(0);
                break;
            case MotionEvent.ACTION_MOVE:
                //求伪瞬时速度
                verTracker.computeCurrentVelocity(1000, mMaxVelocity);
                final float velocityX = verTracker.getXVelocity(mPointerId);
                final float velocityY = verTracker.getYVelocity(mPointerId);
                recodeInfo(velocityX,velocityY);
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
    private static final String sFormatStr = "velocityX=%f\nvelocityY=%f";
    private void recodeInfo(final float velocityX, final float velocityY) {
        final String info = String.format(sFormatStr, velocityX, velocityY);
        mInfo.setText(info);
    }
}
