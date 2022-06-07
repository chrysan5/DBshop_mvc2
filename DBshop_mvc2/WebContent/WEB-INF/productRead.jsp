<%@page import="com.dbshop.ProductDto"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

    <style>
        *,html,body{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        #wrap{
            width: 1024px;
            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Robonto,
             Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
            margin: 0 auto;
            display: flex;
            justify-content: space-between;
        }
        #wrap>div{
            border: 1px solid black;
        }


        #leftImg{
            width: 500px;
            height: 600px;
        }
        #leftImg img{
            width: 100%;
            height: 100%;
        }
        #rightMain{
            width: 500px;
            height: 600px;
        }
        #rightMain>div{
            width: 100%;
            height: 100px;
            border: 1px solid black;
        }
        #rightMain input[type="button"]{
        	display: inline-block;
        }
    </style>
</head>
<body>
<%
	ProductDto productDto = (ProductDto)request.getAttribute("productData");
%>
	<div id="wrap">
        <div id="leftImg">
            <img src="<%=productDto.getImage()%>">
        </div>

		<form action="">
        <div id="rightMain">
            <div id="title"><%=productDto.getName() %></div>
            <div id="price"><%=productDto.getPrice() %></div>
            <div id="content"><%=productDto.getContents() %></div>
            <div id="num">수량: 
            	<input type="button" id="downBtn" value="-" onclick='count("minus")'>
            	<div id="result" name="num">0</div>
            	<input type="button" id="upBtn" value="+" onclick='count("plus")'>
            </div>
            <input type="button" value="장바구니">
            <input type="button" value="구매">
            <input type="hidden" name="productNum" value="<%=productDto.getProductNum() %>">
        </div>
        </form>
    </div>
    
    <script>
    function count(type)  {
        // 결과를 표시할 element
        const resultElement = document.getElementById('result');
        
        // 현재 화면에 표시된 값
        let number = resultElement.innerText;
        
        // 더하기/빼기
        if(type === 'plus') {
            number = parseInt(number) + 1;
        }else if(type === 'minus')  {
            number = parseInt(number) - 1;
        }
        
        // 결과 출력
        resultElement.innerText = number;
        }
    
    </script>
</body>
</html>