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

public class LabTestBookActivity extends AppCompatActivity {

    EditText efullname , econtact , epincode , eaddress ;
    Button ebook , eback;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab_test_book);

        efullname = findViewById(R.id.full_nameLTB) ;
        econtact = findViewById(R.id.contactLTB) ;
        eaddress = findViewById(R.id.addressLTB) ;
        epincode = findViewById(R.id.pincodeLTB) ;
        ebook = findViewById(R.id.bookLTB) ;
        eback = findViewById(R.id.backLTB) ;

        Intent intent = getIntent() ;
        String price = intent.getStringExtra("price") ;
        String date = intent.getStringExtra("date") ;
        String time = intent.getStringExtra("time") ;

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
                    Toast.makeText(LabTestBookActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    int pincode = Integer.parseInt(pincodeString);
                    float priceFloat = Float.parseFloat(priceString);

                    Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                    db.addorder(username, fullname, address, contact, pincode, date, time, priceFloat, "Lab");
                    db.removecart(username, "Lab");

                    Toast.makeText(LabTestBookActivity.this, "Your Booking is Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestBookActivity.this, HomeActivity.class));
                } catch (NumberFormatException e) {
                    Toast.makeText(LabTestBookActivity.this, "Invalid pincode or price format", Toast.LENGTH_SHORT).show();
                }
            }
        });


        eback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestBookActivity.this , CartLabActivity.class));
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}