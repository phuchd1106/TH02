package com.example.th02;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.th02.adapter.FoodAdapter;
import com.example.th02.data.FoodRepository;
import com.example.th02.model.Food;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvFoodList;
    private ImageView btnOpenSearch;
    private CardView cardSearchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupRecyclerView();
        setupListeners();
    }

    private void initViews() {
        rvFoodList = findViewById(R.id.rvFoodList);
        btnOpenSearch = findViewById(R.id.btnOpenSearch);
        cardSearchBar = findViewById(R.id.cardSearchBar);
    }

    private void setupRecyclerView() {
        List<Food> foodList = FoodRepository.getFoodList();
        FoodAdapter foodAdapter = new FoodAdapter(foodList, this::openFoodDetail);
        rvFoodList.setLayoutManager(new LinearLayoutManager(this));
        rvFoodList.setAdapter(foodAdapter);
    }

    private void setupListeners() {
        View.OnClickListener openSearchListener = v -> {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent);
        };

        btnOpenSearch.setOnClickListener(openSearchListener);
        cardSearchBar.setOnClickListener(openSearchListener);
    }

    private void openFoodDetail(Food food) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("EXTRA_FOOD", food);
        startActivity(intent);
    }
}
