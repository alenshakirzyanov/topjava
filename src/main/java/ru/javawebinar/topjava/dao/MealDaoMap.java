package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoMap implements MealRepository {
    private AtomicInteger atomicIntegerId = new AtomicInteger(0);
    private ConcurrentHashMap<Integer, Meal> meals = new ConcurrentHashMap<Integer, Meal>() {{
        put(atomicIntegerId.incrementAndGet(), new Meal(atomicIntegerId.get(), LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        put(atomicIntegerId.incrementAndGet(), new Meal(atomicIntegerId.get(), LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        put(atomicIntegerId.incrementAndGet(), new Meal(atomicIntegerId.get(), LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        put(atomicIntegerId.incrementAndGet(), new Meal(atomicIntegerId.get(), LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        put(atomicIntegerId.incrementAndGet(), new Meal(atomicIntegerId.get(), LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        put(atomicIntegerId.incrementAndGet(), new Meal(atomicIntegerId.get(), LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }};


    @Override
    public void delete(int id) {
        meals.remove(id);
    }

    @Override
    public void updateById(int id, Meal mealToUpdate) {
        meals.replace(id, mealToUpdate);
    }

    @Override
    public Meal getById(int id) {
        return meals.get(id);
    }

    @Override
    public void create(Meal meal) {
        int id = this.atomicIntegerId.getAndIncrement();
        meal.setId(id);
        meals.putIfAbsent(id, meal);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(meals.values());
    }
}
