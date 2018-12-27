package com.example.administrator.activitymanagement;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.activitymanagement.domain.ActivityListBean;

import java.util.List;
import java.util.zip.Inflater;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<ActivityListBean> list;
    private Context context;
    private OnItemClickLitener onItemClickListener;
    //构造方法
    public MyAdapter(Context context,List list){
        this.context = context;
        this.list = list;
    }
    //设置回调接口
    public interface OnItemClickLitener{
        void onItemClick(View view, int position);
    }
    public void setOnItemClickLitener(OnItemClickLitener onItemClickListener){
        this.onItemClickListener =  onItemClickListener;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view,viewGroup,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }
    //绑定数据
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {
        myViewHolder.tv_title.setText(list.get(position).getaName());
        myViewHolder.tv_name.setText(list.get(position).getaUsername());
        myViewHolder.tv_time.setText(list.get(position).getaOpenTime() + "至" +  list.get(position).getaEndTime());
        myViewHolder.tv_address.setText(list.get(position).getaPlace());
        int imageId = myViewHolder.itemView.getResources().getIdentifier(list.get(position).getAimageId(), "drawable", myViewHolder.itemView.getContext().getPackageName());
        myViewHolder.item_image.setImageResource(imageId);
        //Log.i("qqq", String.valueOf(imageId));
        if (onItemClickListener != null){
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(view,position);
                }
            });
        }
    }
    //返回列表条数
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //private TextView textView;
        private TextView tv_status,tv_title,tv_name,tv_time,tv_address;
        private ImageView item_image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //textView = itemView.findViewById(R.id.text_view);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_address = itemView.findViewById(R.id.tv_address);
            item_image = itemView.findViewById(R.id.item_image);
            //这里初始化控件
        }


    }
}
