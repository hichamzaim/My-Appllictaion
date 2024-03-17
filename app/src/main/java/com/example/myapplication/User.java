package com.example.myapplication;

import android.graphics.Bitmap;

public class User {
    int id;
    String username, fullName,email, phone, password, occupation, nationality, address, zipcode, country;
    Bitmap image;

    public User(int id, String fullName,String username, String email, String phone, String password, Bitmap image, String occupation, String nationality, String address, String zipcode, String country) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.image = image;
        this.occupation = occupation;
        this.nationality = nationality;
        this.address = address;
        this.zipcode = zipcode;
        this.country = country;
    }


    public User(String fullName, String username, String email, String phone, String password, Bitmap image, String occupation, String nationality, String address, String zipcode, String country) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.image = image;
        this.occupation = occupation;
        this.nationality = nationality;
        this.address = address;
        this.zipcode = zipcode;
        this.country = country;
    }

    public User(int id, String fullName,String username, String email, String phone, String occupation, String nationality, String address, String zipcode, String country) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.occupation = occupation;
        this.nationality = nationality;
        this.address = address;
        this.zipcode = zipcode;
        this.country = country;
    }


    public User(String fullName, String username, String email, String phone, String occupation, String nationality, String address, String zipcode, String country) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.occupation = occupation;
        this.nationality = nationality;
        this.address = address;
        this.zipcode = zipcode;
        this.country = country;
    }

    public User(int id, String fullName,String username, String email, String phone, String password) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.image = image;
    }


    public User(String fullName, String username, String email, String phone, String password, Bitmap image) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.image = image;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getNationality() {
        return nationality;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
