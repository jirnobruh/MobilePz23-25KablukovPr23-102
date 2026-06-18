package com.example.pr23kablukovpr23_102;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnalysesActivity extends AppCompatActivity {

    private RecyclerView rvNews, rvCategories, rvAnalyses;
    private EditText etSearch;
    private String currentCategory = "Популярные";
    private String currentQuery = "";
    private AnalysesAdapter analysesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyses);

        initViews();
        setupNews();
        setupCategories();
        setupAnalyses();
        setupSearch();
        setupBottomNav();
    }

    private void initViews() {
        rvNews = findViewById(R.id.rvNews);
        rvCategories = findViewById(R.id.rvCategories);
        rvAnalyses = findViewById(R.id.rvAnalyses);
        etSearch = findViewById(R.id.etSearch);
    }

    private void setupNews() {
        List<NewsModel> news = new ArrayList<>();
        news.add(new NewsModel("Чек-ап для\nмужчин", "9 исследований", "8000 ₽", Color.parseColor("#4B91FF")));
        news.add(new NewsModel("Подготовка к\nвакцинации", "Комплекс перед вакц.", "4000 ₽", Color.parseColor("#76E3D4")));
        news.add(new NewsModel("Здоровое\nсердце", "5 исследований", "5500 ₽", Color.parseColor("#4B91FF")));
        
        NewsAdapter adapter = new NewsAdapter(news);
        rvNews.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvNews.setAdapter(adapter);
    }

    private void setupCategories() {
        List<String> categories = Arrays.asList("Популярные", "Covid", "Комплексные", "Биохимия");
        CategoryAdapter adapter = new CategoryAdapter(categories, category -> {
            currentCategory = category;
            analysesAdapter.filter(currentCategory, currentQuery);
        });
        rvCategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvCategories.setAdapter(adapter);
    }

    private void setupAnalyses() {
        List<AnalysisModel> analyses = new ArrayList<>();
        analyses.add(new AnalysisModel("ПЦР-тест на определение РНК коронавируса стандартный", "2 дня", "1800 ₽", "Covid"));
        analyses.add(new AnalysisModel("Клинический анализ крови с лейкоцитарной формулировкой", "1 день", "690 ₽", "Популярные"));
        analyses.add(new AnalysisModel("Биохимический анализ крови, базовый", "1 день", "2440 ₽", "Биохимия"));
        analyses.add(new AnalysisModel("СОЭ (венозная кровь)", "1 день", "250 ₽", "Биохимия"));
        analyses.add(new AnalysisModel("Общий анализ мочи", "1 день", "350 ₽", "Популярные"));
        analyses.add(new AnalysisModel("Гликированный гемоглобин", "1 день", "500 ₽", "Комплексные"));
        
        analysesAdapter = new AnalysesAdapter(analyses);
        rvAnalyses.setLayoutManager(new LinearLayoutManager(this));
        rvAnalyses.setAdapter(analysesAdapter);
    }

    private void setupSearch() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                currentQuery = s.toString();
                analysesAdapter.filter(currentCategory, currentQuery);
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void setupBottomNav() {
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setSelectedItemId(R.id.nav_analyses);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_analyses) return true;
            
            Intent intent = new Intent(this, EmptyActivity.class);
            if (id == R.id.nav_results) intent.putExtra("title", "Результаты");
            else if (id == R.id.nav_support) intent.putExtra("title", "Поддержка");
            else if (id == R.id.nav_profile) intent.putExtra("title", "Профиль");
            startActivity(intent);
            return false;
        });
    }
}