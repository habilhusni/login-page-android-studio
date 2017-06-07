package com.example.husnihabil.simplelogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.husnihabil.simplelogin.R.id.password_input;
import static com.example.husnihabil.simplelogin.R.id.username_input;

public class MainActivity extends AppCompatActivity {
    Button b1;
    EditText userInput, userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.btn_login);
        userInput = (EditText) findViewById(username_input);
        userPassword = (EditText) findViewById(R.id.password_input);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( userInput.getText().toString().equals("luke@kudo.co.id") ) {
                    if( !userPassword.getText().toString().equals("12345") ) {
                        Toast.makeText(MainActivity.this,"Username and password does not match!!",Toast.LENGTH_LONG)                         .show();
                    } else {
                        Intent in = new Intent(MainActivity.this, SecondActivity.class);
                        in.putExtra("userInput", userInput.getText().toString());
                        in.putExtra("userPassword", userPassword.getText().toString());
                        startActivity(in);
                        Toast.makeText(MainActivity.this,"Logged in as: "+userInput.getText().toString(),Toast                                                .LENGTH_LONG).show();
                    }
                } else if( userInput.getText().toString().equals("anakin@kudo.co.id") ) {
                    if( !userPassword.getText().toString().equals("54321") ) {
                        Toast.makeText(MainActivity.this,"Username and password does not match!!",Toast.LENGTH_LONG)                         .show();
                    } else {
                        Intent in = new Intent(MainActivity.this, SecondActivity.class);
                        in.putExtra("userInput", userInput.getText().toString());
                        in.putExtra("userPassword", userPassword.getText().toString());
                        startActivity(in);
                        Toast.makeText(MainActivity.this,"Logged in as: "+userInput.getText().toString(),Toast                                                .LENGTH_LONG).show();
                    }
                } else if( userInput.getText().toString().equals("cade@kudo.co.id") ) {
                    if( !userPassword.getText().toString().equals("15243") ) {
                        Toast.makeText(MainActivity.this,"Username and password does not match!!",Toast.LENGTH_LONG)                         .show();
                    } else {
                        Intent in = new Intent(MainActivity.this, SecondActivity.class);
                        in.putExtra("userInput", userInput.getText().toString());
                        in.putExtra("userPassword", userPassword.getText().toString());
                        startActivity(in);
                        Toast.makeText(MainActivity.this,"Logged in as: "+userInput.getText().toString(),Toast                                                .LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this,"Username does not registered on database!!",Toast.LENGTH_LONG)                         .show();
                }

            }
        });

    }
}
