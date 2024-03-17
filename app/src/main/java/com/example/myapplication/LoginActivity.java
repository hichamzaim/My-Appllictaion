package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    TextView signUp;
    Button signIn;
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.etUsernameLog);
        password = findViewById(R.id.etPasswordLog);
        signIn = findViewById(R.id.signInBtn);

        db = new DatabaseHandler(this);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               String usernameText = username.getText().toString().trim();
               String passwordText = password.getText().toString().trim();
               User user =  db.getUser(usernameText);

                if(user == null){
                   Snackbar snackbar = Snackbar.make(signUp, "", Snackbar.LENGTH_LONG);
                   View customize = getLayoutInflater().inflate(R.layout.failure_snackbar, null);
                   TextView msg = customize.findViewById(R.id.msgAttention);
                   msg.setText("Username does not exist !!");
                   snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
                   Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
                   snackbarLayout.setPadding(0,0,0,0);
                   snackbarLayout.addView(customize, 0);
                   snackbar.show();
               }
               else if (!user.getPassword().equals(passwordText)){
                   Snackbar snackbar = Snackbar.make(signUp, "", Snackbar.LENGTH_LONG);
                   View customize = getLayoutInflater().inflate(R.layout.failure_snackbar, null);
                   TextView msg = customize.findViewById(R.id.msgAttention);
                   msg.setText("The password you entred is \nincorrect. Please try again !!");
                   snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
                   Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
                   snackbarLayout.setPadding(0,0,0,0);
                   snackbarLayout.addView(customize, 0);
                   snackbar.show();
               }
               else if (user.getPassword().equals(passwordText)){

                   username.setText("");
                   password.setText("");
                   Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                   intent.putExtra("username", user.getUsername());
                   startActivity(intent);
                   finish();
                }

            }
        });



        signUp = findViewById(R.id.signUpLinkFromLog);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}