<%@page import="com.dbshop.UsersDto"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
   UsersDto usersDto = (UsersDto)session.getAttribute("userInfo");
    if(usersDto == null) { // �α����� �ȵƴٸ�
%> 
	<script>
		alert("�α����� ���ּ���.");
		location.replace("login.jsp");
	</script>
<%}else{%>
	<script>
		alert("īƮ�� �߰��Ǿ����ϴ�.");
		history.back();
	</script>
<%} %>
</body>
</html>