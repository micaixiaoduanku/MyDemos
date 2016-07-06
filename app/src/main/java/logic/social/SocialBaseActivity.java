package logic.social;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import logic.social.facebook.FaceBookHelper;
import logic.social.google.GoogleHelper;
import logic.social.impl.OnProfileLoadListener;


/**
 * Created by huangli on 16/5/10.
 */
public abstract class SocialBaseActivity extends Activity implements GoogleHelper.GoogleStateListener,FaceBookHelper.FaceBookStateListener {
    private GoogleHelper googleHelper;
    private FaceBookHelper faceBookHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        googleHelper = new GoogleHelper(this,this);
        faceBookHelper = new FaceBookHelper(this,this);
        googleHelper.onCreate(savedInstanceState);
        faceBookHelper.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        googleHelper.onDestory();
        faceBookHelper.onDestory();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        googleHelper.onActivityResult(requestCode, resultCode, data);
        faceBookHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void isGoogleLoading(boolean isLoading) {
        isShowLoadingDialog(isLoading);
    }

    @Override
    public void isFaceBookLoading(boolean isLoading) {
        isShowLoadingDialog(isLoading);
    }

    public void getProfile(SocialType socialType, OnProfileLoadListener onProfileLoadListener){
        if (socialType == SocialType.Facebook){
            faceBookHelper.getProfile(onProfileLoadListener);
        }else if(socialType == SocialType.GooglePlus){
            googleHelper.getProfile(onProfileLoadListener);
        }else{

        }
    }

    public abstract void isShowLoadingDialog(boolean isLoading);

}
