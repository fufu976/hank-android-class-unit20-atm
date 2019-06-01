package com.kingde.atm;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private EditText edUsername;
    private EditText edPasswd;
    private CheckBox cbRemember;

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
        cbRemember = findViewById(R.id.checkBox);
        boolean bRemember = getSharedPreferences("atm", MODE_PRIVATE)
                .getBoolean("REMEMBER_USERNAME", false);
        cbRemember.setChecked(bRemember);
        cbRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                getSharedPreferences("atm", MODE_PRIVATE)
                        .edit()
                        .putBoolean("REMEMBER_USERNAME", isChecked)
                        .apply();
            }
        });
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
                            boolean bRemember = getSharedPreferences("atm", MODE_PRIVATE)
                                    .getBoolean("REMEMBER_USERNAME", false);
                            Log.d(TAG, "onDataChange: bRemember: " + bRemember);
                            String username_t = "";
                            if(bRemember) {
                                username_t = username;
                            }
                            Log.d(TAG, "onDataChange: username_t: " + username_t);
                            getSharedPreferences("atm", MODE_PRIVATE)
                                    .edit()
                                    .putString("USERNAME", username_t)
                                    .commit();
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
