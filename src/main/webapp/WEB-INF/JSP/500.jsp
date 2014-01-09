<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<c:set value="${pageContext.servletContext.contextPath}" var="contextPath"/>
<html lang="nl">
<head>
<title>ToysForBoys - Interne serverfout</title>
<link rel="stylesheet" href="${contextPath}/styles/default.css" />
</head>
<body>
<h1>Interne serverfout</h1>
<img alt="fout" src="${contextPath}/images/datafout.jpg"/>
<p>De pagina die u zocht staat niet op onze website.</p>
</body>
</html>