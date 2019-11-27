package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.DAO.MealDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private MealDao mealDao =new MealDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        log.debug("redirect to meals");
        String action = request.getParameter("action");
        List<MealTo> meals;
        switch (action.toLowerCase()) {
            case "delete":
                mealDao.deleteMeal(Integer.parseInt(request.getParameter("mealId")));
                response.sendRedirect("meals?action=listmeals");
                break;
            case "listmeals":
                meals = MealsUtil.getFiltered(mealDao.getAllMeal(), LocalTime.MIN, LocalTime.MAX, MealsUtil.DEFAULT_CALORIES_PER_DAY);
                request.setAttribute("meals", meals);
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
            default:
                log.error("Unexpected value:" +action.toLowerCase() );
                throw new IllegalStateException("Unexpected value: " + action.toLowerCase());
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String mealId = request.getParameter("mealId");
        LocalDateTime date = LocalDateTime.parse(request.getParameter("date"));
//        LocalDateTime date = LocalDateTime.now();
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        if (mealId == null || mealId.isEmpty()) {
            mealDao.addMeal(new Meal(mealDao.id.getAndIncrement(),date, description, calories));
        } else {
            Meal meal = new Meal();
            meal.setDescription(description);
            meal.setDateTime(date);
            meal.setCalories(calories);
            mealDao.updateMealById(Integer.parseInt(mealId), meal);
        }
        response.sendRedirect("meals?action=listmeals");
    }
}
