package com.example.mobile_labs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class manhinh extends AppCompatActivity {

    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh);

        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent manhinh1 = new Intent(manhinh.this, MainActivity.class);
                startActivity(manhinh1);

                overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_back);
            }
        });

    }
}