package com.example.thefifthone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import Models.MyDataProvider;
import Models.Notification;
import Models.Restaurant;

public class NotificationPersonalActivity extends AppCompatActivity {

    MyDataProvider provider;
    Notification notification;
    TextView name_notification;
    TextView date_notification;
    TextView number_notification;
    TextView kitchen_notification;
    TextView rating_notification;
    TextView phone_notification;
    TextView location_notification;
    Button buttontodelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_personal);

        provider = new MyDataProvider(getApplicationContext());

        Intent intent = getIntent();
        int notificationId = intent.getIntExtra("notificationId", -1);
        notification = provider.getNotification(notificationId); // получение конкретного уведомления после нажатия

        if (notification == null) {
            finish();
        } // проверка уведомления

        name_notification = findViewById(R.id.notification_restaurant_name);
        location_notification = findViewById(R.id.notification_concrete_restaurant_location);
        buttontodelete =findViewById(R.id.buttontodelete);
        kitchen_notification = findViewById(R.id.notification_restaurant_kitchen);
        rating_notification = findViewById(R.id.notification_concrete_restaurant_rating);
        phone_notification = findViewById(R.id.notification_concrete_phone);
        date_notification = findViewById(R.id.notification_concrete_date);
        number_notification = findViewById(R.id.notification_concrete_number); // инициализация элементов


        name_notification.setText(notification.getRestaurantname());
        kitchen_notification.setText(notification.getRestaurantkitchen());
        location_notification.setText(notification.getRestaurantlocation());
        rating_notification.setText(notification.getRestaurantrating());
        date_notification.setText(notification.getDate());
        number_notification.setText(notification.getNumber() + " человек");
        phone_notification.setText(notification.getRestaurantphone());
        // присвоение данных о резерве
    }

    public void buttontodelete(View view) { // удаление уведомления

        provider.deleteNotification(notification); // функция об удалении уведомления в MyDataProvider
        Toast.makeText(getApplicationContext(), "Уведомление удалено", Toast.LENGTH_LONG).show(); // объявление об удаленном резерве

        Intent intent = new Intent(this, MainActivity.class); // переход на MainActivity
        startActivity(intent);
    }
}
