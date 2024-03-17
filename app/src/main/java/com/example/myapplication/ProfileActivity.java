package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    String username;
    TextView usernameValue, fullnameValue, emailValue, phoneValue, editProfileText, occupationValue, nationalityValue, addressValue, zipCodeValue, countryValue;
    ImageView changePic, profileImage, navImage;
    DatabaseHandler db;
    User user;
    private static final int PIKE_IMAGE_REQUEST = 100;
    private static final int EDIT_INFO_REQUEST = 200;
    private Uri imagePath;
    private Bitmap imageToStore;

    FloatingActionButton add, edit;
    boolean isVisible;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    View headerView;
    ImageView headerImage;
    TextView headerName, headerEmail;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        drawerLayout  = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.profile);


        db = new DatabaseHandler(this);
        changePic = findViewById(R.id.changePic);
        profileImage = findViewById(R.id.profileImage);


        headerView = navigationView.getHeaderView(0);
        headerImage = headerView.findViewById(R.id.nav_image);
        headerName = headerView.findViewById(R.id.nav_fullName);
        headerEmail = headerView.findViewById(R.id.nav_email);



        usernameValue = findViewById(R.id.usernameValue);
        fullnameValue = findViewById(R.id.fullNameValue);
        emailValue = findViewById(R.id.emailValue);
        phoneValue = findViewById(R.id.phoneValue);
        occupationValue = findViewById(R.id.occupationValue);
        nationalityValue = findViewById(R.id.nationalityValue);
        addressValue = findViewById(R.id.addressValue);
        zipCodeValue = findViewById(R.id.zipcodeValue);
        countryValue = findViewById(R.id.countryValue);

        add = findViewById(R.id.fabAdd);
        edit = findViewById(R.id.fabEdit);
        editProfileText = findViewById(R.id.editProfileText);

        Intent data = getIntent();
        username = data.getExtras().getString("username");

        user = db.getUser(username);

        usernameValue.setText(user.getUsername());
        fullnameValue.setText(user.getFullName());
        emailValue.setText(user.getEmail());
        phoneValue.setText(user.getPhone());
        profileImage.setImageBitmap(user.getImage());
        headerImage.setImageBitmap(user.getImage());
        occupationValue.setText(user.getOccupation());
        nationalityValue.setText(user.getNationality());
        addressValue.setText(user.getAddress());
        zipCodeValue.setText(user.getZipcode());
        countryValue.setText(user.getCountry());
        headerName .setText(user.getFullName());
        headerEmail.setText(user.getEmail());

        changePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent,PIKE_IMAGE_REQUEST);
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        edit.hide();
        editProfileText.setVisibility(View.GONE);
        add.setImageResource(R.drawable.plus_icon);
        isVisible = false;
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!isVisible){
                    edit.show();
                    editProfileText.setVisibility(View.VISIBLE);
                    add.setImageResource(R.drawable.cancel_icon);
                    isVisible = true;
                }else{
                    edit.hide();
                    editProfileText.setVisibility(View.GONE);
                    add.setImageResource(R.drawable.plus_icon);
                    isVisible = false;
                }
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit.hide();
                editProfileText.setVisibility(View.GONE);
                add.setImageResource(R.drawable.plus_icon);
                isVisible = false;

                Intent intent = new Intent(ProfileActivity.this, EditActivity.class);
                intent.putExtra("user", username);
                startActivityForResult(intent, EDIT_INFO_REQUEST);

            }
        });
        drawerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit.hide();
                editProfileText.setVisibility(View.GONE);
                add.setImageResource(R.drawable.plus_icon);
                isVisible = false;
            }
        });

        ConstraintLayout constraintLayout3 = findViewById(R.id.constraintLayout3);
        constraintLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit.hide();
                editProfileText.setVisibility(View.GONE);
                add.setImageResource(R.drawable.plus_icon);
                isVisible = false;
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.profile){

        }else if (item.getItemId() == R.id.calculator){
            navigationView.setCheckedItem(R.id.profile);
            Intent intent = new Intent(ProfileActivity.this, CalculatorActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.temperature) {
            navigationView.setCheckedItem(R.id.profile);
            Intent intent2 = new Intent(ProfileActivity.this, TemperatureActivity.class);
            startActivity(intent2);

        } else if (item.getItemId() == R.id.logout) {
            Intent intent2 = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(intent2);
            finish();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
       try {
           super.onActivityResult(requestCode, resultCode, data);
           if(requestCode == PIKE_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
                imagePath = data.getData();
                imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                profileImage.setImageBitmap(imageToStore);
                headerImage.setImageBitmap(imageToStore);

                db.updatePic(username, imageToStore);

               Snackbar snackbar = Snackbar.make(profileImage, "", Snackbar.LENGTH_LONG);
               View customize = getLayoutInflater().inflate(R.layout.success_snackbar, null);
               TextView msg = customize.findViewById(R.id.msgAttention);
               msg.setText("Success, Your profile picture has\nbeen updated successfully!!");
               snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
               Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
               snackbarLayout.setPadding(0,0,0,0);
               snackbarLayout.addView(customize, 0);
               snackbar.show();
           }
           if(requestCode == EDIT_INFO_REQUEST && resultCode == RESULT_OK ){


               usernameValue.setText(data.getExtras().getString("username"));
               fullnameValue.setText(data.getExtras().getString("fullName"));
               emailValue.setText(data.getExtras().getString("email"));
               phoneValue.setText(data.getExtras().getString("phone"));
               occupationValue.setText(data.getExtras().getString("occupation"));
               nationalityValue.setText(data.getExtras().getString("nationality"));
               addressValue.setText(data.getExtras().getString("address"));
               zipCodeValue.setText(data.getExtras().getString("zipcode"));
               countryValue.setText(data.getExtras().getString("country"));
               headerName .setText(data.getExtras().getString("fullName"));
               headerEmail.setText(data.getExtras().getString("email"));

               Snackbar snackbar = Snackbar.make(add, "", Snackbar.LENGTH_LONG);
               View customize = getLayoutInflater().inflate(R.layout.success_snackbar, null);
               TextView msg = customize.findViewById(R.id.msgAttention);
               msg.setText("Success, Your Information has\nbeen updated successfully!!");
               snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
               Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
               snackbarLayout.setPadding(0,0,0,0);
               snackbarLayout.addView(customize, 0);
               snackbar.show();
           }

       }
       catch (Exception e){
           Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
       }

    }



}