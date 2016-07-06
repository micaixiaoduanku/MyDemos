package views.floatview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.huangli.mydemos.R;

import utils.DisplayUtils;

/**
 * Created by huangli on 16/3/11.
 */
public class WifiFloatingView extends RelativeLayout {
    public static String TAG = "WifiFloatingView";
    private final int screenHeight;
    private final int screenWidth;
    private int lastLayoutParamsX = 0;
    private int lastLayoutParamsY = 0;
    private int mTouchState;
    private IWifiFloatingView callback;
    public enum WidownDirection {
        LEFT, RIGHT, TOP, Gravity, BOTTOM
    }
    public interface IWifiFloatingView {
        void onUpdateViewLayout(int x, int y);
        void onViewClick();
        void onSide(WidownDirection direction, int start, int end, int lastX, int lastY);
    }
    public WifiFloatingView(Context context) {
        super(context);
        screenWidth = DisplayUtils.getScreenWidth(context);
        screenHeight = DisplayUtils.getScreenHeight(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_ball_floater, null);
        addView(view);
        setOnTouchListener(onTouchListener);
    }
    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        public int paramY;
        public int paramX;
        public int lastY;
        public int lastX;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mTouchState = MotionEvent.ACTION_DOWN;
                    lastX = (int) event.getRawX();
                    lastY = (int) event.getRawY();
                    paramX = lastLayoutParamsX;
                    paramY = lastLayoutParamsY;
                    break;
                case MotionEvent.ACTION_MOVE:
                    mTouchState = MotionEvent.ACTION_MOVE;
                    int dx = (int) event.getRawX() - lastX;
                    int dy = (int) event.getRawY() - lastY;
                    lastLayoutParamsX = paramX + dx;
                    lastLayoutParamsY = paramY + dy;
                    Log.i(TAG,"MOVE X "+lastLayoutParamsX+" Y "+lastLayoutParamsY);
                    updateViewLayout();
                    break;
                case MotionEvent.ACTION_UP:
                    mTouchState = MotionEvent.ACTION_UP;

                    int curX = (int) event.getRawX();
                    int curY = (int) event.getRawY();
                    int px = DisplayUtils.dp2Px(getContext(), 5);
                    //是否是点击的情况
                    if (Math.abs(curX - lastX) < px && Math.abs(curY - lastY) < px) {
                        lastLayoutParamsX = paramX;
                        lastLayoutParamsY = paramY;
                        updateViewLayout();
                        onClick();
                    } else {
                        int start,end;
                        WidownDirection newDirection = WidownDirection.LEFT;
                        int radius = getMeasuredWidth() / 2;
                    }
                    break;
            }
            return true;
        }
    };

    public void setInterface(IWifiFloatingView callback) {
        this.callback = callback;
    }

    private void updateViewLayout() {
        callback.onUpdateViewLayout(lastLayoutParamsX, lastLayoutParamsY);
    }

    public void onClick() {

    }
}
