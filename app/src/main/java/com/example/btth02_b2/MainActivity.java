package com.example.btth02_b2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView LvEms;
    EditText edtID, edtName, edtBirth, edtSalary;
    Button btnAdd;
    ArrayList<String> staffList;  // Danh sách để lưu tên và GPA
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        LvEms = findViewById(R.id.LvEms);
        edtID = findViewById(R.id.edtID);
        edtName = findViewById(R.id.edtName);
        edtBirth = findViewById(R.id.edtBirth);
        edtSalary = findViewById(R.id.edtSalary);
        btnAdd = findViewById(R.id.btnAdd);

        staffList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, staffList);
        LvEms.setAdapter(adapter);

        LvEms.setVisibility(View.GONE);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String staffId = edtID.getText().toString();
                String fullName = edtName.getText().toString();
                String birthDate = edtBirth.getText().toString();
                String salary = edtSalary.getText().toString();

                // Kiểm tra thông tin nhập
                if (staffId.isEmpty() || fullName.isEmpty() || birthDate.isEmpty() || salary.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill in all fields!", Toast.LENGTH_SHORT).show();
                } else {
                    // Thêm thông tin nhân viên vào danh sách
                    String staffInfo = staffId + "-" + fullName + "-" + birthDate + "-" + salary;
                    staffList.add(staffInfo);

                    // Cập nhật ListView và hiển thị danh sách
                    adapter.notifyDataSetChanged();
                    LvEms.setVisibility(View.VISIBLE);

                    // Xóa thông tin trong EditText sau khi thêm
                    edtID.setText("");
                    edtName.setText("");
                    edtBirth.setText("");
                    edtSalary.setText("");
                }
            }
        });
    }
}