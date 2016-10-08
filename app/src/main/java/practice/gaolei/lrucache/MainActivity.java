package practice.gaolei.lrucache;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    @ViewInject(R.id.listViewId)
    private ListView listView;
    MyAdapter adapter;

    List<Photos> datalist;
//    String[] urls = {"http://img2.duitang.com/uploads/item/201210/16/20121016190338_z3jFA.jpeg",
//            "http://m.chanyouji.cn/tips/hanju.jpg",
//            "http://m.chanyouji.cn/articles/534/51b6a7a15a4c35948f09cfb4ec6bfe69.jpg",
//            "http://m.chanyouji.cn/articles/578/50e0f1407caccc6e7379d5699df8582e.jpg"
//           };
    String[] urls={ "http://m.chanyouji.cn/tips/hanju.jpg",
        "http://m.chanyouji.cn/articles/534/51b6a7a15a4c35948f09cfb4ec6bfe69.jpg",
        "http://m.chanyouji.cn/articles/578/50e0f1407caccc6e7379d5699df8582e.jpg",
      };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.Ext.init(getApplication());
        x.Ext.setDebug(true);
        setContentView(R.layout.activity_main);
        x.view().inject(this);

        initData();
        initView();

    }

    private void initView() {

        adapter=new MyAdapter(getApplicationContext(),datalist);
        listView.setAdapter(adapter);
    }

    private void initData() {
        datalist = new ArrayList<>();
        Photos photo = null;
        for (int i = 0; i < urls.length; i++) {
            photo = new Photos(String.format(Locale.CHINA,"第%02d张图片",i),urls[i]);
            datalist.add(photo);
        }

    }


}
