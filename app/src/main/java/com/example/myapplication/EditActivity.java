package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {

    ConstraintLayout back;
    EditText username, occupation, fullName, email, phone, nationality, address, zipCode, country;
    Button save;
    DatabaseHandler db;
    User specificUser;
    int idUser;
    boolean userFound;
    String usernameEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        db = new DatabaseHandler(this);
        Intent data = getIntent();
        usernameEdit = data.getExtras().getString("user");

        specificUser = db.getUser(usernameEdit);

        idUser = specificUser.getId();

        back = findViewById(R.id.back);

        username = findViewById(R.id.etusername);
        occupation = findViewById(R.id.etOccupation);
        fullName = findViewById(R.id.etFullName);
        email = findViewById(R.id.etEmail);
        phone = findViewById(R.id.etPhone);
        nationality = findViewById(R.id.etNationality);
        address = findViewById(R.id.etAddress);
        zipCode = findViewById(R.id.etZipCode);
        country = findViewById(R.id.etCountry);

        username.setText(specificUser.getUsername());
        occupation.setText(specificUser.getOccupation());
        fullName.setText(specificUser.getFullName());
        email.setText(specificUser.getEmail());
        phone.setText(specificUser.getPhone());
        nationality.setText(specificUser.getNationality());
        address.setText(specificUser.getAddress());
        zipCode.setText(specificUser.getZipcode());
        country.setText(specificUser.getCountry());

        save = findViewById(R.id.saveBtn);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String usernameText = username.getText().toString().trim();
                String occupationText = occupation.getText().toString().trim();
                String fullNameText = fullName.getText().toString().trim();
                String emailText = email.getText().toString().trim();
                String phoneText = phone.getText().toString().trim();
                String nationalityText = nationality.getText().toString().trim();
                String addressText = address.getText().toString().trim();
                String zipCodeText = zipCode.getText().toString().trim();
                String countryText = country.getText().toString().trim();

                ArrayList<User> users = db.getAllUsers();

                userFound = false;

                if(!fullNameText.isEmpty() && !usernameText.isEmpty() && !emailText.isEmpty() && !phoneText.isEmpty()){

                    for(int i =0; i< users.size(); i++){

                        if(users.get(i).getId() != idUser){

                            if(users.get(i).getUsername().equals(usernameText)){
                                Snackbar snackbar = Snackbar.make(save, "", Snackbar.LENGTH_LONG);
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
                                Snackbar snackbar = Snackbar.make(save, "", Snackbar.LENGTH_LONG);
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


                    }

                    if(!userFound){

                        User user = new User(fullNameText, usernameText, emailText, phoneText, occupationText, nationalityText, addressText, zipCodeText, countryText);

                        Intent intent = new Intent();
                        intent.putExtra("fullName", user.getFullName());
                        intent.putExtra("username", user.getUsername());
                        intent.putExtra("email", user.getEmail());
                        intent.putExtra("phone", user.getPhone());
                        intent.putExtra("occupation", user.getOccupation());
                        intent.putExtra("nationality", user.getNationality());
                        intent.putExtra("address", user.getAddress());
                        intent.putExtra("zipcode", user.getZipcode());
                        intent.putExtra("country", user.getCountry());
                        setResult(Activity.RESULT_OK, intent);

                        db.updateInfo(usernameEdit, user);

                        finish();
                    }

                }else if(fullNameText.isEmpty() || usernameText.isEmpty() || emailText.isEmpty() || phoneText.isEmpty() ){
                    Snackbar snackbar = Snackbar.make(save, "", Snackbar.LENGTH_LONG);
                    View customize = getLayoutInflater().inflate(R.layout.failure_snackbar, null);
                    TextView msg = customize.findViewById(R.id.msgAttention);
                    msg.setText("Please fill all the required fields.");
                    snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
                    Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
                    snackbarLayout.setPadding(0,0,0,0);
                    snackbarLayout.addView(customize, 0);
                    snackbar.show();
                }


            }
        });
    }
}