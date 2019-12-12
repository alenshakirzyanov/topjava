package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDaoMap;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
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
//    private MealDaoMemoryList mealDaoMemoryList = new MealDaoMemoryList();
    private MealDaoMap mealDaoMap = new MealDaoMap();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        log.debug("redirect to meals");
        String action = request.getParameter("action");
        List<MealTo> meals;
        int mealId;
        if (action == null){
            action = "";
        }
        switch (action.toLowerCase()) {
            case "delete":
                mealId = Integer.parseInt(request.getParameter("mealId"));
                log.debug("mealId "+mealId);
                mealDaoMap.delete(mealId);
                response.sendRedirect("meals");
                break;
            case "update":
                mealId = Integer.parseInt(request.getParameter("mealId"));
                Meal meal = mealDaoMap.getById(mealId);
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/meal.jsp").forward(request, response);
            case "listmeals":
            default:
                meals = MealsUtil.getFiltered(mealDaoMap.getAll(), LocalTime.MIN, LocalTime.MAX, MealsUtil.DEFAULT_CALORIES_PER_DAY);
                request.setAttribute("meals", meals);
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String mealId = request.getParameter("mealId");
        LocalDateTime date = LocalDateTime.parse(request.getParameter("date"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        if (mealId == null || mealId.isEmpty()) {
            mealDaoMap.create(new Meal(date, description, calories));
        } else {
            int mealIdInt = Integer.parseInt(mealId);
            Meal meal = new Meal(mealIdInt,date,description,calories);
            mealDaoMap.updateById(mealIdInt, meal);
        }
        response.sendRedirect("meals");
    }
}
