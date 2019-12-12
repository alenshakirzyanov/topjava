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
        <th style="display: none">id</th>
        <th>description</th>
        <th>dateTime</th>
        <th>calories</th>
    </tr>
    <c:forEach var="meal" items="${requestScope.meals}">
        <form method=POST action="meals">
            <tr bgcolor="${meal.isExcess() ? "red" : "green"}">
                <td style="display: none">${meal.getId()}</td>
                <td>${meal.getDescription()}</td>
                <td><javatime:format value="${meal.getDateTime()}" pattern="dd.MM.yyyy HH:mm:ss"/></td>
                <td>${meal.getCalories()}</td>
                <td>
                    <a href="meals?action=update&mealId=${meal.getId()}">
                        <button type="button">update</button>
                    </a>
                </td>
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
    <button type="button">create</button>
</a>

</body>
</html>