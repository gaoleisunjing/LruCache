package practice.gaolei.lrucache;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

/**
 * Created by gaoleideapple on 16/10/6.
 */

public class MyAdapter extends BaseAdapter {

    Context context;
    List<Photos> list;
    LruCacheUtils lruCacheUtils;
    Handler handler;



    public MyAdapter(Context context, List<Photos> list) {
        this.context = context;
        this.list = list;
        lruCacheUtils=new LruCacheUtils();
        lruCacheUtils.initLruCache();//初始化
        handler=new Handler();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder=null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.item_listview,parent,false);
            viewHolder.textView= (TextView) convertView.findViewById(R.id.textViewId);
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.imageId);
            convertView.setTag(viewHolder);

        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(list.get(position).getName());
       final String imageUrl=list.get(position).getImageUrl();

        if(lruCacheUtils.getImageBitmap(imageUrl)!=null){
            viewHolder.imageView.setImageBitmap(lruCacheUtils.getImageBitmap(imageUrl));
        }else {
            final ViewHolder finalViewHolder = viewHolder;
            new Thread(new Runnable() {
                @Override
                public void run() {

                byte[] bytes=DownLoadUtils.getImageByte(imageUrl);
                 if(bytes!=null){
                     final Bitmap optionBitmap = OptionBitmap.getOptionBitmap(bytes);
                 if(optionBitmap!=null){
                     Log.d("gl","imageurl===="+imageUrl);
                     Log.d("gl","optionBitmap===="+optionBitmap);
                     lruCacheUtils.saveImageBitmap(imageUrl,optionBitmap);
                 }
                 handler.post(new Runnable() {
                     @Override
                     public void run() {
                         finalViewHolder.imageView.setImageBitmap(optionBitmap);
                     }
                 });

                 }

                }
            }).start();
        }

        return convertView;
    }

    class ViewHolder{
        ImageView imageView;
        TextView textView;

    }

}
