package com.example.family10;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Parcel;
import android.text.LoginFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.transition.Transition;

import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.internal.NavigationMenu;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.MultiFactor;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


public class registration extends MainActivity{





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        EditText email = findViewById(R.id.regmailtext);
        EditText password = findViewById(R.id.regpastext);
        EditText checkpas = findViewById(R.id.checkedtext);
        Button next = findViewById(R.id.Nextbutton);
EditText name = findViewById(R.id.name);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
//Intent retintent = new Intent(registration.this, returning.class);
Intent regintent = new Intent(registration.this,registration.class);



        next.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {

         String mailtext = email.getText().toString();
         String pastext = password.getText().toString();
         String checktext = checkpas.getText().toString();
         myname = name.getText().toString();

         if(pastext.equals(checktext)== true && isValidEmail(mailtext) == true){

             onPause();
             createAccount(mailtext, pastext,regintent);



             email.setText("");
             password.setText("");
             checkpas.setText("");
             name.setText("");
     }else {
             Toast.makeText(registration.this,"Проверьте поля ввода",Toast.LENGTH_SHORT).show();
         }
     }
 });
    }



    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }


}