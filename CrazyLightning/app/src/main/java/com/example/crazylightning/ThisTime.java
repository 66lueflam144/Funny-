package com.example.crazylightning;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

public class ThisTime extends AppCompatActivity {
    private ViewFlipper v_01;
    private Button b1, b2;

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_to);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);


        v_01 = (ViewFlipper) findViewById(R.id.view_01);

        b1.setOnClickListener(v->{
            v_01.showNext();
        });
        b2.setOnClickListener(v->{
            v_01.showPrevious();
        });
    }
}
