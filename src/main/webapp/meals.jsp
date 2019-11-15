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
        <th>description</th>
        <th>dateTime</th>
        <th>calories</th>
    </tr>
    <c:forEach var="meal" items="${requestScope.meals}">
        <tr style="${meal.isExcess() ? "color: red" : "color: green"}">
            <td>${meal.getDescription()}</td>
            <td><javatime:format value="${meal.getDateTime()}" pattern="dd.MM.yyyy HH:mm:ss"/></td>
            <td>${meal.getCalories()}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>