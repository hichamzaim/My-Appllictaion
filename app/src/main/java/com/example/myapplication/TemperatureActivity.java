package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.text.DecimalFormat;

public class TemperatureActivity extends AppCompatActivity {

    EditText inputTemperature;
    Button convertButton;
    TextView resultTextView;
    RadioGroup radioGroup;
    ConstraintLayout back;

    DecimalFormat decimalFormat = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        inputTemperature = findViewById(R.id.input_temperature);
        convertButton = findViewById(R.id.convert_button);
        resultTextView = findViewById(R.id.result_text);
        radioGroup = findViewById(R.id.radio_group);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertTemperature();
            }
        });
    }

    private void convertTemperature() {


        if(inputTemperature.getText().toString().isEmpty()){
            resultTextView.setText("Please give temperature");
        }else{
            double inputTemp = Double.parseDouble(inputTemperature.getText().toString());
            int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            if (selectedRadioButton != null) {
                String unit = selectedRadioButton.getText().toString();

                switch (unit) {
                    case "Celsius":
                        resultTextView.setText(convertCelsiusToFahrenheitAndKelvin(inputTemp));
                        break;
                    case "Fahrenheit":
                        resultTextView.setText(convertFahrenheitToCelsiusAndKelvin(inputTemp));
                        break;
                    case "Kelvin":
                        resultTextView.setText(convertKelvinToCelsiusAndFahrenheit(inputTemp));
                        break;
                }
            }else{
                resultTextView.setText("Please select unit");
            }
        }

    }

    private String convertCelsiusToFahrenheitAndKelvin(double celsius) {
        double fahrenheit = (celsius * 9 / 5) + 32;
        double kelvin = celsius + 273.15;
        return "Fahrenheit   :   " + decimalFormat.format(fahrenheit) + "°F\n \nKelvin           :   " + decimalFormat.format(kelvin) + "K";
    }

    private String convertFahrenheitToCelsiusAndKelvin(double fahrenheit) {
        double celsius = (fahrenheit - 32) * 5 / 9;
        double kelvin = (fahrenheit + 459.67) * 5 / 9;
        return "Celsius         :   " + decimalFormat.format(celsius) + "°C\n \nKelvin           :   " + decimalFormat.format(kelvin) + "K";
    }

    private String convertKelvinToCelsiusAndFahrenheit(double kelvin) {
        double celsius = kelvin - 273.15;
        double fahrenheit = (kelvin * 9 / 5) - 459.67;
        return "Celsius         :   " + decimalFormat.format(celsius) + "°C\n \nFahrenheit   :   " + decimalFormat.format(fahrenheit) + "°F";
    }
}
