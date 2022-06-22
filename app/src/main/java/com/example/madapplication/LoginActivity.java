package com.example.madapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;
    Button loginbutton;

    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username= (EditText) findViewById(R.id.loginUsername);
        password= (EditText) findViewById(R.id.loginPassword);
        loginbutton=(Button) findViewById(R.id.loginbutton);

        myDB= new DBHelper(this);

        loginbutton.setOnClickListener(new View.OnClickListener()
        {
          @Override
          public void onClick(View v)
          {
              String user1 = username.getText().toString();
              String pass1= password.getText().toString();

              if(user1.equals("") || pass1.equals("")){
                  Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
              }
              else
              {
                  Boolean data = myDB.checkdata(user1,pass1);
                  if(data==true)
                  {
                      Intent intent= new Intent((getApplicationContext()), MyTimelineActivity.class);
                      startActivity(intent);
                  }
                  else
                  {
                      Toast.makeText(LoginActivity.this, "Invalid ", Toast.LENGTH_SHORT).show();
                  }
              }
          }
        });

    }
}