package views.whewview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by huangli on 16/6/10.
 */
public class WhewView extends View{
    private Paint paint;

    public WhewView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // TODO Auto-generated constructor stub
        init();
    }
    public WhewView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        init();
    }
    public WhewView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        init();
    }

    private void init() {
        paint = new Paint();
        // 设置博文的颜色
        paint.setColor(0xffffe061);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackgroundColor(Color.TRANSPARENT);// 颜色：完全透明
        Log.i("tag","onDraw");
        paint.setAlpha(80);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth()/2+offset_3,
                paint);
        paint.setAlpha(120);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth()/2-EACH_WIDTH+offset_2,
                    paint);
        paint.setAlpha(160);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth()/2-EACH_WIDTH*2+offset_1,
                paint);
        paint.setAlpha(200);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth()/2-EACH_WIDTH*3+offset_0,
                paint);
        Log.i("tag","width "+ (getWidth()/2-300+offset_0));
    }

    private int offset_0 = 0;
    private int offset_1 = 0;
    private int offset_2 = 0;
    private int offset_3 = 0;

    private final int INTERVEIN_TIME_SIZE = 6; //交错次数
    private final int INTERVEIN_TIME = 200; //毫秒
    private final int OFFSET = 50;
    private final int TIMES = 3;
    private final int EACH_WIDTH = 80;

//    private int EACH_DURATION = 1500;  //测试每次动画执行时间1500毫秒最符合需求

    private void animPlayOnce(int duration){
        final int eachdura = (duration+INTERVEIN_TIME*INTERVEIN_TIME_SIZE)/8;
        ValueAnimator valueAnimator_0 =  ValueAnimator.ofInt(OFFSET);
        valueAnimator_0.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                offset_0 = value%(OFFSET+1);
                Log.i("tag","Expand offset_0 "+offset_0);
                invalidate();
            }
        });
        valueAnimator_0.setDuration(eachdura);
        valueAnimator_0.start();
        ValueAnimator valueAnimator_1 =  ValueAnimator.ofInt(OFFSET);
        valueAnimator_1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                offset_1 = value%(OFFSET+1);
                Log.i("tag","Expand offset_1 "+offset_1);
                invalidate();
            }
        });
        valueAnimator_1.setStartDelay(eachdura - INTERVEIN_TIME);
        valueAnimator_1.setDuration(eachdura);
        valueAnimator_1.start();
        ValueAnimator valueAnimator_2 =  ValueAnimator.ofInt(OFFSET);
        valueAnimator_2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                offset_2 = value%(OFFSET+1);
                Log.i("tag","Expand offset_2 "+offset_2);
                invalidate();
            }
        });
        valueAnimator_2.setStartDelay(2*eachdura - 2*INTERVEIN_TIME);
        valueAnimator_2.setDuration(eachdura);
        valueAnimator_2.start();
        ValueAnimator valueAnimator_3 =  ValueAnimator.ofInt(OFFSET);
        valueAnimator_3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                offset_3 = value%(OFFSET+1);
                Log.i("tag","Expand offset_1 "+offset_3);
                invalidate();
            }
        });
        valueAnimator_3.setStartDelay(3*eachdura - 3*INTERVEIN_TIME);
        valueAnimator_3.setDuration(eachdura);
        valueAnimator_3.start();
        valueAnimator_3.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ValueAnimator valueAnimator_4 =  ValueAnimator.ofInt(OFFSET);
                valueAnimator_4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int value = (int) animation.getAnimatedValue();
                        offset_3 = 50 -value%(OFFSET+1);
                        Log.i("tag","Shrink offset_3 "+offset_3);
                        invalidate();
                    }
                });
//                valueAnimator_4.setStartDelay(4*eachdura - 4*INTERVEIN_TIME);
                valueAnimator_4.setDuration(eachdura);
                valueAnimator_4.start();
                ValueAnimator valueAnimator_5 =  ValueAnimator.ofInt(OFFSET);
                valueAnimator_5.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int value = (int) animation.getAnimatedValue();
                        offset_2 = 50 -value%(OFFSET+1);
                        Log.i("tag","Shrink offset_1 "+offset_2);
                        invalidate();
                    }
                });
                valueAnimator_5.setStartDelay(eachdura - INTERVEIN_TIME);
                valueAnimator_5.setDuration(eachdura);
                valueAnimator_5.start();
                ValueAnimator valueAnimator_6 =  ValueAnimator.ofInt(OFFSET);
                valueAnimator_6.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int value = (int) animation.getAnimatedValue();
                        offset_1 = 50 -value%(OFFSET+1);
                        Log.i("tag","Shrink offset_1 "+offset_1);
                        invalidate();
                    }
                });
                valueAnimator_6.setStartDelay(2*eachdura - 2*INTERVEIN_TIME);
                valueAnimator_6.setDuration(eachdura);
                valueAnimator_6.start();
                ValueAnimator valueAnimator_7 =  ValueAnimator.ofInt(OFFSET);
                valueAnimator_7.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int value = (int) animation.getAnimatedValue();
                        offset_0 = 50 -value%(OFFSET+1);
                        Log.i("tag","Shrink offset_0 "+offset_0);
                        invalidate();
                    }
                });
                valueAnimator_7.setStartDelay(3*eachdura - 3*INTERVEIN_TIME);
                valueAnimator_7.setDuration(eachdura);
                valueAnimator_7.start();
            }
        });

    }

    public void startAnim(int duration){
        final int eachtime = duration / TIMES;
        AnimatorSet amSet = new AnimatorSet();
        ValueAnimator va1 = ValueAnimator.ofInt(0);
        va1.setDuration(eachtime);
        va1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                animPlayOnce(eachtime);
            }
        });
        ValueAnimator va2 = ValueAnimator.ofInt(0);
        va2.setDuration(eachtime);
        va2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                animPlayOnce(eachtime);
            }
        });
        ValueAnimator va3 = ValueAnimator.ofInt(0);
        va3.setDuration(eachtime);
        va3.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                animPlayOnce(eachtime);
            }
        });
        amSet.play(va3).after(va2);
        amSet.play(va2).after(va1);
        amSet.start();
    }

}
