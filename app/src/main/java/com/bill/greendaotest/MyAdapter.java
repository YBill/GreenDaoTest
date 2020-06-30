package com.bill.greendaotest;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<MyBean> mDataList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public void setDataList(List<MyBean> dataList) {
        this.mDataList.clear();
        this.mDataList.addAll(dataList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.update(position);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView titleTv;
        private AppCompatTextView contentTv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.tv_title);
            contentTv = itemView.findViewById(R.id.tv_content);
        }

        private void update(final int position) {
            final MyBean bean = mDataList.get(position);

            titleTv.setText(bean.title + "（" + bean.id + "）");
            contentTv.setText(bean.content);

            if (bean.isSelect)
                itemView.setBackgroundColor(Color.parseColor("#87CEFA"));
            else
                itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (MyBean bean : mDataList) {
                        bean.isSelect = false;
                    }
                    bean.isSelect = true;
                    MyAdapter.this.notifyDataSetChanged();

                    if (onItemClickListener != null) {
                        onItemClickListener.onClick(position);
                    }
                }
            });
        }

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

}
