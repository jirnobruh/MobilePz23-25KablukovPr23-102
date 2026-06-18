package com.example.pr23kablukovpr23_102;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class EmptyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Reusing a simple layout
        TextView tv = findViewById(R.id.tvWelcome);
        if (tv != null) {
            String title = getIntent().getStringExtra("title");
            tv.setText(title != null ? title : "Пустой экран");
        }
    }
}