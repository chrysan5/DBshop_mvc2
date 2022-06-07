<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
	<style>
		#wrap{
		width:1000px;
		margin: 0 auto;
		margin-top: 30px;
		}
		#title{
		margin: 0 atuo;
		font-weight: bold;
		font-size: 30px;
		}
		hr{
			border: 1px solid black;
		}
		.title{
			width:990px;
			height:30px;
		}
		.contop{
			margin-top:10px;
			width:490px;
			height:30px;
		}
		.contents{
			margin-top:10px;
			width:990px;
			height:300px;
		}
		.upload{
			margin-top:10px;
			width:60px;
			height:30px;
			color:white;
			background-color: skyblue;
			border: 1px solid skyblue;
			font-weight: bold;
		}
		img{
			width:300px;
			heght:300px;
		}
		input[type="button"]{
			background-color: black;
			color:white;
			width: 100px;
			height: 30px;
			margin-right: 10px;
		}
		#btnBox{
            display: flex;
            justify-content: center;
            margin-top: 30px;
        }
        table{
        	display: inline-block;
        	width: 400px;
			height: 400px;
        }
        
	</style>
</head>
<body>
<jsp:include page="top.jsp"/>
<c:if test="${sessionScope.userInfo.lv != 10}">
   <script>
        alert("�����ڰ� �ƴմϴ�.");
        location.replace("Main.do");
    </script>    
</c:if>
    
<div id="wrap">
	<div id="title">�Խ��� ��ȸ</div>
	<hr><br>
	
	
	<c:forEach var="dto" items="${ requestScope.BoardReadData }"> 
		<table>
			<tr>
				<td>����: </td>
				<td>${dto.name }</td>
			</tr>
			<tr>
				<td>����: </td>
				<td>${dto.kind }</td>
			</tr>
			<tr>
				<td>����: </td>
				<td>${dto.price }</td>
			</tr>
			<tr>
				<td>����: </td>
				<td>${dto.contents }</td>
			</tr>
			<tr>
				<td>��ȸ��: &nbsp; </td>
				<td>${dto.hit }</td>
			</tr>
		</table>
		<img src="img/${dto.image }">	
		
		<div id="btnBox">
			<input type="button" value="����"  onclick="location.href='BoardUpadte.do?productNum=${dto.productNum }'">
			<input type="button" value="����"  onclick="location.href='BoardDelete.do?productNum=${dto.productNum }'">
		</div>		
	</c:forEach>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>