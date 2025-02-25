package com.example.hospital;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FindDoctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_find_doctor);


        CardView back = findViewById(R.id.cardFdBack) ;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FindDoctorActivity.this, HomeActivity.class));
            }
        });

        CardView familyphysicians = findViewById(R.id.cardFdFamilyPhysician) ;
        familyphysicians.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindDoctorActivity.this , DoctorDetailsActivity.class) ;
                intent.putExtra("title" , "Family Physicians") ;
                startActivity(intent);
            }
        });

        CardView dietician = findViewById(R.id.cardFdDietician) ;
        dietician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindDoctorActivity.this , DoctorDetailsActivity.class) ;
                intent.putExtra("title" , "Dietician") ;
                startActivity(intent);
            }
        });

        CardView dentist = findViewById(R.id.cardFdDentist) ;
        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindDoctorActivity.this , DoctorDetailsActivity.class) ;
                intent.putExtra("title" , "Dentist") ;
                startActivity(intent);
            }
        });

        CardView cardiologists = findViewById(R.id.cardFdCardiologists) ;
        cardiologists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindDoctorActivity.this , DoctorDetailsActivity.class) ;
                intent.putExtra("title" , "Cardiologists") ;
                startActivity(intent);
            }
        });

        CardView surgeon = findViewById(R.id.cardFdSurgeon) ;
        surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindDoctorActivity.this , DoctorDetailsActivity.class) ;
                intent.putExtra("title" , "Surgeon") ;
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}