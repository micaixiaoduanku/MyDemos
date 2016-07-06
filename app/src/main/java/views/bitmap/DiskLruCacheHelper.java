package views.bitmap;

import android.os.Environment;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;

/**
 * Created by huangli on 16/7/3.
 */
public class DiskLruCacheHelper {
    private static final int DISK_CACHE_SIZE = 1024*1024*50; //50MB
    private DiskLruCache diskLruCache;

    public DiskLruCacheHelper() throws IOException {
        File diskCacheDir = getDiskCacheDir();

        diskLruCache = DiskLruCache.open(diskCacheDir,1,1,DISK_CACHE_SIZE);
    }

    public void putBitmap(String url){
        try {
            DiskLruCache.Editor editor = diskLruCache.edit(hashKeyFormUrl(url));
            if (editor != null){
                OutputStream outputStream = editor.newOutputStream(DISK_CACHE_SIZE);
            }else {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File getDiskCacheDir(){
        return Environment.getDownloadCacheDirectory();
    }

    private String hashKeyFormUrl(String url){
        String cacheKey;
        try{
            final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(url.getBytes());
            cacheKey = bytesToHexString(messageDigest.digest());
        }catch (Exception e){
            cacheKey = String.valueOf(url.hashCode());
        }
        return cacheKey;
    }

    private String bytesToHexString(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++){
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1){
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}
