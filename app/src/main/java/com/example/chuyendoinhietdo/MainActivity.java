package com.example.chuyendoinhietdo;

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
    EditText edt1, edt2;
    Button btnC, btnF, btnclear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt1 = findViewById(R.id.edta);
        edt2 = findViewById(R.id.edtb);
        btnC = findViewById(R.id.btnC);
        btnF = findViewById(R.id.btnF);
        btnclear = findViewById(R.id.btnclear);

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float a = Float.parseFloat("0" + edt1.getText());
                double b = (a - 32) * 5/9;
                edt2.setText("" + b);
            }
        });

        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float a = Float.parseFloat("0" + edt2.getText());
                double b = a * 9/5 + 32;
                String result = String.format("%.4f",b);
                edt1.setText("" + result);
            }
        });

        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText("");
                edt2.setText("");
            }
        });
    }
}