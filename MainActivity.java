
package com.example.family10;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
public FirebaseAuth mAuth;
public Intent intent;
public static final String APP_PREFERENCES = "mysettings";
public static final String APP_PREFERENCES_NAME = "name";
public SharedPreferences mSettings;
public String myname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button SignButton = findViewById(R.id.SignButton);
        Button ComeButoon = findViewById(R.id.ComeButoon);
        Intent signintent = new Intent(MainActivity.this, registration.class);
        Intent comeintent = new Intent(MainActivity.this, returning.class);
        mAuth = FirebaseAuth.getInstance();
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        if (getIntent().getBooleanExtra("finish", false)) finish();

        SignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(signintent);
            }
        });
        ComeButoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAuth.getCurrentUser()!=null){
                    onstart(intent);
                }else {
                    startActivity(comeintent);
                }
            }
        });
    }




//Пользователь уже существует
public void onstart(Intent intent){
        FirebaseUser currentUser = mAuth.getCurrentUser();
    intent = new Intent(MainActivity.this, Messaging.class);
        if(currentUser!= null){
            startActivity(intent);
        }
}





    //РЕГИСТРАЦИЯ
        public void createAccount(String email, String password,Intent regintent){
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                Toast.makeText(MainActivity.this, "(*°▽°*) Успех",Toast.LENGTH_LONG).show();
                signing(email,password);
            }else{
                    Toast.makeText(MainActivity.this, "(ಥ﹏ಥ) Не удалось создать аккаунт",Toast.LENGTH_LONG).show();
                    startActivity(regintent);
                }
            }
        });
        }



    //ВХОД
        public void signing(String email, String password){
        intent = new Intent(this, Messaging.class);
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser CurrentUser = mAuth.getCurrentUser();
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "(｡•︿•̀｡) Не удалось войти",Toast.LENGTH_LONG).show();

                }
            }
        });
        }


    @Override
    protected void onPause() {
        super.onPause();
        // Запоминаем данные
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_NAME, myname);
        editor.apply();
    }
    @Override
    protected void onResume() {
        super.onResume();

        if (mSettings.contains(APP_PREFERENCES_NAME)) {
            // Получаем число из настроек
            myname = mSettings.getString(APP_PREFERENCES_NAME, "");
        }
    }

}
