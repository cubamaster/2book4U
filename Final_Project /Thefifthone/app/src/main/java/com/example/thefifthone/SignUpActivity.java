package com.example.thefifthone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import Models.MyDataProvider;
import Models.User;

public class SignUpActivity extends AppCompatActivity {

    MyDataProvider provider;
    EditText txtusername;
    EditText txtPasswd;
    EditText confirmpasswd;
    EditText firstname;
    EditText lastname;
    EditText phonenumb;
    EditText mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        provider = new MyDataProvider(getApplicationContext());

        txtusername = findViewById(R.id.username);
        txtPasswd = findViewById(R.id.password);
        confirmpasswd = findViewById(R.id.confirm);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        phonenumb = findViewById(R.id.phone);
        mail = findViewById(R.id.mail);
    }

    public void btnSignUpClicked(View v) {
        String username = txtusername.getText() != null ?
                txtusername.getText().toString() : "";
        String passwd = txtPasswd.getText() != null ?
                txtPasswd.getText().toString() : "";
        String confpasswd = confirmpasswd.getText() != null ?
                confirmpasswd.getText().toString() : "";
        String first = firstname.getText() != null ?
                firstname.getText().toString() : "";
        String last = lastname.getText() != null ?
                lastname.getText().toString() : "";
        String phone = phonenumb.getText() != null ?
                phonenumb.getText().toString() : "";
        String gmail = mail.getText() != null ?
                mail.getText().toString() : "";

        if (username.equals("") || passwd.equals("") || confpasswd.equals("") ||
        first.equals("") || last.equals("") || phone.equals("") || gmail.equals("") ){
            Toast.makeText(getApplicationContext(), "Пожалуйста, введите всю информацию", Toast.LENGTH_LONG).show();
            return;
        } // проверка заполнены ли все данные
        if (!passwd.equals(confpasswd)){
            Toast.makeText(getApplicationContext(), "Пароли не совпадают", Toast.LENGTH_LONG).show();
            return;
        } // проверка на совпадение пароля и проверки для пароля
        User existedUser = provider.getUser(username); // найти пользователя по имени
        if(existedUser != null) { // проверить есть ли уже существующий пользователь с таким именем
            Toast.makeText(getApplicationContext(), "Данное имя пользователя занято", Toast.LENGTH_LONG).show();
            return;
        }
        User user = new User();
        user.setUsername(username);
        user.setPasswd(passwd);
        user.setFirstname(first);
        user.setLastname(last);
        user.setPhonenmb(phone);
        user.setMail(gmail); // придать значения новому пользователю

        provider.addUser(user); // добавить нового пользователя

        provider.setLoggedInUser(user); // установить зарегестрированного пользователя

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent); // переход на экран MainActivity
    }
}
