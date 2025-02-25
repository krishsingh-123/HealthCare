package com.example.hospital;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button ;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicineActivity extends AppCompatActivity {

    private String[][] packages =
            {
                    {"Uprise-03 1000IU Capsule" , "" , "" , "" , "800"} ,
                    {"Healthvit Chromium picolinate 200mcg capsule" , "" , "" , "" , "900"},
                    {"Vtamin-B Complex capsule" , "" , "" , "" , "500"} ,
                    {"Inlife Vitamin E wheat germ oil capsule" , "" , "" , "" , "800"} ,
                    {"Dolo-650 Capsule" , "" , "" , "" , "400"} ,
                    {"Crocine-650 Advance capsule" , "" , "" , "" , "700"} ,
                    {"Strepsils Medicated Lorengezs for sore throat" , "" , "" , "" , "300"} ,
                    {"Feronia XT Tablets" , "" , "" , "" , "1300"} ,
                    {"Tata Img capsule + Vtamin D3 capsule" , "" , "" , "" , "600"}
            } ;
    private String[] package_details = {
            "building and keeping the bones and teeth strong\n" + "reduce the fatigue/stress and muscular pains\n" + "Boosting immunity and increasing resistance against infection\n" , "Chromium is an important trace mineral that plays crucial role inhelping insulin regulate blood glucose\n" , "helps in the formation of red blood cells\n" + "helps to maintain healthy nervous system\n" + "provides relief from vitamin D defieciences\n"  , "It promotes health as well as skin benefits\n" + "It also reduces the skin blemish and pigmentation\n" + "it protectcs the skin from UVA and UVB sun rays\n" , "Dolo 650 helps relief pain and fever by blocking the certain chemical messengers responsible for fever and pains\n" , "Helps relief fever and bring down a high temperature\n" + "It is suitable for the person of heart condition or high blood pressure\n" , "Reliefs the symptoms of bacterial throat infection and soothes the recovery process\n" + "provides a warm and comforting feeling during sore throat\n"  , "Reduces the risk of calcium defieciency , rickets and oestoporosis\n" + "Promotes mobilty and flexibility of the joints" , "It helps to reduce the defieciency of iron doe to low intake of iron or chronic blood loss" } ;
    Button back , cart ;
    ListView listView ;
    ArrayList list ;
    HashMap<String , String> items ;
    SimpleAdapter sa ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buy_medicine);

        back = findViewById(R.id.buttonbackmedicine) ;
        cart = findViewById(R.id.buttoncartmedicine) ;
        listView = findViewById(R.id.listviewmedicine) ;

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

        sa = new SimpleAdapter(this , list , R.layout.multi_lines, new String[]{"Line1", "Line2", "Line3", "Line4", "Line5"}, new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}) ;

        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(BuyMedicineActivity.this , BuyMedicineDetailActivity.class) ;
                intent.putExtra("text1", packages[i][0]) ;
                intent.putExtra("text2", package_details[i]) ;
                intent.putExtra("text3", packages[i][4]) ;
                startActivity(intent);
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this , CartBuyMedicineActivity.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this , HomeActivity.class));
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}