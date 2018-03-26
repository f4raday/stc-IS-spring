<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Products</title>
</head>
<body>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="button" onclick="document.forms['logoutForm'].submit()" value="Log out" name="Log out"></input>
        </form>


    </c:if>

        <%--
        <form method="post" name="checkboxes" action="/" >
            <c:forEach var="product" items="${productList}">
                <input type="radio" name="product_id" value=${product.id}> ${product.description}<br>
            </c:forEach>

            <input type="submit" name="order">
        </form>
        --%>

        <c:choose>
        <c:when test="${productList.size()>0}">
        <form:form method="post" action="sendProduct" modelAttribute="product">
            <table>
                <tr>
                    <td>Products:</td>
                    <td>
                        <c:forEach var="product_ex" items="${productList}">
                            <form:radiobutton path="id" value="${product_ex.id}" label="${product_ex.description}"></form:radiobutton> <br>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td></td><td><input type="submit"></td>
                </tr>
            </table>
        </form:form>
        </c:when>
        <c:otherwise>
        <p>No products</p>
        </c:otherwise>
        </c:choose>

        <a href="/newProduct">Add product</a>

<!--
        <form action="/sendProduct" method="post">

        </form>
        -->
</body>
</html>
