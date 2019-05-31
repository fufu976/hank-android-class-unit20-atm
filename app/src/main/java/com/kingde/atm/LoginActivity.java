package com.kingde.atm;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText edUsername;
    private EditText edPasswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findView();

    }

    private void findView() {
        edUsername = findViewById(R.id.username);
        edPasswd   = findViewById(R.id.passwd);
    }

    public void login(View view) {
        String username = edUsername.getText().toString();
        final String password = edPasswd.getText().toString();
        FirebaseDatabase.getInstance().getReference("users").child(username).child("password")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String pwd = (String) dataSnapshot.getValue();
                        if(pwd.equals(password)) {
                            setResult(RESULT_OK);
                            finish();
                        } else {
                            new AlertDialog.Builder(LoginActivity.this)
                                    .setTitle("Warning")
                                    .setMessage("username or password incorrect")
                                    .setPositiveButton("OK", null)
                                    .show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    public void quit(View view) {

    }
}
