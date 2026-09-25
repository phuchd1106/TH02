package com.example.th02;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.th02.adapter.FoodAdapter;
import com.example.th02.data.FoodRepository;
import com.example.th02.model.Food;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private ImageView btnBack;
    private EditText edtSearchQuery;
    private MaterialButton btnSearch;
    private RecyclerView rvSearchResults;
    private LinearLayout layoutEmptyState;
    private TextView tvEmptyMessage;
    private FoodAdapter foodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupRecyclerView();
        setupListeners();

        // Focus search input
        edtSearchQuery.requestFocus();

        performSearch("");
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        edtSearchQuery = findViewById(R.id.edtSearchQuery);
        btnSearch = findViewById(R.id.btnSearch);
        rvSearchResults = findViewById(R.id.rvSearchResults);
        layoutEmptyState = findViewById(R.id.layoutEmptyState);
        tvEmptyMessage = findViewById(R.id.tvEmptyMessage);
    }

    private void setupRecyclerView() {
        foodAdapter = new FoodAdapter(null, this::openFoodDetail);
        rvSearchResults.setLayoutManager(new LinearLayoutManager(this));
        rvSearchResults.setAdapter(foodAdapter);
    }

    private void setupListeners() {
        btnBack.setOnClickListener(v -> finish());

        btnSearch.setOnClickListener(v -> {
            String query = edtSearchQuery.getText().toString();
            performSearch(query);
        });

        edtSearchQuery.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch(edtSearchQuery.getText().toString());
                return true;
            }
            return false;
        });

        edtSearchQuery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                performSearch(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void performSearch(String query) {
        if (query == null || query.trim().isEmpty()) {
            foodAdapter.updateList(null);
            rvSearchResults.setVisibility(View.GONE);
            tvEmptyMessage.setText(R.string.search_prompt);
            layoutEmptyState.setVisibility(View.VISIBLE);
            return;
        }

        List<Food> searchResults = FoodRepository.searchFood(query);
        foodAdapter.updateList(searchResults);

        if (searchResults.isEmpty()) {
            rvSearchResults.setVisibility(View.GONE);
            tvEmptyMessage.setText(R.string.no_result_found);
            layoutEmptyState.setVisibility(View.VISIBLE);
        } else {
            rvSearchResults.setVisibility(View.VISIBLE);
            layoutEmptyState.setVisibility(View.GONE);
        }
    }

    private void openFoodDetail(Food food) {
        Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
        intent.putExtra("EXTRA_FOOD", food);
        startActivity(intent);
    }
}
