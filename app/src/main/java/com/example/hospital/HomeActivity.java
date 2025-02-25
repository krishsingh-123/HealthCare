package com.example.hospital;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        CardView exit = findViewById(R.id.cardExit) ;
        CardView FindDoctor = findViewById(R.id.cardFindDoctor) ;
        CardView Labtest = findViewById(R.id.cardLabTest) ;
        CardView orderdetails = findViewById(R.id.cardOrderdetails) ;
        CardView buymedicine = findViewById(R.id.cardBuyMedicine) ;
        CardView articles = findViewById(R.id.cardHealthDoctor) ;

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefer" , MODE_PRIVATE);
        String username = sharedPreferences.getString("username" , "").toString() ;
        Toast.makeText(getApplicationContext(), "Welcome" + username , Toast.LENGTH_SHORT).show();

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear() ;
                editor.apply();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            }
        });

        FindDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, FindDoctorActivity.class));

            }
        });

        Labtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, LabTestActivity.class));

            }
        });

        orderdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, OrderDetailsActivity.class));

            }
        });

        buymedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, BuyMedicineActivity.class));

            }
        });

        articles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, HealthArticlesActivity.class));

            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}