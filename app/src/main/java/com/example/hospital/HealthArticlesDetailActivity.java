package com.example.hospital;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HealthArticlesDetailActivity extends AppCompatActivity {

    Button btn ;
    ImageView image ;
    TextView txt ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_health_articles_detail);

        btn = findViewById(R.id.buttonhealthydetails) ;
        txt = findViewById(R.id.titlearticlesdetail) ;
        image = findViewById(R.id.imageViewarticles) ;

        Intent intent = getIntent() ;
        txt.setText(intent.getStringExtra("text1"));

        Bundle bundle = getIntent().getExtras() ;
        if (bundle!=null){
            int resid = bundle.getInt("text2");
            image.setImageResource(resid) ;
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HealthArticlesDetailActivity.this , HealthArticlesActivity.class));
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}