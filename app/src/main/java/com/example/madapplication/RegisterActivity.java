package com.example.madapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    DatePickerDialog dobPicker;
    EditText fullname,emailaddress,dateofbirth,password,repassword;
    Button register,signin;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullname= (EditText)findViewById(R.id.fullname);
        emailaddress=(EditText)findViewById(R.id.emailaddress);
        dateofbirth=(EditText) findViewById(R.id.dateofbirth);
        password=(EditText)findViewById(R.id.password);
        repassword=(EditText)findViewById(R.id.repassword);
        register=(Button)findViewById(R.id.register);

        dateofbirth.setInputType(InputType.TYPE_NULL);
        dateofbirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar dobcal= Calendar.getInstance();
                int day= dobcal.get(Calendar.DAY_OF_MONTH);
                int month=dobcal.get(Calendar.MONTH);
                int year= dobcal.get(Calendar.YEAR);
                dobPicker = new DatePickerDialog(RegisterActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onDateSet(DatePicker view, int yearPicked, int monthPicked, int dayPicked) {
                                dateofbirth.setText(yearPicked+"-"+ monthPicked + "-"+ dayPicked );
                            }
                        },
                        year,
                        month,
                        day);
                dobPicker.show();

            }
        });

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
                    Toast.makeText(RegisterActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                Intent intent= new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(RegisterActivity.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(RegisterActivity.this, "User already exists. Please login", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }
}