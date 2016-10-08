package practice.gaolei.lrucache;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

/**
 * Created by gaoleideapple on 16/10/6.
 */

public class LruCacheUtils {

    LruCache<String, Bitmap> lruCache;

    public LruCache initLruCache() {
        int maxSize = 4 * 1024 * 1024;
        return lruCache = new LruCache<>(maxSize);
    }
    public Bitmap getImageBitmap(String url){
        if(lruCache!=null){
            return lruCache.get(url);
        }
        return null;
    }

    public void saveImageBitmap(String url, Bitmap bitmap) {
        if (getImageBitmap(url) == null) {
            Log.d("gl","url===="+url);
            Log.d("gl","bitmap===="+bitmap);
            lruCache.put(url, bitmap);

        }
    }

    public void deleteImageBitmap(String url){
        if(getImageBitmap(url)!=null){
            lruCache.remove(url);
        }
    }

}
