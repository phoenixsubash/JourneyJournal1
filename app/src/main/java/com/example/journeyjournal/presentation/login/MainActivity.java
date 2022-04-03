package com.example.journeyjournal.presentation.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.journeyjournal.presentation.dashboard.DashboardActivity;
import com.example.journeyjournal.presentation.register.PasswordForgotActivity;
import com.example.journeyjournal.R;
import com.example.journeyjournal.presentation.register.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Declaring instance of Firebase
    private FirebaseAuth mAuth;
    private Button logIn;
    private TextView forgotPassword;
    private EditText editTextEmail, editTextPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        TextView register = findViewById(R.id.txt_goto_register);
        register.setOnClickListener(this);

        logIn = (Button) findViewById(R.id.btn_login);
        logIn.setOnClickListener(this);

        forgotPassword = (TextView) findViewById(R.id.forgot_password);
        forgotPassword.setOnClickListener(this);

        editTextEmail = (EditText) findViewById(R.id.email_login);
        editTextPassword = (EditText) findViewById(R.id.password_login);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txt_goto_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;

            case R.id.btn_login:
               signIn();
               break;

            case R.id.forgot_password:
                startActivity(new Intent(this, PasswordForgotActivity.class));
                break;
        }

    }

    private void signIn() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();


        if (email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
        }

        if (password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please provide valid email");
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent( MainActivity.this, DashboardActivity.class));

                }else {
                    Toast.makeText(MainActivity.this,"Check your login Credentials", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}