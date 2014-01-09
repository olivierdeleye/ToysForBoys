<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<c:set value="${pageContext.servletContext.contextPath}" var="contextPath"/>
<html lang="nl">
<head>
<title>Unshipped orders</title>
<link rel="stylesheet" href="${contextPath}/styles/default.css"/>
</head>
<body>
<h1>Unshipped orders</h1>
<c:if test='${not empty orders}'>
<form action="<c:url value='/unshippedorders.htm'/>" method="post" id = "form">
 <table>
    <thead>
     <tr>
      <th>ID</th>
      <th>Ordered</th>
      <th>Required</th>
      <th>Customer</th>
      <th>Comments</th>
      <th>Status</th>
      <th>Ship</th>
     </tr>
    </thead>
    <tbody>
     <c:forEach items='${orders}' var='order'>
      <tr>
       <c:url value='/orderdetail.htm' var='orderdetailURL'>
         <c:param name='orderId' value='${order.orderID}'/>
       </c:url>
       <td class='right'><a href="${orderdetailURL}" title="orderdetail" >${order.orderID}</a></td>
       <td><fmt:formatDate value='${order.orderDate}' type="date" pattern="d/MM/yy" dateStyle="short" /></td>
       <td><fmt:formatDate value='${order.requiredDate}' type="date" pattern="d/MM/yy" dateStyle="short" /></td>
       <td><c:out value='${order.customer.name}'/></td>
       <td><c:out value='${order.comments}'/></td>
        <c:choose>
            <c:when test="${order.status == 'DISPUTED'}">
              <td class='red' >${order.status}</td>
            </c:when>
            <c:when test="${order.status == 'RESOLVED'}">
              <td class='green' >${order.status}</td>
            </c:when>
            <c:otherwise>
              <td>${order.status}</td>
            </c:otherwise>
        </c:choose>
       <td><input type="checkbox" name="setAsShipped" value="${order.orderID}" /></td>
      </tr>
     </c:forEach>
    </tbody>
  </table>
   <c:if test='${vanafRij != 0}'>
   <c:url value='/unshippedorders.htm' var='vorigePaginaURL'>
    <c:param name='vanafRij' value='${vanafRij - aantalRijen}'/>
   </c:url>
   <a href="<c:out value='${vorigePaginaURL}'/>" title='vorige pagina'><img src="${contextPath}/images/previous.png" alt="vorige pagina" /></a>
  </c:if>
  <c:if test='${empty laatstePagina}'>
   <c:url value='unshippedorders.htm' var='volgendePaginaURL'>
    <c:param name='vanafRij' value='${vanafRij + aantalRijen}'/>
   </c:url>
   <a href="<c:out value='${volgendePaginaURL}'/>" title='volgende pagina'><img src="${contextPath}/images/next.png" alt="volgende pagina" /></a>
 </c:if>
  <input type='submit' value='Set as shipped' id='knop'/>
  </form>
<script>
  document.getElementById('form').onsubmit = function() {
  document.getElementById('knop').disabled = true;
  };
</script>
</c:if>
<c:import url="/WEB-INF/JSP/fouten.jsp"/>
</body>
</html>
