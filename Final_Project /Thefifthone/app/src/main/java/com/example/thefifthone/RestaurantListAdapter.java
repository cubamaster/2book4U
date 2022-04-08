package com.example.thefifthone;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thefifthone.R;

import java.util.ArrayList;

import Models.MyDataProvider;
import Models.Restaurant;

public class RestaurantListAdapter extends BaseAdapter {

    MyDataProvider provider;
    private Context context;
    private ArrayList<Restaurant> restaurantList;
    int images[] = {R.drawable.manga, R.drawable.parad, R.drawable.mamam,
            R.drawable.papajohnss, R.drawable.dod, R.drawable.degir, R.drawable.sam,
            R.drawable.nedel, R.drawable.oceanbaskeet, R.drawable.korean};

    public RestaurantListAdapter(Context context, ArrayList<Restaurant> restaurants){
        this.context = context;
        this.restaurantList = restaurants;
        provider = new MyDataProvider(context);
    }

    @Override
    public int getCount() {
        return this.restaurantList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.restaurantList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(this.context, R.layout.restaurant_item, null);
        ImageView imageView = v.findViewById(R.id.image_restaurant_id);
        TextView txtRestaurantname = v.findViewById(R.id.txtResraurantName);
        TextView txtRestaurantlocation = v.findViewById(R.id.txtResraurantLocation);

        imageView.setImageResource(images[i]);
        Restaurant restaurant = this.restaurantList.get(i);

        txtRestaurantname.setText(restaurant.getName());
        txtRestaurantlocation.setText(restaurant.getLocation());

        v.setTag(restaurant.getId());

        return v;
    }

}
