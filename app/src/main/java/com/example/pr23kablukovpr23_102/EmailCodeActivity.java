package com.example.pr23kablukovpr23_102;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class EmailCodeActivity extends AppCompatActivity {

    private TextView tvTimer;
    private List<TextView> codeBoxes = new ArrayList<>();
    private EditText etHiddenCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_email_code);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.code_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvTimer = findViewById(R.id.tvTimer);
        etHiddenCode = findViewById(R.id.etHiddenCode);
        
        codeBoxes.add(findViewById(R.id.tvCode1));
        codeBoxes.add(findViewById(R.id.tvCode2));
        codeBoxes.add(findViewById(R.id.tvCode3));
        codeBoxes.add(findViewById(R.id.tvCode4));

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
        findViewById(R.id.llCode).setOnClickListener(v -> focusEmailCode());

        etHiddenCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateCodeUI(s.toString());
                if (s.length() == 4) {
                    startActivity(new Intent(EmailCodeActivity.this, CreatePasswordActivity.class));
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        startTimer();
        focusEmailCode();
    }

    private void focusEmailCode() {
        etHiddenCode.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(etHiddenCode, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    private void startTimer() {
        new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                tvTimer.setText("Отправить код повторно можно\nбудет через " + (millisUntilFinished / 1000) + " секунд");
            }
            public void onFinish() {
                tvTimer.setText("Отправить код повторно");
            }
        }.start();
    }

    private void updateCodeUI(String code) {
        for (int i = 0; i < 4; i++) {
            if (i < code.length()) {
                codeBoxes.get(i).setText(String.valueOf(code.charAt(i)));
            } else {
                codeBoxes.get(i).setText("");
            }
        }
    }
}