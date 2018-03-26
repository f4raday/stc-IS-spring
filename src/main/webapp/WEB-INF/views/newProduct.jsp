<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new product</title>
</head>
<body>
    <form:form action="addNewProduct" method="post" modelAttribute="product">
    <spring:bind path="description">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            Description: <form:input path="description" type="text" name="description"/>
            <form:errors path="description"></form:errors>
        </div>
    </spring:bind>

        <input type="submit" value="submit"/>
    </form:form>

</body>
</html>
