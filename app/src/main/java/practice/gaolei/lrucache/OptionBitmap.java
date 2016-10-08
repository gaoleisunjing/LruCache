package practice.gaolei.lrucache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by gaoleideapple on 16/10/6.
 */

public class OptionBitmap {

    public static Bitmap getOptionBitmap(byte[] bytes) {
        int width = 0;
        int height = 0;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeByteArray(bytes,0,bytes.length,options);
        width=options.outWidth;
        height=options.outHeight;
        options.inSampleSize=3;
        options.inJustDecodeBounds=false;
       Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length,options);
        return bitmap;

    }
}
