package com.example.family10;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.service.controls.actions.FloatAction;
import android.text.Layout;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuWrapperICS;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PlayGamesAuthCredential;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Messaging extends MainActivity {

    public DatabaseReference myRef;
public FirebaseAuth mAuth;
private ListView listView;
private ArrayAdapter<String> adapter;
private List<String> listData;
private String USER_KEY = "Message";
private EditText textfield;
private FloatingActionButton send;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messaging);
         init();
displayAllMessages();
onResume();


if(myname==null){ Toast.makeText(this,"Привет незнакомец",Toast.LENGTH_SHORT).show();}
else{Toast.makeText(this,"Привет "+myname,Toast.LENGTH_SHORT).show();}
   // adapter.notifyDataSetChanged();


send.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

String message = textfield.getText().toString();
            myRef.push().setValue(new Message(myname+":\n"+message));
            textfield.setText("");
    }
});
    }

public void init(){
        listView = findViewById(R.id.list_of_messages);
        listData = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData);
        listView.setAdapter(adapter);
        myRef = FirebaseDatabase.getInstance().getReference(USER_KEY);
        mAuth = FirebaseAuth.getInstance();
        textfield = findViewById(R.id.messageField);
        send = findViewById(R.id.btnsend);
    mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

}





private void displayAllMessages() {
        ValueEventListener vListener  = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
if(listData.size()>0){listData.clear();}
                    for (DataSnapshot ds : snapshot.getChildren()) {
Message mes = ds.getValue(Message.class);
assert mes != null;
listData.add(mes.messageText);
                    }
                    adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        myRef.addValueEventListener(vListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_settings:
                Intent intent = new Intent(this,MainActivity.class);
                mAuth.signOut();
                startActivity(intent);
                return true;
            case R.id.action_name1:
                Intent intentexit = new Intent(this, MainActivity.class);

                intentexit.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                intentexit.putExtra("finish", true);

                startActivity(intentexit);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
//Toast.makeText(Messaging.this,"Error",Toast.LENGTH_SHORT).show();