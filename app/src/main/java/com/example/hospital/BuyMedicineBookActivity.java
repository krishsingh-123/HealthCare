package com.example.hospital;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BuyMedicineBookActivity extends AppCompatActivity {

    EditText efullname , econtact , epincode , eaddress ;
    Button ebook , eback;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buy_medicine_book);

        efullname = findViewById(R.id.fullnameBMB) ;
        econtact = findViewById(R.id.contactBMB) ;
        eaddress = findViewById(R.id.addressBMB) ;
        epincode = findViewById(R.id.pincodeBMB) ;
        ebook = findViewById(R.id.bookBMB) ;
        eback = findViewById(R.id.backBMB) ;

        Intent intent = getIntent() ;
        String price = intent.getStringExtra("price") ;
        String date = intent.getStringExtra("date") ;

        ebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("sharedprefer", MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "");

                String fullname = efullname.getText().toString();
                String address = eaddress.getText().toString();
                String contact = econtact.getText().toString();
                String pincodeString = epincode.getText().toString();
                String priceString = price;

                if (fullname.isEmpty() || address.isEmpty() || contact.isEmpty() || pincodeString.isEmpty()) {
                    Toast.makeText(BuyMedicineBookActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    int pincode = Integer.parseInt(pincodeString);
                    float priceFloat = Float.parseFloat(priceString);

                    Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                    db.addorder(username, fullname, address, contact, pincode, date, " ", priceFloat, "Medicine");
                    db.removecart(username, "Medicine");

                    Toast.makeText(BuyMedicineBookActivity.this, "Your Booking is Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicineBookActivity.this, HomeActivity.class));
                } catch (NumberFormatException e) {
                    Toast.makeText(BuyMedicineBookActivity.this, "Invalid pincode or price format", Toast.LENGTH_SHORT).show();
                }
            }
        });

        eback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineBookActivity.this , CartBuyMedicineActivity.class));
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}