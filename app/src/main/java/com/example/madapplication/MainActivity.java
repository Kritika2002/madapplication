package com.example.madapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.DBHelper;

public class MainActivity extends AppCompatActivity {

    EditText fullname,emailaddress,dateofbirth,password,repassword;
    Button register,signin;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fullname= (EditText)findViewById(R.id.fullname);
        emailaddress=(EditText)findViewById(R.id.emailaddress);
        dateofbirth=(EditText) findViewById(R.id.dateofbirth);
        password=(EditText)findViewById(R.id.password);
        repassword=(EditText)findViewById(R.id.repassword);

        register=(Button)findViewById(R.id.register);
        signin=(Button)findViewById(R.id.signin);


        myDB= new DBHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= fullname.getText().toString();
                String pass= password.getText().toString();
                String repass= repassword.getText().toString();
                String mail= emailaddress.getText().toString();
                String dob= dateofbirth.getText().toString();

                if(name.equals("") || pass.equals("") || repass.equals("") || mail.equals("") || dob.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(pass.equals(repass))
                    {
                        Boolean checkResult= myDB.checkfullname(name);
                        if(checkResult==false)
                        {
                            Boolean reResult= myDB.insertData(name,pass);
                            if(reResult==true)
                            {
                                Toast.makeText(MainActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                Intent intent= new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "User already exists. Please login", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

    signin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent= new intent(getApplicationContext().LoginActivity.class);
            startActivity(intent);
        }
    });




    }
}