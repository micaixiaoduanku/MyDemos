package logic.ipc;

import java.io.Serializable;

/**
 * Created by huangli on 16/5/28.
 */
public class UserSerializable implements Serializable{
    private static final long serivalVersionUID = 519067123721295773L;
    public int userId;
    public String userName;
    public boolean isMale;
    public UserSerializable(int userId, String userName, boolean isMale){
        this.userId = userId;
        this.userName = userName;
        this.isMale = isMale;
    }
}
