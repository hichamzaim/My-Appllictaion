package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener{

    Button c, opened_bracket, closed_bracket, divide, seven, eight, nine, multiply, four,
            five, six, plus, one, two, three, minus, ac, zero, dot, equal;
    TextView textEquation, textResult;
    ConstraintLayout back;
    double res = 0;
    String operation = "+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        textEquation =findViewById(R.id.textEquation);
        textResult =findViewById(R.id.textResult);

        assignId(c, R.id.c);
        assignId(opened_bracket, R.id.opened_bracket);
        assignId(closed_bracket, R.id.closed_bracket);
        assignId(divide, R.id.divide);
        assignId(seven, R.id.seven);
        assignId(eight, R.id.eight);
        assignId(nine, R.id.nine);
        assignId(multiply, R.id.multiply);
        assignId(four, R.id.four);
        assignId(five, R.id.five);
        assignId(six, R.id.six);
        assignId(plus, R.id.plus);
        assignId(one, R.id.one);
        assignId(two, R.id.two);
        assignId(three, R.id.three);
        assignId(minus, R.id.minus);
        assignId(ac, R.id.ac);
        assignId(zero, R.id.zero);
        assignId(dot, R.id.dot);
        assignId(equal, R.id.equal);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    void assignId(Button btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Button btn = (Button) view;
        String btnText = btn.getText().toString();
        String dataToCalculate = textEquation.getText().toString();

        if(btnText.equals("AC")){
            textEquation.setText("");
            textResult.setText("0");
            return;
        }
        if (btnText.equals("=")){
            textEquation.setText(textResult.getText());
            return;
        }
        if (btnText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate + btnText;
        }
        textEquation.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            textResult.setText(finalResult);
        }
    }

    String getResult(String data){
        try{
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }
}