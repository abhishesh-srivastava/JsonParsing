package com.abhishesh.projects.medicine.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.abhishesh.projects.medicine.R;
import com.abhishesh.projects.medicine.model.Item;

import java.util.List;

/**
 * Created by Abhishesh on 15/10/15.
 */

/**
 * Adapter for displaying in grid view
 */
public class GridViewAdapter extends BaseAdapter{

    private List<Item> mList;
    private Context mContext;
    private LayoutInflater mInflater;

    public void setList(List<Item> list) {
        mList = list;
    }

    public GridViewAdapter(Context context,List<Item>list) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView == null) {
            convertView = mInflater.inflate(R.layout.grid_row,null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.sLabel = (TextView) convertView.findViewById(R.id.label);
            convertView.setTag(viewHolder);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.sLabel.setText(mList.get(position).getLabel());
        return convertView;
    }

    //Avoid frequent findViewById, and hence improves scrolling and gives better user experience
    private static class ViewHolder {
        private TextView sLabel;
    }
}
