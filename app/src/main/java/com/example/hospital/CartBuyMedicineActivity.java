package com.example.hospital;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CartBuyMedicineActivity extends AppCompatActivity {

    Button buttonback , buttoncheckout , buttontime , buttondate ;
    ListView listView ;
    ArrayList list ;
    SimpleAdapter sa ;
    HashMap<String , String> items ;
    TextView totalcost , package_name , location , date , time  ;
    private DatePickerDialog datePickerDialog ;
    private TimePickerDialog timePickerDialog ;
    private String[][] packages = {} ;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart_buy_medicine);

        date = findViewById(R.id.datetextCBM) ;
        buttondate = findViewById(R.id.buttondatepickerCBM) ;
        buttoncheckout = findViewById(R.id.buttoncheckoutCBM) ;
        buttonback = findViewById(R.id.buttonbackCBM) ;
        totalcost = findViewById(R.id.textViewcostCBM) ;
        listView = findViewById(R.id.listviewCBM) ;

        SharedPreferences sharedPreferences = getSharedPreferences("sharedprefer" , MODE_PRIVATE) ;
        String username = sharedPreferences.getString("username" , "").toString() ;

        Database db = new Database(getApplicationContext() , "healthcare" , null , 1) ;

        float totalamount = 0 ;
        ArrayList dbdata = db.getcartdata(username , "Medicine") ;
        Toast.makeText(getApplicationContext(), "" + dbdata , Toast.LENGTH_SHORT).show();

        packages = new String[dbdata.size()][] ;
        for (int i = 0; i < packages.length; i++) {
            packages[i] = new String[5] ;
        }

        for (int i = 0; i < dbdata.size(); i++) {
            String arrdata = dbdata.get(i).toString() ;
            String[] strdata = arrdata.split(java.util.regex.Pattern.quote("$")) ;
            packages[i][0] = strdata[0] ;
            packages[i][4] = "cost : " + strdata[1] + " /-" ;
            totalamount = totalamount + Float.parseFloat(strdata[1]) ;
        }
        totalcost.setText("Total Cost " + totalamount);

        list = new ArrayList() ;
        for (int i = 0; i < packages.length; i++) {
            items = new HashMap<String , String>() ;
            items.put("Line1" , packages[i][0]) ;
            items.put("Line2" , packages[i][1]) ;
            items.put("Line3" , packages[i][2]) ;
            items.put("Line4" , packages[i][3]) ;
            items.put("Line5" , packages[i][4]) ;
            list.add(items) ;
        }

        sa = new SimpleAdapter(this , list , R.layout.multi_lines , new String[]{"Line1","Line2","Line3","Line4","Line5"},
                new int[] {R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}) ;
        listView.setAdapter(sa);

        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartBuyMedicineActivity.this , BuyMedicineActivity.class ));
            }
        });

        buttoncheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartBuyMedicineActivity.this , BuyMedicineBookActivity.class) ;
                intent.putExtra("price" , totalcost.getText().toString().split(" ")[2]) ;
                intent.putExtra("date" , buttondate.getText().toString()) ;
                startActivity(intent);
            }
        });

        buttondate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initdatepicker() ;
                datePickerDialog.show();
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
                buttondate.setText(i2+"/"+i1+"/"+i);
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
}