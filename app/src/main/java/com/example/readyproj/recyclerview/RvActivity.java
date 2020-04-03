package com.example.readyproj.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readyproj.R;

import java.util.ArrayList;

public class RvActivity extends Activity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private ImageView addImv;

    private RcAdapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;
    private ItemTouchHelper mItemTouchHelper;
    private RecycleItemTouchHelper recycleItemTouchHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        initData();
        initView();
    }
    private void initData() {
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new RcAdapter(getData());
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        addImv=(ImageView) findViewById(R.id.add_item);
        addImv.setOnClickListener(this);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        // 设置Item之间间隔样式
        mRecyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        // 设置Item添加和移除的动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置adapter
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new RcAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(RvActivity.this,"click " + position + " item", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(RvActivity.this,"long click " + position + " item", Toast.LENGTH_SHORT).show();
            }
        });
        recycleItemTouchHelper=new RecycleItemTouchHelper(mAdapter,this);
        mItemTouchHelper=new ItemTouchHelper(recycleItemTouchHelper);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_item:
                mAdapter.addNewItem();
                // 由于Adapter内部是直接在首个Item位置做增加操作，增加完毕后列表移动到首个Item位置
                mLayoutManager.scrollToPosition(0);
                break;

        }
    }
    private ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        String temp = "Event";
        for(int i = 0; i < 10; i++) {
            data.add(temp+i);
        }

        return data;
    }
}
