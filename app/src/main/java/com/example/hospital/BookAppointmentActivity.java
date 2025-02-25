package com.example.hospital;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {

    EditText ed1 , ed2 , ed3 , ed4 ;
    TextView date , time , apptitle ;
    Button buttonback , buttonbook , datepick , timepick ;
    private DatePickerDialog datePickerDialog ;
    private TimePickerDialog timePickerDialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_appointment);

        ed1 = findViewById(R.id.fullname) ;
        ed2 = findViewById(R.id.address) ;
        ed3 = findViewById(R.id.contact) ;
        ed4 = findViewById(R.id.fees) ;
        apptitle = findViewById(R.id.appointmenttitle) ;
        buttonback = findViewById(R.id.back) ;
        buttonbook = findViewById(R.id.appoint) ;
        datepick = findViewById(R.id.buttondateselector) ;
        timepick = findViewById(R.id.buttontimeselector) ;

        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent intent = getIntent() ;
        String title = intent.getStringExtra("text1") ;
        String fullname = intent.getStringExtra("text2") ;
        String address = intent.getStringExtra("text3") ;
        String contact = intent.getStringExtra("text4") ;
        String fees = intent.getStringExtra("text5") ;

        ed1.setText(fullname);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText("Cons.fees Rs. " + fees + " /-");
        apptitle.setText(title) ;

        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookAppointmentActivity.this , DoctorDetailsActivity.class));
            }
        });

        buttonbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("sharedprefer" , MODE_PRIVATE) ;
                String username = sharedPreferences.getString("username" , "").toString() ;
                Database db = new Database(getApplicationContext() , "healthcare" , null , 1) ;
                if (db.checkappointmentexist(username,title+" => "+fullname,address,contact,datepick.getText().toString(),timepick.getText().toString())==1){
                    Toast.makeText(getApplicationContext(), "Appointment already booked", Toast.LENGTH_SHORT).show();
                }
                else {
                    db.addorder(username,title+" => "+fullname,address,contact,0,datepick.getText().toString(),timepick.getText().toString(), Float.parseFloat(fees),"appointment");
                    Toast.makeText(getApplicationContext(), "Your Application is done successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BookAppointmentActivity.this, HomeActivity.class));
                }
            }
        });
        initdatepicker() ;
        datepick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });


        timepick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inittimepicker() ;
                timePickerDialog.show();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initdatepicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                   i1 = i1+1 ;
                   datepick.setText(i2+"/"+i1+"/"+i);
            }
        };
        Calendar calendar = Calendar.getInstance() ;
        int year = calendar.get(Calendar.YEAR) ;
        int month = calendar.get(Calendar.MONTH) ;
        int day = calendar.get(Calendar.DAY_OF_MONTH) ;
        int style = AlertDialog.THEME_HOLO_DARK ;
        datePickerDialog = new DatePickerDialog(this , style , dateSetListener , year , month , day) ;
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis()+86400000);
    }

    private void inittimepicker() {
       TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
           @Override
           public void onTimeSet(TimePicker timePicker, int hour, int min) {
               String formattedtime = String.format("%02d:%02d" , hour , min) ;
               timepick.setText(formattedtime);
           }
       };
        Calendar calendar = Calendar.getInstance() ;
        int hour = calendar.get(Calendar.HOUR_OF_DAY) ;
        int mins = calendar.get(Calendar.MINUTE) ;
        
        int style = AlertDialog.THEME_HOLO_DARK ;
        timePickerDialog = new TimePickerDialog(this , style, timeSetListener , hour , mins , true) ;
    }
}