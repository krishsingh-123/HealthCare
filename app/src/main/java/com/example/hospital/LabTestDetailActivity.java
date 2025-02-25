package com.example.hospital;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LabTestDetailActivity extends AppCompatActivity {

    Button buttoncart , buttonback ;
    EditText multiline ;
    TextView totalcost , package_name , delivery ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab_test_detail);

        buttoncart = findViewById(R.id.buttoncartLTD) ;
        buttonback = findViewById(R.id.buttonbackLTD) ;
        multiline = findViewById(R.id.editTextMultiLineLTD) ;
        totalcost = findViewById(R.id.textViewcostLTD) ;
        package_name = findViewById(R.id.textViewpackageLTD) ;
        delivery = findViewById(R.id.titleLabTestDteail) ;

        multiline.setKeyListener(null);

        Intent intent = getIntent() ;
        package_name.setText(intent.getStringExtra("text1")) ;
        multiline.setText(intent.getStringExtra("text2"));
        totalcost.setText("Checkup charges Rs. " + intent.getStringExtra("text3")) ;


        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestDetailActivity.this , LabTestActivity.class));
            }
        });

        buttoncart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = getSharedPreferences("sharedprefer" , MODE_PRIVATE) ;
                String username = sharedPreferences.getString("username" , "").toString() ;
                String product = package_name.getText().toString() ;
                String priceText = totalcost.getText().toString().replace("Checkup charges Rs. ", "");
                float price = Float.parseFloat(priceText);

                Database db = new Database(getApplicationContext() , "healthcare" , null  , 1) ;
                if (db.checkcart(username , product)==1){
                    Toast.makeText(getApplicationContext() , "Product Aready Added" , Toast.LENGTH_SHORT).show();
                }
                else{
                    db.addcart(username , product , price , "Lab");
                    Toast.makeText(getApplicationContext(), "Product Added Successfully ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestDetailActivity.this , LabTestActivity.class));
                }
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}