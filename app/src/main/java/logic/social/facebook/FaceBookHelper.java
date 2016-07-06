package logic.social.facebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;
import java.util.List;

import logic.social.ProfileInfo;
import logic.social.SocialHelper;
import logic.social.SocialType;
import logic.social.impl.OnProfileLoadListener;


/**
 * Created by huangli on 16/5/10.
 */
public class FaceBookHelper extends SocialHelper {

    private static final String TAG = "FaceBookHelper";

    private Activity myActivity;
    private CallbackManager callbackManager; // Tied to FB Login Button (Login/Logout)
    private FaceBookStateListener mfaceBookStateListener;

    private ProfileTracker profileTracker;

    public interface FaceBookStateListener{
        void isFaceBookLoading(boolean isLoading);
    }

    public FaceBookHelper(Activity activity,FaceBookStateListener faceBookStateListener){
        myActivity = activity;
        mfaceBookStateListener = faceBookStateListener;
        if (!FacebookSdk.isInitialized()) {
            FacebookSdk.sdkInitialize(myActivity.getApplicationContext());
        }
        callbackManager = CallbackManager.Factory.create();
        profileTracker  = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                Log.i(TAG,"facebook onCurrentProfileChanged ");
                if (currentProfile != null) {
                    if (getOnProfileLoadListener() != null) {
                        String id = getUserId();
                        String token = getToken();
                        String name = getProfile().getName();
                        String headUrl = getProfile().getProfilePictureUri(200,200).toString();
                        getOnProfileLoadListener().onProfileLoadedSuccess(getSocialType(),new ProfileInfo("Facebook",id,token,name,headUrl));
                    }
                }else{
                    getOnProfileLoadListener().onProfileLoadedError(getSocialType(),"",0);
                }
                if (mfaceBookStateListener != null){
                    mfaceBookStateListener.isFaceBookLoading(false);
                }
            }
        };
    }

    public void getProfile(OnProfileLoadListener onProfileLoadListener){
        Log.i(TAG,"facebook 登录开始 ");
        List<String> perms = Arrays.asList("email", "user_photos", "public_profile");
        setOnProfileLoadListener(onProfileLoadListener);
//        LoginManager.getInstance().logOut();
        LoginManager.getInstance().logInWithReadPermissions(
                myActivity,
                perms);
    }

    @Override
    public SocialType getSocialType() {
        return SocialType.Facebook;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        initLoginCallback();
    }

    @Override
    public void onDestory() {
        profileTracker.stopTracking();
        revokeAllPermissions();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private Profile getProfile() {
        Profile profile = Profile.getCurrentProfile();
        return profile;
    }

    private boolean isTokenExpired() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null) {
            return accessToken.isExpired();
        }
        return true;
    }

    private String getToken() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null) {
            return accessToken.getToken();
        }
        return "";
    }

    private String getUserId() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null) {
            return accessToken.getUserId();
        }
        return "";
    }

    /**
     * Initializes FB Login Callback
     */
    private void initLoginCallback() {
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.i(TAG,"facebook 登录成功 ");
                //返回个人用户信息也许会有延迟,所以必须通知ui正在加载中
                if (mfaceBookStateListener != null){
                    mfaceBookStateListener.isFaceBookLoading(true);
                }
            }

            @Override
            public void onCancel() {
                Log.i(TAG,"facebook 登录取消 accessToken "+ AccessToken.getCurrentAccessToken());
                if (getOnProfileLoadListener() != null){
                    getOnProfileLoadListener().onProfileLoadedError(getSocialType(),"",0);
                }
//                if (mfaceBookStateListener != null){
//                    mfaceBookStateListener.isFaceBookLoading(false);
//                }
            }

            @Override
            public void onError(FacebookException error) {
                Log.i(TAG,"facebook 登录错误 accessToken "+ AccessToken.getCurrentAccessToken());
                if (getOnProfileLoadListener() != null){
                    getOnProfileLoadListener().onProfileLoadedError(getSocialType(),"",0);
                }
//                if (mfaceBookStateListener != null){
//                    mfaceBookStateListener.isFaceBookLoading(false);
//                }
            }
        });
    }

    public void revokeAllPermissions() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken == null){
            return;
        }
        LoginManager.getInstance().logOut();
    }
}
