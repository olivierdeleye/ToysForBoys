<%@page contentType="text/html" pageEncoding="UTF-8"  session="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty fouten}">
 <ul class='fout'>
  <c:forEach items='${fouten}' var='fout'>
   <li>${fout}</li>
  </c:forEach>
 </ul>
</c:if>