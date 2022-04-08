package com.example.thefifthone;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import Models.MyDataProvider;
import Models.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    private static final int RESULT_OK = 100 ;
    MyDataProvider provider;
    Context context;
    ImageView profilephoto;
    Button chageprofilephoto;
    private static int PICK_IMAGE = 100;
    Uri profilephotoURL;
    public ProfileFragment(Context context) {
        this.provider = new MyDataProvider(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        final Button button = (Button) view.findViewById(R.id.buttonchange);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), ChangingProfileActivity.class);
                startActivity(intent);
            }
        }); // переход на активити с изменением данных при нажатии на кнопку Change profile
        final Button button2 = (Button) view.findViewById(R.id.buttonlogout);
        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        }); // переход на экран log in при нажатии на кнопку log out
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        User user = provider.getLoggedInUser(); // нахождение пользователя
        if(user == null) {
            return;
        } // проверка пользователя

        TextView txtUsername = view.findViewById(R.id.txtUsername);
        txtUsername.setText(user.getUsername());

        TextView txtName = view.findViewById(R.id.txtName);
        txtName.setText(user.getName());


        TextView txtphonenumber = view.findViewById(R.id.txtphonenumber);
        txtphonenumber.setText(user.getPhonenmb());

        TextView txtEmail = view.findViewById(R.id.txtEmail);
        txtEmail.setText(user.getMail());

        TextView txtLastName = view.findViewById(R.id.txtLastNameofuser);
        txtLastName.setText(user.getLastname()); // присвоение данных пользователя из базы данных и вывод на экран

    }

    @Override
    public void onActivityResult(int requiestCode, int resultCode, Intent data) {

        super.onActivityResult(requiestCode, resultCode, data);

        if (requiestCode == RESULT_OK && resultCode == PICK_IMAGE) {
            profilephotoURL = data.getData();
            profilephoto.setImageURI(profilephotoURL);
        }
    }
}
