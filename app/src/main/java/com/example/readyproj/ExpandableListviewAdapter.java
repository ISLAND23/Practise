package com.example.readyproj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

public class ExpandableListviewAdapter extends BaseExpandableListAdapter {
    //Model：定义的数据
    private String[] groups;
    //注意，字符数组不要写成{{"A1,A2,A3,A4"}, {"B1,B2,B3,B4，B5"}, {"C1,C2,C3,C4"}}
    private String[][] childs;
    private Context context;

    public ExpandableListviewAdapter(Context context,String[] groups, String[][] childs) {
        this.groups = groups;
        this.childs = childs;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return groups.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childs[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childs[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.expand_parent_item,parent,false);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.parent_textview_id = convertView.findViewById(R.id.parent_textview_id);
            groupViewHolder.parent_image = convertView.findViewById(R.id.parent_image);
            convertView.setTag(groupViewHolder);
        }else {
            groupViewHolder = (GroupViewHolder)convertView.getTag();
        }
        groupViewHolder.parent_textview_id.setText(groups[groupPosition]);
        //如果是展开状态，
        if (isExpanded){
            groupViewHolder.parent_image.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.up));
        }else{
            groupViewHolder.parent_image.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.down));
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.expand_chidren_item,parent,false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.chidren_item = (TextView)convertView.findViewById(R.id.chidren_item);
            convertView.setTag(childViewHolder);

        }else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        childViewHolder.chidren_item.setText(childs[groupPosition][childPosition]);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class GroupViewHolder {
        TextView parent_textview_id;
        ImageView parent_image;
    }

    static class ChildViewHolder {
        TextView chidren_item;
    }

}
