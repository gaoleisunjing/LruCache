package practice.gaolei.lrucache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by gaoleideapple on 16/10/6.
 */

public class DownLoadUtils {

    public static String getJsonString(String url){
        String jsonString="";

        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()){
                jsonString=response.body().string();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
            return jsonString;

    }

    public static Bitmap getImageBitmap(String url){
        URL address;
        Bitmap bitmap=null;
        try {
            address=new URL(url);
            HttpURLConnection connection= (HttpURLConnection) address.openConnection();
            connection.connect();
            connection.setRequestMethod("GET");
            if (connection.getResponseCode()==HttpURLConnection.HTTP_OK){

                bitmap= BitmapFactory.decodeStream(connection.getInputStream());
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static byte[] getImageByte(String url){
        byte[] bytes=null;
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()){
                bytes=response.body().bytes();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return  bytes;
    }


}
