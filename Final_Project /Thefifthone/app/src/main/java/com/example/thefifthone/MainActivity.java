package com.example.thefifthone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import Models.MyDataProvider;
import Models.User;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;
    MyDataProvider provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        provider = new MyDataProvider(this); // база данных

        bottomNavigationView = findViewById(R.id.bottom_navigation); // нижняя панель
        frameLayout = findViewById(R.id.fragment_container);
        setFragment(new HomeFragment(this)); // фрагмент (окно в MainActivity) по умолчанию - home
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.home) {
                    setFragment(new HomeFragment(getApplicationContext()));
                    return true;
                } // проверка какой item был нажат
                if (menuItem.getItemId() == R.id.search) {
                    setFragment(new SearchFragment(getApplicationContext()));
                    return true;
                }
                if (menuItem.getItemId() == R.id.profile) {
                    setFragment(new ProfileFragment(getApplicationContext()));
                    return true;
                }
                return true;
            }
        });
    }

    @Override
    protected void onStart() { // после выхода из приложения, оно перезагружается и происходит OnStart
        super.onStart();

        User currentUser = provider.getLoggedInUser();
        if(currentUser == null) { // узнаем есть ли зарегестрированный пользователь на данный момент или нет
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent); //если пользователь не залогинен, переводим его на экран Login
        }
    }

    private void setFragment(Fragment fragment) { // установка фрагмента
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}
