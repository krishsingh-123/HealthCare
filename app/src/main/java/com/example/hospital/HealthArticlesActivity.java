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

public class HealthArticlesActivity extends AppCompatActivity {

    private String[][] health_details = {
            {"Waliking daily" , "" , "" , "" , "Click more details"} ,
            {"Home care for COVID-19" , "" , "" , "" , "Click more details"} ,
            {"Stop smoking" , "" , "" , "" , "Click more details"} ,
            {"Menstrual cramps" , "" , "" , "" , "Click more details"} ,
            {"Healthy Gut" , "" , "" , "" , "Click more details"} ,
    } ;

    private int []images = {
            R.drawable.health1 ,
            R.drawable.health2 ,
            R.drawable.health3 ,
            R.drawable.health4 ,
            R.drawable.health5 ,
    } ;

    SimpleAdapter sa ;
    HashMap<String , String> items ;
    ArrayList list ;
    Button btn ;
    ListView lst ;
    TextView tv ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_health_articles);

        btn = findViewById(R.id.buttonarticles) ;
        lst = findViewById(R.id.listviewarticles) ;
        tv = findViewById(R.id.titlearticles) ;

        list = new ArrayList<>() ;
        for (int i = 0; i < health_details.length; i++) {
            items = new HashMap<String , String>() ;
            items.put("Line1", health_details[i][0]) ;
            items.put("Line2", health_details[i][1]) ;
            items.put("Line3", health_details[i][2]) ;
            items.put("Line4", health_details[i][3]) ;
            items.put("Line5", health_details[i][4]+"/-") ;
            list.add(items) ;

        }

        sa = new SimpleAdapter(this , list , R.layout.multi_lines, new String[]{"Line1", "Line2", "Line3", "Line4", "Line5"}, new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}) ;

        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(HealthArticlesActivity.this , HealthArticlesDetailActivity.class) ;
                intent.putExtra("text1", health_details[i][0]) ;
                intent.putExtra("text2", images[i]) ;
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HealthArticlesActivity.this , HomeActivity.class));
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}