package com.example.hospital;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {

    EditText userid , pass , repass , email ;
    Button Register ;
    TextView account ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.email) ;
        userid = findViewById(R.id.userid) ;
        pass = findViewById(R.id.pass) ;
        repass = findViewById(R.id.repass) ;
        Register = findViewById(R.id.Register) ;
        account = findViewById(R.id.account) ;

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_id = userid.getText().toString() ;
                String pass_word = pass.getText().toString() ;
                String email_id = email.getText().toString() ;
                String re_password = repass.getText().toString() ;

                Database db = new Database(getApplicationContext(), "healthcare", null, 1) ;

                if(user_id.length()==0 || pass_word.length()==0 || email_id.length()==0 || re_password.length()==0){
                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (pass_word.compareTo(re_password)==0) {
                        if (isvalid(pass_word) && isvallid(email_id)) {
                            db.register(user_id , email_id, pass_word);
                            Toast.makeText(getApplicationContext(), "Record Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Password must contain atleast 8 characters, having letter, digit and special symbol", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Password and confirm passowrd can't match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this , LoginActivity.class) ;
                startActivity(intent);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public static boolean isvalid(String pass_word) {
        int f1 = 0, f2 = 0, f3 = 0;
        if (pass_word.length() < 8) {
            return false;
        } else {
            for (int i = 0; i < pass_word.length(); i++) {
                if (Character.isLetter(pass_word.charAt(i))) {
                    f1 = 1;
                }
            }
            for (int j = 0; j < pass_word.length(); j++) {
                if (Character.isLetter(pass_word.charAt(j))) {
                    f2 = 1;
                }
            }
            for (int k = 0; k < pass_word.length(); k++) {
                if (Character.isLetter(pass_word.charAt(k))) {
                    f3 = 1;
                }
            }
            if (f1==1 && f2==1 && f3==1){
                return true ;
            }
            else{
                return false ;
            }
        }
    }

    public static boolean isvallid(String email) {
        String emailPattern = "^[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+$";
        return email.matches(emailPattern);
    }
}