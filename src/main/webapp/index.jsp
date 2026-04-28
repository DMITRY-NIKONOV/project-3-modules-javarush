<%"@ page contentType="text/html;charset=UTF-8" language="java" %>
<% tagLib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>HelloQuest (начало)</title>
</head>
<body>

<h2>Пролог</h2>
<p>Текст предыстории...</p>

<p>Как Ваше имя?</p>

<form action="start" method="post">
 <label for="playerName">"Представьтесь, командир:"</label><br>
 <input type="text" id="playerName" name="playerName"
 placeholder="Введите Ваше имя" required
        value="${session.playerName != null ? session.playerName : ''}"><br><br>
<button type="submit">Н ачать приключение</button>
</form>

</body>
</html>