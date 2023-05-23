package com.example.mobile_labs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MainActivity extends AppCompatActivity {
    public static String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            TextView textView = findViewById(R.id.txtRegister);
            Button btnLogin = findViewById(R.id.btnLogin);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), register.class);
                startActivity(intent);

                }
            });

            btnLogin.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    EditText username = findViewById(R.id.editUsername);
                    EditText password =  findViewById(R.id.editPassword);
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    Intent intent = new Intent(view.getContext(), HomeView.class);
                    db.collection("users")
                            .whereEqualTo("Password", encryptPassword(password.getText().toString()))
                            .whereEqualTo("Username", username.getText().toString())
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                                        QuerySnapshot querySnapshot = task.getResult();
                                        if (querySnapshot != null && !querySnapshot.isEmpty()) {
                                            List<DocumentSnapshot> documents = querySnapshot.getDocuments();
                                            String fullname = documents.get(0).getString("FullName");
                                            intent.putExtra("full name", fullname);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_LONG).show();
                                            Log.d("tag", "Không tìm thấy dữ liệu.");
                                        }
                                    } else {
                                        Log.d("tag", "Lỗi khi truy vấn dữ liệu: ", task.getException());
                                    }
                                }
                            });

                }


            });


        }

}