package com.kingde.atm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
        String password = edPasswd.getText().toString();
        if("admin".equals(username) && "admin".equals(password)) {
            finish();
        }
    }

    public void quit(View view) {

    }
}
