<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<html>
<head>
    <title>Meals</title>
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


<table>
    <tr>
        <th>id</th>
        <th>description</th>
        <th>dateTime</th>
        <th>calories</th>
    </tr>
    <c:forEach var="meal" items="${requestScope.meals}">
        <form method=POST action="meals?action=listmeals">
            <tr bgcolor="${meal.isExcess() ? "red" : "green"}">
                <javatime:format var="formDate" value="${meal.getDateTime()}" pattern="dd.MM.yyyy HH:mm:ss"/>
                <td><input type="hidden" name="mealId" value="${meal.getId()}">${meal.getId()}</td>
                <td><input type="text" name="description" value="${meal.getDescription()}"></td>
                <td><input type="datetime-local" name="date" value="${meal.getDateTime()}"></td>
                <td><input type="text" name="calories" value="${meal.getCalories()}"></td>
                <td><input type="submit" value="update">
                <td>
                    <a href="meals?action=delete&mealId=${meal.getId()}">
                        <button type="button">delete</button>
                    </a>
                </td>
            </tr>
        </form>
    </c:forEach>
</table>
<a href="meal.jsp">
    <button type="button">add</button>
</a>

</body>
</html>