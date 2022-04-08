package com.example.thefifthone;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;

import Models.DbHelper;
import Models.MyDataProvider;
import Models.User;

public class ChangingProfileActivity extends AppCompatActivity {

    MyDataProvider provider;
    Button buttonsavechanges;
    EditText username;
    EditText name;
    EditText phonenumber;
    EditText mail;
    EditText password;
    EditText confirmpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changing_profile);

        buttonsavechanges = findViewById(R.id.buttonsavechanges);
        provider = new MyDataProvider(getApplicationContext());
        // инициализация элементов и базы данных

        User user = provider.getLoggedInUser();
        if(user == null) {
            return;
        } //проверка зарегестрированного пользователя

        username = findViewById(R.id.editusername);//
        username.setText(user.getUsername());

        password = findViewById(R.id.editpassword);
        confirmpassword = findViewById(R.id.editconfirmpassword);

        name = findViewById(R.id.editname);
        name.setText(user.getName());

        phonenumber = findViewById(R.id.editphone);
        phonenumber.setText(user.getPhonenmb());

        mail = findViewById(R.id.editmail);
        mail.setText(user.getMail()); //нахождение элементов и присвоение текста в зависимости от данных пользователя

    }

    public void buttonsaveChanges(View view) { // функция при нажатии на кнопку сохранить
        String usernameischanging = username.getText() != null ?
                username.getText().toString() : "";
        String firstname = name.getText() != null ?
                name.getText().toString() : "";
        String number = phonenumber.getText() != null ?
                phonenumber.getText().toString() : "";
        String gmail = mail.getText() != null ?
                mail.getText().toString() : "";
        String passwordischanging = password.getText() != null ?
                password.getText().toString() : "";
        String confirmedpasswordischanging = confirmpassword.getText() != null ?
                confirmpassword.getText().toString() : "";


        if (usernameischanging.equals("") || firstname.equals("") || number.equals("") || gmail.equals("") || passwordischanging.equals("") || confirmedpasswordischanging.equals("")){
            Toast.makeText(getApplicationContext(), "Не все данные введены", Toast.LENGTH_LONG).show();
            return;
        } // проверка на полное заполнение новых данных
        if (!passwordischanging.equals(confirmedpasswordischanging)){
            Toast.makeText(getApplicationContext(), "Пароли не совпадают", Toast.LENGTH_LONG).show();
            return;
        } // проверка если пароль не совпадает с подтверждением пароля

        User user = provider.getLoggedInUser(); // достать залогиненного пользователя

        if(!usernameischanging.equals(user.getUsername())){
            User existedUser = provider.getUser(usernameischanging);
            if(existedUser != null) {
                Toast.makeText(getApplicationContext(), "Данный пользователь уже существует", Toast.LENGTH_LONG).show();
                return;
            } // проверка на уже существующего пользователя
        }
        if (passwordischanging.equals("") || confirmedpasswordischanging.equals("")){
            Toast.makeText(getApplicationContext(), "Пароли не прописаны", Toast.LENGTH_LONG).show();
            return;
        } // проверка совпадают ли новый пароль с подтверждением нового пароля

        user.setUsername(usernameischanging);
        user.setPasswd(passwordischanging);
        user.setFirstname(firstname);
        user.setPhonenmb(number);
        user.setMail(gmail);
       // приписание новых данных для данного пользователя

        provider.updateUser(user); // обновление пользователя

        Toast.makeText(getApplicationContext(), "Изменения сохранены", Toast.LENGTH_LONG).show(); // всплывающее окно, уведомляющее что изменения сохранены

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent); // переход на MainActivity
    }

}
