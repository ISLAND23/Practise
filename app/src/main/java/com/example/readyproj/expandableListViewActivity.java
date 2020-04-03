package com.example.readyproj;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import androidx.annotation.Nullable;

public class expandableListViewActivity extends Activity {

    private ExpandableListView expand_list_id;
    //Model：定义的数据
    private String[] groups = {"开发部", "人力资源部", "销售部","姓名","销售部","销售部","销售部"};
    //注意，字符数组不要写成{{"A1,A2,A3,A4"}, {"B1,B2,B3,B4，B5"}, {"C1,C2,C3,C4"}}
    private String[][] childs = {{"赵珊珊", "钱丹丹", "孙可可", "李冬冬"}, {"周大福", "吴端口", "郑非", "王疯狂"}, {"冯程程", "陈类", "楚哦", "魏王"}};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expandable_listview);
        initView();
    }

    private void initView(){
        expand_list_id=findViewById(R.id.expand_list_id);
        ExpandableListviewAdapter adapter=new ExpandableListviewAdapter(this,groups,childs);
        expand_list_id.setAdapter(adapter);
        //默认展开第一个数组
      //  expand_list_id.expandGroup(0);
        //关闭数组某个数组，可以通过该属性来实现全部展开和只展开一个列表功能
        //expand_list_id.collapseGroup(0);
        expand_list_id.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long l) {
//                showToastShort(groups[groupPosition]);
                return false;
            }
        });
        //子视图的点击事件
        expand_list_id.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
//                showToastShort(childs[groupPosition][childPosition]);
                return true;
            }
        });
        //用于当组项折叠时的通知。
        expand_list_id.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
//                showToastShort("折叠了数据___"+groups[groupPosition]);
            }
        });
        //
        //用于当组项折叠时的通知。
        expand_list_id.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
//                showToastShort("展开了数据___"+groups[groupPosition]);
            }
        });
    }
}
