package logic.ipc;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by huangli on 16/5/28.
 */
public class UserParcelable implements Parcelable{
    public int userId;
    public String userName;
    public boolean isMale;

    public UserParcelable(int userId,String userName,boolean isMale){
        this.userId = userId;
        this.userName = userName;
        this.isMale = isMale;
    }

    protected UserParcelable(Parcel in) {
    }

    public static final Creator<UserParcelable> CREATOR = new Creator<UserParcelable>() {
        @Override
        public UserParcelable createFromParcel(Parcel in) {
            return new UserParcelable(in);
        }

        @Override
        public UserParcelable[] newArray(int size) {
            return new UserParcelable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
