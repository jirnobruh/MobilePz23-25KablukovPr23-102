package com.example.pr23kablukovpr23_102;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            // Добавляем системные отступы к базовому паддингу 24dp (превращенному в пиксели)
            int padding = (int) (24 * getResources().getDisplayMetrics().density);
            v.setPadding(padding, systemBars.top + padding, padding, systemBars.bottom + padding);
            return insets;
        });

        etEmail = findViewById(R.id.etEmail);
        btnNext = findViewById(R.id.btnNext);

        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String email = s.toString().trim();
                boolean isValid = !email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches();
                btnNext.setEnabled(isValid);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        btnNext.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, EmailCodeActivity.class);
            intent.putExtra("email", etEmail.getText().toString());
            startActivity(intent);
        });
    }
}