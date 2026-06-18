package com.example.pr23kablukovpr23_102;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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

        etBirth.setOnClickListener(v -> showDatePicker());
        etGender.setOnClickListener(v -> showGenderPicker());

        findViewById(R.id.tvSkip).setOnClickListener(v -> finish());
        btnCreate.setOnClickListener(v -> finish());
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
            Calendar selectedDate = Calendar.getInstance();
            selectedDate.set(year1, month1, dayOfMonth);
            
            // Формат даты "28 февраля 1991"
            SimpleDateFormat sdf = new SimpleDateFormat("d MMMM yyyy", new Locale("ru"));
            String dateString = sdf.format(selectedDate.getTime());
            
            etBirth.setText(dateString);
        }, year, month, day);
        datePickerDialog.show();
    }

    private void showGenderPicker() {
        String[] genders = {"Мужской", "Женский"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Выберите пол");
        builder.setItems(genders, (dialog, which) -> {
            etGender.setText(genders[which]);
        });
        builder.show();
    }

    private void checkFields() {
        boolean allFilled = !etFirst.getText().toString().trim().isEmpty() &&
                !etMiddle.getText().toString().trim().isEmpty() &&
                !etLast.getText().toString().trim().isEmpty() &&
                !etBirth.getText().toString().trim().isEmpty() &&
                !etGender.getText().toString().trim().isEmpty();
        btnCreate.setEnabled(allFilled);
    }
}