package com.sahil.bmicalculator;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {


    private Button buttonCalculate;
    private RadioButton radioMale;
    private RadioButton radioFemale;
    private EditText textAge;
    private EditText textFeet;
    private EditText textInches;
    private EditText textWeight;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViews();
        setupButtonClickListener();
    }

    private void findViews() {
        radioMale = findViewById(R.id.radio_button_male);
        radioFemale = findViewById(R.id.radio_button_female);
        textAge = findViewById(R.id.edit_text_age);
        textFeet = findViewById(R.id.edit_text_feet);
        textInches = findViewById(R.id.edit_text_inches);
        textWeight = findViewById(R.id.edit_text_weight);
        buttonCalculate = findViewById(R.id.button_calculate);
        resultText = findViewById(R.id.text_view_result);
    }


    private void setupButtonClickListener() {
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double bmiResult = calculateBmi();
                //------------------------------------
                String ageText = textAge.getText().toString();
                int age = Integer.parseInt(ageText);

                if (age >= 20) {
                    displayResult(calculateBmi());
                } else {
                    displayGuidance(bmiResult);
                }
            }
        });
    }


    private double calculateBmi() throws NumberFormatException {

            String feetText = textFeet.getText().toString();
            String inchesText = textInches.getText().toString();
            String weightText = textWeight.getText().toString();
            //converting the number 'strings' into 'int' variables

            int feet = Integer.parseInt(feetText);
            int inches = Integer.parseInt(inchesText);
            int weight = Integer.parseInt(weightText);
            // converting 'feet' into 'inches'
            int totalInches = (feet * 12) + inches;
            //converting 'inches' into 'meters'
            double meters = totalInches * 0.0254;
            //Calculating total BMI

            return weight / (meters * meters);
    }

    void displayResult(double bmi) {
        //We must convert the decimal or double into a String fpr out text view

        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultString;

        if (bmi < 16) {
            fullResultString = bmiTextResult + " - You are Severe Underweight(16 and Below).";
        } else if (bmi >= 16 && bmi < 17) {
            fullResultString = bmiTextResult + " - You are Moderate Underweight(16 - 17).";
        } else if (bmi >= 17 && bmi < 18.5) {
            fullResultString = bmiTextResult + " - You are Mild Underweight(17 - 18.5).";
        } else if (bmi >= 18.5 && bmi < 25) {
            fullResultString = bmiTextResult + " - You are Normal Weight(18.5 - 25).";
        } else if (bmi >= 25 && bmi < 30) {
            fullResultString = bmiTextResult + " - You are Overweight(25 - 30).";
        } else if (bmi >= 30 && bmi < 35) {
            fullResultString = bmiTextResult + " - You are Obese Class 1 (30 - 35).";
        } else if (bmi >= 35 && bmi < 40) {
            fullResultString = bmiTextResult + " - You are Obese Class 2(35 - 40).";
        } else {
            fullResultString = bmiTextResult + " - You are Obese Class 3 (40 and Above).";
        }
        resultText.setText(fullResultString);
    }

    private void displayGuidance(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultString;

        if (radioMale.isChecked()) {
            //Display boy guidance
            fullResultString = bmiTextResult + " - As you are under 20, please contact your doctor to know the healthy range of BMI for boys.";
        } else if (radioFemale.isChecked()) {
            //Display Girl guidance
            fullResultString = bmiTextResult + " - As you are under 20, please contact your doctor to know the healthy range of BMI for girls.";
        } else {
            fullResultString = bmiTextResult + " - As you are under 20, please contact your doctor to know the healthy range of BMI.";

        }
        resultText.setText(fullResultString);
    }
}

