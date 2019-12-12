package ru.javawebinar.topjava.repository;


import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealRepository  {
    void delete(int id);
    void updateById(int id, Meal mealToUpdate);
    Meal getById(int id);
    void create(Meal meal);
    List<Meal> getAll();
}
