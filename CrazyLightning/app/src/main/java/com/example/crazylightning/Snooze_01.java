package com.example.crazylightning;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Scanner;

public class Snooze_01 extends AppCompatActivity {

    private Button calendar, clear;
    private EditText birthday;
    private TextView retire_time, title, hint_, sign;
    private String bbb;

    @Override
    public void onCreate(Bundle bundle) {

        super.onCreate(bundle);

        setContentView(R.layout.do_you_want_to_retire);

        title = findViewById(R.id.show_title);
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
        title.setTextColor(Color.parseColor("#2E8B57"));

        birthday = findViewById(R.id.birthday);

        birthday.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    bbb = birthday.getText().toString();
                    calculate(bbb);
                    return true;
                }
                return false;
            }
        });


        calendar = findViewById(R.id.calendar);
        calendar.setOnClickListener(v->{
            seeCalender();
        });

        hint_ = findViewById(R.id.hint_);
        hint_.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);

        retire_time = findViewById(R.id.retire_time);
        retire_time.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        clear = findViewById(R.id.clear_button);
        clear.setOnClickListener(v-> {
            birthday.setText(" ");

        });

        sign = findViewById(R.id.si);


    }

    // 补全月份和日期格式
    private static String formatDate(String dateStr) {
        String[] parts = dateStr.split("-");
        if (parts[1].length() == 1) {
            parts[1] = "0" + parts[1];
        }
        if (parts[2].length() == 1) {
            parts[2] = "0" + parts[2];
        }
        return parts[0] + "-" + parts[1] + "-" + parts[2];
    }

    // 验证日期是否合法
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static boolean isValidDate(String dateStr) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-M-d");

        try {
            LocalDate date = LocalDate.parse(dateStr, formatter);
            String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return formatDate(dateStr).equals(formattedDate);
        } catch (DateTimeParseException e) {
            return false;
        }
    }




    public void calculate(String bbb){

        boolean r = false;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            r = isValidDate(bbb);
        }

        if (r) {
            String[] numbers = bbb.split("-");
            int year = Integer.parseInt(numbers[0]);
            int month = Integer.parseInt(numbers[1]);
            int day = Integer.parseInt(numbers[2]);



            int retired_year = year + 55;

            String retired_result = retired_year + "-" + month + "-" + day;
            String rr = formatDate(retired_result);
            retire_time.setText(rr);

        }
        else {
            retire_time.setText("WRONG DATE!!!");
        }



    }

    public void seeCalender() {

        final Calendar calendar1 = Calendar.getInstance();
        int year = calendar1.get(Calendar.YEAR);
        int month = calendar1.get(Calendar.MONTH);
        int day = calendar1.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                String selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
                calculate(selectedDate);
            }
        }, year, month, day);
        datePickerDialog.show();

    }
}
