package views.bitmap.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v4.util.LruCache;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;

/**
 * Created by huangli on 16/7/3.
 */
public class ImageLoader {
    private LruCache<String,Bitmap> mMemcoryCache;
    private DiskLruCache mDiskLruCache;
    private Context mContext;

    private ImageLoader(Context context){
//        mContext = context.getApplicationContext();
//        int maxMemory = (int)(Runtime.getRuntime().maxMemory() / 1014);
//        int cacheSize = maxMemory / 8;
//        mMemcoryCache = new LruCache<String,Bitmap>(cacheSize){
//            @Override
//            protected int sizeOf(String key, Bitmap value) {
//                return super.sizeOf(key, value);
//            }
//        };
//        File diskCacheDir = getDiskCacheDir();
//        if (!diskCacheDir.exists()){
//            diskCacheDir.mkdirs();
//        }
//        if (getUsableSpace(diskCacheDir) > DISK_CACHE_SIZE){
//            try{
//                mDiskLruCache = DiskLruCache.open();
//            }catch (IOException e){
//
//            }
//        }
    }

    private File getDiskCacheDir(){
        return Environment.getDownloadCacheDirectory();
    }

}
