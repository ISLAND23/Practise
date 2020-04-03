package com.example.readyproj.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.readyproj.R;

import java.util.ArrayList;

public class RcAdapter extends RecyclerView.Adapter<RcAdapter.ViewHolder> implements RecycleItemTouchHelper.ItemTouchHelperCallback{

        /**
         * 展示数据
         */
        private ArrayList<String> mData;

        /**
         * 事件回调监听
         */
        private RcAdapter.OnItemClickListener onItemClickListener;

        public RcAdapter(ArrayList<String> data) {
            this.mData = data;
        }

        public void updateData(ArrayList<String> data) {
            this.mData = data;
            notifyDataSetChanged();
        }
        public void addNewItem() {
            if(mData == null) {
                mData = new ArrayList<>();
            }
            mData.add(0, "new Item");
            notifyItemInserted(0);
        }

        public void deleteItem() {
            if(mData == null || mData.isEmpty()) {
                return;
            }
            mData.remove(0);
            notifyItemRemoved(0);
        }



    public void setOnItemClickListener(RcAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // 实例化展示的view
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_rv_item, parent, false);
            // 实例化viewholder
            ViewHolder viewHolder = new ViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            // 绑定数据
            holder.mTv.setText(mData.get(position));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    if(onItemClickListener != null) {
                        int pos = holder.getLayoutPosition();
                        onItemClickListener.onItemClick(holder.itemView, pos);
                    }
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(onItemClickListener != null) {
                        int pos = holder.getLayoutPosition();
                        onItemClickListener.onItemLongClick(holder.itemView, pos);
                    }
                    //表示此事件已经消费，不会触发单击事件
                    return true;
                }
            });
        }

        @Override
        public int getItemCount() {
            return mData == null ? 0 : mData.size();
        }

    @Override
    public void onItemDelete(int position) {
        if(mData == null || mData.isEmpty()) {
            return;
        }
        if(position < 0 || position > getItemCount()) {
            return;
        }
        mData.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onMove(int fromPosition, int toPosition) {
        if(mData == null || mData.isEmpty()) {
            return;
        }
        String prev = mData.remove(fromPosition);
        mData.add(toPosition > fromPosition ? toPosition - 1 : toPosition, prev);
        notifyItemMoved(fromPosition, toPosition);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

            TextView mTv;

            public ViewHolder(View itemView) {
                super(itemView);
                mTv = (TextView) itemView.findViewById(R.id.item_tv);
            }
        }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

}
