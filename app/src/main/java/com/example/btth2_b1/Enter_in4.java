package com.example.btth2_b1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Enter_in4 extends AppCompatActivity {

    EditText edtName2, edtGPA2;
    Button btnSubmit;
    ImageButton imgbtnBack;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_in4);
        edtName2 = findViewById(R.id.edtName2);
        edtGPA2 = findViewById(R.id.edtGPA2);
        btnSubmit = findViewById(R.id.btnSubmit);
        imgbtnBack = findViewById(R.id.imgbtnBack);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu người dùng nhập
                String name = edtName2.getText().toString();
                String gpa = edtGPA2.getText().toString();

                if (name.isEmpty() || gpa.isEmpty()) {
                    Toast.makeText(Enter_in4.this, "Please enter both name and GPA!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Tạo Intent để gửi dữ liệu lại MainActivity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("name", name);
                resultIntent.putExtra("gpa", gpa);

                // Set kết quả và kết thúc activity
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
        imgbtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Enter_in4.this, MainActivity.class);
                startActivity(intent2);
            }
        });
    }
}