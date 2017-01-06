package com.example.a7yan.recyclerviewdemo;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.a7yan.recyclerviewdemo.adapter.RecyclerViewAdapter;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button btn1,btn2,btn3,btn4,btn5;
    private RecyclerView rv;
    private ArrayList<String> datas;
    RecyclerViewAdapter recyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        recyclerViewAdapter = new RecyclerViewAdapter(this,this.datas);
        rv.setAdapter(recyclerViewAdapter);
        //ListView
        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        //rv.scrollToPosition(datas.size()-1);
        //可以看看 style.xml 看看自定义分割线 的图案
        rv.addItemDecoration(new DividerListItemDecoration(this,DividerListItemDecoration.VERTICAL_LIST));

        recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, String data) {
                Toast.makeText(MainActivity.this, "datas=" + data, Toast.LENGTH_SHORT).show();
            }
        });

        //设置动画
        rv.setItemAnimator(new DefaultItemAnimator());
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_1:
                recyclerViewAdapter.addData(0,"New_Content");
                rv.scrollToPosition(0);
                break;
            case R.id.btn_2:
                recyclerViewAdapter.delData(0);
                break;
            case R.id.btn_3:
                rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
                break;
            case R.id.btn_4:
                rv.setLayoutManager(new GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false));
                break;
            case R.id.btn_5:
                rv.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
                break;
        }
    }
    private void initData() {
        datas = new ArrayList<>();
        for(int i=0;i<100;i++){
            datas.add("Item mContext"+i);
        }
    }

    private void initView() {
        btn1 = (Button) findViewById(R.id.btn_1);
        btn2 = (Button) findViewById(R.id.btn_2);
        btn3 = (Button) findViewById(R.id.btn_3);
        btn4 = (Button) findViewById(R.id.btn_4);
        btn5 = (Button) findViewById(R.id.btn_5);
        rv=(RecyclerView) findViewById(R.id.rv);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
    }
}
