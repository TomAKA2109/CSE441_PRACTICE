package com.example.dialog;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.content.DialogInterface;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    EditText edt1, edt2, edtbosung;
    RadioGroup idgroup;
    CheckBox cba, cbb, cbc;
    Button btnGui;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt1 = findViewById(R.id.edta);
        edt2 = findViewById(R.id.edtb);
        edtbosung = findViewById(R.id.edtbosung);
        idgroup = findViewById(R.id.idgroup);
        cba = findViewById(R.id.cba);
        cbb = findViewById(R.id.cbb);
        cbc = findViewById(R.id.cbc);
        btnGui = findViewById(R.id.btnGui);

        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //thong tin ho ten
                String hoten = edt1.getText().toString();
                if(hoten.length() < 3)
                {
                    Toast.makeText(MainActivity.this,"Họ tên phải nhiều hơn 3 ký tự", Toast.LENGTH_LONG).show();
                    edt1.requestFocus();
                    edt1.selectAll();
                    return;
                }
                //CMND
                String cmnd = edt2.getText().toString();
                if(cmnd.length() != 12)
                {
                    Toast.makeText(MainActivity.this,"CMND phải đủ 12 số", Toast.LENGTH_LONG).show();
                    edt2.requestFocus();
                    edt2.selectAll();
                    return;
                }
                //thong tin bang cap
                int idselect = idgroup.getCheckedRadioButtonId();
                RadioButton radselect = findViewById(idselect);
                String bangcap = radselect.getText().toString();
                //thong tin so thich
                String sothich = "";
                if(cba.isChecked())
                {
                    sothich += cba.getText().toString() + "-";
                }
                if(cbb.isChecked())
                {
                    sothich += cbb.getText().toString();
                }
                if(cbc.isChecked())
                {
                    sothich += "-" + cbc.getText().toString();
                }
                //thong tin bo sung
                String bosung = edtbosung.getText().toString();
                String tonghop = hoten + "\n" + cmnd + "\n" + bangcap + "\n" + sothich + "\n";
                        tonghop += "------------THÔNG TIN BỔ SUNG------------\n";
                        tonghop += bosung + "\n";
                        tonghop += "------------------------------------------------------------";
                //Tao bang dialog va hien thi thong tin
                AlertDialog.Builder mydialog = new AlertDialog.Builder(MainActivity.this);
                mydialog.setTitle("Thông tin cá nhân");
                mydialog.setMessage(tonghop);
                mydialog.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                mydialog.create().show();
            }
        });
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        //Tao dialog
        AlertDialog.Builder mydialog = new AlertDialog.Builder(MainActivity.this);
        mydialog.setTitle("Question");
        mydialog.setMessage("Are you sure you want to exit");
        mydialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish(); //thoat ung dung
            }
        });
        mydialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        mydialog.create().show();
    }
}