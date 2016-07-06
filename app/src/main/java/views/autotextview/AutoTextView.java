package views.autotextview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huangli.mydemos.R;

import java.util.List;

/**
 * Created by huangli on 16/4/13.
 */
public class AutoTextView extends RelativeLayout{
    private TextView text1,text2;
    private ImageView iVicon;
    private List<String> strlist;
    private int index;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (index >= strlist.size()){
                index = 0;
            }
            String str = strlist.get(index);
            if (text1.getVisibility() == VISIBLE){
                anim(str,text1,text2);
            }else{
                anim(str,text2,text1);
            }
            index++;
            handler.sendEmptyMessageDelayed(0,1000);
        }
    };
    public AutoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_autotext, this);
        findViews();
    }

    private void anim(final String str,final TextView tv1,final TextView tv2){
        final ObjectAnimator objectAnimatorUp = ObjectAnimator.ofFloat(tv1, "translationY", 0, -100).setDuration(500);
        final ObjectAnimator objectAnimatorDown = ObjectAnimator.ofFloat(tv2, "translationY", 100, 0).setDuration(500);
        objectAnimatorUp.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                tv1.setVisibility(GONE);
            }
        });
        objectAnimatorUp.start();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv2.setText(str);
                tv2.setVisibility(VISIBLE);
                objectAnimatorDown.start();
            }
        }, 200);
    }

    public void setLeftIcon(int resid){
        iVicon.setImageResource(resid);
    }

    public void startAutoScroll(List<String> strlist){
        if (strlist == null || strlist.size() == 0){
            return;
        }
        this.strlist = strlist;
        handler.sendEmptyMessage(0);
    }

    private void findViews() {
        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        iVicon = (ImageView)findViewById(R.id.iv_icon);
    }

}
