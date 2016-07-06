package views.floatview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.PixelFormat;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;

import com.example.huangli.mydemos.R;

import utils.DisplayUtils;
import utils.PackageUtils;
import utils.PlatformUtils;

/**
 * Created by huangli on 16/3/11.
 */
public class FloatWindowManager {
    public static String TAG = "FloatWindowManager";
    private WindowManager.LayoutParams params;
    private WifiFloatingView wifiFloatingView = null;
    private WindowManager mWindowManager = null;
    private String defaultLauncher;
    private Context mContext;
    public FloatWindowManager(Context context) {
        this.mContext = context;
        defaultLauncher = getDefaultLauncher();
        wifiFloatingView = new WifiFloatingView(mContext);
        mWindowManager = (WindowManager) mContext.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        wifiFloatingView.setInterface(mViewCallback);
    }

    private WifiFloatingView.IWifiFloatingView mViewCallback = new WifiFloatingView.IWifiFloatingView() {

        @TargetApi(Build.VERSION_CODES.KITKAT)
        @Override
        public void onSide(WifiFloatingView.WidownDirection direction, int start, int end, int lastX, int lastY) {
        }

        @TargetApi(Build.VERSION_CODES.KITKAT)
        @Override
        public void onUpdateViewLayout(int x, int y) {
            if (!wifiFloatingView.isShown()) {
                return;
            }
            if (PlatformUtils.isOverVersion(PlatformUtils.VERSION_CODES.KITKAT)) {
                if (!wifiFloatingView.isAttachedToWindow()) {
                    return;
                }
            }
            params.x = x;
            params.y = y;
            Log.i(TAG, "UPDATE X " + params.x  + " Y " + params.y);
            mWindowManager.updateViewLayout(wifiFloatingView, params);
        }

        @Override
        public void onViewClick() {

        }
    };

    public void onStart(){
        params = new WindowManager.LayoutParams();
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        params.format = PixelFormat.RGBA_8888;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        params.width =(int) mContext.getResources().getDimension(R.dimen.float_ball_height);
        params.height = (int) mContext.getResources().getDimension(R.dimen.float_ball_height);
        params.gravity = Gravity.RIGHT;

        WindowManager.LayoutParams params1 = readLayoutParams();
        params.x = params1.x;
        params.y = params1.y;

        mWindowManager.addView(wifiFloatingView, params);
    }

    private void checkHome() {
        boolean isForeground = isLauncherForeground();
    }

    public void onDestroy(){

    }

    private boolean isLauncherForeground() {
        return PackageUtils.isAppOnTop(mContext, defaultLauncher);
    }

    private String getDefaultLauncher() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        ResolveInfo resolveInfo = mContext.getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (resolveInfo != null) {
            return resolveInfo.activityInfo.packageName;
        } else {
            return "";
        }
    }

    private WindowManager.LayoutParams readLayoutParams() {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.x = 0;
        params.y = 0;
        if (params.x == 0 && params.y == 0) {
            params.x = DisplayUtils.getScreenWidth(mContext);
            params.y = 0;
        }
        return params;
    }
}
