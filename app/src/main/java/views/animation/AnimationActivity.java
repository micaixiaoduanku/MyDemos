package views.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.huangli.mydemos.R;

import java.util.ArrayList;

/**
 * Created by huangli on 16/5/24.
 * Android提供了几种动画类型：View Animation 、Drawable Animation 、Property Animation 。
 * View Animation相当简单，不过只能支持简单的缩放、平移、旋转、透明度基本的动画，且有一定的局限性。
 * 比如：你希望View有一个颜色的切换动画；你希望可以使用3D旋转动画；你希望当动画停止时，View的位置就是当前的位置；
 * 这些View Animation都无法做到。这就是Property Animation产生的原因，本篇博客详细介绍Property Animation的用法。
 */
public class AnimationActivity extends Activity{

    private final String TAG = "AnimationActivity";

    private AnimationFactory animationFactory;
    private Spinner spinnerViewAnim,spinnerObjAnim;
    private Button btnRunViewAnim,btnRunObjAnim,btnSkipLayoutAnim,btnAnimate;
    private ImageView ivAnimObj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        spinnerViewAnim = (Spinner)findViewById(R.id.view_anim_spinner);
        spinnerObjAnim = (Spinner)findViewById(R.id.obj_anim_spinner);
        btnRunViewAnim = (Button)findViewById(R.id.btn_view_anim_run);
        btnRunObjAnim = (Button)findViewById(R.id.btn_obj_anim_run);
        btnAnimate = (Button)findViewById(R.id.btn_animate);
        btnSkipLayoutAnim = (Button)findViewById(R.id.btn_skip);
        ivAnimObj = (ImageView)findViewById(R.id.iv_anim_obj);
        animationFactory = new AnimationFactory(this);
        ArrayList<String> data_list = new ArrayList<>();
        data_list.add("View动画缩放");
        data_list.add("View动画平移");
        data_list.add("View动画旋转");
        data_list.add("View动画透明度");
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data_list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerViewAnim.setAdapter(arrayAdapter);
        btnRunViewAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = spinnerViewAnim.getSelectedItemPosition();
                switch (position){
                    case 0:
                        ivAnimObj.startAnimation(animationFactory.buildScaleAnim());
                        break;
                    case 1:
                        ivAnimObj.startAnimation(animationFactory.buildTranslateAnim());
                        break;
                    case 2:
                        ivAnimObj.startAnimation(animationFactory.buildRotateAnim());
                        break;
                    case 3:
                        ivAnimObj.startAnimation(animationFactory.buildAlphaAnim());
                        break;
                }
            }
        });
        ArrayList<String> data_list2 = new ArrayList<>();
        data_list2.add("ObjectAnimator实现属性动画X轴旋转");
        data_list2.add("ObjectAnimator实现属性动画Y轴旋转");
        data_list2.add("ObjectAnimator实现自定义属性动画(缩放＋透明度)");
        data_list2.add("ObjectAnimator实现一个动画更改多个效果");
        data_list2.add("ValueAnimator实现垂直移动");
        data_list2.add("ValueAnimator实现抛物线");
        data_list2.add("AnimatorSet实现多个动画同时播放");
        data_list2.add("AnimatorSet实现多个动画顺序播放");
        data_list2.add("xml ObjectAnimator加载缩放动画");
        data_list2.add("xml ObjectAnimator加载动画集");
        ArrayAdapter arrayAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data_list2);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerObjAnim.setAdapter(arrayAdapter2);
        btnRunObjAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = spinnerObjAnim.getSelectedItemPosition();
                switch (position){
                    case 0:
                        ObjectAnimator.ofFloat(ivAnimObj, "rotationX", 0.0F, 360.0F).setDuration(500).start();
                        break;
                    case 1:
                        ObjectAnimator.ofFloat(ivAnimObj, "rotationY", 0.0F, 360.0F).setDuration(500).start();
                        break;
                    case 2:
                        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(ivAnimObj,"mine",1,0).setDuration(500);
                        objectAnimator.start();
                        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                float value = (float) animation.getAnimatedValue();
                                ivAnimObj.setAlpha(value);
                                ivAnimObj.setScaleX(value);
                                ivAnimObj.setScaleY(value);
                            }
                        });
                        break;
                    case 3:
                        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,
                                0f, 1f);
                        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f,
                                0, 1f);
                        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f,
                                0, 1f);
                        ObjectAnimator.ofPropertyValuesHolder(ivAnimObj, pvhX, pvhY,pvhZ).setDuration(1000).start();
                        break;
                    case 4:
                        ValueAnimator animator = ValueAnimator.ofFloat(0, 300);
                        animator.setTarget(ivAnimObj);
                        animator.setDuration(1000).start();
                        animator.setInterpolator(new DecelerateInterpolator());
                        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                float value = (float)animation.getAnimatedValue();
                                Log.i("value","value "+value);
                                ivAnimObj.setTranslationY(value);
                            }
                        });
                        break;
                    case 5:
                        ValueAnimator valueAnimator = new ValueAnimator();
                        valueAnimator.setDuration(3000);
                        valueAnimator.setObjectValues(new PointF(0, 0));
                        valueAnimator.setInterpolator(new LinearInterpolator());
                        valueAnimator.setEvaluator(new TypeEvaluator<PointF>() {

                            @Override
                            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
                                PointF pointF = new PointF();
                                float x = fraction * 200 * 3;
                                float y = fraction * 100 * 3;
                                pointF.set(x,y);
                                return pointF;
                            }
                        });
                        valueAnimator.start();
                        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                PointF pointF = (PointF)animation.getAnimatedValue();
                                ivAnimObj.setX(pointF.x);
                                ivAnimObj.setY(pointF.y);
                            }
                        });
                        break;
                    case 6:
                        ObjectAnimator anim1 = ObjectAnimator.ofFloat(ivAnimObj, "scaleX",
                                1.0f, 2f);
                        ObjectAnimator anim2 = ObjectAnimator.ofFloat(ivAnimObj, "scaleY",
                                1.0f, 2f);
                        AnimatorSet animSet = new AnimatorSet();
                        animSet.setDuration(2000);
                        animSet.setInterpolator(new LinearInterpolator());
                        //两个动画同时执行
                        animSet.playTogether(anim1, anim2);
                        animSet.start();
                        break;
                    case 7:
                        float cx = ivAnimObj.getX();
                        ObjectAnimator anim01 = ObjectAnimator.ofFloat(ivAnimObj, "scaleX", 1.0f, 2f);
                        ObjectAnimator anim02 = ObjectAnimator.ofFloat(ivAnimObj, "scaleY", 1.0f, 2f);
                        ObjectAnimator anim03 = ObjectAnimator.ofFloat(ivAnimObj, "x",  cx ,  0f);
                        ObjectAnimator anim04 = ObjectAnimator.ofFloat(ivAnimObj, "x", cx);
                        // anim1，anim2,anim3同时执行anim4接着执行
                        AnimatorSet animSet0 = new AnimatorSet();
                        animSet0.play(anim01).with(anim02);
                        animSet0.play(anim02).with(anim03);
                        animSet0.play(anim04).after(anim03);
                        animSet0.setDuration(1000);
                        animSet0.start();
                        break;
                    case 8:
                        Animator anim = AnimatorInflater.loadAnimator(AnimationActivity.this, R.anim.obj_scale);
                        anim.setTarget(ivAnimObj);
                        anim.start();
                        break;
                    case 9:
                        Animator animset = AnimatorInflater.loadAnimator(AnimationActivity.this, R.anim.obj_set);
                        ivAnimObj.setPivotX(0);
                        ivAnimObj.setPivotY(0);
                        //显示的调用invalidate
                        ivAnimObj.invalidate();
                        animset.setTarget(ivAnimObj);
                        animset.start();
                        break;
                }
            }
        });
        btnSkipLayoutAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AnimationActivity.this,LayoutAnimaActivity.class);
                startActivity(i);
            }
        });
        btnAnimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivAnimObj.animate()//
                        .alpha(0)//
                        .y(300).setDuration(1000)
                        // need API 12
                        .withStartAction(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                Log.i(TAG, "START");
                            }
                            // need API 16
                        }).withEndAction(new Runnable()
                {

                    @Override
                    public void run()
                    {
                        Log.i(TAG, "END");
                        runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                btnAnimate.setY(0);
                                btnAnimate.setAlpha(1.0f);
                            }
                        });
                    }
                }).start();
            }
        });
    }
}
