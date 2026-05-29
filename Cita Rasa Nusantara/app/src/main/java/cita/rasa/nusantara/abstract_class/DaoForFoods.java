package cita.rasa.nusantara.abstract_class;

import citarasa.models.Food;
import java.util.List;

public abstract class DaoForFoods {
    public abstract List<Food> getFoods();

    public abstract List<Food> getFoodsByIsland(String island);

    public abstract List<Food> getFoodsByCity(String city);

    public abstract void insertFood(Food food);

    public abstract void setupTable();
}
