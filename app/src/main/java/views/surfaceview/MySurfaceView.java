package views.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by huangli on 16/6/21.
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback{
    private SurfaceHolder holder;
    private MyThread myThread;

    public MySurfaceView(Context context) {
        super(context);
        init();
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        holder = this.getHolder();
        holder.addCallback(this);
        myThread = new MyThread(holder);//创建一个绘图线程
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                myThread.isRun = true;
                myThread.start();
            }
        },200);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        myThread.isRun = false;
    }

    //线程内部类
    class MyThread extends Thread
    {
        private SurfaceHolder holder;
        public boolean isRun ;
        public  MyThread(SurfaceHolder holder)
        {
            this.holder =holder;
            isRun = true;
        }
        @Override
        public void run() {
            int count = 0;
            while(isRun)
            {
                Canvas c = null;
                try {
                    synchronized (holder) {
                        c = holder.lockCanvas();
                        c.drawColor( Color.TRANSPARENT, PorterDuff.Mode.CLEAR );
//                        c.drawColor(Color.BLACK);//设置画布背景颜色
                        Paint p = new Paint(); //创建画笔
                        p.setColor(Color.WHITE);
                        Rect r = new Rect(100, 50, 300, 250);
                        c.drawCircle(50,50,100,p);
//                        c.drawRect(r, p);
                        c.drawText("这是第" + (count++) + "秒", 100, 310, p);
                        Thread.sleep(1000);//睡眠时间为1秒
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if (c != null){
                        holder.unlockCanvasAndPost(c);//结束锁定画图，并提交改变。
                    }
                }
            }
        }
    }
}
