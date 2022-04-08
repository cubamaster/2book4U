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
import android.widget.ListView;

import java.util.ArrayList;

import Models.MyDataProvider;
import Models.Notification;
import Models.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    View v;
    MyDataProvider provider;
    Context context;
    ListView notificationListView;
    CustomAdapter customAdapter;
    ArrayList<Notification> notificationslist;

    public HomeFragment(Context context) { // конструктор, принимает context
        this.provider = new MyDataProvider(context);
        this.context = context;
        this.notificationslist = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        User user = provider.getLoggedInUser(); // распознавание и проверка пользователя
        if (user == null){
            return;
        }
        notificationslist.clear(); // очищение всего массива уведомлений

        ArrayList<Notification> notifications = provider.getUserNotifications(user); // получение уведомлений в зависимости от зарегестрированного пользователя
        notificationslist.addAll(notifications); // добавление уведомлений

        notificationListView = getView().findViewById(R.id.notificationsListView);
        customAdapter = new CustomAdapter(context, notificationslist);
        notificationListView.setAdapter(customAdapter); // присвоение адаптера

        notificationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int notificationId = Integer.parseInt(view.getTag().toString());
                Intent intent = new Intent(context, NotificationPersonalActivity.class);
                intent.putExtra("notificationId", notificationId);
                startActivity(intent);
            }
        }); // при нажатии на каждое уведомление отдельного (каждый item) пользователя переносит на экран NotificationPersonalActivity
    }
}
