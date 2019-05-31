package com.kingde.atm;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private EditText edUsername;
    private EditText edPasswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findView();

        String username = getSharedPreferences("atm", MODE_PRIVATE)
                .getString("USERNAME", "");
        Log.d(TAG, "onCreate: username " + username);
        edUsername.setText(username);
    }

    private void findView() {
        edUsername = findViewById(R.id.username);
        edPasswd   = findViewById(R.id.passwd);
    }

    public void login(View view) {
        final String username = edUsername.getText().toString();
        final String password = edPasswd.getText().toString();
        FirebaseDatabase.getInstance().getReference("users").child(username).child("password")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String pwd = (String) dataSnapshot.getValue();
                        if(pwd.equals(password)) {
                            setResult(RESULT_OK);
                            getSharedPreferences("atm", MODE_PRIVATE)
                                    .edit()
                                    .putString("USERNAME", username)
                                    .commit();
                            String username = getSharedPreferences("atm", MODE_PRIVATE)
                                    .getString("USERNAME", "");
                            Log.d(TAG, "login: username " + username);
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
