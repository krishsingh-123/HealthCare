package com.example.hospital;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderDetailsActivity extends AppCompatActivity {

    private String[][] order_details = {} ;
    Button btn ;
    ArrayList list ;
    HashMap<String , String> items ;
    SimpleAdapter sa ;
    ListView lst ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_details);

        btn = findViewById(R.id.buttonbackorder) ;
        lst = findViewById(R.id.listvieworder) ;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderDetailsActivity.this , HomeActivity.class));
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("sharedprefer" , MODE_PRIVATE) ;
        String username = sharedPreferences.getString("username" , "") ;

        Database db = new Database(getApplicationContext() , "healthcare" , null , 1 ) ;

        ArrayList dbdata = db.getorderdata(username) ;
        if (dbdata==null || dbdata.size()==0){
            Toast.makeText(getApplicationContext(), "No Orders Found", Toast.LENGTH_SHORT).show();
            return ;
        }
        order_details = new String[dbdata.size()][] ;
        for (int i = 0; i < order_details.length; i++) {
            order_details[i] = new String[5] ;
            String arrdata = dbdata.get(i).toString() ;
            String[] strdata = arrdata.split(java.util.regex.Pattern.quote("$")) ;
            order_details[i][0] = strdata[0] ;
            order_details[i][1] = strdata[1] ;
            if (strdata[7].compareTo("medicine")==0){
                order_details[i][3] = "Del: "+strdata[4] ;
            }
            else {
                order_details[i][3] = "Del: "+strdata[4] + "   " + strdata[5] ;
            }
            order_details[i][2] = "Rs. " + strdata[6] ;
            order_details[i][4] = strdata[7] ;
        }

        list = new ArrayList() ;
        for (int i = 0; i < order_details.length; i++) {
            items = new HashMap<String , String>() ;
            items.put("Line1" , order_details[i][0]) ;
            items.put("Line2" , order_details[i][1]) ;
            items.put("Line3" , order_details[i][2]) ;
            items.put("Line4" , order_details[i][3]) ;
            items.put("Line5" , order_details[i][4]) ;
            list.add(items) ;
        }
        sa = new SimpleAdapter(this , list , R.layout.multi_lines , new String[]{"Line1","Line2","Line3","Line4","Line5"},
                new int[] {R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}) ;
        lst.setAdapter(sa);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}