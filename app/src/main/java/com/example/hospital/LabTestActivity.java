package com.example.hospital;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTestActivity extends AppCompatActivity {

    private String[][] packages =
      {
          {"Packages 1 : full body checkup" , "" , "" , "" , "5000"} ,
          {"Packages 2 : Blood glucose checkup" , "" , "" , "" , "1400"} ,
          {"Packages 3 : COVID-19 checkup" , "" , "" , "" , "1300"} ,
          {"Packages 4 : Thyroid check" , "" , "" , "" , "1500"} ,
          {"Packages 5 : Immunity check" , "" , "" , "" , "1900"}
      } ;
    private String[] package_details = {
           "blood glucose fasting\n" + "complete hemogram\n" + "HBA1C\n" + "Iron studies\n" + "kidney finction test\n" + "LDH lactate Dehydrogenase, Serum \n" + "Lipid profile\n" + "Liver function test\n" , "blood glucose fasting\n" + "oral glucose tolerence test\n" + "random blood glucose\n" + "fructosamine test\n" + "insulin level test\n" + "postprandial glucose test\n" , "Covid-19 Antibody - IgG\n" + "rapid antigen test\n" + "reapid moleculer test\n" + "RT-PCR test\n" +"covid-19 vaccine antibody test\n" + "covid-19 sequential test\n" + "oxygen saturation (pulse oximetry)\n" , "thyroid profile total:-\n" + "TSH(thyroid stimulating hormone)\n" + "thyroid antibodies test\n" + "thyroglubin test\n" + "thyroid ultrasound test \n" + "thyroid scan\n" , "complete herogram\n" + "iron studies\n" + "lipid profile\n" + "Kidney function test\n" + "Liver function test\n" + "vitamin D total-25 hydorxy\n" , "CRP(C reactive protein) quantitative, Serum\n" } ;

    Button buttonback , buttoncart ;
    ListView listView ;
    ArrayList list ;
    HashMap<String , String> items ;
    SimpleAdapter sa ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab_test);

        buttoncart = findViewById(R.id.buttoncartLT) ;
        listView = findViewById(R.id.listviewLT) ;
        buttonback = findViewById(R.id.buttonbackLT) ;

        list = new ArrayList<>() ;
        for (int i = 0; i < packages.length; i++) {
            items = new HashMap<String , String>() ;
            items.put("Line1", packages[i][0]) ;
            items.put("Line2", packages[i][1]) ;
            items.put("Line3", packages[i][2]) ;
            items.put("Line4", packages[i][3]) ;
            items.put("Line5", "Total cost : "+packages[i][4]+"/-") ;
            list.add(items) ;

        }

        sa = new SimpleAdapter(this , list , R.layout.multi_lines, new String[]{"Line1", "Line2", "Line3", "Line4", "Line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}) ;

        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(LabTestActivity.this , LabTestDetailActivity.class) ;
                intent.putExtra("text1", packages[i][0]) ;
                intent.putExtra("text2", package_details[i]) ;
                intent.putExtra("text3", packages[i][4]) ;
                startActivity(intent);
            }
        });

        buttoncart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity.this , CartLabActivity.class));
            }
        });

        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity.this , HomeActivity.class));
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}