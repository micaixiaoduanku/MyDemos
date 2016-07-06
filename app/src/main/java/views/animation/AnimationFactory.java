package views.animation;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.huangli.mydemos.R;

/**
 * Created by huangli on 16/5/31.
 * 使用属性动画的优势
 */
public class AnimationFactory {
    private Context mContext;
    public AnimationFactory(Context context){
        mContext = context;
    }
    public Animation buildScaleAnim(){
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.scale);
        return animation;
    }
    public Animation buildTranslateAnim(){
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.translate);
        return animation;
    }
    public Animation buildAlphaAnim(){
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.alpha);
        return animation;
    }
    public Animation buildRotateAnim(){
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.rotate);
        return animation;
    }
}
