package com.example.thefifthone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import Models.DataUtils;
import Models.MyDataProvider;
import Models.Notification;
import Models.Restaurant;
import Models.User;

public class RestaurantPersonalActivity extends AppCompatActivity {

    MyDataProvider provider;
    Restaurant reserve;
    Restaurant reserve2;
    ImageView imagepersonal;
    TextView restaurantName;
    TextView kitchen;
    TextView location;
    TextView rating;
    TextView timetable;
    TextView phoneofrestaurant;
    TextView dateofreserve;
    EditText numberOfPeople;
    Button reservebutton;
    Calendar dateAndTime = Calendar.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_personal);

        provider = new MyDataProvider(getApplicationContext()); // база данных



        imagepersonal = findViewById(R.id.personalimage);
        restaurantName = findViewById(R.id.personalname);
        kitchen = findViewById(R.id.personalkitchenrating);
        location = findViewById(R.id.personallocation);
        rating = findViewById(R.id.personal);
        timetable = findViewById(R.id.timetable);
        phoneofrestaurant = findViewById(R.id.phonenumber);
        dateofreserve = findViewById(R.id.personalDate);
        numberOfPeople = findViewById(R.id.personalpeople);
        reservebutton = findViewById(R.id.reservebutton);
        provider = new MyDataProvider(getApplicationContext());


        Intent intent = getIntent();
        int restaurantId = intent.getIntExtra("restaurantId", -1);
        reserve = provider.getRestaurant(restaurantId); // нахождение ресторана
        Bundle bundle = this.getIntent().getExtras();
        int pic = bundle.getInt("image");
        imagepersonal.setImageResource(pic);

        if (reserve == null) {
            finish();
        } // проверка ресторана



        restaurantName.setText(reserve.getName());
        kitchen.setText(reserve.getKitchen());
        location.setText(reserve.getLocation());
        rating.setText(reserve.getRating()); // вывод данных о ресторане из базы данных
        timetable.setText("пн-вс: " + reserve.getTimetable());
        phoneofrestaurant.setText(reserve.getPhoneofrestaurant());

    }

    public void savereserveClick(View view) {
        String date = dateofreserve.getText() != null ?
                dateofreserve.getText().toString() : "";
        String number = numberOfPeople.getText() != null ?
                numberOfPeople.getText().toString() : "";

        if (date.equals("") || number.equals("") || number.equals("0")){
            Toast.makeText(getApplicationContext(), "Not all data entered", Toast.LENGTH_LONG).show();
            return;
        } // проверка на ввод даты, времени и кол-ва персон

        User user = provider.getLoggedInUser(); // нахождение пользователя

        Intent intent = getIntent();
        int restaurantId = intent.getIntExtra("restaurantId", -1);

        reserve2 = provider.getRestaurant(restaurantId);
        //int imageId = intent.getIntExtra("image", 0);
        //imagepersonal.setImageResource(imageId);

        Notification notification = new Notification(); // создание нового объявления
        notification.setTitle("Вы зарезервировали столик на " + date + " в " + reserve2.getName());
        notification.setUserid(user.getId());
        notification.setRestaurantname(reserve2.getName());
        notification.setRestaurantkitchen(reserve2.getKitchen());
        notification.setRestaurantlocation(reserve2.getLocation());
        notification.setRestaurantrating(reserve2.getRating());
        notification.setDate(date);
        notification.setNumber(number);
        notification.setRestaurantphone(reserve2.getPhoneofrestaurant());// присвоение данных о резерве в новое объявление

        provider.addNotification(notification); // добавление нового объявления

        if (user != null){
            Intent intent2 = new Intent(this, MainActivity.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent2); // переход на MainActivity
        }
    }
    public void btnCreateFightClicked(android.view.View v) {
        DatePickerDialog dialog = new DatePickerDialog(RestaurantPersonalActivity.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH));

        Calendar minDate = Calendar.getInstance();
        minDate.add(Calendar.DATE, 0);
        dialog.getDatePicker().setMinDate(minDate.getTimeInMillis());
        dialog.show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            //setInitialDateTime();
            new TimePickerDialog(RestaurantPersonalActivity.this, t,
                    dateAndTime.get(Calendar.HOUR_OF_DAY),
                    dateAndTime.get(Calendar.MINUTE), true)
                    .show();
        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute);
            dateofreserve.setText(DataUtils.getDateFromCalendar(dateAndTime));
        }
    };
}
