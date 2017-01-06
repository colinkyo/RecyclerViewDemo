package com.example.a7yan.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a7yan.recyclerviewdemo.R;

import java.util.ArrayList;

/**
 * Created by 7Yan on 2017/1/6.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{
    private Context mContext;
    private ArrayList<String> datas;
    public RecyclerViewAdapter(Context context,ArrayList<String> datas) {
        this.mContext = context;
        this.datas = datas;
    }

    //相当于getView
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = View.inflate(mContext, R.layout.item_recyclerview,null);
        return new ViewHolder(itemview);
    }
    //绑定数据
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String data = datas.get(position);
        holder.tv_rc.setText(data);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
    public void addData(int position,String data)
    {
        datas.add(position,data);
        //这里是instert
        notifyItemInserted(position);
    }
    public void delData(int position)
    {
        datas.remove(position);
        //注意这里是remove
        notifyItemRemoved(position);
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_rc;
        private TextView tv_rc;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_rc = (ImageView) itemView.findViewById(R.id.iv_rc);
            tv_rc = (TextView) itemView.findViewById(R.id.tv_rc);
            //设置点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(mContext, "datas=" + datas.get(getLayoutPosition()), Toast.LENGTH_SHORT).show();
                    if(onItemClickListener != null){
                        onItemClickListener.OnItemClick(v,datas.get(getLayoutPosition()));
                    }
                }
            });
            iv_rc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "ImageView"+getLayoutPosition(), Toast.LENGTH_SHORT).show();
                }
            });


        }
    }

    /**
     * 点击RecyclerView某条数据时的事件接口
     */
    public  interface OnItemClickListener{
        public void OnItemClick(View view,String data);
    }
    private  OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
