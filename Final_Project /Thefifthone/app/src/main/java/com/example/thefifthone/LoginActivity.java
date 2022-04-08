package com.example.thefifthone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Models.MyDataProvider;
import Models.User;

public class LoginActivity extends AppCompatActivity {

    EditText txtLoginUsername;
    EditText txtLoginPassword;
    TextView textView;
    MyDataProvider provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtLoginUsername = findViewById(R.id.loginusername);
        txtLoginPassword = findViewById(R.id.loginpassword);
        textView = findViewById(R.id.loginid);

        provider = new MyDataProvider(getApplicationContext());
    }


    public void btnLoginClicked(View v) { // функция, срабатывающая при нажатии на кнопку log in
        String username = "";
        if (txtLoginUsername.getText() != null){
            username = txtLoginUsername.getText().toString();
        }

        String password = "";
        if (txtLoginPassword.getText() != null){
            password = txtLoginPassword.getText().toString();
        } // получение имени пользователя и пароля от введенных данных

        User user = provider.getUser(username, password); // вытаскиваем пользователя по имени и паролю

        if (user != null){ // если такой пользователь существует, установить зарегестрированного пользователя и перейти на экран MainActivity
            provider.setLoggedInUser(user);
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        else {
            Toast.makeText(getApplicationContext(), "Неправильное имя пользователя или пароль", Toast.LENGTH_LONG).show();
        } // если пользователя не существует, делаем сообщение о неправильно введенных данных
    }

    public void btnOpenSignUpClicked(View view) { // при нажатии на кнопку Sign up переход на SignUpActivity
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}
