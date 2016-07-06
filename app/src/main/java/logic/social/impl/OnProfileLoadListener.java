package logic.social.impl;


import logic.social.ProfileInfo;
import logic.social.SocialType;

/**
 * Created by huangli on 2016/5/3.
 */
public interface OnProfileLoadListener {
   void onProfileLoadedSuccess(SocialType socialType, ProfileInfo profileInfo);

   void onProfileLoadedError(SocialType socialType, String message, int errorcode);
}
