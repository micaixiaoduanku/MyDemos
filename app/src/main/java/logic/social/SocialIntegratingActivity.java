package logic.social;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.huangli.mydemos.R;

import logic.social.impl.OnProfileLoadListener;
import utils.ToastUtils;

/**
 * Created by huangli on 16/5/6.
 */
public class SocialIntegratingActivity extends SocialBaseActivity {

    private Button getProfileGp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getProfileGp = (Button)findViewById(R.id.get_gp_profile_button);
        getProfileGp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getProfile(SocialType.GooglePlus, new OnProfileLoadListener() {
                    @Override
                    public void onProfileLoadedSuccess(SocialType socialType, ProfileInfo profileInfo) {
                        ToastUtils.showShortToast(SocialIntegratingActivity.this,"name "+profileInfo.name);
                    }

                    @Override
                    public void onProfileLoadedError(SocialType socialType, String message, int errorcode) {
                        ToastUtils.showShortToast(SocialIntegratingActivity.this,"获取个人信息失败");
                    }
                });
            }
        });
    }

    @Override
    public void isShowLoadingDialog(boolean isLoading) {

    }
}
