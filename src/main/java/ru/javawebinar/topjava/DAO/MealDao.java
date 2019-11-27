package ru.javawebinar.topjava.DAO;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDao {
    public AtomicInteger id = new AtomicInteger();
    private CopyOnWriteArrayList<Meal> meals = new CopyOnWriteArrayList<>(Arrays.asList(
            new Meal(id.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
            new Meal(id.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
            new Meal(id.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
            new Meal(id.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
            new Meal(id.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
            new Meal(id.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
    ));

    public void deleteMeal(int id) {
        meals.remove(getMealById(id));
    }

    public List<Meal> getAllMeal() {
        return this.meals;
    }

    public Meal getMealById(int id) {
        return this.meals.stream().filter(meal -> meal.getId() == id).findFirst().orElse(null);
    }

    public void updateMealById(int id, Meal mealToUpdate) {
        meals.stream().filter(meal -> meal.getId() == id).forEach(meal -> {
            meal.setCalories(mealToUpdate.getCalories());
            meal.setDateTime(mealToUpdate.getDateTime());
            meal.setDescription(mealToUpdate.getDescription());
        });
    }

    public void addMeal(Meal meal) {
        meals.add(meal);
    }

}
