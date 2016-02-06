package com.example.yahooseventest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**********************************
 * Created by Michael Carr on 06-Feb-16.
 *********************************/
public class ResultsListAdapter extends BaseAdapter {

    private ArrayList<Program> mListItems = null;
    private Context mContext;

    public ResultsListAdapter(Context context, ArrayList<Program> listItems) {
        mContext = context;
        mListItems = listItems;
    }

    @Override
    public int getCount() {
        return mListItems.size();
    }

    @Override
    public Program getItem(int position) {
        return mListItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


            viewHolder = new ViewHolder();
            row = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder.name = (TextView) row.findViewById(R.id.list_item_name);
            viewHolder.startTime = (TextView) row.findViewById(R.id.list_item_start_time);
            viewHolder.endTime = (TextView) row.findViewById(R.id.list_item_end_time);
            viewHolder.channel = (TextView) row.findViewById(R.id.list_item_channel);
            viewHolder.rating = (TextView) row.findViewById(R.id.list_item_rating);


            row.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) row.getTag();
        }

        Program program = getItem(position);

        viewHolder.name.setText(program.name);
        viewHolder.startTime.setText(program.startTime);
        viewHolder.endTime.setText(program.endTime);
        viewHolder.channel.setText(program.channel);
        viewHolder.rating.setText(program.rating);

        return row;
    }


    protected class ViewHolder {
        public TextView name;
        public TextView startTime;
        public TextView endTime;
        public TextView channel;
        public TextView rating;
    }



}
