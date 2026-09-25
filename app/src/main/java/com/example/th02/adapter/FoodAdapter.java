package com.example.th02.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.th02.R;
import com.example.th02.model.Food;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    public interface OnFoodClickListener {
        void onFoodClick(Food food);
    }

    private List<Food> foodList;
    private final OnFoodClickListener listener;

    public FoodAdapter(List<Food> foodList, OnFoodClickListener listener) {
        this.foodList = foodList != null ? foodList : new ArrayList<>();
        this.listener = listener;
    }

    public void updateList(List<Food> newList) {
        this.foodList = newList != null ? newList : new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food food = foodList.get(position);
        holder.bind(food, listener);
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    static class FoodViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgFood;
        private final TextView tvFoodName;
        private final TextView tvFoodDescription;
        private final TextView tvFoodPrice;
        private final Button btnViewDetail;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.imgFood);
            tvFoodName = itemView.findViewById(R.id.tvFoodName);
            tvFoodDescription = itemView.findViewById(R.id.tvFoodDescription);
            tvFoodPrice = itemView.findViewById(R.id.tvFoodPrice);
            btnViewDetail = itemView.findViewById(R.id.btnViewDetail);
        }

        public void bind(final Food food, final OnFoodClickListener listener) {
            imgFood.setImageResource(food.getImageResId());
            tvFoodName.setText(food.getName());
            tvFoodDescription.setText(food.getDescription());
            tvFoodPrice.setText(food.getFormattedPrice());

            View.OnClickListener clickListener = v -> {
                if (listener != null) {
                    listener.onFoodClick(food);
                }
            };

            itemView.setOnClickListener(clickListener);
            btnViewDetail.setOnClickListener(clickListener);
        }
    }
}
