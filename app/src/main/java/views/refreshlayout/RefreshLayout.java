package views.refreshlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import utils.DisplayUtils;


public class RefreshLayout extends RelativeLayout {

    private final String TAG = "RefreshLayout";

    //一些变量
    private int HeighttopContentView,HeightheaderRefresh;
    private float lastY;        //处理touch event 滑动时候的中间变量

    private boolean isInit = false;

    private VelocityTracker mVelocityTracker;

    private Scroller mScroller = new Scroller(getContext());

    private ActionParser actionParser = new ActionParser();

    public RefreshLayout(Context context) {
        super(context);
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 目前只支持上拉下拉动作分析
     */
    private class ActionParser{
        //一些常量
        public static final int ACTION_PULL_DOWN = 0;
        public static final int ACTION_PULL_UP = 1;
        public static final int ACTION_PULL_LEFT = 2;
        public static final int ACTION_PULL_RIGHT = 3;
        public static final int ACTION_NONE = -1;
        private float baseX,baseY;

        public void setbaseY(float baseX,float baseY){
            Log.i("action","设置 baseY "+baseY);
            this.baseX = baseX;
            this.baseY = baseY;
        }

        public int getAction(float curX,float curY){
            Log.i("action","动作 curY "+curY);
            float absx = Math.abs(curX - baseX);
            float absy = Math.abs(curY - baseY);
            if (absx >= absy){

            }else {

            }
            if (curY > baseY){
                baseY = curY;
                Log.i("action","动作 ACTION_PULL_DOWN");
                return ACTION_PULL_DOWN;
            }else if (curY < baseY){
                baseY = curY;
                Log.i("action","动作 ACTION_PULL_UP");
                return ACTION_PULL_UP;
            }else{
                return ACTION_NONE;
            }
        }

        public void reset(){
            baseY = 0;
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }

    private void init(){
        if (!isInit){
            //初始化一些参数
            View headerRefreshView = getChildAt(0);
            View topContentView = getChildAt(1);
            if (headerRefreshView != null){
                HeightheaderRefresh = headerRefreshView.getHeight();
                scrollBy(0,HeightheaderRefresh);
                Log.i("onLayout","高度 HeightheaderRefresh dip "+ DisplayUtils.px2Dp(getContext(),HeightheaderRefresh));
            }
            if (topContentView != null){
                HeighttopContentView = topContentView.getHeight();
                Log.i("onLayout","高度 HeighttopContentView dip "+DisplayUtils.px2Dp(getContext(),HeighttopContentView));
            }
            isInit = true;
        }
    }

    private void smoothScrollTo(int destX,int destY){
        int scrollY = getScrollY();
        int deltaY = destY - scrollY;
        mScroller.startScroll(0,scrollY,0,deltaY,1000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        init();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        int action  = event.getAction();
        float curX = event.getX();
        float curY = event.getY();
        switch (action){
            case MotionEvent.ACTION_DOWN:
//                Log.i("touch","收到 ACTION_DOWN");
                lastY = curY;
                actionParser.setbaseY(curX,curY);
                break;
            case MotionEvent.ACTION_MOVE:
//                Log.i("touch","收到 ACTION_MOVE");
                int actionParsetAction = actionParser.getAction(curX,curY);
                switch (actionParsetAction){
                    case ActionParser.ACTION_PULL_DOWN:     //下拉(没有区域限制)
                        if (lastY != 0){
                            scrollBy(0,-(int)(curY-lastY));
                        }
                        lastY = curY;
                        break;
                    case ActionParser.ACTION_PULL_UP:       //上拉(有区域限制)
                        if (getScrollY() < (HeighttopContentView+HeightheaderRefresh)){
                            Log.i("touch","上拉 ScrollY "+getScrollY()+" limit height "+(HeighttopContentView+HeightheaderRefresh));
                            //未上拉到类容区域，允许上拉
                            if (lastY != 0){
                                scrollBy(0,-(int)(curY-lastY));
                            }
                            lastY = curY;
                        }
                        break;
                    case ActionParser.ACTION_NONE:
                        break;
                }
                break;
            case MotionEvent.ACTION_UP:
//                Log.i("touch","收到 ACTION_UP");
                lastY = 0;
                actionParser.reset();
                if (getScrollY() < HeightheaderRefresh){        //这里说明是下拉刷新
                    smoothScrollTo(0,HeightheaderRefresh);
                }
                break;
        }
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        super.onInterceptTouchEvent(ev);
        boolean intercept = true;
        int action  = ev.getAction();
        float curX = ev.getX();
        float curY = ev.getY();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                intercept = false;
                actionParser.setbaseY(curX,curY);
                Log.i("intercept","收到 ACTION_DOWN intercept "+intercept);
                break;
            case MotionEvent.ACTION_MOVE:
                int actionParserAction = actionParser.getAction(curX,curY);
                switch (actionParserAction){
                    case ActionParser.ACTION_PULL_DOWN:
                        //下拉一律拦截
                        intercept = true;
                        break;
                    case ActionParser.ACTION_PULL_UP:
                        //上拉到指定区域步拦截事件
                        if (getScrollY() >= (HeighttopContentView+HeightheaderRefresh)){
                            intercept = false;
                        }else{
                            intercept = true;
                        }
                        break;
                    case ActionParser.ACTION_NONE:
                        intercept = true;
                        break;
                }
                Log.i("intercept","收到 ACTION_MOVE intercept "+intercept);
                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
                actionParser.reset();
                Log.i("intercept","收到 ACTION_UP intercept "+intercept);
                break;
        }
//        Log.i(TAG,"是否拦截事件 "+intercept);
        return intercept;
    }
}
