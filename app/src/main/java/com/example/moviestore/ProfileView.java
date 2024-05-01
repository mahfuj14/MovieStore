package com.example.moviestore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class ProfileView extends AppCompatActivity {
    List<UserModel> user;
    UserModel userModel2;

    TextView name, email, phone, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        name = (TextView) findViewById(R.id.nameViewTv);
        email = (TextView) findViewById(R.id.emailViewTv);
        phone = (TextView) findViewById(R.id.contactViewTv);
        address = (TextView) findViewById(R.id.addressViewTv);


        Intent intent =getIntent();
        if(intent!=null){
            userModel2 = (UserModel) intent.getSerializableExtra("User_information");
            name.setText(userModel2.getName().toString());
            email.setText(userModel2.getEmail().toString());
            phone.setText(userModel2.getPhone().toString());
            address.setText(userModel2.getAddress().toString());
        }



    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(ProfileView.this, MainActivity.class));
        // Write your custom logic here
        // For example, you can perform some actions before navigating back or prevent the user from navigating back
        // If you want to simply navigate back to the previous activity, call super.onBackPressed()
        super.onBackPressed();
    }

}