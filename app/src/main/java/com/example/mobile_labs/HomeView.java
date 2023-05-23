package com.example.mobile_labs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_view);
        Intent intent = getIntent();
        String fullname = intent.getStringExtra("full name");
        TextView txtName = findViewById(R.id.txtFullname);
        txtName.setText(fullname);
        Button btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent login = new Intent(view.getContext(), MainActivity.class);
                startActivity(login);
                finish();
            }
        });
    }
}