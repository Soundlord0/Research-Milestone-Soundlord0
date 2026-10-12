package com.example.researchmilestone;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;
import android.util.Patterns;


public class MainActivity extends AppCompatActivity {

    private EditText emailInput;
    private EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);

        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                        MainActivity.this,
                        "Enter both an email and password.",
                        Toast.LENGTH_SHORT
                ).show();
            }
            else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailInput.setError("Enter a valid email address.");
                emailInput.requestFocus();
            }
            else {
                Intent intent = new Intent(
                        MainActivity.this,
                        ProfileSetupActivity.class
                );

                intent.putExtra("USER_EMAIL", email);

                startActivity(intent);
            }
        });

    }
}