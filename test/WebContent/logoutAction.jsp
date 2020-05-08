<%@ page language="java" contentType="text/html; charset =UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="UTF-8">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>source project</title>
</head>
<body>
    <%
		session.invalidate();
    %>
    <script>
    	location.href = 'main.jsp';
    </script>
</html>