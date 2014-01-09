<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<c:set value="${pageContext.servletContext.contextPath}" var="contextPath"/>
<html lang="nl">
<head>
<title>ToysForBoys - pagina niet gevonden</title>
<link rel="stylesheet" href="${contextPath}/styles/default.css" />
</head>
<body>
<h1>Pagina niet gevonden</h1>
<img alt="fout" src="${contextPath}/images/fout.jpg"/>
<p>De pagina die u zocht staat niet op onze website.</p>
</body>
</html>