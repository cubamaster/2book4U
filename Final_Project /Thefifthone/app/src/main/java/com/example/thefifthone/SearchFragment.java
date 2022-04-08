package com.example.thefifthone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import Models.MyDataProvider;
import Models.Restaurant;
import Models.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    MyDataProvider provider;
    Context context;
    GridView restaurantslistview;
    RestaurantListAdapter adapter;
    ArrayList<Restaurant> restaurantslist;
    int images[] = {R.drawable.manga, R.drawable.parad, R.drawable.mamam,
            R.drawable.papajohnss, R.drawable.dod, R.drawable.degir, R.drawable.sam,
            R.drawable.nedel, R.drawable.oceanbaskeet, R.drawable.korean};

    public SearchFragment(Context context) { // конструктор search, принимает context
        this.provider = new MyDataProvider(context);
        this.context = context;
        this.restaurantslist = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        User user = provider.getLoggedInUser(); // нахождение пользователя
        if (user == null){
            return;
        } // проверка пользователя
        updateListView(); // обновление массива ресторанов

        restaurantslistview = getView().findViewById(R.id.restaurantsListView);
        adapter = new RestaurantListAdapter(context, restaurantslist);
        restaurantslistview.setAdapter(adapter); // установление адаптера

        restaurantslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() { // установление Listener
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int restaurantid = Integer.parseInt(view.getTag().toString());
                int a = restaurantid;
                Intent intent = new Intent(context, RestaurantPersonalActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("image", images[a-1]);
                intent.putExtras(bundle);
                intent.putExtra("restaurantId", restaurantid);
                startActivity(intent);
            } // переход на RestaurantPersonalActivity
        });
    }

    private void updateListView(){
        User user = provider.getLoggedInUser(); // нахождение пользователя
        if (user == null){
            return;
        } // проверка пользователя
        restaurantslist.clear(); // очистка всего массива

        ArrayList<Restaurant> restaurants = provider.getRestaurants(); // добыча массива ресторанов
        restaurantslist.addAll(restaurants); // добавление ресторанов в массив
    }
}
