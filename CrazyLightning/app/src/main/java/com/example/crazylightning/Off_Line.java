package com.example.crazylightning;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Off_Line extends AppCompatActivity {

    private ImageButton a1, a2, a3, a4, a5, a6;
    private TextView show_tmi;
    private Button click_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_tmi);

        a1 = findViewById(R.id.a1);
        a2 = findViewById(R.id.a2);
        a3 = findViewById(R.id.a3);
        a4 = findViewById(R.id.a4);
        a5 = findViewById(R.id.a5);
        a6 = findViewById(R.id.a6);

        show_tmi = findViewById(R.id.show_tmi);
        click_b = findViewById(R.id.click_me);

//        a1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                show_tmi.setText("Name: Alice \nAge: 19 \nFemale");
//            }
//        });
        //建议的lambda写法
        a1.setOnClickListener(v->{
            show_tmi.setText("Name: Alice \nAge: 19 \nFemale");
        });

        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_tmi.setText("Name: Dean \nAge: 20 \nMale");
            }
        });

        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_tmi.setText("Name: Tmi \nAge: 19 \nFemale");
            }
        });

        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_tmi.setText("Name: Meow \nAge: 19 \nFemale");
            }
        });

        a5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_tmi.setText("Name: Alex \nAge: 18 \nMale");
            }
        });

        a6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_tmi.setText("Name: Jun \nAge: 19 \nMale");
            }
        });

        click_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_tmi.setText("hello");
            }
        });


    }


}
