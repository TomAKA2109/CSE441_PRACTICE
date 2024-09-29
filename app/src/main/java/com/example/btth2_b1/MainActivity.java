package com.example.btth2_b1;

import static com.example.btth2_b1.R.layout.activity_main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ListView Lv;
    Button btnStart;
    ArrayList<String> studentList;  // Danh sách để lưu tên và GPA
    ArrayAdapter<String> adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);

        btnStart = findViewById(R.id.btnStart);
        Lv = findViewById(R.id.LvStu);

        studentList = new ArrayList<>();

        // Tạo adapter để kết nối ListView với dữ liệu trong ArrayList
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentList);
        Lv.setAdapter(adapter);

        // Ẩn ListView ban đầu
        Lv.setVisibility(View.GONE);

        // Tạo ActivityResultLauncher để nhận dữ liệu từ Enter_in4
        ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        // Nhận dữ liệu từ Intent
                        Intent data = result.getData();
                        String name = data.getStringExtra("name");
                        String gpa = data.getStringExtra("gpa");

                        // Thêm dữ liệu vào danh sách và cập nhật ListView
                        studentList.add("Name: " + name + ", GPA: " + gpa);
                        adapter.notifyDataSetChanged();

                        // Hiển thị ListView
                        Lv.setVisibility(View.VISIBLE);
                    }
                });

        // Bắt sự kiện khi nhấn nút "START NEW ACTIVITY"
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Enter_in4.class);
                resultLauncher.launch(intent);
            }
        });
    }
}