package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {


    EditText fullName, username, email, phone, password;
    CheckBox checkBox;
    Button signUp;
    TextView signIn;
    DatabaseHandler db;
    boolean  userFound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fullName =  findViewById(R.id.etFullNameReg);
        username = findViewById(R.id.etUsernameReg);
        email = findViewById(R.id.etEmailReg);
        phone = findViewById(R.id.etPhoneReg);
        password = findViewById(R.id.etPasswordReg);

        signUp = findViewById(R.id.signUpBtn);
        signIn = findViewById(R.id.signInLinkFromReg);

        db = new DatabaseHandler(this);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullNameText = fullName.getText().toString().trim();
                String usernameText = username.getText().toString().trim();
                String emailText = email.getText().toString().trim();
                String phoneText = phone.getText().toString().trim();
                String passwordText = password.getText().toString().trim();

                checkBox = findViewById(R.id.conditionCheckBox);

                userFound = false;

                ArrayList<User> users = db.getAllUsers();

                if(!fullNameText.isEmpty() && !usernameText.isEmpty() && !emailText.isEmpty() && !phoneText.isEmpty() && !passwordText.isEmpty() && checkBox.isChecked()){

                    for(int i =0; i< users.size(); i++){

                        if(users.get(i).getUsername().equals(usernameText)){
                            Snackbar snackbar = Snackbar.make(signUp, "", Snackbar.LENGTH_LONG);
                            View customize = getLayoutInflater().inflate(R.layout.failure_snackbar, null);
                            TextView msg = customize.findViewById(R.id.msgAttention);
                            msg.setText("Username already exists, \nplease choose another one.");
                            snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
                            Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
                            snackbarLayout.setPadding(0,0,0,0);
                            snackbarLayout.addView(customize, 0);
                            snackbar.show();
                            userFound = true;
                            break;
                        }

                        if(users.get(i).getEmail().equals(emailText)) {
                            Snackbar snackbar = Snackbar.make(signUp, "", Snackbar.LENGTH_LONG);
                            View customize = getLayoutInflater().inflate(R.layout.failure_snackbar, null);
                            TextView msg = customize.findViewById(R.id.msgAttention);
                            msg.setText("Email already exists, \nplease choose another one.");
                            snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
                            Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
                            snackbarLayout.setPadding(0,0,0,0);
                            snackbarLayout.addView(customize, 0);
                            snackbar.show();
                            userFound = true;
                            break;
                        }
                    }

                    if(!userFound){
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_picture);

                        User user = new User(fullNameText ,usernameText, emailText, phoneText, passwordText, bitmap);

                        db.addUser(user);

                        Snackbar snackbar = Snackbar.make(signUp, "", Snackbar.LENGTH_LONG);
                        View customize = getLayoutInflater().inflate(R.layout.success_snackbar, null);
                        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
                        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
                        snackbarLayout.setPadding(0,0,0,0);
                        snackbarLayout.addView(customize, 0);


                        username.setText("");
                        email.setText("");
                        password.setText("");
                        fullName.setText("");
                        email.setText("");
                        phone.setText("");
                        password.setText("");

                    }

                }
                else if(fullNameText.isEmpty() || usernameText.isEmpty() || emailText.isEmpty() || phoneText.isEmpty() || passwordText.isEmpty()){
                    Snackbar snackbar = Snackbar.make(signUp, "", Snackbar.LENGTH_LONG);
                    View customize = getLayoutInflater().inflate(R.layout.failure_snackbar, null);
                    snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
                    Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
                    snackbarLayout.setPadding(0,0,0,0);
                    snackbarLayout.addView(customize, 0);
                    snackbar.show();
                }
                else if (!checkBox.isChecked()) {
                    Snackbar snackbar = Snackbar.make(signUp, "", Snackbar.LENGTH_LONG);
                    View customize = getLayoutInflater().inflate(R.layout.failure_snackbar, null);
                    TextView msg = customize.findViewById(R.id.msgAttention);
                    msg.setText("You must accept the terms, and \nconditions to register an account.");
                    snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
                    Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
                    snackbarLayout.setPadding(0,0,0,0);
                    snackbarLayout.addView(customize, 0);
                    snackbar.show();
                }

            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}