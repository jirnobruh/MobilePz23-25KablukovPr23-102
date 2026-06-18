package com.example.pr23kablukovpr23_102;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class PatientCardActivity extends AppCompatActivity {

    private EditText etFirst, etMiddle, etLast, etBirth, etGender;
    private Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_patient_card);

        etFirst = findViewById(R.id.etFirstName);
        etMiddle = findViewById(R.id.etMiddleName);
        etLast = findViewById(R.id.etLastName);
        etBirth = findViewById(R.id.etBirthDate);
        etGender = findViewById(R.id.etGender);
        btnCreate = findViewById(R.id.btnCreate);

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkFields();
            }
            @Override
            public void afterTextChanged(Editable s) {}
        };

        etFirst.addTextChangedListener(watcher);
        etMiddle.addTextChangedListener(watcher);
        etLast.addTextChangedListener(watcher);
        etBirth.addTextChangedListener(watcher);
        etGender.addTextChangedListener(watcher);

        findViewById(R.id.tvSkip).setOnClickListener(v -> finish());
        btnCreate.setOnClickListener(v -> finish());
    }

    private void checkFields() {
        boolean allFilled = !etFirst.getText().toString().isEmpty() &&
                !etMiddle.getText().toString().isEmpty() &&
                !etLast.getText().toString().isEmpty() &&
                !etBirth.getText().toString().isEmpty() &&
                !etGender.getText().toString().isEmpty();
        btnCreate.setEnabled(allFilled);
    }
}