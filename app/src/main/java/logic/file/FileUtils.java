package logic.file;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;

import utils.ToastUtils;

/**
 * Created by huangli on 16/5/5.
 */
public class FileUtils {
    /**
     * 获得图片SD卡路径
     */
    public static String getPicSDPath(Context context){
        File sdDir = null;
        // 判断sd卡是否存在
        boolean sdCardExist = isSdcardExist();
        if (sdCardExist) {
            // 获取跟目录
//            sdDir = Environment.getExternalStoragePublicDirectory(
//                    Environment.DIRECTORY_PICTURES);
            sdDir = new File("/mnt/sdcard");//Environment.getExternalStorageDirectory();
            boolean exists = sdDir.exists();
            Log.i("tag","exists "+exists);
//            String newFilePath = sdDir.getAbsolutePath()+"/gogobuy";
            File file = new File(sdDir.getAbsolutePath()+"/go");
            if (!file.exists()){
                boolean isok = file.mkdirs();
                if (isok){
                    ToastUtils.showShortToast(context,"创建文件夹成功");
                }else {
                    ToastUtils.showShortToast(context,"创建文件夹失败");
                }
            }
            return file.toString();
        }
        return null;
    }

    /**
     * 判断SD是否存在
     *
     * @return
     */
    public static boolean isSdcardExist() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }
}
