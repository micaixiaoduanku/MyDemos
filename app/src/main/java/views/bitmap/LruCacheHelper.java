package views.bitmap;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * Created by huangli on 16/7/2.
 */
public class LruCacheHelper{
    LruCache lruCache;
    public LruCacheHelper(){
        int maxMemory = (int)(Runtime.getRuntime().maxMemory()/1024);
        int cacheSize = maxMemory / 8;
        lruCache = new LruCache<String,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }
}
