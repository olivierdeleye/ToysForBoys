<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<c:set value="${pageContext.servletContext.contextPath}" var="contextPath"/>
<html lang="nl">
<head>
<title>Orderdetail</title>
<link rel="stylesheet" href="${contextPath}/styles/default.css"/>
</head>
<body>
<a href="<c:url value='/unshippedorders.htm'/>">Unshipped orders</a><br/>
<h1>Order ${order.orderID}</h1>
<section>
Ordered:<br/>
<b><fmt:formatDate value='${order.orderDate}' type="date" pattern="d/MM/yy" dateStyle="short" /></b>
</section>
<section>
Required:<br/>
<b><fmt:formatDate value='${order.requiredDate}' type="date" pattern="d/MM/yy" dateStyle="short" /></b>
</section>
<section>
Customer:<br/>
<b><c:out value='${order.customer.name}'/></b><br/>
<b><c:out value='${order.customer.adres.streetAndNumber}' /></b><br/>
<b><c:out value='${order.customer.adres.postalCode} ${order.customer.adres.city}' /></b><br/>
<b><c:out value='${order.customer.country.name}' /></b>
</section>
<section>
Details:
  <table>
    <thead>
     <tr>
      <th>Product</th>
      <th>Price each</th>
      <th>Quantity</th>
      <th>Value</th>
     </tr>
    </thead>
    <tbody>
     <c:forEach items='${orderdetails}' var='detail'>
      <tr class='bold'>
       <td>${detail.product.name}</td>
       <td class='right'><fmt:formatNumber value='${detail.priceEach}' minFractionDigits='2'/></td>
       <td class='right'>${detail.quantityOrdered}</td>
       <td class='right'><fmt:formatNumber value='${detail.amount}' minFractionDigits='2'/></td>
      </tr>
     </c:forEach>
    </tbody>
  </table>
  </section>
 <section>
 Value:<br/>
 <b><fmt:formatNumber value='${order.totalValue}' minFractionDigits='2'/></b>
</section>
<c:import url="/WEB-INF/JSP/fouten.jsp"/>
</body>
</html>
