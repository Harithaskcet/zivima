package com.example.admin.zivima;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListingAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<User> users;
    public ListingAdapter() {

    }
    public ListingAdapter(Context con,ArrayList<User> users)
    {
        context=con;
        layoutInflater = LayoutInflater.from(context);
        this.users=users;
    }
    @Override
    public int getCount() {
        return users.size();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.adapter_listing, null, false);
            holder = new ViewHolder();
            holder.fullname = (TextView) convertView.findViewById(R.id.user_fullname);
            holder.phonenumber = (TextView) convertView.findViewById(R.id.user_pnum);
            holder.college = (TextView) convertView.findViewById(R.id.user_college);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        User user=users.get(position);
        holder.fullname.setText(user.getUsername());
        holder.phonenumber.setText(user.getPhone_num());
        holder.college.setText(user.getCollege());
        return convertView;
    }
    public class ViewHolder {
        TextView fullname, phonenumber,college;
    }
    @Override
    public Object getItem(int position) {
        return users.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
}
