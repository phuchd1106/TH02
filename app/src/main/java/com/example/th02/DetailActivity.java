package com.example.th02;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.th02.model.Food;
import com.google.android.material.button.MaterialButton;

public class DetailActivity extends AppCompatActivity {

    private ImageView btnBack;
    private ImageView imgFoodDetail;
    private TextView tvFoodName;
    private TextView tvFoodPrice;
    private TextView tvFoodDescription;
    private TextView tvFoodIngredients;
    private ImageView btnDecrease;
    private ImageView btnIncrease;
    private TextView tvQuantity;
    private TextView tvTotalPrice;
    private MaterialButton btnOrder;

    private Food food;
    private int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        getIntentData();
        displayFoodDetails();
        setupListeners();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        imgFoodDetail = findViewById(R.id.imgFoodDetail);
        tvFoodName = findViewById(R.id.tvFoodName);
        tvFoodPrice = findViewById(R.id.tvFoodPrice);
        tvFoodDescription = findViewById(R.id.tvFoodDescription);
        tvFoodIngredients = findViewById(R.id.tvFoodIngredients);
        btnDecrease = findViewById(R.id.btnDecrease);
        btnIncrease = findViewById(R.id.btnIncrease);
        tvQuantity = findViewById(R.id.tvQuantity);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        btnOrder = findViewById(R.id.btnOrder);
    }

    @SuppressWarnings("deprecation")
    private void getIntentData() {
        if (getIntent() != null && getIntent().hasExtra("EXTRA_FOOD")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                food = getIntent().getSerializableExtra("EXTRA_FOOD", Food.class);
            } else {
                food = (Food) getIntent().getSerializableExtra("EXTRA_FOOD");
            }
        }
    }

    private void displayFoodDetails() {
        if (food == null) {
            Toast.makeText(this, "Không thể tải thông tin món ăn!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        imgFoodDetail.setImageResource(food.getImageResId());
        tvFoodName.setText(food.getName());
        tvFoodPrice.setText(food.getFormattedPrice());
        tvFoodDescription.setText(food.getDescription());
        tvFoodIngredients.setText(food.getIngredients());

        updateQuantityAndPrice();
    }

    private void setupListeners() {
        btnBack.setOnClickListener(v -> finish());

        btnDecrease.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                updateQuantityAndPrice();
            }
        });

        btnIncrease.setOnClickListener(v -> {
            quantity++;
            updateQuantityAndPrice();
        });

        btnOrder.setOnClickListener(v ->
                Toast.makeText(DetailActivity.this, getString(R.string.order_success), Toast.LENGTH_SHORT).show()
        );
    }

    private void updateQuantityAndPrice() {
        tvQuantity.setText(String.valueOf(quantity));
        if (food != null) {
            tvTotalPrice.setText(food.getFormattedTotalPrice(quantity));
        }
    }
}
