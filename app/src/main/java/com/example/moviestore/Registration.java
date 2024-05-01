package com.example.moviestore;

import static android.text.TextUtils.isEmpty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Registration extends AppCompatActivity {

    EditText name, email, phone, address;
    String nameS, emailS, phoneS, addressS;
    Button regBtn;
    UserModel userModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        name = (EditText) findViewById(R.id.usernameReg);
        email = (EditText) findViewById(R.id.emailReg);
        phone = (EditText) findViewById(R.id.phoneReg);
        address = (EditText) findViewById(R.id.addressReg);
        regBtn = (Button) findViewById(R.id.registerBtn);



        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameS = name.getText().toString();
                emailS = email.getText().toString();
                phoneS = phone.getText().toString();
                addressS = address.getText().toString();
                if (!isEmpty(nameS) && isEmail(email) && !isEmpty(phoneS) && !isEmpty(addressS)) {
                    userModel =new UserModel(nameS,emailS,phoneS, addressS );
                    Intent intent = new Intent(Registration.this, MainActivity.class);
                    intent.putExtra("User_information",(Serializable) userModel);
                    startActivity(intent);


                } else {
                    Toast.makeText(Registration.this, "Please fill the form", Toast.LENGTH_SHORT);
                }
            }
        });
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
}