package com.example.pr23kablukovpr23_102;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TextView tvSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Pr23KablukovPr23102);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        tvSkip = findViewById(R.id.tvSkip);

        ViewCompat.setOnApplyWindowInsetsListener(tvSkip, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            lp.topMargin = systemBars.top + (int)(24 * getResources().getDisplayMetrics().density);
            v.setLayoutParams(lp);
            return insets;
        });

        OnboardingAdapter adapter = new OnboardingAdapter();
        viewPager.setAdapter(adapter);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 2) {
                    tvSkip.setText("Завершить");
                } else {
                    tvSkip.setText("Пропустить");
                }
            }
        });

        tvSkip.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            // finish() убран, чтобы можно было вернуться назад
        });
    }
}