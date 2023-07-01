package com.eliftekin.todolistapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button addbutton;

    Button SaveButton;
    EditText TaskTitle;
    EditText PickedDate;
    ImageView DatePick;

    Context context = this;

    private RecyclerView recyclerView;
    private rvAdapter adapter;

    private ArrayList<data> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.grey));

        setContentView(R.layout.activity_main);

        addbutton = findViewById(R.id.addbutton);
        addbutton.setOnClickListener(new View.OnClickListener() { //artı butonuna basıldığında showDialog fonksiyonunu çalıştırır
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new rvAdapter(dataList);
        recyclerView.setAdapter(adapter);

    }

    private void showDialog() {
        final Dialog dialog = new Dialog(this); //dialog nesnesi tanımlar
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //dialog penceresinin başlığını gizler
        dialog.setContentView(R.layout.bottom_sheet_dialog); //dialog penceresinin içeriğini yükler

        //xml dosyasındaki içindeki nesneler tanımlanır
        SaveButton = dialog.findViewById(R.id.SaveButton);
        TaskTitle = dialog.findViewById(R.id.TaskTitle);
        PickedDate = dialog.findViewById(R.id.PickedDate);
        DatePick = dialog.findViewById(R.id.DatePick);

        DatePick.setOnClickListener(new View.OnClickListener() { //takvim ikonuna basıldığında fonksiyonu çalıştırır.
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taskTitle = TaskTitle.getText().toString();
                String dueDate = PickedDate.getText().toString();

                //herhangi bir alanın boş bırakılması durumunda uyarı mesajı verir
                if ((taskTitle.isEmpty() && dueDate.isEmpty()) || (taskTitle.isEmpty()) || (dueDate.isEmpty()))
                {
                    Toast.makeText(context, "Lütfen alanları boş bırakmayın", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    data newData = new data(taskTitle, dueDate);
                    dataList.add(newData);
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
            }
        });

        dialog.show(); //pencereyi görüntüler
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT); //pencerenin genişliğini yüksekliğini ayarlar
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //pencerenin arka plan rengini şeffaf yapar
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //pencerenin animasyonunu themes dosyasından alır
        dialog.getWindow().setGravity(Gravity.BOTTOM); //pencerenin yerini ayarlar
    }

    private void showDatePickerDialog() {
        //bugünün tarihini alır
        Calendar today = Calendar.getInstance();
        int day = today.get(Calendar.DAY_OF_MONTH);
        int month = today.get(Calendar.MONTH);
        int year = today.get(Calendar.YEAR);

        //DatePickerDialog nesnesi oluşturur. Parametreleri (dialoğun kullanılacağı bağlam, tarih seçiminde tetiklenecek olaylar, başlangıç tarihleri)
        DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) { //parametreler (kullanıcının tarih seçimini gerçekleştirdiği görünüm, seçilen yıl, ay ve gün)
                PickedDate.setText(day+"/"+(month+1)+"/"+year); //PickedDate nesnesine seçilen tarihi yazdırır
            }
        }, year, month, day);
        dpd.show(); //dialoğu gösterir
    }
}