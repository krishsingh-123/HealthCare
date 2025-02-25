package com.example.hospital;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {

    private String[][] doctor_details1 =
            {
               {  "Doctor_Name: Ajit Saste","Hospital_Address: Pipra", "Exp: 5yrs", "Mobile_Phone:9572186852", "1000" } ,
               {  "Doctor_Name: Prasad Pawar","Hospital_Address: Nighadi", "Exp: 15yrs", "Mobile_Phone:9572956852", "900" } ,
               {  "Doctor_Name: Swapnil Tare","Hospital_Address: Pune", "Exp: 8yrs", "Mobile_Phone:7972186852", "300" } ,
               {  "Doctor_Name: Deepak Deshmukh","Hospital_Address: Chhichhad", "Exp: 6yrs", "Mobile_Phone:9537186852", "800" } ,
               {  "Doctor_Name: Ashok Panda","Hospital_Address: Pipra", "Exp: 7yrs", "Mobile_Phone:9572186486", "600" } ,
            } ;
    private String[][] doctor_details2 =
            {
                    {  "Doctor_Name: Neelam Mahto","Hospital_Address: Pipra", "Exp: 5yrs", "Mobile_Phone:9572186852", "300" } ,
                    {  "Doctor_Name: Rahul Kumar","Hospital_Address: Nighadi", "Exp: 15yrs", "Mobile_Phone:9572956852", "500" } ,
                    {  "Doctor_Name: Sumarl Kale","Hospital_Address: Pune", "Exp: 8yrs", "Mobile_Phone:7972186852", "400" } ,
                    {  "Doctor_Name: Kishor Kumra","Hospital_Address: Chhichhad", "Exp: 6yrs", "Mobile_Phone:9537186852", "700" } ,
                    {  "Doctor_Name: Shivam Tripathi","Hospital_Address: Pipra", "Exp: 7yrs", "Mobile_Phone:9572186486", "200" } ,
            } ;
    private String[][] doctor_details3 =
            {
                    {  "Doctor_Name: Munna Tripathi","Hospital_Address: Pipra", "Exp: 5yrs", "Mobile_Phone:9572186852", "1000" } ,
                    {  "Doctor_Name: Guddu Pandit","Hospital_Address: Nighadi", "Exp: 15yrs", "Mobile_Phone:9572956852", "900" } ,
                    {  "Doctor_Name: Bablu Pandit","Hospital_Address: Pune", "Exp: 8yrs", "Mobile_Phone:7972186852", "300" } ,
                    {  "Doctor_Name: Richa Ghosh","Hospital_Address: Chhichhad", "Exp: 6yrs", "Mobile_Phone:9537186852", "800" } ,
                    {  "Doctor_Name: Shikhar Dhawan","Hospital_Address: Pipra", "Exp: 7yrs", "Mobile_Phone:9572186486", "600" } ,
            } ;
    private String[][] doctor_details4 =
            {
                    {  "Doctor_Name: Dhananjay Rajput","Hospital_Address: Pipra", "Exp: 5yrs", "Mobile_Phone:9572186852", "1000" } ,
                    {  "Doctor_Name: Kabir Singh","Hospital_Address: Nighadi", "Exp: 15yrs", "Mobile_Phone:9572956852", "900" } ,
                    {  "Doctor_Name: Arjun Reddy","Hospital_Address: Pune", "Exp: 8yrs", "Mobile_Phone:7972186852", "300" } ,
                    {  "Doctor_Name: Rohit Sharma","Hospital_Address: Chhichhad", "Exp: 6yrs", "Mobile_Phone:9537186852", "800" } ,
                    {  "Doctor_Name: Kunal Pandya","Hospital_Address: Pipra", "Exp: 7yrs", "Mobile_Phone:9572186486", "1000" }
            } ;
    private String[][] doctor_details5 =
            {
                    {  "Doctor_Name: Krish Kumar","Hospital_Address: Kolkat","Exp: 15yrs", "Mobile_Phone:9572186852", "1500" } ,
                    {  "Doctor_Name: Akash Raj","Hospital_Address: Nighadi", "Exp: 15yrs", "Mobile_Phone:9572956852", "1900" } ,
                    {  "Doctor_Name: Shivam Kumar","Hospital_Address: Pune", "Exp: 8yrs", "Mobile_Phone:7972186852", "1300" } ,
                    {  "Doctor_Name: Harish Suman","Hospital_Address: Chhichhad", "Exp: 6yrs", "Mobile_Phone:9537186852", "1100" } ,
                    {  "Doctor_Name: Kunal Kumar","Hospital_Address: Pipra", "Exp: 7yrs", "Mobile_Phone:9572186486", "1600" } ,
            } ;
    TextView titletext ;
    ListView listView ;
    Button button ;
    String[][] doctor_details = {};
    ArrayList list ;
    HashMap<String , String> items ;
    SimpleAdapter sa ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doctor_details);

        titletext = findViewById(R.id.titleDoctorDetail) ;
        button =findViewById(R.id.buttonDD) ;

        Intent intent = getIntent() ;
        String title = intent.getStringExtra("title") ;
        titletext.setText(title);

        if (title.compareTo("Family Physicians")==0){
            doctor_details = doctor_details1 ;
        }
        else if(title.compareTo("Dietician")==0){
            doctor_details = doctor_details2 ;
        }
        else if(title.compareTo("Dentist")==0){
            doctor_details = doctor_details3 ;
        }
        else if(title.compareTo("Surgeon")==0){
            doctor_details = doctor_details4 ;
        }
        else{
            doctor_details = doctor_details5 ;
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this , FindDoctorActivity.class));
            }
        });

        list = new ArrayList<>() ;
        for (int i = 0; i < doctor_details.length; i++) {
            items = new HashMap<String , String>() ;
            items.put("Line1", doctor_details[i][0]) ;
            items.put("Line2", doctor_details[i][1]) ;
            items.put("Line3", doctor_details[i][2]) ;
            items.put("Line4", doctor_details[i][3]) ;
            items.put("Line5", "Cons fees "+doctor_details[i][4]+"/-") ;
            list.add(items) ;

        }

        sa = new SimpleAdapter(this , list , R.layout.multi_lines, new String[]{"Line1", "Line2", "Line3", "Line4", "Line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}) ;

        listView = findViewById(R.id.listviewDD) ;
        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DoctorDetailsActivity.this , BookAppointmentActivity.class) ;
                intent.putExtra("text1", title) ;
                intent.putExtra("text2", doctor_details[i][0]) ;
                intent.putExtra("text3", doctor_details[i][1]) ;
                intent.putExtra("text4", doctor_details[i][3]) ;
                intent.putExtra("text5", doctor_details[i][4]) ;
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
