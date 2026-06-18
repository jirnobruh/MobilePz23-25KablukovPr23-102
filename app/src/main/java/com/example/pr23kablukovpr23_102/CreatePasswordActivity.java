package com.example.pr23kablukovpr23_102;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CreatePasswordActivity extends AppCompatActivity {

    private List<ImageView> dots = new ArrayList<>();
    private StringBuilder password = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_password);

        dots.add(findViewById(R.id.ivDot1));
        dots.add(findViewById(R.id.ivDot2));
        dots.add(findViewById(R.id.ivDot3));
        dots.add(findViewById(R.id.ivDot4));

        findViewById(R.id.tvSkip).setOnClickListener(v -> goToNext());

        setupNumPad();
    }

    private void setupNumPad() {
        int[] ids = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};
        for (int id : ids) {
            findViewById(id).setOnClickListener(v -> {
                if (password.length() < 4) {
                    password.append(((TextView) v).getText().toString());
                    updateDots();
                    if (password.length() == 4) {
                        goToNext();
                    }
                }
            });
        }
        findViewById(R.id.btnDel).setOnClickListener(v -> {
            if (password.length() > 0) {
                password.deleteCharAt(password.length() - 1);
                updateDots();
            }
        });
    }

    private void updateDots() {
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