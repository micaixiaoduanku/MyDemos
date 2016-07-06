package views.remoteViews;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.SystemClock;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.huangli.mydemos.R;

import utils.ToastUtils;

/**
 * Created by huangli on 16/5/19.
 */
public class MyAppWidgetProvider extends AppWidgetProvider{

    public static final String TAG = "MyAppWidgetProvider";
    public static final String CLICK_ACTION = "views.remoteViews.action_CLICK";

    public MyAppWidgetProvider(){
        super();
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.i(TAG,"onReceive : action = "+intent.getAction());
        //如果小部件被单击了，做一个动画效果
        if (intent.getAction().equals(CLICK_ACTION)){
            ToastUtils.showShortToast(context,"小部件被点击了!");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Bitmap srcbBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.com_facebook_button_icon);
                    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                    for (int i = 0; i < 37;i++){
                        float degree = (i * 10)%360;
                        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.widget_remote);
                        remoteViews.setImageViewBitmap(R.id.imageView1,rotateBitmap(context,srcbBitmap,degree));
                        Intent intentClick = new Intent();
                        intentClick.setAction(CLICK_ACTION);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intentClick,0);
                        remoteViews.setOnClickPendingIntent(R.id.imageView1,pendingIntent);
                        appWidgetManager.updateAppWidget(new ComponentName(context,MyAppWidgetProvider.class),remoteViews);
                        SystemClock.sleep(30);
                    }
                }
            }).start();
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.i(TAG,"onUpdate");
        final int counter = appWidgetIds.length;
        Log.i(TAG,"counter = "+counter);
        for (int i = 0; i < counter; i++){
            int appWifgetId = appWidgetIds[i];
            onWidgetUpdate(context,appWidgetManager,appWifgetId);
        }
    }

    private void onWidgetUpdate(Context context, AppWidgetManager appWidgetManager, int appWidgetId){
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.widget_remote);
        Intent intentClick = new Intent();
        intentClick.setAction(CLICK_ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intentClick,0);
        remoteViews.setOnClickPendingIntent(R.id.imageView1,pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId,remoteViews);
    }

    private Bitmap rotateBitmap(Context context, Bitmap srcbBitmap, float degree){
        Matrix matrix = new Matrix();
        matrix.reset();
        matrix.setRotate(degree);
        Bitmap tmpBitmap = Bitmap.createBitmap(srcbBitmap,0,0,srcbBitmap.getWidth(),srcbBitmap.getHeight(),matrix,true);
        return tmpBitmap;
    }
}
