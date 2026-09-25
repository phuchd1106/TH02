package com.example.th02.data;

import com.example.th02.R;
import com.example.th02.model.Food;

import java.util.ArrayList;
import java.util.List;

public class FoodRepository {

    public static List<Food> getFoodList() {
        List<Food> foods = new ArrayList<>();
        foods.add(new Food(
                1,
                "Hamburger bò",
                "Bánh mì hamburger nhân thịt bò mọng nước",
                55000,
                R.drawable.ic_hamburger,
                "Thịt bò, phô mai, xà lách, cà chua, sốt đặc biệt"
        ));
        foods.add(new Food(
                2,
                "Pizza hải sản",
                "Pizza đế giòn ngập tràn hải sản tươi ngon",
                120000,
                R.drawable.ic_pizza,
                "Tôm, mực, phô mai, sốt cà chua, hành tây..."
        ));
        foods.add(new Food(
                3,
                "Pizza bò",
                "Pizza nhân thịt bò băm thơm lừng",
                110000,
                R.drawable.ic_pizza,
                "Thịt bò băm, phô mai Mozzarella, sốt BBQ, ớt đà lạt"
        ));
        foods.add(new Food(
                4,
                "Pizza phô mai",
                "Pizza 4 loại phô mai tan chảy béo ngậy",
                95000,
                R.drawable.ic_pizza,
                "Phô mai Mozzarella, Cheddar, Parmesan, Gorgonzola"
        ));
        foods.add(new Food(
                5,
                "Mì cay",
                "Mì cay Hàn Quốc cấp độ chuẩn vị hải sản",
                65000,
                R.drawable.ic_noodles,
                "Mì Koreno, tôm, mực, bò, nấm kim châm, ớt Hàn Quốc"
        ));
        foods.add(new Food(
                6,
                "Gà rán",
                "Gà rán giòn rụm chuẩn vị Hàn Quốc",
                75000,
                R.drawable.ic_chicken,
                "Thịt gà tươi, bột chiên giòn, sốt cay ngọt, vừng"
        ));
        foods.add(new Food(
                7,
                "Trà sữa",
                "Trà sữa trân châu đường đen đậm vị trà",
                35000,
                R.drawable.ic_tea,
                "Trà đen, sữa tươi, trân châu đường đen, đá"
        ));
        return foods;
    }

    public static List<Food> searchFood(String query) {
        if (query == null || query.trim().isEmpty()) {
            return new ArrayList<>();
        }
        List<Food> allFoods = getFoodList();
        List<Food> filtered = new ArrayList<>();
        String lowerQuery = query.toLowerCase().trim();
        for (Food food : allFoods) {
            if (food.getName().toLowerCase().contains(lowerQuery)) {
                filtered.add(food);
            }
        }
        return filtered;
    }
}
