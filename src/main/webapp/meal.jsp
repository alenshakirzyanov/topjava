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
    <form method=POST action="meals">
        <td><input type="text" name="description"></td>
        <td><input type="datetime-local" name="date"></td>
        <td><input type="text" name="calories"></td>
        <td><input type="submit" value="add"></td>
        </tr>
    </form>
</table>

</body>
</html>