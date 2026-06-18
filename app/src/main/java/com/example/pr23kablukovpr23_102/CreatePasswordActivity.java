package com.example.pr23kablukovpr23_102;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CreatePasswordActivity extends AppCompatActivity {

    private List<ImageView> dots = new ArrayList<>();
    private EditText etHiddenPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_password);

        etHiddenPassword = findViewById(R.id.etHiddenPassword);
        dots.add(findViewById(R.id.ivDot1));
        dots.add(findViewById(R.id.ivDot2));
        dots.add(findViewById(R.id.ivDot3));
        dots.add(findViewById(R.id.ivDot4));

        findViewById(R.id.tvSkip).setOnClickListener(v -> goToNext());
        findViewById(R.id.llDots).setOnClickListener(v -> focusPassword());

        etHiddenPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateDots(s.toString());
                if (s.length() == 4) {
                    goToNext();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        focusPassword();
    }

    private void focusPassword() {
        etHiddenPassword.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(etHiddenPassword, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    private void updateDots(String password) {
        for (int i = 0; i < 4; i++) {
            if (i < password.length()) {
                dots.get(i).setImageResource(R.drawable.dot_active);
            } else {
                dots.get(i).setImageResource(R.drawable.dot_password_inactive);
            }
        }
    }

    private void goToNext() {
        startActivity(new Intent(CreatePasswordActivity.this, PatientCardActivity.class));
    }
}