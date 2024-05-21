package com.example.quanlysv;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
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
    EditText edtMalop, edtTenlop, edtSiso;
    Button btnAdd, btnDel, btnUpdate;
    ListView lv;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;
    SQLiteDatabase mydatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtMalop = findViewById(R.id.edtMalop);
        edtTenlop = findViewById(R.id.edtTenlop);
        edtSiso = findViewById(R.id.edtSiso);
        btnAdd = findViewById(R.id.btnAdd);
        btnDel = findViewById(R.id.btnDel);
        btnUpdate = findViewById(R.id.btnUpdate);
        //tao listview
        lv = findViewById(R.id.lv);
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,mylist);
        //tao va mo CSDL
        mydatabase = openOrCreateDatabase("qlsinhvien.db",MODE_PRIVATE,null);
        //tao table de chua du lieu
        try {
            String sql = "Create table tblop(malop varchar(20) primary key, tenlop varchar(20), siso int)";
            mydatabase.execSQL(sql);
        }
        catch (Exception e)
        {
            Log.e("Error", "Table đã tồn tại");
        }
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String malop = edtMalop.getText().toString();
                String tenlop = edtTenlop.getText().toString();
                int siso = Integer.parseInt(edtSiso.getText().toString());
                ContentValues myvalue = new ContentValues();
                myvalue.put("malop", malop);
                myvalue.put("tenlop", tenlop);
                myvalue.put("siso", siso);
                String msg = "";
                if(mydatabase.insert("tblop", null, myvalue) == -1)
                {
                    msg = "Không thể thêm bản ghi";
                }
                else {
                    msg = "Thêm bản ghi thành công";
                    hienthidanhsach();
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                hienthidanhsach();
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String malop = edtMalop.getText().toString();
                int n = mydatabase.delete("tblop", "malop = ?", new String[]{malop});
                String msg = "";
                if(n == 0)
                {
                    msg = "Không có bản ghi để xóa";
                }
                else {
                    msg = "Xóa thành công";
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int siso = Integer.parseInt(edtSiso.getText().toString());
                String malop = edtMalop.getText().toString();
                ContentValues myvalue = new ContentValues();
                myvalue.put("siso", siso);
                int n = mydatabase.update("tblop", myvalue, "malop = ?", new String[]{malop});
                String msg = "";
                if(n == 0)
                {
                    msg = "Không có bản ghi nào được cập nhật";
                }
                else {
                    msg = n + "bản ghi được cập nhật";
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void hienthidanhsach() {
        mylist.clear();
        Cursor c = mydatabase.query("tblop", null, null, null, null, null, null);
        c.moveToNext();
        String data = "";
        while (c.isAfterLast() == false)
        {
            data = c.getString(0)+" - "+c.getString(1)+" - "+c.getString(2);
            c.moveToNext();
            mylist.add(data);
        }
        c.close();
        myadapter.notifyDataSetChanged();
    }
}