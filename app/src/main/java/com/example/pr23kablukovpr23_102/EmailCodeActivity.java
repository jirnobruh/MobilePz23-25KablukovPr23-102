package com.example.pr23kablukovpr23_102;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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
    private StringBuilder currentCode = new StringBuilder();

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
        codeBoxes.add(findViewById(R.id.tvCode1));
        codeBoxes.add(findViewById(R.id.tvCode2));
        codeBoxes.add(findViewById(R.id.tvCode3));
        codeBoxes.add(findViewById(R.id.tvCode4));

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        startTimer();
        setupNumPad();
    }

    private void startTimer() {
        new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                tvTimer.setText("Отправить код повторно можно\nбудет через " + (millisUntilFinished / 1000) + " секунд");
            }
            public void onFinish() {
                tvTimer.setText("Отправить код повторно");
                tvTimer.setClickable(true);
            }
        }.start();
    }

    private void setupNumPad() {
        int[] ids = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};
        for (int id : ids) {
            findViewById(id).setOnClickListener(v -> {
                if (currentCode.length() < 4) {
                    String num = ((TextView) v).getText().toString();
                    currentCode.append(num);
                    updateCodeUI();
                    if (currentCode.length() == 4) {
                        startActivity(new Intent(EmailCodeActivity.this, CreatePasswordActivity.class));
                    }
                }
            });
        }
        findViewById(R.id.btnDel).setOnClickListener(v -> {
            if (currentCode.length() > 0) {
                currentCode.deleteCharAt(currentCode.length() - 1);
                updateCodeUI();
            }
        });
    }

    private void updateCodeUI() {
        for (int i = 0; i < 4; i++) {
            if (i < currentCode.length()) {
                codeBoxes.get(i).setText(String.valueOf(currentCode.charAt(i)));
            } else {
                codeBoxes.get(i).setText("");
            }
        }
    }
}