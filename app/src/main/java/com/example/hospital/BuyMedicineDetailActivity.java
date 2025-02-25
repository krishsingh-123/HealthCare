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

public class BuyMedicineDetailActivity extends AppCompatActivity {

    Button buybuttoncart , buybuttonback ;
    EditText buymultiline ;
    TextView buytotalcost , buypackage_name , delivery ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buy_medicine_detail);

        buybuttonback = findViewById(R.id.buttonbackBMD) ;
        buybuttoncart = findViewById(R.id.buttoncartBMD) ;
        buymultiline = findViewById(R.id.editTextMultiLineBMD) ;
        buytotalcost = findViewById(R.id.textViewcostBMD) ;
        buypackage_name = findViewById(R.id.textViewpackageBMD) ;

        buymultiline.setKeyListener(null);

        Intent intent = getIntent() ;
        buypackage_name.setText(intent.getStringExtra("text1")) ;
        buymultiline.setText(intent.getStringExtra("text2"));
        buytotalcost.setText("Total Rs. " + intent.getStringExtra("text3")) ;

        buybuttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineDetailActivity.this , BuyMedicineActivity.class));
            }
        });

        buybuttoncart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = getSharedPreferences("sharedprefer" , MODE_PRIVATE) ;
                String username = sharedPreferences.getString("username" , "").toString() ;
                String product = buypackage_name.getText().toString() ;
                String priceText = buytotalcost.getText().toString().replace("Total Rs. ", "");
                float price = Float.parseFloat(priceText);

                Database db = new Database(getApplicationContext() , "healthcare" , null  , 1) ;
                if (db.checkcart(username , product)==1){
                    Toast.makeText(getApplicationContext() , "Product Already Added" , Toast.LENGTH_SHORT).show();
                }
                else{
                    db.addcart(username , product , price , "Medicine");
                    Toast.makeText(getApplicationContext(), "Product Added Successfully ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicineDetailActivity.this , BuyMedicineActivity.class));
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