package com.example.thefifthone;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import Models.MyDataProvider;
import Models.Notification;

public class CustomAdapter extends BaseAdapter {

    MyDataProvider provider;
    private Context context;
    private ArrayList<Notification> notificationArrayList;

    public CustomAdapter(Context context, ArrayList<Notification> notifications) {
        this.context = context;
        this.notificationArrayList = notifications;
        provider = new MyDataProvider(context);
    }

    @Override
    public int getCount() {
        return this.notificationArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(this.context, R.layout.layout_file, null);
        TextView title = v.findViewById(R.id.titleforhome);

        Notification notification = this.notificationArrayList.get(position);

        title.setText(notification.getTitle()); // присвоение текста заголовка оповещения из базы данных

        v.setTag(notification.getId());

        return v;
    }
}