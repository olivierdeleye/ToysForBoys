<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<c:set value="${pageContext.servletContext.contextPath}" var="contextPath"/>
<html lang="nl">
<head>
<title>Orderstatus niet gewijzigd</title>
<link rel="stylesheet" href="${contextPath}/styles/default.css"/>
</head>
<body>
<h2 class='fout'>Orderstatus niet gewijzigd !</h2>
<c:import url="/WEB-INF/JSP/fouten.jsp"/>
Terug naar&nbsp;<a href="<c:url value='/unshippedorders.htm'/>">Unshipped orders</a><br/>
</body>
</html> 