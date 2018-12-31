package com.example.administrator.activitymanagement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.activitymanagement.domain.ActivityListBean;
import com.example.administrator.activitymanagement.domain.UserInfo;

import java.util.List;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.MyViewHolder> implements View.OnClickListener{
    private List<ActivityListBean> list;
    private Context context;

    public  ActivityAdapter(List<ActivityListBean> list,Context context){
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.drop_item_view, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        ActivityListBean activityListBean = list.get(position);
        myViewHolder.tv_drop_title.setText(activityListBean.getaName());
        myViewHolder.tv_drop_name.setText(activityListBean.getaUsername());
        myViewHolder.tv_drop_time.setText(activityListBean.getaOpenTime() + "至" + activityListBean.getaEndTime());
        myViewHolder.tv_drop_address.setText(activityListBean.getaPlace());
        int imageid = myViewHolder.itemView.getResources().getIdentifier(activityListBean.getAimageId(),"drawable",myViewHolder.itemView.getContext().getPackageName());
        myViewHolder.item_drop_image.setImageResource(imageid);
        myViewHolder.itemView.setTag(position);
        myViewHolder.btn_drop_delete.setTag(position);
        myViewHolder.btn_drop_update.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView item_drop_image;
        private TextView tv_drop_title,tv_drop_name,tv_drop_time,tv_drop_address,tv_drop_status;
        private Button btn_drop_update,btn_drop_delete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_drop_image = itemView.findViewById(R.id.item_drop_image);
            tv_drop_title = itemView.findViewById(R.id.tv_drop_title);
            tv_drop_name = itemView.findViewById(R.id.tv_drop_name);
            tv_drop_time = itemView.findViewById(R.id.tv_drop_time);
            tv_drop_address = itemView.findViewById(R.id.tv_drop_address);
            tv_drop_status = itemView.findViewById(R.id.tv_drop_status);
            btn_drop_update = itemView.findViewById(R.id.btn_drop_update);
            btn_drop_delete = itemView.findViewById(R.id.btn_drop_delete);

            itemView.setOnClickListener(ActivityAdapter.this);
            btn_drop_update.setOnClickListener(ActivityAdapter.this);
            btn_drop_delete.setOnClickListener(ActivityAdapter.this);
        }

    }
    public enum ViewName{
        ITEM,
        PRACTISE
    }
    public interface OnItemClickListener  {
        void onItemClick(View v, ViewName viewName, int position);
        void onItemLongClick(View v);
    }
    private OnItemClickListener mOnItemClickListener;

    //定义方法并传给外面的使用者
    public void setOnItemClickListener(OnItemClickListener  listener) {
        this.mOnItemClickListener  = listener;
    }
    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        if (mOnItemClickListener!= null){
            switch (v.getId()){
                case R.id.rv_release_item:
                    mOnItemClickListener.onItemClick(v,ViewName.PRACTISE,position);
                    break;
                    default:
                        mOnItemClickListener.onItemClick(v,ViewName.ITEM,position);
                        break;
            }
        }
    }
    public void removeList(int position){
        list.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }
}
