<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<html>
<head>
    <title>Meal</title>
</head>
<body>
<h3><a href="index.html">Meals</a></h3>
<hr>
<h2>Meals</h2>
<style>
    table, th, td {
        border: 1px solid black;
    }
</style>

<c:set var="meal" value="${requestScope.meal}"/>
<table>
    <tr>
        <th style="display: none">id</th>
        <th>description</th>
        <th>dateTime</th>
        <th>calories</th>
    </tr>
    <form method=POST action="meals">
        <tr>
            <td style="display: none"><input type="hidden" name="mealId" value="${meal.getId()}">${meal.getId()}</td>
            <td><input type="text" name="description" value="${meal.getDescription()}"></td>
            <td><input type="datetime-local" name="date" value="${meal.getDateTime()}"></td>
            <td><input type="text" name="calories" value="${meal.getCalories()}"></td>
            <td><input type="submit" value="Submit"></td>
        </tr>
    </form>
</table>

</body>
</html>