package com.example.bmi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edt1, edt2, edt3, edt4, edt5;
    Button btnTinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt1 = findViewById(R.id.edta);
        edt2 = findViewById(R.id.edtb);
        edt3 = findViewById(R.id.edtc);
        edt4 = findViewById(R.id.edtd);
        edt5 = findViewById(R.id.edte);
        btnTinh = findViewById(R.id.btnTinh);

        btnTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double H = Double.parseDouble("0" + edt2.getText());
                double W = Double.parseDouble("0" + edt3.getText());
                double BMI = W/Math.pow(H,2);
                String chandoan = "";
                if(BMI < 18)
                {
                    chandoan = "người gầy";
                }
                else if (BMI <= 24.9)
                {
                    chandoan = "người bình thường";
                }
                else if (BMI <= 29.9)
                {
                    chandoan = "người béo phì độ 1";
                }
                else if (BMI <= 34.9)
                {
                    chandoan = "người béo phì độ 2";
                }
                else
                {
                    chandoan = "người béo phì độ 3";
                }
                String result = String.format("%.4f", BMI);
                edt4.setText("" + result);
                edt5.setText("" + chandoan);
            }
        });
    }
}