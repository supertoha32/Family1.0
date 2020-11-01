package com.example.family10;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class returning extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.returning);
        EditText email = findViewById(R.id.mailtext);
        EditText password = findViewById(R.id.pastext);
        Button sign = findViewById(R.id.exitbutton);
        EditText name = findViewById(R.id.editname);


        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    myname = name.getText().toString();
                    String mailtext = email.getText().toString();
                    String pastext = password.getText().toString();

                    signing(mailtext, pastext);
onPause();
name.setText("");
email.setText("");
password.setText("");

                } catch (Exception y){
                    Toast.makeText(returning.this,"(⇀‸↼‶) Проверьте поля",Toast.LENGTH_LONG).show();}
                    finally {

                }
            }
        });
    }
}
