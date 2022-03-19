<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sporty Shoes - Make Payment</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/components/header.jsp" ></jsp:include>
<jsp:include page="/WEB-INF/view/components/topbar.jsp" ></jsp:include>



<br><br>
Your card will be charged an amount of ${cartValue}<br><br>

<a href="completepurchase">Click to complete checkout</a>

<jsp:include page="/WEB-INF/view/components/footer.jsp"></jsp:include>
</body>
</html>